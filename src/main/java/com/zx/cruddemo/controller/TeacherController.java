package com.zx.cruddemo.controller;

import com.zx.cruddemo.domain.Teacher;
import com.zx.cruddemo.specification.Criteria;
import com.zx.cruddemo.specification.Criterion;
import com.zx.cruddemo.specification.Restrictions;
import com.zx.cruddemo.util.Helper;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TeacherController extends BaseController {

    //查看
    @RequestMapping(value = "/getAllTeacher", method = RequestMethod.GET)
    public Map<String, Object> getAllTeacher(@RequestParam(value = "pageNum") Integer page,
                                             @RequestParam(value = "pageSize") Integer size,
                                             @RequestParam(value = "sortKey") String sortKey,
                                             @RequestParam(value = "sortOrder") String sortOrder) {
        Map<String, Object> map = new HashMap<String, Object>();
        Helper.getSortInfos(sortKey, sortOrder);  //获取sort信息
        return Helper.getCurrentPageInfos(teacherService, "teacher", page, size);
    }

    //修改+增加
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.PUT)
    public Map<String, Object> updateTeacher(@RequestBody Teacher teacher,
                                             @RequestParam(value = "pageNum") Integer page,
                                             @RequestParam(value = "pageSize") Integer size) {
        teacherService.update(teacher, "teacher");
        return Helper.getCurrentPageInfos(teacherService, "teacher", page, size);
    }

    //删除
    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.DELETE)
    public Map<String, Object> deleteTeacher(@RequestBody Teacher teacher,
                                             @RequestParam(value = "pageNum") Integer page,
                                             @RequestParam(value = "pageSize") Integer size){
        teacherService.delete(teacher, "teacher");
        return Helper.getCurrentPageInfos(teacherService, "teacher", page, size);
    }

    //批量删除
    @RequestMapping(value = "/deleteTeachers", method = RequestMethod.DELETE)
    public Map<String, Object> deleteTeachers(@RequestBody List<Teacher> teachers,
                                        @RequestParam(value = "pageNum") Integer page,
                                        @RequestParam(value = "pageSize") Integer size){
        teacherService.deleteList(teachers, "teacher");
        return Helper.getCurrentPageInfos(teacherService, "teacher", page, size);
    }

    //动态查找
    @RequestMapping(value = "/getTeachersBySpeci",method = RequestMethod.GET)
    public Map<String, Object> getSchoolsBySpeci(@RequestParam(value = "pageNum") Integer page,
                                                 @RequestParam(value = "pageSize") Integer size,
                                                 @RequestParam(value = "nameValue") String nameValue,
                                                 @RequestParam(value = "schoolIds") String schoolIds) {

        List<Integer>  schoolArrIds = Helper.IDConverter(schoolIds);
        Criteria<Teacher> criteria = new Criteria<>();
        criteria.setOperator(Criterion.Operator.AND); //这里 设置条件是and还是or
        criteria.add(Restrictions.like("name", nameValue)).add(Restrictions.in("school", schoolArrIds));
        return Helper.getCriteriaPageInfos(teacherService, criteria, page, size);
    }
}
