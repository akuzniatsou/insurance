package com.dww.insurance.service;

import java.io.FileInputStream;
import java.util.Properties;

public class AppProperties {

    private static Properties props = new Properties();
    private static AppProperties ourInstance = new AppProperties();

    public static AppProperties getInstance() {
        return ourInstance;
    }

    private AppProperties() {
        try {
            props.load(new FileInputStream(System.getProperty("user.dir")+ "/conf/app.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Properties getAppProps() {
        return props;
    }

}
