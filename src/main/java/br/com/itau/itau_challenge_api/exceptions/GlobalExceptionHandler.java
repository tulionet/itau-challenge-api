package br.com.itau.itau_challenge_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(TransacaoInvalidaException.class)
    public ResponseEntity<ErroResponse> handleTransacaoInvalida(TransacaoInvalidaException ex) {
        ErroResponse erro = new ErroResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(erro, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleTransacaoSemCorpo(Exception ex) {
        ErroResponse erro = new ErroResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String mensagem = String.format("O parâmetro '%s' tem um valor inválido: '%s'. Certifique-se de que ele é do tipo UUID.",
                ex.getName(), ex.getValue());
        ErroResponse erro = new ErroResponse(mensagem, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErroResponse> handleNoResourceFoundException(NoResourceFoundException ex) {
        String mensagem = "Coloque um Path válido!";
        ErroResponse erro = new ErroResponse(mensagem, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String mensagem = "Ocorreu algum erro na validação dos dados.";
        ErroResponse erro = new ErroResponse(mensagem, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }



}
