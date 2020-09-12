package com.test.telegram.bots.common;

//Набор сообщения бота
public enum BotMessages {
    HELLO("Первое сообщения"),
    TWO("Второе сообщение"),
    THREE("Третье сообщение"),
    FOUR("Четвертое сообщение");

    private final String val;

    BotMessages(String str) {
        val = str;
    }

    public String getVal() {
        return val;
    }
}
