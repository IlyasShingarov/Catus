//package com.ishingarov.catus.controller.handler;
//
//
//import com.ishingarov.catus.controller.GroupController;
//import com.ishingarov.catus.exception.ForbiddenException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import javax.validation.ValidationException;
//
//@ControllerAdvice(basePackageClasses = GroupController.class)
//public class GroupControllerExceptionHandler {
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(ForbiddenException.class)
//    public void handleForbidden() {
//
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ValidationException.class)
//    public void handleValidation() {
//
//    }
//}
//
