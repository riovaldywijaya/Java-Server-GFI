package com.gefami.library.service.service.book;

import com.gefami.library.service.model.response.book.GetBooksResponse;
import com.gefami.library.service.model.response.book.GetBooksResponseBuilder;
import com.gefami.library.service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBooksService {

    private final BookRepository bookRepository;

    public List<GetBooksResponse> execute() {
        var books = bookRepository.findAll();

        return books.stream()
                .map(b -> GetBooksResponseBuilder.builder()
                        .id(b.getId())
                        .name(b.getName())
                        .author(b.getAuthor())
                        .publishedYear(b.getPublishedYear())
                        .category(b.getCategory())
                        .isAvailable(b.getIsAvailable())
                        .build()).toList();
    }

}
