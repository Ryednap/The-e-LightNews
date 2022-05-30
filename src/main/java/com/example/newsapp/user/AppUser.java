package com.example.newsapp.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;


@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_id_generator", allocationSize = 1)
    @GeneratedValue(generator = "user_id_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = false, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role applicationRole;

    private LocalDateTime createdAt;

    private boolean enabled = false;

    private boolean isLocked = false;

    public AppUser(String username, String email, String password, LocalDateTime createdAt, Role applicationRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.applicationRole = applicationRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.applicationRole.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
