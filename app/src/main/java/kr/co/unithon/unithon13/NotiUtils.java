package kr.co.unithon.unithon13;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

/**
 * Created by daehyun on 16. 2. 14..
 */
public class NotiUtils {
    public static void Notify(Context context, String station, long time, long travelTmMilliseconds) {
        String s_time = String.valueOf(travelTmMilliseconds/(1000*60));

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Resources res = context.getResources();
        Intent notificationIntent = new Intent(context, NotificationSomething.class);
        notificationIntent.putExtra("notificationId", 9999); //전달할 값

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("현재 역은 " + station + "역입니다.")
                .setContentText("" + s_time + "분후 도착 예정입니다.")
                .setTicker("상태바 한줄 메시지")
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.logo))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setShowWhen(true)
                .setUsesChronometer(true)
                .setWhen(time)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
        Notification  n = builder.build();
        nm.notify(1234, n);
    }
}
