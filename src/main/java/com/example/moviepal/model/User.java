package com.example.moviepal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor @RequiredArgsConstructor
@Setter @Getter
@Entity
public class User implements UserDetails {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Username is required")
    @Column(unique = true)
    @Size(min = 4,max = 15,message = "Your username must be more than 4 and less than 15")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 3,message = "Your password must be more than 3")
    @Size(max = 60,message = "Your password must less than 60")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}", message = "Password need to have a minimum eight characters, at least one letter and one number")

    private String password;
//    @NotEmpty(message ="role may not be empty" )
    @Pattern(regexp = "Admin|User",message = "Role must be in (ADMIN|USER)")
    private String role;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<WishListMovie> wishListMovie;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<WatchedListMovie> watchedListMovies;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<FavoriteListMovie> favoriteListMovies;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.getRole()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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
