package com.yunblog.entiy;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
    private Long id;
    private String title;

    private String content;
    private String firstPicture;
    private String flag;
    private Integer view;
    private String description;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Date updateTime;


    private Type type;
    private Long typeId;
    private Long userId;
    private List<Tag> tages = new ArrayList<>();

    private String tagIds;

    private User user;

    private List<Comment> comments;
}
