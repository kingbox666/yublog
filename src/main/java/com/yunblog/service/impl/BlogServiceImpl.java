package com.yunblog.service.impl;

import com.yunblog.entiy.*;
import com.yunblog.exception.NotFoundException;
import com.yunblog.mapper.BlogMapper;
import com.yunblog.mapper.BlogTagMapper;
import com.yunblog.mapper.TagMapper;
import com.yunblog.service.BlogService;
import com.yunblog.util.MarkdownUtils;
import com.yunblog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 * @Author: huang
 * @Date: 2021/09/08/22:53
 * @Description:
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogTagMapper blogTagMapper;

    @Autowired
    TagMapper tagMapper;

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setView(0);
        int save = blogMapper.saveBlog(blog);
        handlerBlogTag(blog.getId(), blog.getTages());
        return save;
    }

    void handlerBlogTag(Long blogId, List<Tag> tags) {
        for (Tag tag : tags) {
            blogTagMapper.insertBlogTag(blogId, tag.getId());
        }
    }

    @Override
    public List<BlogQuery> getAllBlog() {
        return blogMapper.getAllBlogQuery();
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        Blog blog1 = blogMapper.getBlogByID(blog.getId());
        BeanUtils.copyProperties(blog, blog1, MyBeanUtils.getNullPropertyNames(blog));
        blog1.setUpdateTime(new Date());

        int update = blogMapper.updateBlog(blog1);
        //删除所有标签（之后优化）
        blogTagMapper.delete(blog.getId());
        //插入新的标签
        handlerBlogTag(blog.getId(), blog.getTages());
        return update;
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogMapper.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    public Blog getBlogByID(Long id) {
        //System.out.println(id);
        Blog blog = blogMapper.getBlogByID(id);
        List<Long> longList = blogTagMapper.selectByBlogId(id);
        List<Tag> tags = tagMapper.listTag1(longList);
        String s = tagsToIds(tags);
        blog.setTagIds(s);
        return blog;
    }

    @Override
    public Blog getBlogIndex(Long id) {
        blogMapper.updateView(id);
        Blog blog = blogMapper.getBlogByID(id);
        if (blog == null) {
            throw new NotFoundException("博客不存在！");
        }
        Blog blog1 = new Blog();
        BeanUtils.copyProperties(blog, blog1);
        String content = blog1.getContent();
        blog1.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog1;
    }

    @Override
    public List<BlogQuery> listBlogIndex(BlogQuery blogQuery) {
        return blogMapper.listBlogIndex(blogQuery);
    }

    @Override
    public List<BlogQuery> listBlogIndexHot(BlogQuery blogQuery) {
        return blogMapper.listBlogIndexHot(blogQuery);
    }

    @Override
    public List<BlogQuery> listBlogType(Long typeId) {
        List<BlogQuery> list = blogMapper.listBlogType(typeId);
        return list;
    }

    @Override
    public List<BlogQuery> listBlogTag(Long tagId) {
        List<BlogQuery> list = blogMapper.listBlogTag(tagId);
        return list;
    }

    @Override
    public List<BlogQuery> listBlogSearch(String query) {
        List<BlogQuery> list = blogMapper.listBlogSearch(query);
        return list;
    }


    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return null;
        }
    }
}
