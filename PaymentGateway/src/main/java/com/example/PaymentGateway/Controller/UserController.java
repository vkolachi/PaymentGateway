package com.example.PaymentGateway.Controller;

import com.example.PaymentGateway.Model.User;
import com.example.PaymentGateway.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/totalSuccessfull")
    public int totalSuccessfull(@RequestParam("UserId") int UserId){
        return userService.totalSuccessfull(UserId);
    }

    @GetMapping("/maxRefundAmount")
    public int maxRefundAmount(){
        return userService.maxRefundAmount();
    }


}
