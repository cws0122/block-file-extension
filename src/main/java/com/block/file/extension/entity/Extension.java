package com.block.file.extension.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "extension")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Extension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean checked;

    @Column(nullable = false)
    private Boolean fixed;

    @Column(nullable = false)
    private Boolean deleted;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private String createdAt;
}
