package com.neysem.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "restaurant")
    private Manager manager;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurant",orphanRemoval = true)
    private List<Comment> comment;

    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;

}
