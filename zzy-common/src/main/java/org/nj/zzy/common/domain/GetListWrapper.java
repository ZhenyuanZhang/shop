package org.nj.zzy.common.domain;

import java.util.List;

import org.nj.zzy.common.domain.PageBean;

import lombok.Data;

/**
 * 查询结果：业务数据、分页信息
 *
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Data
public class GetListWrapper<T> {
    private List<T> getVoList;
    private PageBean page;
}
