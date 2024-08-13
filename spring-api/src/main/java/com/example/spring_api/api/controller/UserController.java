package com.example.spring_api.api.controller;

// import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;

import com.example.spring_api.api.model.User;
import com.example.spring_api.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return (User) user.get();
        }
        return null;
    }

    // @GetMapping("/admin")
    // public Admin getUser(@RequestParam Integer id){
    //     Optional<Admin> admin = userService.getUser(id);
    //     if (admin.isPresent()){
    //         return (Admin) admin.get();
    //     }
    // }

    // PR DIRUMAH//

    // // CREATE
    // @PostMapping("/admin")
    // public User createUser(@RequestBody User user) {
    // return userService.saveUser(user);
    // }

    // // READ (Get All Users)
    // @GetMapping
    // public List<User> getAllUsers() {
    // return userService.getAllUsers();
    // }

    // // READ (Get User by ID)
    // @GetMapping("/{id}")
    // public User getUser(@PathVariable Integer id) {
    // Optional<User> user = userService.getUser(id);
    // return user.orElse(null);
    // }

    // // UPDATE
    // @PutMapping("/admin/{id}")
    // public User updateUser(@PathVariable Integer id, @RequestBody User
    // updatedUser) {
    // Optional<User> user = userService.getUser(id);
    // if (user.isPresent()) {
    // User existingUser = user.get();
    // existingUser.setName(updatedUser.getName());
    // existingUser.setEmail(updatedUser.getEmail());
    // // Lakukan pembaruan lainnya sesuai dengan properti yang ada
    // return userService.saveUser(existingUser);
    // }
    // return null; // Atau Anda bisa mengembalikan response yang sesuai jika user
    // tidak ditemukan
    // }

    // // DELETE
    // @DeleteMapping("/admin/{id}")
    // public void deleteUser(@PathVariable Integer id) {
    // userService.deleteUser(id);
    // }
}
