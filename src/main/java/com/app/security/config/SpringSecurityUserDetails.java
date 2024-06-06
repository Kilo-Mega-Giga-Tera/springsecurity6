package com.app.security.config;

import com.app.security.model.Customer;
import com.app.security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpringSecurityUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = null;
        List<GrantedAuthority> authorityList = null;

        Optional<List<Customer>> customerList = Optional.ofNullable(customerRepository.findByEmail(username));

        if (customerList.isPresent()) {
            if (customerList.get().isEmpty()) throw new UsernameNotFoundException("User not found");

            for (Customer customer : customerList.get()) {
                username = customer.getEmail();
                password = customer.getPwd();

                authorityList = new ArrayList<>();
                authorityList.add(new SimpleGrantedAuthority(customer.getRole()));
            }
        }

        return new User(username, password, Objects.requireNonNull(authorityList));
    }

}
