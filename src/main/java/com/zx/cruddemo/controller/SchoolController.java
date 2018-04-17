package com.zx.cruddemo.controller;

import com.zx.cruddemo.domain.School;
import com.zx.cruddemo.specification.Criteria;
import com.zx.cruddemo.specification.Criterion;
import com.zx.cruddemo.specification.Restrictions;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SchoolController extends BaseController {

    //查询所有
    @RequestMapping(value = "/getAllSchool", method = RequestMethod.GET)
    public List<School> getAllSchool() {
        List<School> schools = schoolService.findAllT();
        return schools;
    }

    //根据id查找
    @RequestMapping(value = "/getOneSchoolById",method = RequestMethod.GET)
    public Optional getSchoolByID() {
        School school = new School();
        school.setId(4);
        Example<School> schoolEx = Example.of(school);
        return schoolService.findOne(schoolEx);
    }

    //根据description查找
    @RequestMapping(value = "/getOneSchoolByDes",method = RequestMethod.GET)
    public School getSchoolByDes() {
        School school = new School();
        school.setDescription("教学办");
        Example<School> schoolEx = Example.of(school);
        School schoolResult = null;
        if(schoolService.findOne(schoolEx).isPresent()){  //不为空时,才能做某事
            schoolResult = schoolService.findOne(schoolEx).get();
            System.out.println("学校不为空时，才能打印");
        }
        return schoolResult;
    }

    //根据id和description查找
    @RequestMapping(value = "/getSchoolBySpeci",method = RequestMethod.GET)
    public List<School> getSchoolsBySpeci() {
        Criteria<School> criteria = new Criteria<>();
        criteria.setOperator(Criterion.Operator.OR);
        criteria.add(Restrictions.eq("id",2)).add(Restrictions.like("description", "室"));
        return schoolService.findTsByXX(criteria);
    }
}
