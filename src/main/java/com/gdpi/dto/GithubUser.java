package com.gdpi.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String dio;
    private String avatar_url;

}
