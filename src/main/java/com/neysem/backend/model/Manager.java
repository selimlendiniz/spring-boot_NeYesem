package com.neysem.backend.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Manager extends User {


    @OneToOne
    @JoinColumn(name = "restaurants_id")
    private Restaurant restaurants;
}