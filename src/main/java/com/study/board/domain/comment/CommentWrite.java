package com.study.board.domain.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentWrite {

    private Long boardIdx;

    private Long memberIdx;

    private String commentContent;
}
