package org.example.pojo;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Article {
    private Integer id;//ID
    private String title;
    private String content;
    private String coverImg;
    private String state;// published|draft
    private Integer categoryId;//category ID
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
