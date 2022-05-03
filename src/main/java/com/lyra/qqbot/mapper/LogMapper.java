package com.lyra.qqbot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyra.qqbot.entity.Log;

import java.util.List;

public interface LogMapper extends BaseMapper<Log> {

    List<Log> get7TotalSignLog();

}
