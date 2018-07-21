package com.yunxin.cb.mall.web.action.upload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.mall.entity.meta.UploadType;
import com.yunxin.cb.storage.IStorageService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 富文本中的图片、文件等的上传
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 **/
@Controller
@RequestMapping(value = "/upload")
public class KindEditorController implements ServletContextAware {

    private static final Logger LOGGER = Logger.getLogger(KindEditorController.class);

    //图片扩展名
    private static final List<String> IMAGE_TYPES = Arrays.asList("gif", "jpg", "jpeg", "png", "bmp");

    //文件扩展名
    private static final List<String> FILE_TYPES = Arrays.asList("image", "flash", "media", "file");

    //最大文件大小
    private static final long MAX_SIZE = 2147483648L;

    //定义允许上传的文件扩展名
    private static final HashMap<String, String> EXT_MAP = new HashMap<>();

    @Value("${application.uploadPath}")
    private String uploadImageDir;

    @Resource
    private IStorageService qiniuStorageService;

    private ServletContext servletContext;

    static {
        EXT_MAP.put("image", "gif,jpg,jpeg,png,bmp");
        EXT_MAP.put("flash", "swf,flv");
        EXT_MAP.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        EXT_MAP.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 图片上传
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public void fileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //json转换工具
        ObjectMapper mapper = new ObjectMapper();

        //设置响应头信息并获取输出流
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        //检查是否有上传的图片
        if (!ServletFileUpload.isMultipartContent(request)) {
            writeValueAsString(mapper, writer, "请选择要上传的文件！");
            return;
        }

        //获取dir参数，作为文件存储目录
        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        //检查目录名是否允许
        if (!EXT_MAP.containsKey(dirName)) {
            writeValueAsString(mapper, writer, "参数dir是不被允许的目录名！");
            return;
        }

        //检查请求方式以及参数
        if (!new CommonsMultipartResolver(servletContext).isMultipart(request)) {
            writeValueAsString(mapper, writer, "不被允许的请求类型！");
            return;
        }
        //检查是否存在提交的图片
        MultipartHttpServletRequest e = (MultipartHttpServletRequest) request;
        Map rFileMap = e.getFileMap();
        Iterator entries = rFileMap.entrySet().iterator();
        if (!entries.hasNext()) {
            writeValueAsString(mapper, writer, "请选择要上传的图片！");
            return;
        }
        Map.Entry entry = (Map.Entry) entries.next();
        MultipartFile file = (MultipartFile) entry.getValue();
        if (file == null) {
            writeValueAsString(mapper, writer, "请选择要上传的图片！");
            return;
        }
        //检查图片大小是否超出限制
        if (file.getSize() > MAX_SIZE) {
            writeValueAsString(mapper, writer, "上传文件大小超过限制，请上传2G以下的文件！");
            return;
        }
        //检查图片文件名
        String myFileName = file.getOriginalFilename();
        if (StringUtils.isBlank(myFileName)) {
            writeValueAsString(mapper, writer, "上传的图片文件名称不合法！");
            return;
        }
        //检查图片后缀判断图片类型是否合法
        String suffix = myFileName.substring(myFileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.asList(EXT_MAP.get(dirName).split(",")).contains(suffix)) {
            writeValueAsString(mapper, writer, "上传文件扩展名是不允许的扩展名！\n只允许" + EXT_MAP.get(dirName) + "格式！");
            return;
        }

        //图片存储
         String url = qiniuStorageService.put(file.getInputStream(), UploadType.RESOURCE);

        //图片上传成功，响应成功信息
        Map<String, Object> successMap = new HashMap<>();
        successMap.put("error", 0);
        successMap.put("url", url);
        writer.println(mapper.writeValueAsString(successMap));
    }

    /**
     * 文件上传
     *
     * @param request
     * @param response
     */
    @GetMapping(value = "/fileManager")
    public void fileManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //响应输出流
        ServletOutputStream out = response.getOutputStream();

        //根据path参数，设置各路径和URL
        final String path = StringUtils.isNotBlank(request.getParameter("path")) ? request.getParameter("path") : "";

        //不允许使用..移动到上一级目录
        if (path.contains("..")) {
            out.println("不允许使用..移动到上一级目录！");
            return;
        }
        //最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            out.println("最后一个字符不是/！");
            return;
        }

        //文件保存目录路径
        String savePath = uploadImageDir + "attached/";
        //文件保存目录URL
        String saveUrl = servletContext.getContextPath() + "/upload/attached/";

        //判断是否有dir目录参数
        String dirName = request.getParameter("dir");
        if (StringUtils.isNotBlank(dirName)) {
            if (!FILE_TYPES.contains(dirName)) {
                out.println("不允许的dir参数代表的文件类型！");
                return;
            }
            savePath += dirName + "/";
            saveUrl += dirName + "/";

            try {
                FileUtils.forceMkdir(new File(savePath));
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
                out.println("文件存储目录创建失败！");
                return;
            }
        }

        savePath += path;
        saveUrl += path;

        //目录不存在或不是目录
        File currentPathFile = new File(savePath);
        if (!currentPathFile.exists()
                || !currentPathFile.isDirectory()) {
            out.println("目录不存在或不是目录！");
            return;
        }

        //遍历目录取的文件信息
        List<Map<String, Object>> fileList = new ArrayList<>();
        File[] files = currentPathFile.listFiles();
        if (ArrayUtils.isNotEmpty(files)) {
            for (File file : files) {
                Hashtable<String, Object> hash = new Hashtable<>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", ArrayUtils.isNotEmpty(file.listFiles()));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if (file.isFile()) {
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", IMAGE_TYPES.contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }

        //排序形式，name or size or type
        String paramOrder = request.getParameter("order");
        String order = StringUtils.isNotBlank(paramOrder) ? paramOrder.toLowerCase() : "name";
        //根据排序类型排序
        if ("size".equals(order)) {
            fileList.sort(new SizeComparator());
        } else if ("type".equals(order)) {
            fileList.sort(new TypeComparator());
        } else {
            fileList.sort(new NameComparator());
        }

        String moveUpDirPath = "";
        if (StringUtils.isNotBlank(path)) {
            String str = path.substring(0, path.length() - 1);
            moveUpDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }

        //返回响应结果
        Map<String, Object> result = new HashMap<>();
        result.put("moveup_dir_path", moveUpDirPath);
        result.put("current_dir_path", path);
        result.put("current_url", saveUrl);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);
        response.setContentType("application/json; charset=UTF-8");
        out.println(new ObjectMapper().writeValueAsString(result));
    }

    /**
     * 向输出流中写入响应信息
     *
     * @param objectMapper
     * @param writer
     * @param message
     * @throws IOException
     */
    private void writeValueAsString(ObjectMapper objectMapper, PrintWriter writer, String message) throws IOException {
        writer.println(objectMapper.writeValueAsString(getError(message)));
    }

    /**
     * 返回错误信息
     *
     * @param errorMsg
     * @return
     */
    private Map<String, Object> getError(String errorMsg) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("error", 1);
        errorMap.put("message", errorMsg);
        return errorMap;
    }

    private static class NameComparator implements Comparator<Map<String, Object>>, Serializable {
        @Override
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
            }
        }
    }

    private static class SizeComparator implements Comparator<Map<String, Object>>, Serializable {
        @Override
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
                    return 1;
                } else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    private static class TypeComparator implements Comparator<Map<String, Object>>, Serializable {
        @Override
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
            }
        }
    }
}