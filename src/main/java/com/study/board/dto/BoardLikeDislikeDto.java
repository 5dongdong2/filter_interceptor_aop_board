package com.study.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class BoardLikeDislikeDto {

    @NotBlank
    private String board_idx;

    @NotBlank
    private String member_idx;
}
