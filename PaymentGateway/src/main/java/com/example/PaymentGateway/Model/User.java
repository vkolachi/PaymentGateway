package com.example.PaymentGateway.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

   String userid;

   String Name;

   String Email;


   String  AccountNumber;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Transaction> transactions=new ArrayList<>();

}
