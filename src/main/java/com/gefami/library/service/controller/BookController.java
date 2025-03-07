package com.gefami.library.service.controller;

import com.gefami.library.service.model.response.book.GetBookResponse;
import com.gefami.library.service.model.response.book.GetBooksResponse;
import com.gefami.library.service.service.book.GetBookService;
import com.gefami.library.service.service.book.GetBooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/books")
@RequiredArgsConstructor
public class BookController {

    private final GetBookService getBookService;
    private final GetBooksService getBooksService;

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetBookResponse> getBookController(@PathVariable String id){
        var result = getBookService.execute(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetBooksResponse>> getBooksController(){
        var result = getBooksService.execute();
        return ResponseEntity.ok(result);
    }
}
