package com.quizgroup.examquiz.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponse {

    private Integer id;
    private String response;
}
