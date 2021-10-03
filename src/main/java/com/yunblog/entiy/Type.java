package com.yunblog.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {

    private Long id;

    private String name;
    private String typeImg;
    private List<Blog> blogs;
}
