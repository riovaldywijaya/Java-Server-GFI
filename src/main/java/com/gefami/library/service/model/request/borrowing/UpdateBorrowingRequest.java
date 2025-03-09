package com.gefami.library.service.model.request.borrowing;

import com.gefami.library.service.util.enums.BorrowingStatus;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.NotNull;

@RecordBuilder
public record UpdateBorrowingRequest(
        @NotNull
        String borrowingId,
        @NotNull
        String status
) {
}
