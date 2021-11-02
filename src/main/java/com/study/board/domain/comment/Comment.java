package com.study.board.domain.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Comment {

    //comment
    private Long comment_idx;
    private String comment_content;
    private Long comment_like;
    private Long comment_dislike;
    private Date comment_create_date;
    private Date comment_update_date;

    //board
    private Long board_idx;

    //member
    private Long member_idx;
    private String member_name;


}
