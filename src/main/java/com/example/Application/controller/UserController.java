package com.example.Application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    List<HashMap<String, String>> list;

    public UserController(List<HashMap<String, String>> list) {
        this.list = list;
    }

    @GetMapping("getAll")
    public List<HashMap<String, String>> getAll(){
        return list ;
    }

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody HashMap<String,String> userRecord){

        String name= userRecord.get("name");
        String cnic= userRecord.get("cnic");
        String email= userRecord.get("email");
        String phone= userRecord.get("phone");

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
            return ResponseEntity.badRequest().body("Invalid request: CNIC format must be XXXXX-XXXXXXX-X");
        }
        if (!phone.matches(phoneRegex)) {
            return ResponseEntity.badRequest().body("Invalid request: Phone number must be a valid Pakistani mobile format");
        }
        if (!email.matches(emailRegex)) {
            return ResponseEntity.badRequest().body("Invalid request: Invalid email address format");
        }

        list.add(userRecord);
        return ResponseEntity.ok("Valid Request");
    }

    @PutMapping("update/{id}")
    public String update(@PathVariable int id, @RequestBody HashMap<String,String> userRecord ){
        if(id>=0 && id<=list.size()) {
            list.set(id, userRecord);
            return "Student Record Updated: "+ userRecord;
        }
        return "Id not Found";

    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        if(id>=0 && id<=list.size()) {
            list.remove(id);
            return "Student deleted";
        }
        return "Invalid id";
    }

}
