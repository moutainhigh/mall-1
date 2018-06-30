package com.yunxin.cb.mall.web.action;

import com.yunxin.cb.security.SecurityConstants;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * Created by gonglei on 16/2/18.
 */
public class MediaPather {

    /**
     * 获得图片前台存储绝对路径
     *
     * @param servletContext
     * @param webPath
     * @return
     */
    public static String getPicSiteRealPath(ServletContext servletContext, String webPath) {
        return servletContext.getRealPath(servletContext.getInitParameter(SecurityConstants.PIC_DIR) + webPath);
    }

    /**
     * 创建图片前台存储绝对路径目录
     * @param servletContext
     * @param webPath
     * @return
     */
    public static File createPicSiteRealDir(ServletContext servletContext, String webPath){
        String fileDir=getPicSiteRealPath(servletContext,webPath);
        File file=new File(fileDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    /**
     * 获得图片库文件存储绝对路径
     * @param servletContext
     * @param webPath
     * @return
     */
    public static String getPicStoreRealPath(ServletContext servletContext, String webPath){
        return servletContext.getRealPath(servletContext.getInitParameter(SecurityConstants.PIC_PATH) + webPath);
    }

    public static File createPicStoreRealDir(ServletContext servletContext, String webPath){
        String fileDir=getPicStoreRealPath(servletContext,webPath);
        File file=new File(fileDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获得图片库文件
     * @param servletContext
     * @param webPath
     * @return
     */
    public static File getPicStoreRealFile(ServletContext servletContext, String webPath){
        String fileDir=getPicStoreRealPath(servletContext,webPath);
        File file=new File(fileDir);
        return file;
    }
}
