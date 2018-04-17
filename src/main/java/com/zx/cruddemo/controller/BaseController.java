package com.zx.cruddemo.controller;

import com.zx.cruddemo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;

public abstract class BaseController {


    @Autowired
    protected ActorService actorService;
    @Autowired
    protected ResourceService resourceService;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected SchoolService schoolService;
    @Autowired
    protected StudentService studentService;
    @Autowired
    protected TeacherService teacherService;
    @Autowired
    protected UserRoleService userRoleService;
    @Autowired
    protected UserService userService;



}