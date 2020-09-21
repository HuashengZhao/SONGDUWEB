package com.example.EAS.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author $$ Watson $$ zhs19951228@163.com
 * @Description 分页类
 * @Date 2019/10/30 15.16
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PageBean<T> {

    private List<T> pageData;
    private Integer currentPage = Integer.valueOf(1);
    private Integer pageSize = Integer.valueOf(10);
    private Long totalCount;

    public int getPageCount() {
        if (this.totalCount.intValue() % this.pageSize.intValue() == 0) {
            return this.totalCount.intValue() / this.pageSize.intValue();
        }
        return this.totalCount.intValue() / this.pageSize.intValue() + 1;
    }

    public PageBean(List<T> pageData, Long totalCount) {
        this.pageData = pageData;
        this.totalCount = totalCount;
    }

    public boolean isFirst() {
        return (this.currentPage.intValue() == 1) || (this.totalCount.intValue() == 0);
    }

    public boolean isLast() {
        return (this.totalCount.intValue() == 0) || (this.currentPage.intValue() >= getPageCount());
    }

    public boolean isHasNext() {
        return this.currentPage.intValue() < getPageCount();
    }

    public boolean isHasPrev() {
        return this.currentPage.intValue() > 1;
    }

    public Integer getNextPage() {
        if (this.currentPage.intValue() >= getPageCount()) {
            return Integer.valueOf(getPageCount());
        }
        return Integer.valueOf(this.currentPage.intValue() + 1);
    }

    public Integer getPrevPage() {
        if (this.currentPage.intValue() <= 1) {
            return Integer.valueOf(1);
        }
        return Integer.valueOf(this.currentPage.intValue() - 1);
    }

}
