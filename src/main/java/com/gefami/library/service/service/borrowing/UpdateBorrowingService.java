package com.gefami.library.service.service.borrowing;

import com.gefami.library.service.model.entity.Borrowing;
import com.gefami.library.service.model.request.borrowing.UpdateBorrowingRequest;
import com.gefami.library.service.model.response.borrowing.UpdateBorrowingResponse;
import com.gefami.library.service.model.response.borrowing.UpdateBorrowingResponseBuilder;
import com.gefami.library.service.repository.BookRepository;
import com.gefami.library.service.repository.BorrowingRepository;
import com.gefami.library.service.util.enums.BorrowingStatus;
import com.gefami.library.service.util.exception.BusinessValidationException;
import com.gefami.library.service.util.exception.ResourceNotFoundException;
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
        validateStatus(updateBorrowingRequest);

        var borrowing = updateBorrowing(updateBorrowingRequest.borrowingId());

        updateBook(borrowing);

        return UpdateBorrowingResponseBuilder.builder()
                .id(borrowing.getId())
                .bookName(borrowing.getBook().getName())
                .userName(borrowing.getUser().getName())
                .status(borrowing.getStatus())
                .borrowingDate(borrowing.getBorrowingDate())
                .returnedDate(borrowing.getReturnedDate())
                .dueDate(borrowing.getDueDate())
                .build();
    }

    private void updateBook(Borrowing borrowing) {
        var book = borrowing.getBook();
        book.setIsAvailable(Boolean.TRUE);
        bookRepository.save(book);
    }

    private Borrowing updateBorrowing(String borrowingId){
        var borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing Not Found") );
        borrowing.setStatus(BorrowingStatus.RETURNED);
        borrowing.setReturnedDate(LocalDateTime.now());
        return borrowingRepository.save(borrowing);
    }

    private void validateStatus(UpdateBorrowingRequest updateBorrowingRequest) {
        if (!updateBorrowingRequest.status().equalsIgnoreCase(BorrowingStatus.RETURNED.name())) {
            throw new BusinessValidationException("Status are not allowed");
        }
    }

}
