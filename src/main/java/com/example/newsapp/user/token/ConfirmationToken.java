package com.example.newsapp.user.token;

import com.example.newsapp.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class ConfirmationToken{

    @Id
    @SequenceGenerator(name = "token_id_generator", allocationSize = 1)
    @GeneratedValue(generator = "token_id_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiryAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiryAt, AppUser user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiryAt = expiryAt;
        this.user = user;
    }
}
