package com.gefami.library.service.controller;

import com.gefami.library.service.model.request.borrowing.AddBorrowingRequest;
import com.gefami.library.service.model.request.borrowing.UpdateBorrowingRequest;
import com.gefami.library.service.model.response.borrowing.AddBorrowingResponse;
import com.gefami.library.service.model.response.borrowing.GetBorrowingsResponse;
import com.gefami.library.service.model.response.borrowing.UpdateBorrowingResponse;
import com.gefami.library.service.service.borrowing.AddBorrowingService;
import com.gefami.library.service.service.borrowing.GetBorrowingsService;
import com.gefami.library.service.service.borrowing.UpdateBorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final AddBorrowingService addBorrowingService;
    private final GetBorrowingsService getBorrowingsService;
    private final UpdateBorrowingService updateBorrowingService;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AddBorrowingResponse> addBorrowing(
            @RequestBody @Validated AddBorrowingRequest addBorrowingRequest
    ) {
        var result = addBorrowingService.execute(addBorrowingRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetBorrowingsResponse>> getBorrowings(
            @RequestParam(required = false, defaultValue = "BORROWED") String status) {
        var result = getBorrowingsService.execute(status);
        return ResponseEntity.ok(result);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UpdateBorrowingResponse> updateBorrowing(@RequestBody UpdateBorrowingRequest request) {
        var result = updateBorrowingService.execute(request);
        return ResponseEntity.ok(result);
    }
}
