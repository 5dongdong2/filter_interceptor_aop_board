package com.study.board.domain.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardLikeDislike {

    private Long board_idx;

    private Long member_idx;

    private String board_like_dislike;
}
