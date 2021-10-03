package com.yunblog.mapper;

import com.yunblog.entiy.BlogTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: huang
 * @Date: 2021/09/08/21:01
 * @Description:
 */
@Mapper
@Repository
public interface BlogTagMapper {
    int insertBlogTag(@Param("blogId") Long blogId, @Param("tagId") Long tagId);

    int delete(@Param("blogId") Long blogId);

    List<Long> selectByBlogId(@Param("blogId") Long blogId);

}
