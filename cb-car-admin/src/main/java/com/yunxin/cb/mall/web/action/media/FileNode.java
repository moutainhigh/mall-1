/**
 *
 */
package com.yunxin.cb.mall.web.action.media;

import com.yunxin.core.util.CalendarUtils;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author gonglei
 */
public class FileNode implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5712924289950374360L;

    private static DecimalFormat format=new DecimalFormat("####.##");


    private String path;

    private int idx;

    /**
     * 组织名称
     */
    private String fileName;

    private long fileLength;

    private Date createTime;

    private boolean file;

    /**
     * 图片宽度
     */
    private int width;
    private int height;


    public FileNode(String name, String path, boolean file) {
        this.path = path;
        this.fileName = name;
        this.file = file;
    }




    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return format.format(fileLength/1000);
    }

    public String getModifyTime(){
        return CalendarUtils.formatDate(createTime);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isFile() {
        return file;
    }

    public void setFile(boolean file) {
        this.file = file;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
