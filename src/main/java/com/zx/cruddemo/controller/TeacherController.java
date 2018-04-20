package com.zx.cruddemo.controller;

import com.zx.cruddemo.domain.School;
import com.zx.cruddemo.domain.Teacher;
import com.zx.cruddemo.specification.Criteria;
import com.zx.cruddemo.specification.Criterion;
import com.zx.cruddemo.specification.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class TeacherController extends BaseController {

    public Map<String, Object> getCurrentPageTeachers(Integer page, Integer size){
        Map<String, Object> map = new HashMap<String, Object>();
        Page<Teacher> list = teacherService.findAllPagebleT(new PageRequest(page-1, size), "teacher");  //这个PageRequest咋回事?
        int total = teacherService.findAllT("teacher").size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }

    //查看
    @RequestMapping(value = "/getAllTeacher", method = RequestMethod.GET)
    public Map<String, Object> getAllTeacher(@RequestParam(value = "pageNum") Integer page,
                                             @RequestParam(value = "pageSize") Integer size) {
        Map<String, Object> map = new HashMap<String, Object>();
        return getCurrentPageTeachers(page, size);
    }

    //修改+增加
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.PUT)
    public Map<String, Object> updateTeacher(@RequestBody Teacher teacher,
                                             @RequestParam(value = "pageNum") Integer page,
                                             @RequestParam(value = "pageSize") Integer size) {
        teacherService.update(teacher);
        return getCurrentPageTeachers(page, size);
    }

    //删除
    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.DELETE)
    public Map<String, Object> deleteTeacher(@RequestBody Teacher teacher,
                                             @RequestParam(value = "pageNum") Integer page,
                                             @RequestParam(value = "pageSize") Integer size){
        teacherService.delete(teacher);
        return getCurrentPageTeachers(page, size);
    }

    //批量删除
    @RequestMapping(value = "/deleteTeachers", method = RequestMethod.DELETE)
    public Map<String, Object> deleteTeachers(@RequestBody List<Teacher> teachers,
                                        @RequestParam(value = "pageNum") Integer page,
                                        @RequestParam(value = "pageSize") Integer size){
        teacherService.deleteList(teachers);
        return getCurrentPageTeachers(page, size);
    }

    //动态查找
    @RequestMapping(value = "/getTeachersBySpeci",method = RequestMethod.GET)
    public Map<String, Object> getSchoolsBySpeci(@RequestParam(value = "pageNum") Integer page,
                                                 @RequestParam(value = "pageSize") Integer size,
                                                 @RequestParam(value = "nameValue") String nameValue,
                                                 @RequestParam(value = "schoolIds") String schoolIds) {

        String[] schoolStringIds = schoolIds.split(","); //用数组效率高
        List<Integer>  schoolArrIds = new ArrayList<Integer>();
        if(schoolStringIds[0] != ""){
            for (int i = 0; i<schoolStringIds.length; i++) {
                schoolArrIds.add(Integer.parseInt(schoolStringIds[i]));
            }
        }
        Criteria<Teacher> criteria = new Criteria<>();
        criteria.setOperator(Criterion.Operator.AND); //这里 设置条件是and还是or
        criteria.add(Restrictions.like("name", nameValue)).add(Restrictions.in("school", schoolArrIds));
        Map<String, Object> map = new HashMap<String, Object>();
        Page<Teacher> list = teacherService.findPageTsByXX(criteria, new PageRequest(page-1, size));  //这个PageRequest咋回事?
        int total = teacherService.findTsByXX(criteria).size();
        map.put("total", total);
        map.put("rows", list.getContent());
        return map;
    }
}
