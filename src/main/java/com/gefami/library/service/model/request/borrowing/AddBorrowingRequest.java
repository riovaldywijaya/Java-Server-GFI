package com.gefami.library.service.model.request.borrowing;

import io.soabase.recordbuilder.core.RecordBuilder;
import lombok.NonNull;

@RecordBuilder
public record AddBorrowingRequest(
        @NonNull
        String bookId,
        @NonNull
        String userId //hrusnya dari token
) {
}
