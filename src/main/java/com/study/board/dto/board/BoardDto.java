package com.study.board.dto.board;

import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
public class BoardDto {

    //board
    private Long boardIdx;
    private String boardTitle;
    private String boardContent;
    private Long boardLike;
    private Long boardDislike;
    private Long boardViews;
    private Date boardCreateDate;
    private Date boardUpdateDate;

    //member
    private Long memberIdx;
    private String memberName;
}
