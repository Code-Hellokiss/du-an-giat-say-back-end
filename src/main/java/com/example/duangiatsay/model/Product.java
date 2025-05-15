package com.example.duangiatsay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Tên sản phẩm (VD: Giặt ủi áo sơ mi)

    @Column(columnDefinition = "TEXT")
    private String description; // Mô tả chi tiết sản phẩm

    @Column(nullable = false)
    private Double basePrice;   // Giá cơ bản

    private String imageUrl;    // Link ảnh sản phẩm (upload Cloudinary)

    private Boolean expressAvailable = false; // Có hỗ trợ dịch vụ nhanh?

    private Double expressPrice; // Phụ thu dịch vụ nhanh

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;


    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
