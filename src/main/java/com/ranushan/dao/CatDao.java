package com.ranushan.dao;

import com.ranushan.domain.Cat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatDao extends JpaRepository<Cat, String> {

    // Custom query
    @Transactional
    @Modifying
    @Query("UPDATE Cat c SET c.votes = c.votes + 1 WHERE c.id = :id")
    void updateVoteCat(@Param("id") String id);

}
