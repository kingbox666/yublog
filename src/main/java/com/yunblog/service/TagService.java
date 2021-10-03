package com.yunblog.service;

import com.yunblog.entiy.Tag;
import com.yunblog.entiy.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagService {

    int saveTag(Tag tag);

    Tag getTag( Long id);

    List<Tag> PageTag();

    List<Tag> listTag(List<Long> ids);

    List<Tag> listTag(String ids);

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    int updateTag(Tag tag);

    void deleteTag(Long id);

    Tag getByName(String name);


}
