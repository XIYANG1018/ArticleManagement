package org.example.pojo;



import lombok.Data;

import java.time.LocalDateTime;

@Data  // lombok, automatically generate setter and getter when compiling
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
