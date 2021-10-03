package com.yunblog.entiy;

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
public class Comment implements Serializable {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;

    private boolean adminComment;

    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;

    private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();

    private Comment parentComment;
}
