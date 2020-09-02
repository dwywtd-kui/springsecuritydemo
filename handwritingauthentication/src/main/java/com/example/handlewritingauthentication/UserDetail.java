package com.example.handlewritingauthentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author hanliukui
 */
@Setter
@Getter
public class UserDetail {
    public static final String SESSION_KEY  = "my_session";

    private Long id;

    private String username;

    private String password;

    private Integer age;

    /**用户授权信息*/
    private Set<String> authorities;

    public UserDetail(Long id, String username, String password, Integer age, Set<String> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.authorities = authorities;
    }
}
