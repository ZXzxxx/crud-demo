package com.zx.cruddemo.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 简单查询条件的接口
 */
public interface Criterion {
    //定义区别查询条件的元素
    public enum Operator {
        IN, EQ, NE, LIKE, GT, LT, GTE, LTE, AND, OR
    }

    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder);
}
