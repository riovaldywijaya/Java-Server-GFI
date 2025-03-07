package com.gefami.library.service.repository;

import com.gefami.library.service.model.entity.Borrowing;
import com.gefami.library.service.util.enums.BorrowingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, String> {
    boolean existsByUserIdAndStatus(String userId, BorrowingStatus status);

    List<Borrowing> findAllByStatus(BorrowingStatus status);

    List<Borrowing> findAllByStatusAndDueDateBefore(BorrowingStatus status, LocalDateTime now);


}
