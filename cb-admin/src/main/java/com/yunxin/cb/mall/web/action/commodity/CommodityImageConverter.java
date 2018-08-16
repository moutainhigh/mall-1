//package com.yunxin.cb.mall.web.action.commodity;
//
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
// * Created by gonglei on 16/1/27.
// */
//public class CommodityImageConverter {
//
//
//    private Commodity commodity;
//
//    private String[] imagePath;
//
//    private ServletContext servletContext;
//
//
//    private String defaultImagePath;
//
//    public CommodityImageConverter(Commodity commodity, String[] imagePath, ServletContext servletContext) {
//        this.commodity = commodity;
//        this.imagePath = imagePath;
//        this.servletContext = servletContext;
//        MediaPather.createPicSiteRealDir(servletContext,"commodity/" + commodity.getCommodityCode());
//    }
//
//    public void compress() {
//        String commodityCode = commodity.getCommodityCode();
//        if(LogicUtils.isNotNullAndEmpty(imagePath)) {
//            for (int i = 0; i < imagePath.length; i++) {
//                File imageFile = MediaPather.getPicStoreRealFile(servletContext, imagePath[i]);
//                if(imageFile.exists()){
//                    ImageConverter imageConverter = new ImageConverter(imageFile);
//                    String imageName = System.currentTimeMillis() + "";
//                    if (imagePath[i].equals(commodity.getDefaultPicPath())) {
//                        defaultImagePath = "commodity/" + commodityCode + "/" + imageName;
//                    }
//                    try {
//                        imageConverter.compressJpg(500, 539,  getCommodityImageRealPath(commodityCode, imageName, 500, 539));
//                        imageConverter.compressJpg(64, 69,  getCommodityImageRealPath(commodityCode, imageName, 64, 69));
//                        imageConverter.compressJpg(89, 89,  getCommodityImageRealPath(commodityCode, imageName, 89, 89));
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
//    public String getCommodityImagePath(String commodityCode, String fileName, int width, int height) {
//        return servletContext.getInitParameter(SecurityConstants.PIC_DIR)+"commodity/" + commodityCode + "/" + fileName + "_" + width + "_" + height + ".jpg";
//    }
//
//    public String getCommodityImageRealPath(String commodityCode, String fileName, int width, int height) {
//        return servletContext.getRealPath(getCommodityImagePath(commodityCode, fileName, width, height));
//    }
//}
