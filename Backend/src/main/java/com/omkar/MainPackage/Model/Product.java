package com.omkar.MainPackage.Model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private int prodId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private long price;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "releasedate")
    private Date releaseDate;

    @Column(name = "imagename")
    private String imageName;

    @Column(name = "image")
    @Lob
    private byte[] image;

    @Column(name = "imagetype")
    private String imageType;




}
