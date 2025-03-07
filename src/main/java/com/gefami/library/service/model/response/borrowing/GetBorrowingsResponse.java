package com.gefami.library.service.model.response.borrowing;

import com.gefami.library.service.util.enums.BorrowingStatus;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.time.LocalDateTime;

@RecordBuilder
public record GetBorrowingsResponse(
        String id,
        String bookName,
        String userName,
        BorrowingStatus status,
        LocalDateTime borrowingDate,
        LocalDateTime dueDate,
        LocalDateTime returnedDate
) {
}
