package com.warehouse.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private double capacity;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Product> products;
}
