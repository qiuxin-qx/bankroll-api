package com.goldbao.bankroll.model;

import java.util.List;

/**
 * 分页支持的list包装器
 */
public class PageableList<T> {

    private int index;
    
    private int size;

    private long count;

    private List<T> list;

    public PageableList(int i, int s) {
        this.index = i;
        this.size = s;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPageCount() {
        // 其实size是不能小于1的
        if (count == 0 || size <= 0) return 0;

        if (count % size == 0) {
            return (count / size);
        } else {
            return (count / size) + 1;
        }
    }
}
