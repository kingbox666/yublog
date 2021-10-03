package com.yunblog.service;

import com.yunblog.entiy.Type;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 *
 */
public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> PageType();

    List<Type>getAllTypeAndBlog();

    List<Type> listTypeTop(Integer size);

    int updateType( Type type);

    void deleteType(Long id);

    Type getByName(String name);

}
