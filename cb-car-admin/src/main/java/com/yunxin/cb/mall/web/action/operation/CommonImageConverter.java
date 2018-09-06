//package com.yunxin.cb.mall.web.action.operation;
//
//import com.yunxin.cb.cms.entity.Article;
//import com.yunxin.cb.mall.entity.Activity;
//import com.yunxin.cb.mall.entity.Category;
//import com.yunxin.cb.mall.entity.Commodity;
//import com.yunxin.cb.mall.web.action.MediaPather;
//import com.yunxin.cb.security.SecurityConstants;
//import com.yunxin.core.util.ImageConverter;
//import com.yunxin.core.util.LogicUtils;
//
//import javax.servlet.ServletContext;
//import java.io.File;
//import java.io.IOException;
//
///**
// * 通用的图片压缩工具类，依次调用构造函数、compress()方法，即可实现功能。
// * 传入对象暂时支持Commodity Activity category，如需传入其它类型对象，请添加、修改与对象类型有关的代码
// * Created by chenxing on 16/3/4.
// */
//public class CommonImageConverter {
//
//    private enum PicMainDirType {
//        COMMODITY("commodity"), ACTIVITY("activity"),CATEGORY("category"),ARTICLE("article");
//        private String name;
//
//        PicMainDirType(String name) {
//            this.name = name;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        @Override
//        public String toString() {
//            return name;
//        }
//    }
//
//    private ServletContext servletContext;
//    private PicMainDirType picMainDirType;
//    private String codeStr;
//    private String[] imagePath;
//    private String defaultPath;
//    private String defaultImagePath;
//
//    /**
//     * 传入对象暂时支持Commodity Activity category，如需传入其它类型对象，请添加、修改与对象类型有关的代码
//     *
//     * @param servletContext
//     * @param sourceObj      可以为 Commodity Activity
//     */
//    public CommonImageConverter(ServletContext servletContext, Object sourceObj) {
//        this.servletContext = servletContext;
//        if (sourceObj instanceof Commodity) {
//            picMainDirType = PicMainDirType.COMMODITY;
//            this.codeStr = ((Commodity) sourceObj).getCommodityCode();
//            this.imagePath = ((Commodity) sourceObj).getImagePath();
//            this.defaultPath = ((Commodity) sourceObj).getDefaultPicPath();
//            MediaPather.createPicSiteRealDir(servletContext,picMainDirType.toString() + "/" + codeStr);
//        } else if (sourceObj instanceof Activity) {
//            picMainDirType = PicMainDirType.ACTIVITY;
//            this.codeStr = ((Activity) sourceObj).getActivityCode();
//            this.imagePath = new String[]{ ((Activity) sourceObj).getPicPath()};
//            this.defaultPath = ((Activity) sourceObj).getPicPath();
//            MediaPather.createPicSiteRealDir(servletContext,picMainDirType.toString() + "/" + codeStr);
//        }else if (sourceObj instanceof Category) {
//            picMainDirType = PicMainDirType.CATEGORY;
//            this.codeStr = ((Category) sourceObj).getCategoryNo();
//            this.imagePath = new String[]{ ((Category) sourceObj).getIconPath()};
//            this.defaultPath = ((Category) sourceObj).getIconPath();
//            MediaPather.createPicSiteRealDir(servletContext,picMainDirType.toString() + "/" + codeStr);
//        }else if (sourceObj instanceof Article) {
//            picMainDirType = PicMainDirType.ARTICLE;
//            this.codeStr = ((Article) sourceObj).getArticleCode();
//            this.imagePath = new String[]{ ((Article) sourceObj).getPicPath()};
//            this.defaultPath = ((Article) sourceObj).getPicPath();
//            MediaPather.createPicSiteRealDir(servletContext,picMainDirType.toString() + "/" + codeStr);
//        }
//
//
//    }
//
//    public void compress() {
//        if (LogicUtils.isNotNullAndEmpty(imagePath)) {
//            for (int i = 0; i < imagePath.length; i++) {
//                File imageFile = MediaPather.getPicStoreRealFile(servletContext, imagePath[i]);
//                if (imageFile.exists()) {
//                    ImageConverter imageConverter = new ImageConverter(imageFile);
//                    String imageName = System.currentTimeMillis() + "";
//                    if (imagePath[i].equals(defaultPath)) {
//                        defaultImagePath = picMainDirType.toString() + "/"  + codeStr + "/"  + imageName;
//                    }
//                    try {
//                        switch (picMainDirType) {
//                            case COMMODITY: {
//                                imageConverter.compressJpg(500, 539, getImageRealPath(codeStr, imageName, 500, 539));
//                                imageConverter.compressJpg(64, 69, getImageRealPath(codeStr, imageName, 64, 69));
//                                imageConverter.compressJpg(89, 89, getImageRealPath(codeStr, imageName, 89, 89));
//                                break;
//                            }
//                            case ACTIVITY: {
//                                imageConverter.compressJpg(192, 69, getImageRealPath(codeStr, imageName, 192, 69));
//                                imageConverter.compressJpg(870, 280, getImageRealPath(codeStr, imageName, 870, 280));
//                                imageConverter.compressJpg(1960, 647, getImageRealPath(codeStr, imageName, 1960, 647));
//                                break;
//                            }
//                            case CATEGORY: {
//                                imageConverter.compressJpg(20, 20, getImageRealPath(codeStr, imageName, 20, 20));
//                                break;
//                            }
//                            case ARTICLE:{
//                                imageConverter.compressJpg(900, 400, getImageRealPath(codeStr, imageName, 900, 400));
//                                imageConverter.compressJpg(90, 40, getImageRealPath(codeStr, imageName, 90, 40));
//                                break;
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }
//    }
//
//    public String getDefaultImagePath() {
//        return defaultImagePath;
//    }
//
//    public String getImagePath(String codeStr, String fileName, int width, int height) {
//        String postfix=null;
//        switch (picMainDirType){
//            case CATEGORY:{
//                postfix = ".png";
//                break;
//            }
//            default:{
//                postfix = ".jpg";
//            }
//        }
//        return servletContext.getInitParameter(SecurityConstants.PIC_DIR) + picMainDirType.toString() + "/"  + codeStr + "/"  + fileName + "_" + width + "_" + height + postfix;
//    }
//
//    public String getImageRealPath(String codeStr, String fileName, int width, int height) {
//        return servletContext.getRealPath(getImagePath(codeStr, fileName, width, height));
//    }
//}
