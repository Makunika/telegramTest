package com.test.telegram.bots;

import com.test.telegram.bots.common.BotMessages;
import com.test.telegram.data.Message;
import com.test.telegram.data.User;
import com.test.telegram.service.MessageService;
import com.test.telegram.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.util.Random;

@Component
public class TestBot extends TelegramLongPollingBot {

    private static final String TELEGRAM_TOKEN = "1384795327:AAHwIMCEjysJwxUayhlo5SJ-tDGXIVLYPkM";

    private static final String TELEGRAM_USERNAME = "Груша";

    private final MessageService messageService;
    private final UserService userService;

    public TestBot(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            //Создаение рандомного сообщения бота
            String botMessage = BotMessages.values()[new Random().nextInt(BotMessages.values().length - 1)].getVal();
            //Получения юзера
            User user = userService.findByUserTgIdOrSaveUser(
                    update.getMessage().getFrom().getFirstName(),
                    update.getMessage().getFrom().getId(),
                    update.getMessage().getChatId());
            // Создание сообщения для сохранения в бд
            Message message = new Message(update.getMessage().getText(), botMessage, user);
            // Сохранение сообщения в бд
            messageService.save(message);
            // Меняем у юзера Id последнего сообщения
            userService.changeMessageAt(user, message);

            //Отправка сообщения
            SendMessage sendMessage = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(botMessage);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    //Рассылка сообщения "date. Собрано countDomain доменов"
    public void domainMailing(String date, long countDomain) {
        //Идем по всем юзерам в бд (чтобы не перегружать память, можно использовать Pageable)
        for (User user: userService.findAll()) {
            //Отправка сообщения
            SendMessage sendMessage = new SendMessage()
                    .setChatId(user.getChatId())
                    .setText(date + ". Собрано " + countDomain + " доменов");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String getBotUsername() {
        return TELEGRAM_USERNAME;
    }

    @Override
    public String getBotToken() {
        return TELEGRAM_TOKEN;
    }
}
