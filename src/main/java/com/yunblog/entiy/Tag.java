package com.yunblog.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {

    private Long id;
    private String name;

    private List<Blog> blogs;
}
