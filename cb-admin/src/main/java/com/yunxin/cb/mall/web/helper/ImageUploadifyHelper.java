package com.yunxin.cb.mall.web.helper;

import com.yunxin.core.util.SFtpTool;

public class ImageUploadifyHelper {
	
	private static String userName;         //FTP 登录用户名
	
    private static String password;         //FTP 登录密码
	
    private static String ip;                     //FTP 服务器地址IP地址
	
    private static int port;                        //FTP 端口
	
    private static String rootPath;
	
    private static String accessPath;
    
    private static String localPath;
    
    private static SFtpTool sftp;
    @SuppressWarnings("unused")
	private void init() {
    	sftp=new SFtpTool(ip, port, userName, password, rootPath,localPath);
	}

    @SuppressWarnings("unused")
	private void destory(){
//    	sftp.disconnect();
//    	sftp.exits();
    }
//    public static String upload(ImageSource imageSource)throws Exception {
//    	//上传图片
//    	sftp.connectServer();
//		String access=sftp.upload(imageSource.getImgPhysical());
//		sftp.upload(imageSource.getImgPhysical()+ImageConstans.FALG_BIG_SUFFIX);
//		sftp.upload(imageSource.getImgPhysical()+ImageConstans.FALG_MIDDLE_SUFFIX);
//		sftp.upload(imageSource.getImgPhysical()+ImageConstans.FALG_SMALL_SUFFIX);
//		if(LogicUtils.isNotNullAndEmpty(access)){
//			access=accessPath+"/"+access;
//		}
//		return access;
//
//    }

	
	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		ImageUploadifyHelper.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ImageUploadifyHelper.password = password;
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		ImageUploadifyHelper.ip = ip;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		ImageUploadifyHelper.port = port;
	}

	public static String getRootPath() {
		return rootPath;
	}

	public static void setRootPath(String rootPath) {
		ImageUploadifyHelper.rootPath = rootPath;
	}


	public static String getAccessPath() {
		return accessPath;
	}


	public static void setAccessPath(String accessPath) {
		ImageUploadifyHelper.accessPath = accessPath;
	}


	public static String getLocalPath() {
		return localPath;
	}


	public static void setLocalPath(String localPath) {
		ImageUploadifyHelper.localPath = localPath;
	}
    
}
