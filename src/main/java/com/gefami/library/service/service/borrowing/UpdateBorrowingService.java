package com.gefami.library.service.service.borrowing;

import com.gefami.library.service.model.request.borrowing.UpdateBorrowingRequest;
import com.gefami.library.service.model.response.borrowing.UpdateBorrowingResponse;
import com.gefami.library.service.model.response.borrowing.UpdateBorrowingResponseBuilder;
import com.gefami.library.service.repository.BookRepository;
import com.gefami.library.service.repository.BorrowingRepository;
import com.gefami.library.service.util.enums.BorrowingStatus;
import com.gefami.library.service.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateBorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;

    @Transactional
    public UpdateBorrowingResponse execute(UpdateBorrowingRequest updateBorrowingRequest) {
        if (!updateBorrowingRequest.status().equals(BorrowingStatus.RETURNED)) {
            throw new RuntimeException("Status are not allowed");
        }

        var borrowing = borrowingRepository.findById(updateBorrowingRequest.borrowingId())
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing  Not Found") );

        borrowing.setStatus(updateBorrowingRequest.status());
        borrowing.setReturnedDate(LocalDateTime.now());

        var book = borrowing.getBook();
        book.setIsAvailable(Boolean.TRUE);
        bookRepository.save(book);

        var updateBorrowing = borrowingRepository.save(borrowing);

        return UpdateBorrowingResponseBuilder.builder()
                .id(updateBorrowing.getId())
                .bookName(updateBorrowing.getBook().getName())
                .userName(updateBorrowing.getUser().getName())
                .status(updateBorrowing.getStatus())
                .borrowingDate(updateBorrowing.getBorrowingDate())
                .returnedDate(updateBorrowing.getReturnedDate())
                .dueDate(updateBorrowing.getDueDate())
                .build();
    }

}
