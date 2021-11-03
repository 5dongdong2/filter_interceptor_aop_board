package com.study.board.domain.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Board {

    //board
    private Long board_idx;
    private String board_title;
    private String board_content;
    private Long board_like;
    private Long board_dislike;
    private Long board_views;
    private Date board_create_date;
    private Date board_update_date;

    //member
    private Long member_idx;
    private String member_name;
}