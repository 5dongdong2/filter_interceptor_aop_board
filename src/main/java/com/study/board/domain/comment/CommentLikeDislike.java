package com.study.board.domain.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentLikeDislike {

    private Long comment_idx;
    private Long member_idx;
    private String comment_like_dislike;
}
