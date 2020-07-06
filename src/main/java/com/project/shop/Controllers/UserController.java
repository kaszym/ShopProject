package com.project.shop.Controllers;


import com.project.shop.Entities.User;
import com.project.shop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add", produces = "application/json")
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/byUserName/{userName}", produces = "application/json")
    public ResponseEntity<Optional<User>> getUserByName(@PathVariable String userName) {

        Optional<User> user = userService.findByUserName(userName);

        if (user.isPresent())
            return new ResponseEntity<>(user, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/byId/{id}", produces = "application/json")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);

        if(user.isPresent())
            return ResponseEntity.ok(user.get());

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);

        Boolean isDeleted = false;

        if (user.isPresent())
            isDeleted = userService.deleteById(id);
        if (isDeleted)
            return ResponseEntity.ok("Deleted User, id: " + id);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> searchedUser = userService.findById(id);

        if(!searchedUser.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        User currentUser = searchedUser.get();

        currentUser.setUserName(user.getUserName());
        currentUser.setPassword(user.getPassword());
        currentUser.setUserStatus(user.getUserStatus());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());

        Boolean isUpdated = userService.updateUser(currentUser);

        if(isUpdated){

            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
