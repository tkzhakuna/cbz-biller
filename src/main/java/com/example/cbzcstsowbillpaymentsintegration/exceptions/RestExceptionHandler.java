package com.example.cbzcstsowbillpaymentsintegration.exceptions;


import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        String response = ex.getMessage().replaceAll("soap:", "");
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");

        JSONObject jsonObject = XML.toJSONObject(response);

        return new ResponseEntity<>(jsonObject, HttpStatus.EXPECTATION_FAILED);


    }
}
