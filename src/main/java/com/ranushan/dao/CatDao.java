package com.ranushan.dao;

import com.ranushan.domain.Cat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CatDao extends JpaRepository<Cat, String> {

    /**
     * Connecting to the Database
     * Custom query => Update votes by ID
     * @param id Identification for cat picture
     */
    @Transactional
    @Modifying
    @Query("UPDATE Cat c SET c.votes = c.votes + 1 WHERE c.id = :id")
    void updateVoteCat(@Param("id") String id);

}
