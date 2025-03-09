package com.gefami.library.service.util.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gefami.library.service.model.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        var errorResponse = new ErrorResponse(403, "You are not authorized");

        var objectMapper = new ObjectMapper();
        var jsonResponse = objectMapper.writeValueAsString(errorResponse);

        var out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}

