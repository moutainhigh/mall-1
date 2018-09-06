package com.yunxin.cb.mall.template;

import com.yunxin.core.annotation.Property;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * Created by gonglei on 16/2/16.
 */
@Component
public class FreemarkerStatics {

    private static final Logger logger = LoggerFactory.getLogger(FreemarkerStatics.class);

    //加载模版文件的路径
    @Property(name = "freemarker.config")
    private String templateDir;

    @Property(name = "static.html.path")
    private String staticsDir;

    public void createHTML(ServletContext context,Map<String,Object> data,String templatePath,String targetHtmlPath){
        Configuration freemarkerCfg = new Configuration();
        //加载模版文件的路径
        freemarkerCfg.setServletContextForTemplateLoading(context, templateDir);
        freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
        try {
            //指定模版路径
            Template template = freemarkerCfg.getTemplate(templatePath,"UTF-8");
            template.setEncoding("UTF-8");
            //静态页面路径
            String htmlPath = context.getRealPath(staticsDir)+targetHtmlPath;
            File dir=new File(htmlPath.substring(0,htmlPath.lastIndexOf("/")));
            if (!dir.exists()){
                dir.mkdirs();
            }
            File htmlFile = new File(htmlPath);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
            //处理模版并开始输出静态页面
            template.process(data, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("生成静态网页出错");
            e.printStackTrace();
        }
    }
}
