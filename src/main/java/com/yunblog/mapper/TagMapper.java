package com.yunblog.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunblog.entiy.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Mapper
@Repository
public interface TagMapper {

    int saveTag(Tag tag);

    Tag getTag(@Param("id") Long id);

    List<Tag> PageTag();

    int updateTag(Tag tag);

    List<Tag> listTag1(@Param("ids") List<Long> ids);

    List<Tag> listTagTop(Page<Tag> page);

    void deleteTag(@Param("id") Long id);

    Tag getByName(@Param("name")String name);

}
