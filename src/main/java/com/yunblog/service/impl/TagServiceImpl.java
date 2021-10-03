package com.yunblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunblog.entiy.Tag;
import com.yunblog.entiy.Type;
import com.yunblog.mapper.TagMapper;
import com.yunblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }

    @Override
    public List<Tag> PageTag() {
        List<Tag> list = tagMapper.PageTag();
        return list;
    }

    @Override
    public List<Tag> listTag(List<Long> ids) {
        return tagMapper.listTag1(ids);
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagMapper.listTag1(convertToLong(ids));
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.PageTag();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Page<Tag> page = PageHelper.startPage(1, size);
        return tagMapper.listTagTop(page);
    }

    public List<Long> convertToLong(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                list.add(new Long(split[i]));
            }
        }

        return list;
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public Tag getByName(String name) {
        return tagMapper.getByName(name);
    }


}
