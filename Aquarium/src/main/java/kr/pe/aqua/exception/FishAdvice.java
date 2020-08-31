package kr.pe.aqua.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class FishNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(FishNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String fishNotFoundHandler(FishNotFoundException ex) {
    return ex.getMessage();
  }
}