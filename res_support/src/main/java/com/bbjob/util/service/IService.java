package com.bbjob.util.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<T> {

    T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);
    
    int delBy(T entity);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectByExample(Object example);
    
    public T selectByOne(T entity);

    //TODO 其他...
}