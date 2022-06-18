import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class keepPostureBot extends TelegramLongPollingBot {
    public long chat_id;
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chat_id));
            message.setText("Этот бот рандомно отправляет тебе уведомление держать осанку");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            new TimTask().run();
        }
    }

    class TimTask extends TimerTask {
        public void run() {
            Timer timer = new Timer();
            int delay = (1 + new Random().nextInt(4)) * 100;
            timer.schedule(new TimTask(), delay);
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chat_id));
            message.setText("Держи осанку");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }
}