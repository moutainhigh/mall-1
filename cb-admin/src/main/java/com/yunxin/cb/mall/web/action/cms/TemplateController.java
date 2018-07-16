package com.yunxin.cb.mall.web.action.cms;

import com.yunxin.cb.mall.web.vo.Template;
import com.yunxin.cb.mall.web.vo.TemplateNode;
import com.yunxin.core.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cms")
public class TemplateController implements ServletContextAware {

    private static Logger logger=LoggerFactory.getLogger(TemplateController.class);

    public static final String BASE_PATH = "/templates";

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    @RequestMapping(value = "templates", method = RequestMethod.GET)
    public String templates(ModelMap modelMap) {
        return "cms/templates";
    }

    @RequestMapping(value = "toAddTemplate", method = RequestMethod.GET)
    public String toAddTemplate(@ModelAttribute("template") Template template, ModelMap modelMap) {
        template.setTemplatePath("/");
        return "cms/addTemplate";
    }

    @RequestMapping(value = "addTemplate", method = RequestMethod.POST)
    public String addTemplate(@ModelAttribute("template") Template template, HttpServletRequest request) throws IOException {
        String path = servletContext.getRealPath(BASE_PATH + template.getTemplatePath());
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        if(template.getTemplateName().lastIndexOf(".") == -1){
            template.setTemplateName(template.getTemplateName()+".ftl");
        }
        File file = new File(path + template.getTemplateName());
        if (!file.exists()){
            file.createNewFile();
        }else{
            logger.info("文件已存在");
        }

        if(file.canWrite()){
            FileUtils.writerFileByFilePathAndContent(file.getPath(), template.getContent());
        }

        return "redirect:../common/success.do?reurl=cms/templates.do";
    }


    @RequestMapping(value = "toEditTemplate", method = RequestMethod.GET)
    public String toEditTemplate(@RequestParam("templatePath") String templatePath, ModelMap modelMap) throws IOException{
        String path = servletContext.getRealPath(BASE_PATH + templatePath);
        File file = new File(path);
        Template template = new Template();
        template.setTemplateName(file.getName());
        template.setTemplatePath(templatePath);
        template.setContent(FileUtils.bufferedReaderByFilePathRN(path));
        modelMap.addAttribute("template", template);
        return "cms/editTemplate";
    }

    @RequestMapping(value = "editTemplate", method = RequestMethod.POST)
    public String editTemplate(@ModelAttribute("template") Template template, ModelMap modelMap) throws IOException {
        String path = servletContext.getRealPath(BASE_PATH + template.getTemplatePath());
        FileUtils.writerFileByFilePathAndContent(path,template.getContent());
        return "redirect:../common/success.do?reurl=cms/templates.do";
    }

    @RequestMapping(value = "getTemplateNodesByPath", method = RequestMethod.POST)
    @ResponseBody
    public List<TemplateNode> getDataNodesById(@RequestBody Map<String, Object> model, HttpServletRequest request) {
        String path = (String) model.get("path");
        String realPath = path != null ? BASE_PATH + path : BASE_PATH;
        File fileDir = new File(servletContext.getRealPath(realPath));
        File[] files = fileDir.listFiles();
        List<TemplateNode> folderNodes = new ArrayList<>(files.length);
        for (File file : files) {
            String dirName = file.getName();
            if (file.isDirectory()) {
                int childDirSize = file.listFiles((dir, name) -> {
                    return dir.isDirectory();
                }).length;
                String folderPath = "/" + dirName;
                if (path != null) {
                    folderPath = path + folderPath;
                }
                TemplateNode node = new TemplateNode(folderPath, dirName, "folder", childDirSize > 0);
                folderNodes.add(node);
            } else {
                if (path == null) {
                    path="/";
                }
                TemplateNode node = new TemplateNode(path, dirName, "file", false);
                folderNodes.add(node);
            }
        }
        return folderNodes;
    }

}
