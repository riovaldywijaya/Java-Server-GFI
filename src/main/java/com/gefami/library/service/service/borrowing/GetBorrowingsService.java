package com.gefami.library.service.service.borrowing;

import com.gefami.library.service.model.entity.Borrowing;
import com.gefami.library.service.model.response.borrowing.GetBorrowingsResponse;
import com.gefami.library.service.model.response.borrowing.GetBorrowingsResponseBuilder;
import com.gefami.library.service.repository.BorrowingRepository;
import com.gefami.library.service.util.constants.Constants;
import com.gefami.library.service.util.enums.BorrowingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBorrowingsService {

    private final BorrowingRepository borrowingRepository;

    public List<GetBorrowingsResponse> execute(String status) {
        List<Borrowing> borrowings;

        if (Constants.OVERDUE.equalsIgnoreCase(status)) {
            borrowings = borrowingRepository.findAllByStatusAndDueDateBefore(BorrowingStatus.BORROWED, LocalDateTime.now());
        } else {
            borrowings = borrowingRepository.findAllByStatus(BorrowingStatus.valueOf(status.toUpperCase()));
        }

        return borrowings.stream()
                .map(b -> GetBorrowingsResponseBuilder.builder()
                        .id(b.getId())
                        .bookName(b.getBook().getName())
                        .userName(b.getUser().getName())
                        .status(b.getStatus())
                        .borrowingDate(b.getBorrowingDate())
                        .returnedDate(b.getReturnedDate())
                        .dueDate(b.getDueDate())
                        .build()).toList();
    }

}
