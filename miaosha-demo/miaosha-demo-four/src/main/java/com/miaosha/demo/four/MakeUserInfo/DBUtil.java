package com.miaosha.demo.four.MakeUserInfo;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static Properties props;

    static {
        try{
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("application.properties");
            props = new Properties();
            props.load(in);
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getCon() throws ClassNotFoundException, SQLException {
        String url = props.getProperty("spring.datasource.url");
        String username = props.getProperty("spring.datasource.username");
        String password = props.getProperty("spring.datasource.password");
        String driver = props.getProperty("spring.datasource.driver-class-name");
        Class.forName(driver);
        return DriverManager.getConnection(url,username, password);
    }
}
