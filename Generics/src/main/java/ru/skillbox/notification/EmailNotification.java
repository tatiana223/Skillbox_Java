package notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EmailNotification implements ru.skillbox.notification.Notification {

    private String subject;
    private List<String> recipients;
    private String message;

    @Override
    public String formattedMessage() {
        return "<p>" + message + "</p>";
    }


}