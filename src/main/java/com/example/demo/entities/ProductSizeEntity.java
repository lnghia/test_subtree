package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductSizeEntity {
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @ManyToMany
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private SizeEntity size;

    private int inStock;
}
