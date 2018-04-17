package com.zx.cruddemo.specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对应的基本的条件表达式
 */
public class SimpleExpression implements Criterion{

    private String filedName; //属性名
    private Object filedValue; //对应值
    private Operator operator; //条件符

    public SimpleExpression(String filedName, Object filedValue, Operator operator) {
        this.filedName = filedName;
        this.filedValue = filedValue;
        this.operator = operator;
    }

    public String getFiledName() {
        return filedName;
    }

    public Object getFiledValue() {
        return filedValue;
    }

    public Operator getOperator() {
        return operator;
    }

    @SuppressWarnings({"rawtypes", "unchecked"}) //禁止显示警告
    public Predicate toPredicate(Root<?> root, javax.persistence.criteria.CriteriaQuery<?> query, CriteriaBuilder builder) {

        Path expression = root.get(filedName); //root参数是用来对应实体类信息的，.get得到的是结果类型是Path

        switch (operator) {
            case IN: //多表
                return builder.in(root.join(filedName).get("id")).value(filedValue);
            case EQ: //等于
                return builder.equal(expression, filedValue);
            case NE: //不等于
                return builder.notEqual(expression, filedValue);
            case LIKE: //等于
                return builder.like((Expression<String>) expression, "%" + filedValue + "%");
            case LT: //小于
                return builder.lessThan(expression, (Comparable) filedValue);
            case GT: //大于
                return builder.greaterThan(expression, (Comparable) filedValue);
            case LTE: //小于等于
                return builder.lessThanOrEqualTo(expression, (Comparable) filedValue);
            case GTE: //大于等于
                return builder.greaterThanOrEqualTo(expression, (Comparable) filedValue);
            default:
                return null;
        }
    }

}
