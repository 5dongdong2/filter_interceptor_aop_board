package com.study.board.domain.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class BoardWrite {

    private Long member_idx;

    private String board_title;

    private String board_content;
}
