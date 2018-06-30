package myplayer.com.locationsetting;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;


public class GpsLocationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent data) {
        if (data.getAction().matches("android.location.PROVIDERS_CHANGED")) {
            ContentResolver contentResolver = context.getContentResolver();
            // Find out what the settings say about which providers are enabled
            int mode = Settings.Secure.getInt(contentResolver,
                    Settings.Secure.LOCATION_MODE,
                    Settings.Secure.LOCATION_MODE_OFF);

            if (mode == Settings.Secure.LOCATION_MODE_OFF) {
                Intent i = new Intent();
                i.setClassName("myplayer.com.locationsetting",
                        "myplayer.com.locationsetting.LocationActivity");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    }
}
