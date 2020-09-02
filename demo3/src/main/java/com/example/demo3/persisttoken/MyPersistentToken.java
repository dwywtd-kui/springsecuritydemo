package com.example.demo3.persisttoken;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_token")
public class MyPersistentToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private  String username;
    @Column(unique = true)
    private  String series;
    private  String tokenValue;
    private  Date user_last;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Date getUser_last() {
        return user_last;
    }

    public void setUser_last(Date user_last) {
        this.user_last = user_last;
    }
}
