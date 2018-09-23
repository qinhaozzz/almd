package com.lim.code;

import com.lim.annotation.LimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qinhao
 */
@LimService
public class LoginServiceImp implements ILoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginServiceImp.class);

    @Override
    public String login(String username) {
        this.logger.info("username:{}", username);
        return "SUCCESS-" + username;
    }
}
