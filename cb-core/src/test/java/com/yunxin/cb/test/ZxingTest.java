package com.yunxin.cb.test;

import com.yunxin.cb.util.ZxingUtils;

public class ZxingTest {

    public static void main(String[] args) throws Exception {
        String imgPath = "D:/barcode1.png";
        // 益达无糖口香糖的条形码
        String contents = "1113021345873023";
        int width = 150, height = 50;
        ZxingUtils handler = new ZxingUtils();
        handler.encodeBarCode(contents, width, height, imgPath);
        String barcode = handler.decodeBarCode(imgPath);
        System.out.println(barcode);
//        handler.encodeQRCode("abc123中文@#\\", 200, 200, imgPath);
//        String qrcode = handler.decodeQRCode(imgPath);
//        System.out.println(qrcode);
    }
}
