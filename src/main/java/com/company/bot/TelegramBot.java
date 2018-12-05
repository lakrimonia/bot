package com.company.bot;

import com.company.Bot;
import com.company.Reader;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class TelegramBot extends TelegramLongPollingBot {
    private static final String botUserName = "adsg_bot";
    private static final String token = "626697956:AAHw8x9DRymWKaN9ZE0szWuxP264S1JdsTE";
    private static Reader reader = new Reader();
    private Bot bot = new Bot(reader.makeTopicContent(), reader.makeQuestionAnswer());
    private static Logger logger = Logger.getLogger(TelegramBot.class.getName());

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        String botAnswer = bot.handleMessage(message, chatId);
        sendMsg(chatId, botAnswer);
    }

    private synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.log(Level.ERROR, "Exeption:", e);
        }
    }

    private synchronized void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setResizeKeyboard(true);

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Бот, покажи список команд"));
        keyboardFirstRow.add(new KeyboardButton("Бот, пока"));
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public static void main(String[] args) {
        DOMConfigurator.configure("log.xml");
        ApiContextInitializer.init();
        Logger rootLogger = Logger.getLogger(TelegramBot.class.getName());

        TelegramBotsApi botApi = new TelegramBotsApi();
        try {
            botApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            rootLogger.log(Level.ERROR, "Exeption:", e);
        }
    }

}
