package com.gefami.library.service.model.request.borrowing;

import com.gefami.library.service.util.enums.BorrowingStatus;
import io.soabase.recordbuilder.core.RecordBuilder;
import lombok.NonNull;

@RecordBuilder
public record UpdateBorrowingRequest(
        @NonNull
        String borrowingId,
        @NonNull
        BorrowingStatus status
) {
}
