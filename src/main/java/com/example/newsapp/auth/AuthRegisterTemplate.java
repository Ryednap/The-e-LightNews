package com.example.newsapp.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AuthRegisterTemplate {
    private String username;
    private String email;
    private String password;
}
