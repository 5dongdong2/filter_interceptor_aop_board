package com.study.board.dto.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CommentUpdateDto {

    @NotBlank
    private String comment_content;
}
