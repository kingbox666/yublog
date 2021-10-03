package com.yunblog.mapper;

import com.github.pagehelper.Page;
import com.yunblog.entiy.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TypeMapper {

    int saveType(Type type);

    Type getType(@Param("id")Long id);

    List<Type> PageType();

    int updateType(Type type);

    void deleteType(@Param("id")Long id);

    Type getByName(@Param("name")String name);

    List<Type> listTypeTop(Page<Type> page);

    List<Type> getAllTypeAndBlog();
}
