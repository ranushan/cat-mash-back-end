package com.ranushan.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cat {
    @Id
    private String id; // Unique Identification
    private String url; // Url for cat image
    private Integer votes; // Number of votes

    // For JPA only, no use
    public Cat() {}
}
