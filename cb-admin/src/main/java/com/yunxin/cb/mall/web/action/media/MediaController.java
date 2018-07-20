package com.yunxin.cb.mall.web.action.media;

import com.yunxin.cb.mall.web.action.MediaPather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author gonglei
 */
@Controller
@RequestMapping(value = "/media")
public class MediaController {


    @Value("${application.uploadPath}")
    private String uploadPath;


    @RequestMapping(value = "medias", method = RequestMethod.GET)
    public String medias(ModelMap modelMap) {
        modelMap.addAttribute("picPath", uploadPath);
        return "media/medias";
    }

    @RequestMapping(value = "chooseMedias", method = RequestMethod.GET)
    public String chooseMedias(ModelMap modelMap) {
        modelMap.addAttribute("picPath", uploadPath);
        return "media/chooseMedias";
    }


    @RequestMapping(value = "getFolderNodesByPath", method = RequestMethod.POST)
    @ResponseBody
    public List<FolderNode> getDataNodesById(@RequestBody Map<String, Object> model) {
        String path = (String) model.get("path");
        String realPath = uploadPath;
        if (path != null) {
            realPath = realPath + path;
        }
        File fileDir = new File(realPath);
        File[] files = fileDir.listFiles();
        List<FolderNode> folderNodes = new ArrayList<>(files.length);
        for (File file : files) {
            if (file.isDirectory()) {
                String dirName = file.getName();
                int childDirSize = file.listFiles((dir, name) -> {
                    return dir.isDirectory();
                }).length;
                String folderPath = dirName + "/";
                if (path != null) {
                    folderPath = path + folderPath;
                }
                FolderNode node = new FolderNode(folderPath, dirName, childDirSize > 0);

                folderNodes.add(node);
            }
        }
        return folderNodes;
    }

    @RequestMapping(value = "getFileNodesByPath", method = RequestMethod.GET)
    @ResponseBody
    public PageUtil getFileNodesByPath(@RequestParam("path") String path, @RequestParam("page") int page) {
        File fileDir = new File(uploadPath + path);
        File[] files = fileDir.listFiles();
        List<FileNode> folderNodes = new ArrayList<>(files.length);
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String fileName = file.getName();
            String folderPath = null;
            if (path.equals("/")) {
                folderPath = fileName;
            } else {
                if (path.endsWith("/")) {
                    folderPath = path + fileName;
                } else {
                    folderPath = path + "/" + fileName;
                }
            }
            FileNode node = new FileNode(fileName, folderPath, file.isFile());
            node.setCreateTime(new Date(file.lastModified()));
            node.setIdx(i + 1);
            if (file.isFile()) {
                node.setFileLength(file.length());
                node.setFile(true);
                if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
                    BufferedImage bi = null;
                    try {
                        bi = ImageIO.read(file);
                        double width = bi.getWidth();
                        double height = bi.getHeight();
                        node.setWidth((int) width);
                        node.setHeight((int) height);
                    } catch (IOException e) {
                    }
                }
            }
            folderNodes.add(node);
        }
        PageUtil pageUtil = new PageUtil(folderNodes.size());
        pageUtil.setCurrentPage(page);
        pageUtil.setFileNodes(folderNodes.subList(pageUtil.getPageStartRow(), pageUtil.getPageEndRow()));
        return pageUtil;
    }

    @RequestMapping(value = "deleteFileNodeByPath", method = RequestMethod.GET)
    @ResponseBody
    public MediaResult deleteFileNodeByPath(@RequestParam("filePath") String filePath) {
        File file = new File(uploadPath + filePath);
        if (file.exists()) {
            file.delete();
        }
        MediaResult mediaResult = new MediaResult();
        mediaResult.setResult("success");
        return mediaResult;
    }

    @RequestMapping(value = "createDirByPath", method = RequestMethod.GET)
    @ResponseBody
    public MediaResult createDirByPath(@RequestParam("filePath") String filePath, @RequestParam("dirName") String dirName) {
        File file = new File(uploadPath+ filePath + "/" + dirName);
        if(!file.exists()){
            file.mkdirs();
        }
        MediaResult mediaResult = new MediaResult();
        mediaResult.setResult("success");
        FileNode node = new FileNode(dirName, filePath + "/" + dirName, false);
        node.setCreateTime(new Date(file.lastModified()));
        mediaResult.setFileNode(node);
        return mediaResult;
    }

    /**
     * @param file
     * @return
     */
    @RequestMapping(value = "uploadMediaFile", method = RequestMethod.POST)
    @ResponseBody
    public MediaResult uploadMediaFile(@RequestParam("mediaFile") CommonsMultipartFile file, HttpServletRequest request) {
        String path = request.getParameter("path");
        int scaleWidth = Integer.parseInt(request.getParameter("scaleWidth"));
        int scaleHeight = Integer.parseInt(request.getParameter("scaleHeight"));
        File fileDir = new File(uploadPath+path);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String fileName = file.getFileItem().getName();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String name = System.currentTimeMillis() + suffix.toLowerCase();
        File newFile = new File(uploadPath + "/" + name);
        MediaResult mediaResult = new MediaResult();
        try {
            file.getFileItem().write(newFile);
            FileNode node = new FileNode(name, path + "/" + name, true);
            node.setFileLength(newFile.length());
            node.setFile(true);
            if (suffix.endsWith(".jpg") || suffix.endsWith(".png")) {
                BufferedImage bi = null;
                try {
                    bi = ImageIO.read(newFile);
                    double width = bi.getWidth();
                    double height = bi.getHeight();
                    node.setWidth((int) width);
                    node.setHeight((int) height);
                } catch (IOException e) {
                }
            }
            mediaResult.setResult("success");
            mediaResult.setFileNode(node);
        } catch (Exception e) {
            e.printStackTrace();
            mediaResult.setResult("failure");
        }
        return mediaResult;
    }


}
