package com.test.telegram.service;


import com.test.telegram.data.Message;
import com.test.telegram.data.User;
import com.test.telegram.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findById(int id) {
        return userRepo.findById(id).orElseThrow();
    }

    // Если юзер уже существовал - то возвращает его, а если нет - то создает нового
    public User findByUserTgIdOrSaveUser(String username, int userTgId, long chatId) {

        User us = userRepo.findByUserTgId(userTgId);
        return us == null ? save(new User(username, chatId, userTgId)) : us;
    }

    // Поменять id последнего сообщения у юзера
    public User changeMessageAt(User user, Message message) {
        user.setLastMessageAt(message.getId());
        return save(user);
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }
}
