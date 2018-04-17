package com.zx.cruddemo.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件容器
 * @param <T>
 */
public class Criteria<T> implements Specification<T>{

    private List<Criterion> criterionList = new ArrayList<>();
    private Criterion.Operator operator; //条件符

    //将条件表达式加进来
    public Criteria<T> add(Criterion criterion){
        if(criterion!=null){
            criterionList.add(criterion);
        }
        return this; //返回这个类本身
    }

    public void setOperator(Criterion.Operator operator) {
        this.operator = operator;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
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
