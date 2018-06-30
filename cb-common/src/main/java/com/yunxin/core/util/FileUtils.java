/**
 * 
 */
package com.yunxin.core.util;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @author Aidy_He
 *
 */
public class FileUtils {

	private static HashMap<String, String> extMap = new HashMap<String, String>();
	private static final String DATA_DIR = File.separator+"data"+File.separator+"customerPic";
	public static final String FILE_TYPE_IMAGE="image";
	public static final String FILE_TYPE_FLASH="flash";
	public static final String FILE_TYPE_MEDIA="media";
	public static final String FILE_TYPE_FILE="file";

	static {
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	}

	public static String getCommonFileDir(String folderName, String dataCode) {
		String path = DATA_DIR + File.separator + folderName + File.separator + dataCode;
		return path;
	}

	public static Map<String,String> upload(String folderName, String dataCode,HttpServletRequest request) throws Exception{
		return upload(folderName,dataCode,FILE_TYPE_IMAGE,request);
	}

	/**
	 * 上传文件到服务器
	 * @param
	 * @param request
	 *
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> upload(String folderName, String dataCode,String fileType,HttpServletRequest request) throws Exception {
		if (fileType == null) {
			fileType = "image";
		}
		if(!extMap.containsKey(fileType)){
			throw new FileUploadException("文件类型不正确。");
		}

		String path = getCommonFileDir(folderName,dataCode) + File.separator + fileType +File.separator;
		//文件保存目录路径
		String savePath = request.getServletContext().getRealPath("/") +path;

		//创建文件夹
		String saveUrl = path;
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		Map<String,String> imagePathsMap=new HashMap<>();

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			try{
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				//取得request中的所有文件名
				List<MultipartFile> files = multiRequest.getFiles("files");

				for (MultipartFile file :files) {
					//取得上传文件
					if (file != null) {
						String myFileName = file.getOriginalFilename();
						if(LogicUtils.isNotNullAndEmpty(myFileName)){
							String suffix = myFileName.substring(myFileName.lastIndexOf(".") + 1).toLowerCase();
							//检查扩展名
							if(!Arrays.asList(extMap.get(fileType).split(",")).contains(suffix)){
								throw new FileUploadException("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(fileType) + "格式。");
							}
							String saveFileName = System.currentTimeMillis() +"."+ suffix;
							File localFile = new File(savePath , saveFileName);
							file.transferTo(localFile);
							imagePathsMap.put(myFileName, saveUrl + saveFileName);
						}
					}
				}
			}catch (Exception e){
				e.printStackTrace();
				throw new Exception(e);
			}
			return imagePathsMap;
		}
		return null;
	}


	public static String[] getImagesByCode(String folderName, String dataCode,HttpServletRequest request) {
		String path = getCommonFileDir(folderName, dataCode);
		File imageDir = new File(request.getServletContext().getRealPath(path)+File.separator+"image");
		String[] images = imageDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith("jpg") || name.endsWith("jpeg") || name.endsWith("png")) {
					return true;
				}
				return false;
			}
		});
		if (images != null) {
			String[] fImages = new String[images.length];
			for (int i = 0; i < images.length; i++) {
				fImages[i] = path + File.separator +"image" +File.separator+ images[i];
			}
			return fImages;
		}
		return null;
	}

	public static boolean removeFile(String filePath,HttpServletRequest request){
		String rPath = request.getServletContext().getRealPath("/") +filePath;
		File rFile = new File(rPath);
		if (rFile.isFile() && rFile.exists()) {
			rFile.delete();
			return true;
		}
		return false;
	}

	public static String bufferedReaderByFilePath(String path) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
		String data = null;
		StringBuilder sb=new StringBuilder(1024);
		while((data = br.readLine())!=null)
		{
			sb.append(data);
		}
		br.close();
		return sb.toString();
	}

	public static String bufferedReaderByFilePathRN(String path) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
		String data = null;
		StringBuilder sb=new StringBuilder(1024);
		while((data = br.readLine())!=null)
		{
			sb.append(data).append("\r\n");
		}
		br.close();
		return sb.toString();
	}

	public static void writerFileByFilePathAndContent(String path,String content) throws IOException{
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, false),"utf-8"));
		bufferedWriter.write(content);
		bufferedWriter.flush();
		bufferedWriter.close();
	}

}
