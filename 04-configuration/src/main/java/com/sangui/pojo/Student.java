package com.sangui.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * student
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private Integer id;

    private String name;

    private String email;

    private Integer age;

    private static final long serialVersionUID = 1L;
}