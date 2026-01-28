package notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PushNotification implements ru.skillbox.notification.Notification {

    private final String message;
    private final String title;
    private final String account;

    @Override
    public String formattedMessage() {
        return "\\uD83D\\uDC4B " + message;
    }


}