package com.neysem.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager")
@SuperBuilder
public class Manager extends User {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id",referencedColumnName = "id")
    private Restaurant restaurant;
    
}