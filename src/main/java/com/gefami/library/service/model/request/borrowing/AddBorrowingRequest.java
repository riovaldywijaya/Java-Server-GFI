package com.gefami.library.service.model.request.borrowing;

import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.NotNull;

@RecordBuilder
public record AddBorrowingRequest(
        @NotNull
        String bookId,
        @NotNull
        String userId
) {
}
