package com.example.moviepal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor @RequiredArgsConstructor
@Data
@Entity
public class User implements UserDetails {
    @Id
    private Integer id;
    @NotNull(message ="username may not be empty" )
    private String username;
    @NotNull(message ="password may not be empty" )
    private String password;
    private String role;


    @OneToOne(mappedBy = "user")
    private WishListMovie wishListMovie;

//    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "wishList_id", referencedColumnName = "ListId")
//    private WishListMovie wishListMovie;

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
