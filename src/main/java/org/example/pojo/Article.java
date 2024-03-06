package org.example.pojo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.example.anno.State;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
@Data
public class Article {
    private Integer id;//ID
    @NotEmpty
    @Pattern(regexp = "^\\S{1,100}$")
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    @URL
    private String coverImg;
    @State
    private String state;// published|draft
    @NotNull
    private Integer categoryId;//category ID
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
