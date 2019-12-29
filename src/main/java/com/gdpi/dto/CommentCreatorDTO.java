package com.gdpi.dto;

import lombok.Data;

@Data
public class CommentCreatorDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
