package com.sangui.pojo;



import lombok.Data;


/**
 * @Author: sangui
 * @CreateTime: 2025-05-23
 * @Description:
 * @Version: 1.0
 */
@Data
public class Stu {
    private Integer sid;
    private String sname;
    private Clazz clazz;

    public Stu() {
    }

    public Stu(Integer sid, String sname) {
        this.sid = sid;
        this.sname = sname;
    }
}
