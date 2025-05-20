package com.sangui.javassist;


import com.sangui.bank.dao.AccountDao;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-19
 * @Description: 测试 javassist
 * @Version: 1.0
 */
public class JavassistTest {
    @Test
    public void testGenerateAccountDaoImpl() throws Exception {
        // 获取类池
        ClassPool pool = ClassPool.getDefault();

        // 制造类
        CtClass ctClass = pool.makeClass("com.sangui.bank.dao.impl.AccountDaoImpl");

        // 制造接口
        CtClass ctInterface = pool.makeInterface("com.sangui.bank.dao.AccountDao");

        // 实现接口
        ctClass.addInterface(ctInterface);


        // 实现接口中所有的方法
        Method[] methods = AccountDao.class.getDeclaredMethods();
        System.out.println(methods.length);
        for (Method method : methods) {
            StringBuilder methodCode = new StringBuilder();
            methodCode.append("public ");
            methodCode.append(method.getReturnType().getName());
            methodCode.append(" ");
            methodCode.append(method.getName());
            methodCode.append("(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                methodCode.append(parameterTypes[i].getName());
                methodCode.append(" ");
                methodCode.append("arg" + i);
                if (i != parameterTypes.length - 1) {
                    methodCode.append(",");
                }
            }
            methodCode.append("){System.out.println(\"method execute!\");");
            if ("java.lang.String".equals(method.getReturnType().getName())) {
                methodCode.append("return \"yes\";");
            }
            if ("int".equals(method.getReturnType().getName())){
                methodCode.append("return 1;");
            }
            methodCode.append("}");

            System.out.println(methodCode);
            CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
            ctClass.addMethod(ctMethod);
        }
//        ctClass.toClass();
//        // 在内存中生成 class，并且加载到JVM当中
//        Class<?> clazz = Class.forName("com.sangui.bank.dao.impl.AccountDaoImpl");

        Class<?> clazz = ctClass.toClass();


        // 创建对象
        AccountDao accountDao = (AccountDao) clazz.newInstance();
        int ansInsert = accountDao.insert("11");
        System.out.println(ansInsert);

        String ansSelectByActno = accountDao.selectByActno("11");
        System.out.println(ansSelectByActno);

        accountDao.delete();

        int ansUpdate = accountDao.update("11",1.1);
        System.out.println(ansUpdate);


    }

    @Test
    public void testGenerateImpl() throws Exception {
        // 获取类池
        ClassPool pool = ClassPool.getDefault();

        // 制造类
        CtClass ctClass = pool.makeClass("com.sangui.bank.dao.impl.AccountDaoImpl");

        // 制造接口
        CtClass ctInterface = pool.makeInterface("com.sangui.bank.dao.AccountDao");

        // 添加接口到类中（让这个类实现这个接口）
        ctClass.addInterface(ctInterface);

        // 实现接口中的方法
        // 制造方法
        CtMethod ctMethod = CtMethod.make("public void delete(){System.out.println(\"delete method execute!\");}", ctClass);

        // 将方法添加到类中
        ctClass.addMethod(ctMethod);

        // 在内存中生成类，同时，将生成的类加载到JVM当中
        Class<?> clazz = ctClass.toClass();

        AccountDao accountDoa = (AccountDao) clazz.newInstance();

        Method declaredMethod = clazz.getDeclaredMethod("delete");

        declaredMethod.invoke(accountDoa);


    }

    @Test
    public void testGenerateFirstClass() throws Exception {
        // 获取类池，这个类池就是用来生成class的
        ClassPool pool = ClassPool.getDefault();

        // 制造类（需要告诉 javassist，类名是啥）
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
