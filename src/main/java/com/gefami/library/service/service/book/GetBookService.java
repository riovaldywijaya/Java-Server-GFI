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

    public GetBookResponse execute(String id){
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book Not Found"));

        return GetBookResponseBuilder.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .publishedYear(book.getPublishedYear())
                .category(book.getCategory())
                .isAvailable(book.getIsAvailable())
                .build();
    }

}
