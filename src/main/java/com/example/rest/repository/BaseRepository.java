package com.example.rest.repository;

import com.example.rest.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseRepository extends JpaRepository<Base, Long> {

    //Find files that contains searches characters
    List<Base> findByNameContaining(String contain);

}
