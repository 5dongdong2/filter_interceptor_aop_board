package com.study.board.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BoardLikeDislikeDto {

    @NotNull
    private Long boardIdx;

    @NotNull
    private Long memberIdx;
}
