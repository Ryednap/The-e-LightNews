package com.example.newsapp.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AuthLoginTemplate {
    private String username;
    private String password;
}
