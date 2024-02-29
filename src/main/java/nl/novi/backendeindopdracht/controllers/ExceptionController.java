package nl.novi.backendeindopdracht.controllers;

import nl.novi.backendeindopdracht.exceptions.DuplicateException;
import nl.novi.backendeindopdracht.exceptions.RecordNotFoundException;
import nl.novi.backendeindopdracht.exceptions.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<Object> exception(IndexOutOfBoundsException exception) {
        return new ResponseEntity<>("This Id cannot be found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<String> exception(UsernameNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<String> exception(DuplicateException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }


}
