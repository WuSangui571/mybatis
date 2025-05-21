package com.sangui.mapper;


import com.sangui.pojo.Log;

import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-21
 * @Description:
 * @Version: 1.0
 */
public interface LogMapper {
    List<Log> selectAllTable(String date);
}
