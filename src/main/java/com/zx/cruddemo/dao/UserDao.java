package com.zx.cruddemo.dao;

import com.zx.cruddemo.domain.User;
import com.zx.cruddemo.jpaRepository.MyRepository;


public interface UserDao extends MyRepository<User, Integer> {

}