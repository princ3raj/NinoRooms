package nino.rooms.pgcompany.utils;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import nino.rooms.pgcompany.R;

public class MyMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyMessagingService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }

    public void showNotification(String title, String message) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "My Notifications")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_newicon)
                .setAutoCancel(true)
                .setContentText(message);

        Log.d(TAG, "showNotification: " + message);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, builder.build());
    }

}
