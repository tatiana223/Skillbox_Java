package notification_sender;

import notification.PushNotification;

import java.util.List;

public class PushNotificationSender implements ru.skillbox.notification_sender.NotificationSender<PushNotification> {
    @Override
    public void send(PushNotification notification) {
        System.out.println("Отправка Push: " + notification.getTitle() + " " + notification.formattedMessage());
    }

    @Override
    public void send(List<PushNotification> notifications) {
        notifications.forEach(notification -> send(notification));
    }
}
