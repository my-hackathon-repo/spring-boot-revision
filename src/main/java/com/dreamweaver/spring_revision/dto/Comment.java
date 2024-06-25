package com.dreamweaver.spring_revision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private int id;
    private String comment;
}
