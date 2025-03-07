package com.gefami.library.service.model.response.book;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record GetBookResponse(
        String id,
        String name,
        String author,
        String category,
        Integer publishedYear,
        Boolean isAvailable
) {
}
