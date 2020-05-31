package org.nj.zzy.common.constant;

/**
 * 操作类型
 *
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
public enum OperationType {
    NULL,
    CREATE,
    UPDATE,
    DELETE,
    SELECT;

    private String lower;

    public String lower() {
        return name();
    }
}
