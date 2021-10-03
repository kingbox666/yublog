package com.yunblog.service;

import com.yunblog.entiy.Blog;
import com.yunblog.entiy.BlogQuery;
import com.yunblog.entiy.BlogTag;
import com.yunblog.entiy.SearchBlog;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: huang
 * @Date: 2021/09/08/22:38
 * @Description:
 */
public interface BlogService {
    //保存
    int saveBlog(Blog blog);
    //查出
    List<BlogQuery> getAllBlog();

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    Blog getBlogByID(Long id);

    Blog getBlogIndex(Long id);

    List<BlogQuery> listBlogIndex(BlogQuery blogQuery);

    List<BlogQuery> listBlogIndexHot(BlogQuery blogQuery);

    List<BlogQuery> listBlogType(Long typeId);

    List<BlogQuery> listBlogTag(Long tagId);

    List<BlogQuery> listBlogSearch(String query);
}
