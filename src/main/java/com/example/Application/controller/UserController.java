package com.example.Application.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

//[
//        {
//        "name": "Ahmed Ali",
//        "cnic": "42101-1234567-1",
//        "email": "ahmed.ali@example.com",
//        "phoneNo": "+923001234567"
//        },
//        {
//        "name": "Ayesha Khan",
//        "cnic": "35202-9876543-2",
//        "email": "ayesha.khan@example.com",
//        "phoneNo": "+923219876543"
//        }
//        ]
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
    public List<HashMap<String, String>> create(@RequestBody HashMap<String,String> userRecord){
        list.add(userRecord);
        return list;
    }

    @PutMapping("update/{id}")
    public String update(@PathVariable int id, @RequestBody HashMap<String,String> userRecord ){
        list.set(id, userRecord);
        return "Student Record Updated: "+ userRecord;
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        list.remove(id);
        return "Student deleted";
    }

}
