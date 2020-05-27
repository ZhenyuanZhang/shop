package org.nj.zzy.common.domain;

import java.util.List;

import org.nj.zzy.common.domain.PageBean;

import lombok.Data;

@Data
public class GetListWrapper<T> {
    private List<T> getVoList;
    private PageBean page;
}
