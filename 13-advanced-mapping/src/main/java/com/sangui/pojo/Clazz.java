package com.sangui.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-23
 * @Description:
 * @Version: 1.0
 */
@Data
public class Clazz {
    private Integer cid;
    private String cname;

    private List<Stu> stuList;

    public Clazz() {
    }

    public Clazz(String cname, Integer cid) {
        this.cname = cname;
        this.cid = cid;
    }
}
