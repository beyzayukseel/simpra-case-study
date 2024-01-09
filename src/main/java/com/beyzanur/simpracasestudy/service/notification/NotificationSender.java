package com.beyzanur.simpracasestudy.service.notification;

import com.beyzanur.simpracasestudy.model.NotificationModel;

public interface NotificationSender {
    void sendNotification(NotificationModel message);
}
