package com.example.moviepal.advice;



import com.example.moviepal.DTO.API;
import com.example.moviepal.exceptions.InvalidIdException;
import com.example.moviepal.exceptions.MovieAlreadyInTheListException;
import com.example.moviepal.exceptions.NoMovieWasFoundException;
import com.example.moviepal.exceptions.NoSuchFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdviseHandler {
    Logger logger = LoggerFactory.getLogger(ControllerAdviseHandler.class);
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<API> handleMethodArgument(MethodArgumentNotValidException methodArgumentNotValidException){
        String message=methodArgumentNotValidException.getFieldError().getDefaultMessage();
        logger.info("handleMethodArgument was triggered");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API(message,400));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<API> handleDataIntegrity(DataIntegrityViolationException dataIntegrityViolationException){
        String message=dataIntegrityViolationException.getRootCause().getMessage();
        logger.info("handleDataIntegrity was triggered");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API(message,400));
    }



    @ExceptionHandler(value = InvalidIdException.class)
    public ResponseEntity<API> handleDataIntegrity(InvalidIdException invalidIDException){
        String message=invalidIDException.getMessage();
        logger.info("handleDataIntegrity was triggered");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API(message,400));
    }
    @ExceptionHandler(value = NoSuchFoundException.class)
    public ResponseEntity<API> handleNoSuchFoundException(NoSuchFoundException noSuchFoundException){
        String message=noSuchFoundException.getMessage();
        logger.info("handleDataIntegrity was triggered");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API(message,400));
    }

    @ExceptionHandler(value = NoMovieWasFoundException.class)
    public ResponseEntity<API> NoMovieWasFoundException(NoMovieWasFoundException noMovieWasFoundException){
        String message=noMovieWasFoundException.getMessage();
        logger.info("NoMovieWasFoundException was triggered");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API(message,400));
    }
    @ExceptionHandler(value = MovieAlreadyInTheListException.class)
    public ResponseEntity<API> MovieAlreadyInTheListException(MovieAlreadyInTheListException movieAlreadyInTheListException){
        String message=movieAlreadyInTheListException.getMessage();
        logger.info("MovieAlreadyInTheListException was triggered");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API(message,400));
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<API> handleException(Exception exception){
        System.out.println(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new API("SERVER ERROR !",500));
    }
}