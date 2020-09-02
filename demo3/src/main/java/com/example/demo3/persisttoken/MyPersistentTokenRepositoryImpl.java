package com.example.demo3.persisttoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class MyPersistentTokenRepositoryImpl implements PersistentTokenRepository {

    @Autowired
    private JPAPersistentTokenRepository  repository;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        MyPersistentToken myPersistentToken = new MyPersistentToken();
        myPersistentToken.setSeries(persistentRememberMeToken.getSeries());
        myPersistentToken.setUsername(persistentRememberMeToken.getUsername());
        myPersistentToken.setTokenValue(persistentRememberMeToken.getTokenValue());
        myPersistentToken.setUser_last(persistentRememberMeToken.getDate());
        repository.save(myPersistentToken);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        MyPersistentToken myPersistentToken = repository.findBySeries(series);
        myPersistentToken.setUser_last(lastUsed);
        myPersistentToken.setTokenValue(tokenValue);
        repository.save(myPersistentToken);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        MyPersistentToken myPersistentToken = repository.findBySeries(series);
        PersistentRememberMeToken persistentRememberMeToken = new PersistentRememberMeToken(myPersistentToken.getUsername(), myPersistentToken.getSeries(), myPersistentToken.getTokenValue(), myPersistentToken.getUser_last());
        return persistentRememberMeToken;
    }

    @Override
    @Transactional
    public void removeUserTokens(String username) {
        repository.deleteByUsername(username);
    }
}
