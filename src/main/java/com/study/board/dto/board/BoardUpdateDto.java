package com.study.board.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BoardUpdateDto {

    @NotNull
    private Long board_idx;

    @NotBlank
    private String board_title;

    @NotBlank
    private String board_content;
}
