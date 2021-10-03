package com.yunblog.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTag implements Serializable {
    private Long id;

    private Long blogId;

    private Integer tagsId;
}
