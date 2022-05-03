package com.lyra.qqbot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.qqbot.entity.Log;
import com.lyra.qqbot.mapper.LogMapper;
import com.lyra.qqbot.service.ILogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService  {
}
