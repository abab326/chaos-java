package org.liushuxue.chaos.securiity;

import lombok.Data;
import org.liushuxue.chaos.entity.UserPo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class LoginUser implements UserDetails {
    private UserPo userPo;
    public LoginUser(UserPo userPo) {
        this.userPo = userPo;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return this.userPo.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userPo.getName();
    }
}
