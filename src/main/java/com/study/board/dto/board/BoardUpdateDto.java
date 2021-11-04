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
    private Long boardIdx;

    @NotBlank
    private String boardTitle;

    @NotBlank
    private String boardContent;
}
