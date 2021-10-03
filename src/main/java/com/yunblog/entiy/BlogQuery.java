package com.yunblog.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * @Author: huang
 * @Date: 2021/09/08/22:46
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {
    private Long id;
    private String title;
    private Date updateTime;
    private String firstPicture;
    private String flag;
    private Integer view;
    private boolean recommend;
    private boolean published;
    private String description;
    private Long typeId;
    private Type type;
    private Long userId;
    private User user;
}
