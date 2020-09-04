package nino.rooms.pgcompany;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;
import java.util.Map;

import io.customerly.Customerly;

import static nino.rooms.pgcompany.utils.UpdateHelper.KEY_UPDATE_ENABLE;
import static nino.rooms.pgcompany.utils.UpdateHelper.KEY_UPDATE_URL;
import static nino.rooms.pgcompany.utils.UpdateHelper.KEY_UPDATE_VERSION;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        // Initialize Pushbots Library
//        Pushbots.sharedInstance().init(this);

        //customerly
        Customerly.configure(this, "1d42b905");
        Customerly.setVerboseLogging(BuildConfig.DEBUG);


        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();


        Map<String, Object> defaultValue = new HashMap<>();
        defaultValue.put(KEY_UPDATE_ENABLE, false);
        defaultValue.put(KEY_UPDATE_VERSION, "1.4");
        defaultValue.put(KEY_UPDATE_URL, "https://play.google.com/store/apps/details?id=nino.rooms.pgcompany&hl=en");

        remoteConfig.setDefaults(defaultValue);
        remoteConfig.fetch(5).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    remoteConfig.activateFetched();
                }

            }
        });


    }
}
