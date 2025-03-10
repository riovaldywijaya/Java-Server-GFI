package com.gefami.library.service.service.borrowing;

import com.gefami.library.service.model.entity.Book;
import com.gefami.library.service.model.entity.Borrowing;
import com.gefami.library.service.model.entity.User;
import com.gefami.library.service.model.request.borrowing.AddBorrowingRequest;
import com.gefami.library.service.model.response.borrowing.AddBorrowingResponse;
import com.gefami.library.service.model.response.borrowing.AddBorrowingResponseBuilder;
import com.gefami.library.service.repository.BookRepository;
import com.gefami.library.service.repository.BorrowingRepository;
import com.gefami.library.service.repository.UserRepository;
import com.gefami.library.service.util.constants.Constants;
import com.gefami.library.service.util.enums.BorrowingStatus;
import com.gefami.library.service.util.exception.BusinessValidationException;
import com.gefami.library.service.util.exception.ResourceNotFoundException;
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
        var book = validateBook(addBorrowingRequest.bookId());
        var user = validateUser(addBorrowingRequest.userId());

        book.setIsAvailable(Boolean.FALSE);
        bookRepository.save(book);

        var borrowing = createBorrowing(book, user);

        return AddBorrowingResponseBuilder.builder()
                .id(borrowing.getId())
                .bookName(book.getName())
                .userName(user.getName())
                .borrowingDate(borrowing.getBorrowingDate())
                .dueDate(borrowing.getDueDate())
                .status(borrowing.getStatus())
                .build();
    }

    private Borrowing createBorrowing(Book book, User user) {
        var borrowingDate = LocalDateTime.now();
        var dueDate = borrowingDate.plusSeconds(Constants.DUE_DATE);

        return borrowingRepository.save(
                Borrowing.builder()
                        .book(book)
                        .user(user)
                        .status(BorrowingStatus.BORROWED)
                        .borrowingDate(borrowingDate)
                        .dueDate(dueDate)
                        .build()
        );
    }

    private User validateUser(String userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (borrowingRepository.existsByUserIdAndStatus(userId, BorrowingStatus.BORROWED)) {
            throw new BusinessValidationException("User still has an active borrowing.");
        }
        return user;
    }

    private Book validateBook(String bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book Not Found."));

        if (!book.getIsAvailable()) {
            throw new BusinessValidationException("Book is Borrowed.");
        }
        return book;
    }

}
