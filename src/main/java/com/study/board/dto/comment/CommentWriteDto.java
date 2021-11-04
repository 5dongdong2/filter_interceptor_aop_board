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
    private Long board_idx;

    @NotNull
    private Long member_idx;

    @NotBlank
    private String comment_content;
}
