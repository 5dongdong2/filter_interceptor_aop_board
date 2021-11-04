package com.study.board.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BoardLikeDislike {

    private Long board_idx;

    private Long member_idx;

    private String board_like_dislike;
}
