package org.nj.zzy.common.domain;

import java.util.Optional;

import lombok.ToString;

/**
 * 分页信息模型
 *
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@ToString()
public class PageBean {
    private static final Integer PAGE_SIZE = 30000;

    private Integer size;

    private Integer index;

    private Long count;

    public Integer getIndex() {
        return index;
    }

    public void setSize(String size) {
        this.size = Optional.ofNullable(size).map(Integer::valueOf).orElse(PAGE_SIZE);
    }

    public Integer getSize() {
        return size;
    }

    public void setIndex(String index) {
        this.index = Optional.ofNullable(index).map(Integer::valueOf).orElse(1);
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getOffset() {
        if (size != null && index != null) {
            return size * (index - 1);
        }
        return null;
    }
}
