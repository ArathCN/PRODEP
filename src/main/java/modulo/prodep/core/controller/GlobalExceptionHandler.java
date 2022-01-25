package modulo.prodep.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private Environment env;

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> handlerMultipart(MultipartException e, RedirectAttributes attributes){
        //attributes.addFlashAttribute("message", e.getCause().getMessage());
        String mensaje = e.getCause().getMessage();
        if(e instanceof MaxUploadSizeExceededException){
            mensaje = "El archivo excede el límite permitido: " + env.getProperty("spring.servlet.multipart.max-file-size");
        }
        
        ResponseEntity<String> response = new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        return response;
    }
}
