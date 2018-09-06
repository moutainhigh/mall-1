/**
 * 
 */
package com.yunxin.cb.mall.web.helper.imageBrowser;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author apple
 *
 */
public interface IImageBrowser {

	 public List<ImageBrowser> getList(String path);

	    public byte[] getThumbnail(String path);
	    
	    public void destroy(String path, ImageBrowser imageBrowser) throws IOException;
	    
	    public void create(String path, ImageBrowser imageBrowser) throws IOException;
	    
	    public ImageBrowser saveFile(MultipartFile file, String path) throws IllegalStateException, IOException;
}
