package notification_sender;

import notification.EmailNotification;

import java.util.List;

public class EmailNotificationSender implements ru.skillbox.notification_sender.NotificationSender<EmailNotification> {

    @Override
    public void send(EmailNotification notification) {
        System.out.println("Отправка Email: " + notification.getSubject() + " " + notification.formattedMessage());
    }

    @Override
    public void send(List<EmailNotification> notifications) {
        notifications.forEach(notification -> this.send(notification));
    }
}
