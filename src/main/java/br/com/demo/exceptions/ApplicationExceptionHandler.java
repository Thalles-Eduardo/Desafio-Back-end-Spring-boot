package br.com.demo.exceptions;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import br.com.demo.exceptions.personal_exceptions.AuthorizationException;
import br.com.demo.exceptions.personal_exceptions.BalanceInsufficientException;
import br.com.demo.exceptions.personal_exceptions.TransferAuthorizationException;
import br.com.demo.exceptions.personal_exceptions.UserNotFoundException;



@ControllerAdvice(basePackages = "com.example.demo.controller")
public class ApplicationExceptionHandler {
    
    @ResponseBody
    @ExceptionHandler(BalanceInsufficientException.class)
    public ResponseEntity<MessageExceptionHandler> balanceInsufficientException(BalanceInsufficientException ex){
            MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Saldo insuficiente.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> UserNotFoundException(UserNotFoundException ex){
            MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Usuários não encontrados.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<MessageExceptionHandler> AuthorizationException(AuthorizationException ex){
            MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.UNAUTHORIZED.value(), "Tranferência não autorizada.");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

    @ResponseBody
    @ExceptionHandler(TransferAuthorizationException.class)
    public ResponseEntity<MessageExceptionHandler> TransferAuthorization(TransferAuthorizationException ex){
            MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.UNAUTHORIZED.value(), "Usuários do tipo lojista não podem fazer transações de pagamento.");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

    @ResponseBody
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<MessageExceptionHandler> HttpClientErrorException(HttpClientErrorException ex){
            MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Não foi possível extrair a mensagem do JSON.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> argumentNotValid(MethodArgumentNotValidException notValid){
          
        BindingResult result = notValid.getBindingResult();
        List<FieldError> fieldErros = result.getFieldErrors();

        StringBuilder sb = new StringBuilder("Os seguintes campos não podem ser nulos:");
        for(FieldError fieldError : fieldErros){
            sb.append(" |");
            sb.append(" -> ");
            sb.append(fieldError.getField());
            sb.append(" <- ");
            sb.append("| ");
        }
        

        MessageExceptionHandler error = new MessageExceptionHandler(
            new Date(), HttpStatus.BAD_REQUEST.value(), sb.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageExceptionHandler> UserDubplicate(DataIntegrityViolationException ex){
            MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.CONFLICT.value(), "Desculpe, não foi possível processar seus dados, confira os campos e tente novamente.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
}

