package com.yunblog.mapper;

import com.yunblog.entiy.Blog;
import com.yunblog.entiy.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: huang
 * @Date: 2021/09/08/19:34
 * @Description:
 */
@Mapper
@Repository
public interface IndexMapper {
    Blog getBlogById(Long id);

    List<Blog> listBlog(BlogQuery blogQuery);


}
