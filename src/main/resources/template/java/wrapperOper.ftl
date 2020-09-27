package ${project.dtoDotDir};

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

@ApiModel(description = "对mybatisplus的wrapper进行简单封装")
@Data
public class WrapperOper {
    public final static String OPER_EQ = "eq";
    public final static String OPER_NE = "ne";
    public final static String OPER_GT = "gt";
    public final static String OPER_GE = "ge";
    public final static String OPER_LT = "lt";
    public final static String OPER_LE = "le";
    public final static String OPER_BETWEEN = "between";
    public final static String OPER_NOT_BETWEEN = "notBetween";

    public final static String OPER_LIKE_LEFT = "likeLeft";
    public final static String OPER_LIKE_RIGHT = "likeRight";
    public final static String OPER_LIKE = "like";
    public final static String OPER_NOT_LIKE = "notLike";
    public final static String OPER_IS_NULL = "isNull";
    public final static String OPER_IS_NOT_NULL = "isNotNull";
    public final static String OPER_EXIST = "exist";
    public final static String OPER_NOT_EXIST = "notExist";
    public final static String OPER_IN = "in";
    public final static String OPER_NOT_IN = "notIn";
    public final static String OPER_IN_SQL = "inSql";
    public final static String OPER_NOT_IN_SQL = "notInSql";
    public final static String OPER_GROUP_BY = "groupBy";
    public final static String OPER_HAVING = "having";
    public final static String OPER_ORDER_BY_ASC = "orderByAsc";
    public final static String OPER_ORDER_BY_DESC = "orderByDesc";
    public final static String OPER_LIMIT = "limit";

    public final static String CONCAT_AND = "and";
    public final static String CONCAT_OR = "or";

    /**
     * 列,或者函数
     */
    @ApiModelProperty(value = "列,或者函数",  name = "field", example = "id")
    protected String field;
    /**
     * 一个操作
     */
    @ApiModelProperty(value = "操作,sql中=->eq,!=->ne,>->gt,>=->ge,<->lt,<=->le,between...and...->between," +
            "not between...and....->notBetween,like '%xxx%'->like,not like '%xxx%'-> notLike,like '%xxx'->likeLeft,like'xxx%'->likeRight,is null->isNull,isNotNull->is not null," +
            "exist->exist,not exist->notExist,in->in,not in->notIn,in (select xxxxx)->inSql,not in (select xxxxx)->notInSql,group by->groupBy,having->having," +
            "order by xx asc->orderByAsc,order by xx desc->orderByDesc,limit->limit",  name = "field", example = "eq")
    protected String oper;
    /**
     * 第一个值
     */
    @ApiModelProperty(value = "对于操作，如=,!=等,指定该字段如1；对于in、group by操作,指定[\"field1\",\"field2\"],groupBy指定为真实列名，对于between(如a between 2 and 5)操作第一个值指定这里为2", example = "1")
    protected Object sVal;
    /**
     * 第二个值
     */
    @ApiModelProperty(value = "对于between(如a between 2 and 5)操作第二个值指定这里为5", example = "5")
    private Object eVal;
    /**
     * 连接符，与上一个操作连接，
     */
    @ApiModelProperty(value = "与上一个操作的连接符，对于sql(a=1 and b=2),field=\"b\",\"oper\"=\"eq\",\"sVal\"=2,\"concat\"=\"and\";对于sql(a=1 or (b=2)),\"concat\"=\"or\",innerOper={field=\"b\",\"oper\"=\"eq\",\"sVal\"=2}", example = "and")
    protected String concat;
    /**
     * 括号内操作
     */
    @ApiModelProperty(value = "括号内的操作,sql a=1 and (b=2),List中第一个元素不要指定concat", example = "[{\"field\":\"b\",\"oper\":\"eq\",\"sVal\":2}]")
    protected List<WrapperOper> innerOper;

    public AbstractWrapper innerOperExtract(final AbstractWrapper aw, Map<String, String> fieldToColumn) {
        if (innerOper == null || innerOper.isEmpty()) {
            return aw;
        }
        aw.nested(wrapper -> {
            for (WrapperOper wrapperOper : innerOper) {
                wrapperOper.extract((AbstractWrapper) wrapper, fieldToColumn);
            }
        });
        return aw;
    }

    /**
     * 解析操作
     * @param aw
     * @return
     */
    public AbstractWrapper operExtract(AbstractWrapper aw, Map<String, String> fieldToColumn) {
        String field = fieldToColumn.get(this.field);
        field = field == null ? this.field : field;
        switch (oper) {
            case OPER_EQ:
                aw.eq(true, field, sVal);
                break;
            case OPER_NE:
                aw.ne(true, field, sVal);
                break;
            case OPER_GT:
                aw.gt(true, field, sVal);
                break;
            case OPER_GE:
                aw.ge(true, field, sVal);
                break;
            case OPER_LT:
                aw.gt(true, field, sVal);
                break;
            case OPER_LE:
                aw.le(true, field, sVal);
                break;
            case OPER_LIKE_LEFT:
                aw.likeLeft(true, field, sVal);
                break;
            case OPER_LIKE_RIGHT:
                aw.likeRight(true, field, sVal);
                break;
            case OPER_NOT_LIKE:
                aw.notLike(true, field, sVal);
                break;
            case OPER_IS_NULL:
                aw.isNull(true, field);
                break;
            case OPER_IS_NOT_NULL:
                aw.isNotNull(true, field);
                break;
            case OPER_EXIST:
                aw.exists(true, field);
                break;
            case OPER_NOT_EXIST:
                aw.notExists(true, field);
                break;
            case OPER_IN:
                aw.in(true, field, JSONObject.parseArray(sVal.toString()).toArray());
                break;
            case OPER_NOT_IN:
                aw.notIn(true, JSONObject.parseArray(sVal.toString()));
                break;
            case OPER_IN_SQL:
                aw.in(true, field, new String[]{sVal.toString()});
                break;
            case OPER_NOT_IN_SQL:
                aw.notInSql(true, field, sVal.toString());
                break;
            case OPER_GROUP_BY:
                aw.groupBy(true, JSONObject.parseArray(sVal.toString()).toArray(new String[0]));
                break;
            case OPER_HAVING:
                aw.having(true, field, JSONObject.parseArray(sVal.toString()));
                break;
            case OPER_ORDER_BY_ASC:
                aw.orderByAsc(field);
                break;
            case OPER_ORDER_BY_DESC:
                aw.orderByDesc(field);
                break;
            case OPER_LIMIT:
                aw.last(true, "limit " + sVal);
                break;
            case OPER_BETWEEN:
                aw.between(field, sVal, eVal);
                break;
            case OPER_NOT_BETWEEN:
                aw.notBetween(true, sVal, eVal);
                break;
            default:;
        }
        return aw;
    }

    /**
     * 将实体解析成sql,如果innerOper和field、oper、val都指定了，只取innerOper
     * @param aw
     * @return
     */
    public AbstractWrapper extract(AbstractWrapper aw, Map<String, String> fieldToColumn) {
        if (StringUtils.isNotBlank(concat)) {
            if (CONCAT_OR.equals(concat)) {
                aw = aw.or(true);
            }
        }
        if (innerOper != null && !innerOper.isEmpty()) {
            aw = innerOperExtract(aw, fieldToColumn);
            return aw;
        }

        if (StringUtils.isNotBlank(oper)) {
            aw = operExtract(aw, fieldToColumn);
        }
        return aw;
    }
}
