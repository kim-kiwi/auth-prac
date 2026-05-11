# Spring Security 인증/인가

## 프로그램 내용

 - GET / - 공개됨
 - GET /user - 로그인된 유저와 관리자만 접근 가능
 - GET /admin - 관리자만 접근 가능
 - GET /login - 공개됨
 - GET /logout - 공개됨

하드코딩으로 계정을 추가해 두었다.
```
user:
    password: 1234
    roles: USER
admin:
    password: 1234
    roles: ADMIN
```

## 과정

SecurityConfig 클래스에서 두 개의 Bean을 만들었다.

첫 번째 Bean에는 SecurityFilterChain을 등록하여 각 URL 별 접근 권한을 설정했고,

두 번째 Bean에는 UserDetailsService를 등록하여 Spring Security 로그인이 사용할 유저 정보를 전달했다.

우선 In Memory 방식을 사용해서 User를 저장했다.

첫 번쨰 `@Bean` 코드 간단한 주석:
```java
return http
    .authorizeHttpRequests(auth -> auth
            .requestMatchers("/").permitAll() // '/'로 오는 요청은 모두 접근 가능
            .requestMatchers("/user").authenticated() // '/user'로 오는 요청은 인증된 사용자만
            .requestMatchers("/admin").hasRole("ADMIN") // '/admin'으로 오는 요청은 인증된 사용자 중 ADMIN Role을 가진 사용자만
            .anyRequest().authenticated() // 모든 요청은 인증된 사용자만
    )
    .formLogin(form -> form.permitAll()) // 로그인 페이지는 모두 접근 가능
    .logout(logout -> logout.permitAll()) // 로그아웃 요청은 모두 접근 가능
    .build();
```
