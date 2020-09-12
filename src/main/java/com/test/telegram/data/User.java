package com.test.telegram.data;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Column(name = "last_message_at")
    private Integer lastMessageAt;

    private Long chatId;

    private Integer userTgId;

    public User(String username, Long chatId, Integer userTgId) {
        this.username = username;
        this.chatId = chatId;
        this.userTgId = userTgId;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLastMessageAt() {
        return lastMessageAt;
    }

    public void setLastMessageAt(Integer lastMessageAt) {
        this.lastMessageAt = lastMessageAt;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Integer getUserTgId() {
        return userTgId;
    }

    public void setUserTgId(Integer userTgId) {
        this.userTgId = userTgId;
    }
}
