package com.gefami.library.service.service.book;

import com.gefami.library.service.model.response.book.GetBookResponse;
import com.gefami.library.service.model.response.book.GetBookResponseBuilder;
import com.gefami.library.service.repository.BookRepository;
import com.gefami.library.service.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBookService {

    private final BookRepository bookRepository;

    public GetBookResponse execute(String id) {
        return bookRepository.findById(id)
                .map(b ->
                        GetBookResponseBuilder.builder()
                                .id(b.getId())
                                .name(b.getName())
                                .author(b.getAuthor())
                                .publishedYear(b.getPublishedYear())
                                .category(b.getCategory())
                                .isAvailable(b.getIsAvailable())
                                .build())
                .orElseThrow(() -> new ResourceNotFoundException("Book Not Found"));
    }

}
