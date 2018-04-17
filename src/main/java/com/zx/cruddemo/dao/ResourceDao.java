package com.zx.cruddemo.dao;

import com.zx.cruddemo.domain.Resource;
import com.zx.cruddemo.jpaRepository.MyRepository;


public interface ResourceDao extends MyRepository<Resource, Integer> {

}