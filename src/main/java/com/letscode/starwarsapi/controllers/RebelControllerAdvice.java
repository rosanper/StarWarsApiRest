package com.letscode.starwarsapi.controllers;

import com.letscode.starwarsapi.exceptions.ExceptionMessage;
import com.letscode.starwarsapi.exceptions.NoFindRebelException;
import com.letscode.starwarsapi.exceptions.NoRebelsException;
import com.letscode.starwarsapi.exceptions.TradeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class RebelControllerAdvice {

    @ExceptionHandler(NoRebelsException.class)
    public ResponseEntity<ExceptionMessage> noRebel(NoRebelsException e){
        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Banco de dados vazio")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(NoFindRebelException.class)
    public ResponseEntity<ExceptionMessage> noFindRebel(NoFindRebelException e){
        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Este rebelde não existe.")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessage> noEnumName(IllegalArgumentException e){
        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Este item não consta no banco (escolha entre Agua, Municao, Arma ou Comida")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionMessage> incompatibleTypeVar(HttpMessageNotReadableException e){
        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Tipo da variável não compatível com a esperado")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessage> incompatibleTypeVar(MethodArgumentNotValidException e){
        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Dado enviado não compatível com a esperado")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(TradeException.class)
    public ResponseEntity<ExceptionMessage> tradeError(TradeException e){
        ExceptionMessage errorMessage = ExceptionMessage.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("A transferencia de itens não pode ser realizada!")
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
