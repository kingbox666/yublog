package com.yunblog.mapper;

import com.yunblog.entiy.Blog;
import com.yunblog.entiy.BlogQuery;
import com.yunblog.entiy.SearchBlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: huang
 * @Date: 2021/09/08/14:43
 * @Description:
 */
@Mapper
@Repository
public interface BlogMapper {

    Blog getBlogByID(Long id);

    //保存
    int saveBlog(Blog blog);
    //查出
    List<Blog> getAllBlog();

    List<BlogQuery> getAllBlogQuery();

    int updateBlog(Blog blog);

    int deleteBlog(@Param("id") Long id);

    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    List<BlogQuery> listBlogIndex(BlogQuery blog);

    List<BlogQuery> listBlogIndexHot(BlogQuery blogQuery);

    List<BlogQuery> listBlogType(Long typeId);

    List<BlogQuery> listBlogTag(Long tagId);

    List<BlogQuery> listBlogSearch(String query);

    int updateView(Long id);

}
