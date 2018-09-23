package com.lim.servlet;

import com.lim.annotation.LimAutowired;
import com.lim.annotation.LimController;
import com.lim.annotation.LimRequestMapping;
import com.lim.annotation.LimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * DispatcherServlet
 * @author qinhao
 */
public class LimDispatcherServlet extends HttpServlet {

    /**
     * 日志器
     */
    private final Logger logger = LoggerFactory.getLogger(LimDispatcherServlet.class);
    /**
     * 配置文件路径
     */
    private static final String LOCATION = "contextConfigLocation";

    /**
     * 保存所有配置信息
     */
    private Properties properties = new Properties();

    /**
     * 保存被扫描到的相关类名
     */
    private List<String> classNames = new ArrayList<>();

    /**
     * 初始化IOC容器,Bean管理
     */
    private Map<String, Object> ioc = new HashMap<>();

    /**
     * 保存url和方法的映射关系
     */
    private Map<String, Method> handlerMapping = new HashMap<>();

    public LimDispatcherServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1.加载配置文件
        doLoadConfig(config.getInitParameter(LOCATION));
        // 2.扫描带有注解的类
        doScanner(properties.getProperty("scanPackage"));
        // 3.初始化相关类的实例,并保存到IOC容器
        doInstance();
        // 4.依赖注入DI
        doAutowired();
        // 5.构造HandlerMapping
        initHandlerMapping();
        // 6.处理请求
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace())
                    .replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace())
                    .replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
        }
    }

    /**
     * 加载配置文件
     * @param location
     */
    private void doLoadConfig(String location) {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
            properties.load(inputStream);
        } catch (IOException e) {
            this.logger.error("load {} config failed,\r\n{}", location, e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 扫描带有注解的类
     * @param basePackage
     */
    private void doScanner(String basePackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + basePackage.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(basePackage + "." + file.getName());
            } else {
                classNames.add(basePackage + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * bean初始化
     */
    private void doInstance() {
        if (classNames.size() == 0) {
            return;
        }
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                // @LimController
                if (clazz.isAnnotationPresent(LimController.class)) {
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, clazz.getDeclaredConstructor().newInstance());
                    this.logger.info("<bean instance>," +
                            "" + beanName);
                }
                // @LimService
                else if (clazz.isAnnotationPresent(LimService.class)) {
                    LimService service = clazz.getAnnotation(LimService.class);
                    String beanName = service.value();
                    if (!"".equals(beanName.trim())) {
                        ioc.put(beanName, clazz);
                        this.logger.info("<bean instance>," + beanName);
                        continue;
                    }
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        ioc.put(i.getName(), clazz.getDeclaredConstructor().newInstance());
                        this.logger.info("<bean instance>," + beanName);
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            this.logger.error("IOC init failed,\r\n{}", e.getMessage());
        }
    }

    /**
     * 依赖注入
     */
    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(LimAutowired.class)) {
                    continue;
                }
                LimAutowired autowired = field.getAnnotation(LimAutowired.class);
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                // 设置私有属性访问权限
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), ioc.get(beanName));
                    this.logger.info("<dependency injection>," + beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    /**
     * 初始化handlerMapping
     */
    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(LimRequestMapping.class)) {
                continue;
            }
            String baseUrl = "";
            if (clazz.isAnnotationPresent(LimRequestMapping.class)) {
                LimRequestMapping requestMapping = clazz.getAnnotation(LimRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(LimRequestMapping.class)) {
                    continue;
                }
                LimRequestMapping requestMapping = method.getAnnotation(LimRequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                handlerMapping.put(url, method);
                this.logger.info("<mapping>,{" + url + "-" + method + "}");
            }
        }
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (this.handlerMapping.isEmpty()) {
            return;
        }
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        if (!this.handlerMapping.containsKey(url)) {
            response.getWriter().write("404 Not Found!");
            return;
        }
        Map<String, String[]> params = request.getParameterMap();
        Method method = this.handlerMapping.get(url);
        String beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
        /**
         * TODO 调用方法
         * 1.方法调用
         * 2.参数解析,即RequestParam
         */
        method.invoke(this.ioc.get(beanName), request, response, params.get("username")[0]);
    }
}
