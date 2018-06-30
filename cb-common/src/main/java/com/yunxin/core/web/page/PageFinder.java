package com.yunxin.core.web.page;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 *
 * @author 龚磊
 */
@SuppressWarnings("serial")
public class PageFinder<T> implements Serializable {

    private static int DEFAULT_PAGE_SIZE = 20;

    /**
     * 当前页号
     */
    private int number;

    /**
     * 每页的记录数
     */
    private int size;

    /**
     * 总页数
     */
    private int totalPages;

    private int numberOfElements;

    /**
     * 是否有上一页
     */
    private boolean previousPage;

    private boolean firstPage;

    /**
     * 是否有下一页
     */
    private boolean nextPage;

    /**
     * 总记录数
     */
    private long totalElements;

    private boolean lastPage;

    /**
     * 当前页中存放的记录,类型一般为List
     */
    private List<T> content;

    public PageFinder(Page<T> page) {
        this.size = page.getSize();
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.number = page.getNumber();
        this.previousPage = page.hasPrevious();
        this.firstPage = page.isFirst();
        this.nextPage = page.hasNext();
        this.lastPage = page.isLast();
        this.numberOfElements = page.getNumberOfElements();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(boolean previousPage) {
        this.previousPage = previousPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}