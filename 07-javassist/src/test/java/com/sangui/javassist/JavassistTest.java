package com.sangui.javassist;


import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-19
 * @Description: 测试 javassist
 * @Version: 1.0
 */
public class JavassistTest {
    @Test
    public void testGenerateFirstClass() throws Exception {
        // 获取类池，这个类池就是用来生成class的
        ClassPool pool = ClassPool.getDefault();

        // 制造类（需要告诉javassist，类名是啥）
        CtClass ctClass = pool.makeClass("com.sangui.bank.dao.impl.AccountDaoImpl");

        // 制造方法
        String methodCode = "public void insert(){System.out.println(123456);}";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);

        // 将方法添加到类中
        ctClass.addMethod(ctMethod);

        // 在内存中生成class
        ctClass.toClass();



        // 类加载到 JVM 中，返回 AccountDaoImpl 类的字节码
        Class<?> clazz = Class.forName("com.sangui.bank.dao.impl.AccountDaoImpl");

        // 获取对象
        Object object = clazz.newInstance();

        // 获取 AccountDaoImpl中的insert方法
        Method insertMethod = clazz.getDeclaredMethod("insert");
        insertMethod.invoke(object);
    }
}
