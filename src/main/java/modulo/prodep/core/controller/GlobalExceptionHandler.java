package modulo.prodep.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> handlerMultipart(MultipartException e, RedirectAttributes attributes){
        //attributes.addFlashAttribute("message", e.getCause().getMessage());
        return new ResponseEntity<>("WEEE"+e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }
}
