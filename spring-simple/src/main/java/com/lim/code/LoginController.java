package com.lim.code;

import com.lim.annotation.LimAutowired;
import com.lim.annotation.LimController;
import com.lim.annotation.LimRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qinhao
 */
@LimController
@LimRequestMapping("/lim/")
public class LoginController {

    @LimAutowired
    private ILoginService loginService;

    @LimRequestMapping("login.lim")
    public void login(HttpServletRequest request, HttpServletResponse response,
                      String username) {
        String result = loginService.login(username);
        try {
            response.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
