package com.example.Application.controller;

import com.example.Application.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/")
public class UserController {

    List<User> users;

    public UserController(List<User> list) {
        this.users = list;
    }

    @GetMapping("getAll")
    public List<User> getAll(){
        return users ;
    }

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody User userRecord){

        String name= userRecord.getName();
        String cnic= userRecord.getCnic();
        String email= userRecord.getEmail();
        String phone= userRecord.getPhone();

        if (name == null || name.trim().isEmpty() ||
                cnic == null || cnic.trim().isEmpty() ||
                phone == null || phone.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid request: Missing required fields");
        }

        // Regex Patterns
        String cnicRegex = "^\\d{5}-\\d{7}-\\d{1}$";
        String phoneRegex = "^((\\+92)|(0))3\\d{9}$";
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (!cnic.matches(cnicRegex)) {
            return ResponseEntity.badRequest().body("Invalid request: CNIC format must be +92/03000000000");
        }
        if (!phone.matches(phoneRegex)) {
            return ResponseEntity.badRequest().body("Invalid request: Phone number must be a valid Pakistani mobile format");
        }
        if (!email.matches(emailRegex)) {
            return ResponseEntity.badRequest().body("Invalid request: Invalid email address format");
        }

        users.add(userRecord);
        return ResponseEntity.ok("Valid Request");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody User userRecord ){
        if(id>=0 && id<=users.size()) {
            users.set(id, userRecord);
            return ResponseEntity.ok("Student Record Updated: "+ userRecord);
        }
        return ResponseEntity.badRequest().body("Id not Found");

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if(id>=0 && id<=users.size()) {
            users.remove(id);
            return ResponseEntity.ok("Student Deleted");
        }
        return ResponseEntity.badRequest().body("Id not Found");
    }

}
