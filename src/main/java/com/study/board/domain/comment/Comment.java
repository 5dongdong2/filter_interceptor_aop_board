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
    private Long commentIdx;
    private String commentContent;
    private Long commentLike;
    private Long commentDislike;
    private Date commentCreateDate;
    private Date commentUpdateDate;

    //board
    private Long boardIdx;

    //member
    private Long memberIdx;
    private String memberName;


}
