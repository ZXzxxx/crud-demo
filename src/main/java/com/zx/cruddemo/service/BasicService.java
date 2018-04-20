package com.zx.cruddemo.service;

import com.zx.cruddemo.jpaRepository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Service
public abstract class BasicService<T extends Serializable,ID extends Serializable> {


@Autowired
MyRepository<T,ID> basicDao;

/**
 * https://www.cnblogs.com/rulian/p/6533109.html
 */
//传入的参数是要获取的实体类，返回结果可有可无。查找单个实体类的时候用这个传条件
//通过ID查找也用这个,用getOne时返回的是一个代理对象，如果查找的ID为空就会抛异常
public Optional<T> findOne(Example<T> t){
    return basicDao.findOne(t);
}

//单个动态条件查找，查询到某个实体类信息
public Optional<T> findOneByX(Specification<T> spec){
    return basicDao.findOne(spec);
}

//得到某实体类所有信息
//cacheable表示该方法参与缓存，value不可为空，key是区别用的
@Cacheable(value = "t.all", key = "#tName")
public List<T> findAllT(String tName) {
    return basicDao.findAll();
}

//得到可以分页的所有实体类信息
@Cacheable(value = "t.pageable.all", key = "#tName")
public Page<T> findAllPagebleT(Pageable pt, String tName) {
    return basicDao.findAll(pt);
}

//多个动态条件查找，查询到满足条件的多个实体类信息
public List<T> findTsByXX(Specification<T> specification) {
    return basicDao.findAll(specification);
}

//多个动态条件查找，查询到满足条件的多个实体类信息---分页显示
public Page<T> findPageTsByXX(Specification<T> specification, Pageable pageable) {
    return basicDao.findAll(specification, pageable);
}

//单个增加
@CacheEvict(value = {"t.all","t.pageable.all"}, beforeInvocation=true, allEntries=true)
public void add(T t) {
    this.basicDao.save(t);
}

//批量增加
public void addList(Iterable<T> ts) {
    this.basicDao.saveAll(ts);
}

//单个更新
@CacheEvict(value = {"t.all","t.pageable.all"}, beforeInvocation=true, allEntries=true)
public void update(T t) {
    this.basicDao.saveAndFlush(t);
}

//单个删除，传的是实体类
@CacheEvict(value = {"t.all","t.pageable.all"}, beforeInvocation=true, allEntries=true)
public void delete(T t) {
    this.basicDao.delete(t);
}

//批量删除,后台是生成一条SQL语句[之前那个是一条条删除]，效率更高些
@CacheEvict(value = {"t.all","t.pageable.all"}, beforeInvocation=true, allEntries=true)
public void deleteList(Iterable<T> ts) {
    this.basicDao.deleteInBatch(ts);
}


}