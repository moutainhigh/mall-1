package com.yunxin.cb.config;

import com.yunxin.cb.util.CalendarUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by gonglei on 16/1/31.
 */
public class GlobalConfig {

    private static Properties prop = new Properties();

    private static String propFilePath;

    public static void loadConfig(String filePath) {
        propFilePath = filePath;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();//关闭流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(Config config) {
        String value = (String) prop.get(config.name());
        if (value == null) {
            value = config.getDefaultValue();
            setProperty(config, value);
        }
        return value;
    }

    public static String getProperty(String config) {
        String value = (String) prop.get(config);
        if (value == null) {
            value = "";
        }
        return value;
    }

    public static void setProperty(Config config, String value) {
        prop.setProperty(config.name(), value);
    }

    public static void store() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(propFilePath);
            //将Properties集合保存到流中
            prop.store(fos, "update time:" + CalendarUtils.getCurrentDateTime());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();//关闭流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
