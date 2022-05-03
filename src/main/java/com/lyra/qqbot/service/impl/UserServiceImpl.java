package com.lyra.qqbot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.qqbot.entity.User;
import com.lyra.qqbot.mapper.UserMapper;
import com.lyra.qqbot.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
