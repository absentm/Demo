package com.demo;

import com.demo.beans.User;
import com.demo.dao.IUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class mainTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.getConfiguration().addMapper(IUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            IUser iuser = session.getMapper(IUser.class);
            User user = iuser.getUserById(1);
            System.out.println("名字："+user.getName());
            System.out.println("所属部门："+user.getDept());
            System.out.println("主页："+user.getWebsite());
        } finally {
            session.close();
        }
    }
}
