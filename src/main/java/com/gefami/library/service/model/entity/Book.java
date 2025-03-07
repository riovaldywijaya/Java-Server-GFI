package com.gefami.library.service.model.entity;

import com.gefami.library.service.helper.NanoIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(generator = "nanoid")
    @GenericGenerator(name = "nanoid", type = NanoIdGenerator.class)
    private String id;

    @Column(nullable = false)
    private String name;

    private String author;

    private String category;

    private Integer publishedYear;

    private Boolean isAvailable;
}

