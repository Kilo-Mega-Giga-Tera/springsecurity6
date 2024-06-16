package com.app.security.config;

//@Configuration
@Deprecated
public class SpringSecurityConfigBasic {

//    @Value("${url}")
//    private String url;

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
//        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");

//        http.securityContext((context) -> context.requireExplicitSave(false))
//                .sessionManagement((management) -> management.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .cors((cors) -> cors.configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowedOrigins(Collections.singletonList(url));
//                    config.setAllowedMethods(Collections.singletonList("*"));
//                    config.setAllowCredentials(true);
//                    config.setAllowedHeaders(Collections.singletonList("*"));
//                    config.setMaxAge(3600L);
//                    return config;
//                }))
//                .csrf((csrf) -> csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler).ignoringRequestMatchers("/contact", "/register")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new BeforeBasicAuthenticationFilter(), BasicAuthenticationFilter.class)
//                .addFilterAt(new AtBasicAuthenticationFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new AfterBasicAuthenticationFilter(), BasicAuthenticationFilter.class)
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/myAccount").hasRole("USER")
//                        .requestMatchers("/myBalance").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers("/myLoans").hasRole("USER")
//                        .requestMatchers("/myCards").hasRole("USER")
//                        .requestMatchers("/user").authenticated()
//                        .requestMatchers("/notices", "/contact", "/register").permitAll()
//                )
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());

//        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//}