package com.app.security.config;

import com.app.security.model.Authority;
import com.app.security.model.Customer;
import com.app.security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SpringSecurityAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<Customer> customers = customerRepository.findByEmail(username);

        if (ObjectUtils.isEmpty(customers)) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        if (passwordEncoder.matches(password, customers.get(0).getPwd())) {
            return new UsernamePasswordAuthenticationToken(username, password, getAuthorities(customers.get(0).getAuthorities()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    private List<GrantedAuthority> getAuthorities(Set<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        authorities.forEach(authority -> grantedAuthorities.add(
                new SimpleGrantedAuthority(authority.getName())
        ));

        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
