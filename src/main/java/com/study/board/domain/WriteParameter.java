package com.study.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class WriteParameter {

    @NotBlank
    private String member_idx;

    @NotBlank
    private String board_title;

    @NotBlank
    private String board_content;
}
