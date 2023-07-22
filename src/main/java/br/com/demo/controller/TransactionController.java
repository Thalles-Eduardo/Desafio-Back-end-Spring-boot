package br.com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.demo.exceptions.personal_exceptions.AuthorizationException;
import br.com.demo.exceptions.personal_exceptions.BalanceInsufficientException;
import br.com.demo.exceptions.personal_exceptions.TransferAuthorizationException;
import br.com.demo.exceptions.personal_exceptions.UserNotFoundException;
import br.com.demo.model.TransactionModel;
import br.com.demo.model.UserModel;
import br.com.demo.service.ConsultaService;
import br.com.demo.service.TransactionService;
import br.com.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
public class TransactionController {

    private ConsultaService consultaService;
    private UserService userService;
    private TransactionService transactionService;


    public TransactionController(ConsultaService consultaService, UserService userService, TransactionService transactionService) {
        this.consultaService = consultaService;
        this.userService = userService;
        this.transactionService = transactionService;
    }    
   

    @PostMapping("/transaction")
    public ResponseEntity<String> transaction(@Valid @RequestBody TransactionModel transactionModel) throws Exception {
        Long payerId = transactionModel.getPayer();
        Long payeeId = transactionModel.getPayee();
        Double amount = transactionModel.getValue();

        if(consultaService.consultaAltorizacao().equals("Autorizado")) {
            // salvando o id no payer e do payee
            Optional<UserModel> payerOptional = userService.findById(payerId);
            Optional<UserModel> payeeOptional = userService.findById(payeeId);

            if (payerOptional.isPresent() && payeeOptional.isPresent()) {
                UserModel payer = payerOptional.get();
                UserModel payee = payeeOptional.get();

                String payerType = payer.getTypeUser() != null ? payer.getTypeUser().toLowerCase() : null;
                if (payerType != null && payerType.equals("comum")) {
                    if (payer.getValue() >= amount) {
                        // Realizar a transferência
                        payer.setValue(payer.getValue() - amount);
                        payee.setValue(payee.getValue() + amount);

                        // Salvar as alterações nos usuários e a transação
                        userService.save(payee);
                        userService.save(payee);
                        transactionService.save(transactionModel);
                        

                        if(consultaService.consultaNotify().equals("Success")){
                            String message = "Transferência autorizada e email enviado com sucesso.";
                            return ResponseEntity.ok().body("{\"message\": \"" + message + "\"}");
                        }else{
                            String message = "Transferência autorizada, mas houve um problema no envio do email.";
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"" + message + "\"}");
                        }

                        
                    } else {
                        // O pagador não tem saldo suficiente
                        // Exception personalizada BalanceInsufficientException
                        throw new BalanceInsufficientException();
                    }
                } else {
                    // Pagamento não autorizado entre lojista e comum
                    // Exception personalizada TransferAuthorization
                    throw new TransferAuthorizationException();
                }
            } else {
                // Usuário(s) não encontrado(s)
                // Exception personalizada UserNotFoundException
                throw new UserNotFoundException();
            }
        } else{
            // Tranferencia não autorizada
            // Exception personalizada AuthorizationException
            throw new AuthorizationException();
        }   
    }

    @GetMapping("/listTransactions")
    public List<TransactionModel> list() {
        return transactionService.list();
    }
    

}