package com.gefami.library.service.model.entity;

import com.gefami.library.service.util.helper.NanoIdGenerator;
import com.gefami.library.service.util.enums.BorrowingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(generator = "nanoid")
    @GenericGenerator(name = "nanoid", type = NanoIdGenerator.class)
    private String id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BorrowingStatus status;

    @Column(nullable = false)
    private LocalDateTime borrowingDate;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    private LocalDateTime returnedDate;
}
