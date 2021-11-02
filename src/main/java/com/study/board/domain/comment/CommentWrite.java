package com.study.board.domain.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentWrite {

    private Long board_idx;

    private Long member_idx;

    private String comment_content;
}
