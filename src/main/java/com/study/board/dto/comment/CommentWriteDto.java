package com.study.board.dto.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CommentWriteDto {

    @NotNull
    private Long boardIdx;

    @NotNull
    private Long memberIdx;

    @NotBlank
    private String commentContent;
}
