package com.lyra.qqbot.service;

import com.lyra.qqbot.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface AuthService {
    Boolean auth(Long qq);
}
