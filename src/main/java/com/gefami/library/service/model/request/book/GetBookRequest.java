package com.gefami.library.service.model.request.book;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record GetBookRequest(
        String id
) {
}
