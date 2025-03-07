package com.gefami.library.service.service.borrowing;

import com.gefami.library.service.model.entity.Borrowing;
import com.gefami.library.service.model.request.borrowing.AddBorrowingRequest;
import com.gefami.library.service.model.response.borrowing.AddBorrowingResponse;
import com.gefami.library.service.model.response.borrowing.AddBorrowingResponseBuilder;
import com.gefami.library.service.repository.BookRepository;
import com.gefami.library.service.repository.BorrowingRepository;
import com.gefami.library.service.repository.UserRepository;
import com.gefami.library.service.util.enums.BorrowingStatus;
import com.gefami.library.service.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddBorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public AddBorrowingResponse execute(AddBorrowingRequest addBorrowingRequest) {
        var bookId = addBorrowingRequest.bookId();
        var userId = addBorrowingRequest.userId();

        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book Not Found."));

        if (!book.getIsAvailable()) {
            throw new RuntimeException("Book is Borrowed.");
        }

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (borrowingRepository.existsByUserIdAndStatus(userId, BorrowingStatus.BORROWED)) {
            throw new RuntimeException("User still has an active borrowing.");
        }

        book.setIsAvailable(Boolean.FALSE);
        bookRepository.save(book);

        var borrowingDate = LocalDateTime.now();
        var dueDate = borrowingDate.plusSeconds(7 * 86400);

        var borrowing = borrowingRepository.save(
                Borrowing.builder()
                        .book(book)
                        .user(user)
                        .status(BorrowingStatus.BORROWED)
                        .borrowingDate(borrowingDate)
                        .dueDate(dueDate)
                        .build()
        );

        return AddBorrowingResponseBuilder.builder()
                .id(borrowing.getId())
                .bookName(book.getName())
                .userName(user.getName())
                .borrowingDate(borrowing.getBorrowingDate())
                .dueDate(borrowing.getDueDate())
                .status(borrowing.getStatus())
                .build();
    }

}
