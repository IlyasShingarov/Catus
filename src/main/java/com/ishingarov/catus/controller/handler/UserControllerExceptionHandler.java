//package com.ishingarov.catus.controller.handler;
//
//import com.ishingarov.catus.controller.UserController;
//import com.ishingarov.catus.exception.DeletionErrorException;
//import com.ishingarov.catus.exception.UserNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ControllerAdvice(basePackageClasses = UserController.class)
//public class UserControllerExceptionHandler {
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(UserNotFoundException.class)
//    public void handleNotFound() {
//
//    }
//
//    @ResponseStatus(HttpStatus.NOT_MODIFIED)
//    @ExceptionHandler(DeletionErrorException.class)
//    public void handleDeletionError() {
//
//    }
//}
