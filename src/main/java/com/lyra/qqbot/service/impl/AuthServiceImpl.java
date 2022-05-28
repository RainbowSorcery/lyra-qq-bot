package com.lyra.qqbot.service.impl;

import com.lyra.qqbot.entity.AdminUser;
import com.lyra.qqbot.mapper.AdminUserMapper;
import com.lyra.qqbot.service.AuthService;
import com.lyra.qqbot.utils.QQBotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AdminUserMapper adminUserMapper;


    @Override
    public Boolean auth(Long qq) {
        List<AdminUser> adminUsers = adminUserMapper.selectList(null);
        List<AdminUser> collect = adminUsers.stream().filter((adminUser -> Objects.equals(adminUser.getQq(), qq))).collect(Collectors.toList());

        return collect.size() > 0;
    }
}
