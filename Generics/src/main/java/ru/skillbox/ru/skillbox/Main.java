package ru.skillbox;

import notification.EmailNotification;
import notification.PushNotification;
import notification.SmsNotification;
import notification_sender.EmailNotificationSender;
import notification_sender.PushNotificationSender;
import notification_sender.SmsNotificationSender;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println("EMAIL");

        EmailNotification email =new EmailNotification("Регистрация", Arrays.asList("user@example.com", "user2@example.com"), "Спасибо за регистрацию!");

        EmailNotification email2 =new EmailNotification("Регистрация", Arrays.asList("user3@example.com", "user4@example.com"), "Спасибо за регистрацию!");

        System.out.println("Subject: " + email.getSubject());
        System.out.println("Receivers: " + email.getRecipients());
        System.out.println("Message: " + email.getMessage());
        System.out.println('\t');
        System.out.println("Subject: " + email2.getSubject());
        System.out.println("Receivers: " + email2.getRecipients());
        System.out.println("Message: " + email2.getMessage());
        System.out.println('\t');
        new EmailNotificationSender().send(Arrays.asList(email, email2));

        System.out.println('\t');

        System.out.println("SMS");

        SmsNotification sms = new SmsNotification("Спасибо за регистрацию", Arrays.asList("+3374"));

        System.out.println("Receivers: " + sms.getPhoneNumbers());
        System.out.println("Message: " + sms.getMessage());
        System.out.println('\t');
        new SmsNotificationSender().send(sms);
        System.out.println('\t');

        System.out.println("PUSH");

        PushNotification push = new PushNotification("Успешная регистрация!", "Спасибо за регстрацию", "o.yanovich");

        System.out.println("Title: " + push.getTitle());
        System.out.println("Receivers: " + push.getAccount());
        System.out.println("Message: " + push.getMessage());
        System.out.println('\t');
        new PushNotificationSender().send(push);


    }
}
