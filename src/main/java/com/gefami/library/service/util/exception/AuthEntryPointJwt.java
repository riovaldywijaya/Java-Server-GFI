package com.gefami.library.service.util.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gefami.library.service.model.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class AuthEntryPointJwt  implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        var errorResponse = new ErrorResponse(401, "Invalid Token");

        var objectMapper = new ObjectMapper();
        var jsonResponse = objectMapper.writeValueAsString(errorResponse);

        var out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}