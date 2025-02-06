package com.batchproject.housingservice.configs;

import com.batchproject.housingservice.configs.exceptions.customexceptions.*;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;

@ControllerAdvice
public class FeignClientErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            String errorMessage = "Error response came from rent service: " + response.status() + " " + response.reason();
            try {
                if (response.body() != null) {
                    errorMessage += ", Body: " + Util.toString(response.body().asReader());
                }
            } catch (IOException e) {
                errorMessage += ", Error reading body: " + e.getMessage();
            }
            System.out.println(errorMessage);
            switch (response.status()) {
                case 404:
                    return new ItemNotFoundException("Resource not found in rent-service");
                case 400:
                    return new BadDataException("Invalid request to rent-service");
                case 401:
                    return new UnAuthorizedError("Unauthorized access to rent-service");
                case 403:
                    return new PermissionError("Forbidden access to rent-service");
                case 422:
                    return new UnprocessableEntityException("Got unprocessable entity error from rent-microservice");
                default:
                    return new SystemException("Unknown Client error occurred in rent-service");
            }
        }
        return defaultErrorDecoder.decode(methodKey, Response.class.cast(response));
    }
}
