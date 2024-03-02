package org.example.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data  // lombok, automatically generate setter and getter when compiling
public class User {
    @NotNull
    private Integer id;

    private String username;
    @JsonIgnore // spring will ignore this field
    private String password;

    @NotEmpty
    @Pattern(regexp = "^\\${1,10}$")
    private String nickname;

    @NotEmpty
    @Email
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
