package com.sangui.bank.utils;


import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-20
 * @Description: 工具类，生成Dao的实现类
 * @Version: 1.0
 */
public class GenerateDaoProxy {
    private GenerateDaoProxy() {}

    /**
     * 生成dao接口实现类，并且将实现类的对象创造出来并返回
     * @param daoInterface 需实现的接口类（dao类）
     * @return dao接口的实现类
     */
    public static Object generate(SqlSession sqlSession,Class daoInterface){
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Proxy");
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        ctClass.addInterface(ctInterface);

        // 实现接口中的所有方法
        Method[] declaredMethods = daoInterface.getDeclaredMethods();
        for (Method method : declaredMethods) {
            StringBuilder methodCode = new StringBuilder();
            /*
            样板：
                public Account selectByActno(String actno) {
                    SqlSession sqlSession = SqlSessionUtil.openSession();
                    return sqlSession.selectOne("com.sangui.bank.dao.AccountDao.selectByActno", actno);
                }
             */
            methodCode.append("public ");
            methodCode.append(method.getReturnType().getName());
            methodCode.append(" ");
            methodCode.append(method.getName());
            methodCode.append("(");
            for (int i = 0; i < method.getParameterTypes().length; i++) {
                methodCode.append(method.getParameterTypes()[i].getName());
                methodCode.append(" arg" + i);
                if (i != method.getParameterTypes().length - 1) {
                    methodCode.append(", ");
                }
            }
            methodCode.append(") {org.apache.ibatis.session.SqlSession sqlSession = com.sangui.utils.SqlSessionUtil.openSession();");

            String sqlId = daoInterface.getName() + "." + method.getName();
            SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
            if (SqlCommandType.INSERT == sqlCommandType) {
                methodCode.append("return sqlSession.insert(\"" + sqlId + "\", arg0)");
            } else if (SqlCommandType.DELETE == sqlCommandType) {
                methodCode.append("return sqlSession.delete(\"" + sqlId + "\", arg0)");
            } else if (SqlCommandType.UPDATE == sqlCommandType) {
                methodCode.append("return sqlSession.update(\"" + sqlId + "\", arg0)");
            } else if (SqlCommandType.SELECT == sqlCommandType) {
                methodCode.append("return sqlSession.selectOne(\"" + sqlId + "\", arg0)");
            }

            methodCode.append("}");

            try {
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (CannotCompileException e) {
                throw new RuntimeException(e);
            }
        }

        Class<?> clazz = null;
        try {
            clazz = ctClass.toClass();
            return clazz.newInstance();
        } catch (CannotCompileException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
