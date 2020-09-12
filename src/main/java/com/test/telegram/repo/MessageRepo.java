package com.test.telegram.repo;

import com.test.telegram.data.Message;
import com.test.telegram.data.User;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    Message findByUser(User user);
}
