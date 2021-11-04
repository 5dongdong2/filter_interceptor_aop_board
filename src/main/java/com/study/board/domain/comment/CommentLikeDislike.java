package com.study.board.domain.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentLikeDislike {

    private Long commentIdx;
    private Long memberIdx;
    private String commentLikeDislike;
}
