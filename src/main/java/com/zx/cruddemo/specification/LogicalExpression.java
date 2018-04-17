package com.zx.cruddemo.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 对应的逻辑条件表达式    也就是and或者or
 */
public class LogicalExpression implements Criterion {

    private List<Criterion> criterionList = new ArrayList<>(); ////逻辑表达式中包含的基本条件表达式
    private Operator operator;  //and或or

    public LogicalExpression(List<Criterion> criterionList, Operator operator) {
        this.criterionList = criterionList;
        this.operator = operator;
    }

    @Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>(); //builder得到的结果都是Predicate类型的

        if(!criterionList.isEmpty()){
            for (Criterion c : criterionList) {
                predicates.add(c.toPredicate(root, query, builder));
            }
        }

        switch (operator) {
            case OR:
                return builder.or(predicates.toArray(new Predicate[predicates.size()]));
            case AND:
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));

            default:
                return null;
        }
    }

}
