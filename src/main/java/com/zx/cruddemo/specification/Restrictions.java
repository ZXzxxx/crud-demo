package com.zx.cruddemo.specification;


import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件构造器，用于创建查询的条件表达式
 */
public class Restrictions {

    //多表
    public static SimpleExpression in(String fieldName, List filedValue) {
        if(filedValue.isEmpty()) return null;
        return new SimpleExpression (fieldName, filedValue, Criterion.Operator.IN);
    }

    //等于
    public static SimpleExpression eq(String fieldName, Object filedValue) {
        if(StringUtils.isEmpty(filedValue))return null;
        return new SimpleExpression (fieldName, filedValue, Criterion.Operator.EQ);
    }

    //不等于
    public static SimpleExpression ne(String fieldName, Object filedValue) {
        if(StringUtils.isEmpty(filedValue))return null;
        return new SimpleExpression (fieldName, filedValue, Criterion.Operator.NE);
    }

    //模糊匹配
    public static SimpleExpression like(String fieldName, String filedValue) {
        if(StringUtils.isEmpty(filedValue))return null;
        return new SimpleExpression (fieldName, filedValue, Criterion.Operator.LIKE);
    }

    //大于
    public static SimpleExpression gt(String fieldName, Object filedValue) {
        if(StringUtils.isEmpty(filedValue))return null;
        return new SimpleExpression (fieldName, filedValue, Criterion.Operator.GT);
    }

    //小于
    public static SimpleExpression lt(String fieldName, Object filedValue) {
        if(StringUtils.isEmpty(filedValue))return null;
        return new SimpleExpression (fieldName, filedValue, Criterion.Operator.LT);
    }

    //大于等于
    public static SimpleExpression lte(String fieldName, Object filedValue) {
        if(StringUtils.isEmpty(filedValue))return null;
        return new SimpleExpression (fieldName, filedValue, Criterion.Operator.LTE);
    }

    //小于等于
    public static SimpleExpression gte(String fieldName, Object filedValue) {
        if(StringUtils.isEmpty(filedValue))return null;
        return new SimpleExpression (fieldName, filedValue, Criterion.Operator.GTE);
    }

    //Between
    public static LogicalExpression between(String fieldName, Object object1, Object object2)
    {
        if(object1 == null || object2 == null)
        {
            return null;
        }
        List<Criterion> ses = new ArrayList<>();
        ses.add(new SimpleExpression(fieldName,object1, Criterion.Operator.GTE));
        ses.add(new SimpleExpression(fieldName,object2, Criterion.Operator.LTE));
        return new LogicalExpression(ses, Criterion.Operator.AND);
    }

    //并且
    public static LogicalExpression and(List<Criterion> criterions){
        return new LogicalExpression(criterions, Criterion.Operator.AND);
    }

    //或者
    public static LogicalExpression or(List<Criterion> criterions){
        return new LogicalExpression(criterions, Criterion.Operator.OR);
    }

}
