package ru.thematdev.bot;
import com.company.Conversation;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LoggingMXBean;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


public class TelegramBot extends TelegramLongPollingBot{
	private static final String botUserName = "adsg_bot";
    private static final String token = "626697956:AAHw8x9DRymWKaN9ZE0szWuxP264S1JdsTE";
    private Logger log = Logger.getLogger(LoggingMXBean.class.getName());

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return botUserName;
	}

	@Override
	public void onUpdateReceived(Update update) {
		String message = update.getMessage().getText();
		sendMsg(update.getMessage().getChatId().toString(), message);
		
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return token;
	}
	
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }
	
	
	public static void main(String[] args) {
		ApiContextInitializer.init(); 
		TelegramBotsApi botapi = new TelegramBotsApi();
		try {
		    botapi.registerBot(new TelegramBot());
		} catch (TelegramApiException e) {
		    e.printStackTrace();
	    }
    }

}
