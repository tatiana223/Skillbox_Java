package notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SmsNotification implements ru.skillbox.notification.Notification {

    private String message;
    private List<String> phoneNumbers;

    @Override
    public String formattedMessage() {
        return message;
    }

}