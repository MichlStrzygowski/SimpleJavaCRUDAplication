package com.restapi.cruddemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class crudDemoController
{
    @Autowired
    UserRepository userRepository;


    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userRepository.getAll();
    }

    @GetMapping("/{id}")
    public User GetSingleUsr(@PathVariable("id") int id){
        return userRepository.getUserById(id);
    }

    @PostMapping("/")
    public int addUser(@RequestBody List<User> Users){
        userRepository.addUser(Users);
        return 1;
    }

    @PutMapping("/{id}")
    public int updateUser(@PathVariable("id") int id, @RequestBody User updateUser){
        User user = userRepository.getUserById(id);
        if(user != null){
            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());
            user.setPhone(updateUser.getPhone());
            userRepository.updateUser(id, user);
            return 1;
        } else {
            return 0;
        }
    }
}
