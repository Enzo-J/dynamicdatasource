package com.szwg.dynamicdatasource.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class SingleValOper {
    public final static String OPER_EQ = "eq";
    public final static String OPER_NE = "ne";
    public final static String OPER_GT = "gt";
    public final static String OPER_GE = "ge";
    public final static String OPER_LT = "lt";
    public final static String OPER_LIKE = "like";
    public final static String OPER_NOT_LIKE = "notLike";
    public final static String OPER_IS_NULL = "isNull";
    public final static String OPER_IS_NOT_NULL = "isNotNull";
    public final static String OPER_EXIST = "exist";
    public final static String OPER_NOT_EXIST = "notExist";
    public final static String OPER_IN = "in";
    public final static String OPER_BETWEEN = "between";
    public final static String OPER_NOT_BETWEEN = "notBetween";

    public final static String CONCAT_AND = "and";
    public final static String CONCAT_OR = "or";
    public final static String CONCAT_GROUP_BY = "groupBy";
    public final static String CONCAT_HAVING = "having";
    public final static String CONCAT_ORDER_BY = "orderBy";
    public final static String CONCAT_LIMIT = "limit";

    private String col;
    private String oper;
    private String val;
    private String concat;
    private List<SingleValOper> innerOper;
}
