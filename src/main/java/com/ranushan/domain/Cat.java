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
    private String id;
    private String url;
    private Integer votes;

    // for JPA only, no use
    public Cat() {}
}
