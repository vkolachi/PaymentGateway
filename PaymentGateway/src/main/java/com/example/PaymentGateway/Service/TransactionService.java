package com.example.PaymentGateway.Service;

import com.example.PaymentGateway.Enum.TransactionStatus;
import com.example.PaymentGateway.Model.Refund;
import com.example.PaymentGateway.Model.Transaction;
import com.example.PaymentGateway.Model.User;
import com.example.PaymentGateway.Repository.RefundRepository;
import com.example.PaymentGateway.Repository.TransactionRepository;
import com.example.PaymentGateway.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    RefundRepository refundRepository;
    @Autowired
    UserRepository userRepository;

    public String addTransaction(int userId, int amount, String status) {
        Optional<User> user=userRepository.findById(userId);
        User user1=user.get();
        Transaction transaction=new Transaction();
        transaction.setUser(user1);
        transaction.setAmount(amount);
        transaction.setTransactionStatus(TransactionStatus.valueOf(status));

        if(TransactionStatus.valueOf(status).equals(TransactionStatus.FAILED)){
            Refund refund=new Refund();
            refund.setAmount(amount);
            refund.setTransaction(transaction);
            transaction.setRefund(refund);

            userRepository.save(user1);
            transactionRepository.save(transaction);
            return "saved succeful and refund raised";
        }

        userRepository.save(user1);
        transactionRepository.save(transaction);
        return "transaction fully successful";




    }

    public String deleteFailed() {
        List<Transaction> transactions=transactionRepository.findAll();
        for(Transaction transaction:transactions){
            if(transaction.getTransactionStatus().equals(TransactionStatus.FAILED)){
               Refund refund=transaction.getRefund();
                transactionRepository.delete(transaction);
                refundRepository.delete(refund);
            }
        }
        return "deleted Successfully";
    }
}
