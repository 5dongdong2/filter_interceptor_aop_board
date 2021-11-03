package com.study.board.dto.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CommentLikeDislikeDto {

    @NotNull
    private Long comment_idx;

    @NotNull
    private Long member_idx;
}
