package br.com.quarkus.exception;

import br.com.quarkus.exception.ErrorResponse;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;

public class ExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> mapException(Exception error) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), INTERNAL_SERVER_ERROR.getStatusCode(), INTERNAL_SERVER_ERROR.getReasonPhrase(), List.of(error.getMessage()));
        return RestResponse.status(INTERNAL_SERVER_ERROR, errorResponse);
    }

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> mapException(BadRequestException error) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), BAD_REQUEST.getStatusCode(), BAD_REQUEST.getReasonPhrase(), List.of(error.getMessage()));
        return RestResponse.status(BAD_REQUEST, errorResponse);
    }

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> mapException(NotFoundException error) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), NOT_FOUND.getStatusCode(), NOT_FOUND.getReasonPhrase(), List.of(error.getMessage()));
        return RestResponse.status(NOT_FOUND, errorResponse);
    }
}
