package com.example.Application.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user/")
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
    public List<HashMap<String, String>> create(@RequestBody HashMap<String,String> userRecord){
        list.add(userRecord);
        return list;
    }

    @PutMapping("update/{id}")
    public String update(){

        return "Student Record Updated";
    }

    @DeleteMapping("delete/{id}")
    public String delete(){

        return "Student deleted";
    }

}
