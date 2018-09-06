package com.yunxin.cb.mall.web.action.commodity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.mall.web.helper.imageBrowser.IImageBrowser;
import com.yunxin.cb.mall.web.helper.imageBrowser.ImageBrowser;
import com.yunxin.cb.mall.web.helper.imageBrowser.IImageBrowser;
import com.yunxin.cb.mall.web.helper.imageBrowser.ImageBrowser;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/commodity")
public class ImageBrowserController {
    
    private IImageBrowser imageBrowser;

    @RequestMapping(value = "imagebrowserRead", method = RequestMethod.POST)
    public @ResponseBody
    List<ImageBrowser> imagebrowserRead(String path) {
        return imageBrowser.getList(path);
    }   
    
    @RequestMapping(value = {"thumbnail"}, method = RequestMethod.GET)
    public @ResponseBody
    byte[] thumbnail(String path) throws IOException {
        byte[] bytes = path.getBytes("ISO-8859-1");
        path = new String(bytes, "UTF-8");
        return imageBrowser.getThumbnail(path);
    } 
    
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public @ResponseBody
    ImageBrowser upload(@RequestParam MultipartFile file, @RequestParam String path) throws IllegalStateException, IOException {
        if (file != null) {
            return imageBrowser.saveFile(file, path);
        }        
        return null;
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public @ResponseBody
    ImageBrowser create(@RequestParam final String name, @RequestParam final String type, @RequestParam String path) throws IOException {
    	ImageBrowser entry = new ImageBrowser() {{ 
            setName(name); 
            setType(type);
        }};
        
        imageBrowser.create(path, entry);        
        return entry;
    }
    
    @RequestMapping(value = "destroy", method = RequestMethod.POST)
    public @ResponseBody
    ImageBrowser destroy(@RequestParam final String name, @RequestParam final String type, @RequestParam String path) throws IOException {
    	ImageBrowser entry = new ImageBrowser() {{
            setName(name); 
            setType(type);
        }};
        
        imageBrowser.destroy(path, entry);        
        return entry;
    }

    @RequestMapping(value = "kindImage")
    public void kindImage(HttpServletRequest request,HttpServletResponse response) throws IOException, FileUploadException {
        PrintWriter out =response.getWriter();

        //文件保存目录路径
        String savePath = request.getSession().getServletContext().getRealPath("/") + "kindeditor/attached/";

//文件保存目录URL
        String saveUrl  = request.getContextPath() + "/kindeditor/attached/";

//定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

//最大文件大小
        long maxSize = 1000000;

        response.setContentType("text/html; charset=UTF-8");

        if(!ServletFileUpload.isMultipartContent(request)){
            out.println(getError("请选择文件。"));
            return;
        }
        //检查目录
        File uploadDir = new File(savePath);
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        if(!uploadDir.isDirectory()){
            out.println(getError("上传目录不存在。"));
            return;
        }
//检查目录写权限
        if(!uploadDir.canWrite()){
            out.println(getError("上传目录没有写权限。"));
            return;
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if(!extMap.containsKey(dirName)){
            out.println(getError("目录名不正确。"));
            return;
        }
//创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        // 创建磁盘工厂
        FileItemFactory factory = new DiskFileItemFactory();
        // 创建处理工具
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置参数
        upload.setHeaderEncoding("UTF-8");
        // 解析
        List<FileItem> items = upload.parseRequest(request);
        Iterator itr = items.iterator();
        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            String fileName = item.getName();
            long fileSize = item.getSize();
            if (!item.isFormField()) {
                //检查文件大小
                if(item.getSize() > maxSize){
                    out.println(getError("上传文件大小超过限制。"));
                    return;
                }
                //检查扩展名
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
                    out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                    return;
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                try{
                    File uploadedFile = new File(savePath, newFileName);
                    item.write(uploadedFile);
                }catch(Exception e){
                    out.println(getError("上传文件失败。"));
                    return;
                }
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> obj = new HashMap<>();
                obj.put("error", 0);
                obj.put("url", saveUrl + newFileName);
                out.println(mapper.writeValueAsString(obj));
            }
        }



    }
    private String getError(String message) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> obj = new HashMap<>();
        obj.put("error", 1);
        obj.put("message", message);
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}