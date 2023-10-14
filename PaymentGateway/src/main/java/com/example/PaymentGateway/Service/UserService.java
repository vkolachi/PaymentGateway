package com.example.PaymentGateway.Service;

import com.example.PaymentGateway.Enum.TransactionStatus;
import com.example.PaymentGateway.Model.Transaction;
import com.example.PaymentGateway.Model.User;
import com.example.PaymentGateway.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(User user) {
        user.setAccountNumber(String.valueOf(UUID.randomUUID()));
        userRepository.save(user);
        return "saved Successfully";

    }

    public int totalSuccessfull(int userId) {
        Optional<User> user=userRepository.findById(userId);
        User user1=user.get();
        int amount=0;
        for(Transaction transaction:user1.getTransactions()){
            if(transaction.getTransactionStatus().equals(TransactionStatus.SUCCESS)){
                amount+=transaction.getAmount();
            }
        }
        return amount;
    }

    public int maxRefundAmount() {
        List<User> users=userRepository.findAll();
        int amount=0,firstamount=0,id=0;
        for(User user:users){
            for(Transaction transaction: user.getTransactions()){
                if(transaction.getTransactionStatus().equals(TransactionStatus.FAILED)){
                    firstamount+=transaction.getAmount();
            }}
            if(amount<firstamount){
                id=user.getId();
                amount=firstamount;
            }


        }
        return id;
    }
}
