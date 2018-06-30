/**
 * 
 */
package com.yunxin.cb.mall.web.helper.imageBrowser;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.xml.ws.http.HTTPException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author apple
 * 
 */
@Component
public class ImageBrowserService implements IImageBrowser {

	private final static Logger log= LoggerFactory.getLogger(ImageBrowserService.class);
	
	@Autowired
	private ResourceLoader loader;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private IContentInitializer contentInitializer;
	
	private final String contentPath = "/data/pics/commodity/imagebrowser/";
	
	private final String sourceFolder = "/data/pics/commodity/photos";

	@PostConstruct
	void Init() {
		try {
			File souFile =new File(context.getRealPath(sourceFolder));
			if(!souFile.exists()){
				souFile.mkdirs();
			}
			File conFile =new File(context.getRealPath(contentPath));
			if(!conFile.exists()){
				conFile.mkdirs();
			}
			contentInitializer.initialize(souFile,conFile);
		} catch(Exception e){
			log.info("Create Folder Exception", e);
		}
		
	}

	private String normalizePath(String path) {
		return new File(contentPath, path).getPath();
	}

	private boolean canAccess(String path) {
		try {
			return new File(contentPath, path).getCanonicalPath().startsWith(
					new File(contentPath).getCanonicalPath());
		} catch (IOException e) {
			log.info("access file Exception", e);
		}
		return false;
	}

	@Override
	public List<ImageBrowser> getList(String path) {
		if (!canAccess(path)) {
			throw new HTTPException(401);
		}
		List<ImageBrowser> result = new ArrayList<ImageBrowser>();
		try {
			File folder = loader.getResource(normalizePath(path)).getFile();
			for (final File fileEntry : folder.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.isDirectory()
							|| pathname.getName().matches(
									".*((png)|(PNG)|(gif)|(GIF)|(jpg)|(JPG)|(jpeg)|(JPEG))$");
				}
			})) {
				ImageBrowser entry = new ImageBrowser();
				if (fileEntry.isDirectory()) {
					entry.setType("d");
				} else {
					entry.setType("f");
					entry.setSize(fileEntry.length());
				}
				entry.setName(fileEntry.getName());
				result.add(entry);
			}
		} catch (IOException e) {
			log.info("get ImageBrowserList IOException", e);
		}
		return result;
	}

	@Override
	public byte[] getThumbnail(String path) {
		if (!canAccess(path)) {
			throw new HTTPException(401);
		}
		try {
			File file = loader.getResource(normalizePath(path)).getFile();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			try {
				ImageIO.write(scaleImage(ImageIO.read(file), 80),
						getExtension(file), stream);
				return stream.toByteArray();
			} finally {
				stream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.info("getThumbnail IOException", e);
		}
		return null;
	}

	private BufferedImage scaleImage(final BufferedImage bufferedImage,
			final int size) {
		final double boundSize = size;
		final int origWidth = bufferedImage.getWidth();
		final int origHeight = bufferedImage.getHeight();

		double scale;
		if (origHeight > origWidth) {
			scale = boundSize / origHeight;
		} else {
			scale = boundSize / origWidth;
		}
		if (scale > 1.0)
			return (null);
		final int scaledWidth = (int) (scale * origWidth);
		final int scaledHeight = (int) (scale * origHeight);
		final Image scaledImage = bufferedImage.getScaledInstance(scaledWidth,
				scaledHeight, Image.SCALE_SMOOTH);
		final BufferedImage scaledBI = new BufferedImage(scaledWidth,
				scaledHeight, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g = scaledBI.createGraphics();
		try {
			g.drawImage(scaledImage, 0, 0, null);
		} finally {
			g.dispose();
		}
		return scaledBI;
	}

	@Override
	public void destroy(String path, ImageBrowser imageBrowser)
			throws IOException {
		if (!canAccess(path)) {
			throw new HTTPException(401);
		}

		File file = new File(context.getRealPath(""), new File(
				normalizePath(path), imageBrowser.getName()).getPath());
		if (file.isDirectory()) {
			deleteRecursive(file);
		}
		file.delete();
	}

	private void deleteRecursive(File directory) {
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				deleteRecursive(file);
			}
			file.delete();
		}
	}

	@Override
	public void create(String path, ImageBrowser imageBrowser)
			throws IOException {
		if (!canAccess(path)) {
			throw new HTTPException(401);
		}

		new File(context.getRealPath(""), new File(normalizePath(path),
				imageBrowser.getName()).getPath()).mkdir();
	}

	@Override
	public ImageBrowser saveFile(final MultipartFile file, String path)
			throws IllegalStateException, IOException {
		if (!canAccess(path)) {
			throw new HTTPException(401);
		}
		String fileName = file.getOriginalFilename();
		file.transferTo(new File(context.getRealPath(""), new File(
				normalizePath(path), fileName).getPath()));
		ImageBrowser imageBrowser = new ImageBrowser();
		imageBrowser.setSize(file.getSize());
		imageBrowser.setName(fileName);
		return imageBrowser;
	}

	private String getExtension(File file) {
		String name = file.getName();
		return name.substring(name.lastIndexOf('.') + 1);
	}

}
