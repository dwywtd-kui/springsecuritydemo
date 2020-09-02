package com.example.demo3.persisttoken;

import com.example.demo3.MyUserDetailService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAPersistentTokenRepository extends JpaRepository<MyPersistentToken,Long> {
    MyPersistentToken findBySeries(String series);
    void deleteByUsername(String username);
}
