package notification_sender;

import notification.SmsNotification;

import java.util.List;

public class SmsNotificationSender implements ru.skillbox.notification_sender.NotificationSender<SmsNotification> {


    @Override
    public void send(SmsNotification notification) {
        System.out.println("Отправка SMS: " + notification.formattedMessage());
    }

    @Override
    public void send(List<SmsNotification> notifications) {
        notifications.forEach(notification -> this.send(notification));
    }
}
