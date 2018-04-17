package com.zx.cruddemo.dao;

import com.zx.cruddemo.domain.Actor;
import com.zx.cruddemo.jpaRepository.MyRepository;
import org.springframework.data.jpa.repository.Query;


public interface ActorDao extends MyRepository<Actor, Integer> {

}