package com.yunxin.cb.mall.web.action.media;

import java.util.List;

/**
 * Created by Aidy_He on 2016/10/25.
 */
public class PageUtil implements java.io.Serializable{

    private static final long serialVersionUID =1l;

    int totalRowsAmount; //总行数

    boolean rowsAmountSet; //是否设置过totalRowsAmount

    int pageSize = 10; //每页行数

    int currentPage = 1; //当前页码

    int nextPage;

    int previousPage;

    int totalPages; //总页数

    boolean hasNext; //是否有下一页

    boolean hasPrevious; //是否有前一页

    int pageStartRow;

    int pageEndRow;

    List<FileNode> fileNodes;

    public PageUtil() {
    }

    public PageUtil(int totalRows) {
        setTotalRowsAmount(totalRows);
    }


    /**
     * @param i 设定总行数
     */
    public void setTotalRowsAmount(int i) {
        if (!this.rowsAmountSet) {
            totalRowsAmount = i;
            totalPages = totalRowsAmount / pageSize + 1;
            setCurrentPage(1);
            this.rowsAmountSet = true;
        }
    }

    /**
     * @param i 当前页
     */
    public void setCurrentPage(int i) {
        currentPage = i;
        nextPage = currentPage + 1;
        previousPage = currentPage - 1;
        //计算当前页开始行和结束行
        if (currentPage * pageSize < totalRowsAmount) {
            pageEndRow = currentPage * pageSize;
            pageStartRow = pageEndRow - pageSize;
        } else {
            pageEndRow = totalRowsAmount;
            pageStartRow = pageSize * (totalPages - 1);
        }

        //是否存在前页和后页
        if (nextPage > totalPages) {
            hasNext = false;
        } else {
            hasNext = true;
        }

        if (previousPage == 0) {
            hasPrevious = false;
        } else {
            hasPrevious = true;

        }
    }

    /**
     * @return
     */

    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @return
     */

    public boolean isHasNext() {
        return hasNext;
    }

    /**
     * @return
     */

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    /**
     * @return
     */

    public int getNextPage() {
        return nextPage;
    }

    /**
     * @return
     */

    public int getPageSize() {
        return pageSize;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalRowsAmount() {
        return totalRowsAmount;
    }

    public void setHasNext(boolean b) {
        hasNext = b;
    }

    public void setHasPrevious(boolean b) {
        hasPrevious = b;
    }

    public void setNextPage(int i) {
        nextPage = i;
    }

    public void setPageSize(int i) {
        pageSize = i;
    }

    public void setPreviousPage(int i) {
        previousPage = i;
    }

    public void setTotalPages(int i) {
        totalPages = i;
    }

    public int getPageEndRow() {
        return pageEndRow;
    }

    public int getPageStartRow() {
        return pageStartRow;
    }

    public List<FileNode> getFileNodes() {
        return fileNodes;
    }

    public void setFileNodes(List<FileNode> fileNodes) {
        this.fileNodes = fileNodes;
    }
}