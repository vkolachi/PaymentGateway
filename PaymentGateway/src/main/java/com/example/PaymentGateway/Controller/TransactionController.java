package com.example.PaymentGateway.Controller;

import com.example.PaymentGateway.Model.Transaction;
import com.example.PaymentGateway.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/addTransaction")
    public String addTransaction(@RequestParam("UserId") int UserId,@RequestParam("Amount") int Amount,@RequestParam("Status") String Status ){
        return transactionService.addTransaction(UserId,Amount,Status);
    }

    @DeleteMapping("/deleteFailed")
    public String deleteFailed(){
        return transactionService.deleteFailed();
    }





}
