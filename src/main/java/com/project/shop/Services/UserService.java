package com.project.shop.Services;

import com.project.shop.Entities.Product;
import com.project.shop.Entities.User;
import com.project.shop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByName(userName);
    }

    public Boolean deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception ex){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean updateUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception ex){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
