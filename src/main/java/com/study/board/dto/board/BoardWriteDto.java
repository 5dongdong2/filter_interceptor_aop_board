package com.study.board.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BoardWriteDto {

    @NotNull
    private Long memberIdx;

    @NotBlank
    private String boardTitle;

    @NotBlank
    private String boardContent;
}
