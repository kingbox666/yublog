package com.yunblog.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: huang
 * @Date: 2021/09/09/15:14
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlog {
    private String title;
    private Long typeId;
    private Boolean recommend;
}
