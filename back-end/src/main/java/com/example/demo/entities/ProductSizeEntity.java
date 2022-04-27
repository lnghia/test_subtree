package com.example.demo.entities;

import com.example.demo.entities.idclasses.ProductSizeId;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@IdClass(ProductSizeId.class)
public class ProductSizeEntity {
//    @Id
//    @Column(name = "product_id")
//    private int productId;
//
//    @Id
//    @Column(name = "size_id")
//    private int sizeId;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @Id
    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private SizeEntity size;

    private int inStock;
}
