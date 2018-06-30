/**
 * 
 */
package com.yunxin.cb.mall.web.helper.imageBrowser;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author apple
 *
 */
@Component
public class ContentInitializerService implements IContentInitializer{

	private final static Logger log=LoggerFactory.getLogger(ContentInitializerService.class);
	private final int bufferSize = 4096;

    @Override
    public void initialize(File source, File destination) {
        if (!destination.exists()) {
            destination.mkdir();
            copyFolder(source, destination);
        }        
    }
        
    private void copyFolder(File source, File destination){
        if(source.isDirectory()){
            if(!destination.exists()){               
                destination.mkdir();
            }
            
            for (final File file : source.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory() || pathname.getName().matches(".*((png)|(gif)|(jpg)|(jpeg))$");
                }
                })) {
                copyFolder(file, new File(destination, file.getName()));
            }           
        }
        else{
            copyFile(source, destination); 
        }        
    }
    
    private void copyFile(File source, File destination){
        InputStream input;
        OutputStream output;
        try {
            input = new FileInputStream(source);
            output  = new FileOutputStream(destination);
            try{
                byte[] buffer = new byte[bufferSize];

                int length;
                while ((length = input.read(buffer)) > 0){
                    output.write(buffer, 0, length);
                }
            }
            catch(IOException e){
            	log.info("readFile IO error", e);
            }
            finally{
                input.close();
                output.close();
            }
        } catch (FileNotFoundException e) {
        	log.info("file not found", e);
        	e.printStackTrace();
        } 
        catch(IOException e){
        	log.info("copyFile IO error", e);
        	e.printStackTrace();
        }
    }   

}
