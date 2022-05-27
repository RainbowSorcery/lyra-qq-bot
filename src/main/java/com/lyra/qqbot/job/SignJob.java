package com.lyra.qqbot.job;


import com.lyra.qqbot.entity.User;
import com.lyra.qqbot.service.ISignService;
import com.lyra.qqbot.service.IUserService;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SignJob  {
    @Autowired
    private ISignService signService;
    @Autowired
    private IUserService userService;

    @Scheduled(cron = "0 0 0 * * ? ")
    public void sign() {
        List<User> userList = userService.list();
        signService.setUrl("https://portal.touhou.tel/");

        userList.forEach((e) -> {
            String cookie = signService.login(e);
            signService.sign(cookie);
        });
    }
}
