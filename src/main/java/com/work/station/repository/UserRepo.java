package com.work.station.repository;

import com.work.station.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {
    User findByNameAndIsDeletedIsFalse(String name);

    @Query("select u from User u where u.isDeleted = false ")
    Page<User> findAll(Pageable pageable);

    @Query("select u from User u where u.isDeleted = false ")
    List<User> findAll();

    @Modifying
    @Query("UPDATE User u SET u.isDeleted = true WHERE u.name =:name")
    void markAsDeleted(String name);
}
