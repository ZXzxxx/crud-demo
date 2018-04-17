package com.zx.cruddemo.dao;

import com.zx.cruddemo.domain.Student;
import com.zx.cruddemo.jpaRepository.MyRepository;


public interface StudentDao extends MyRepository<Student, Integer> {

}