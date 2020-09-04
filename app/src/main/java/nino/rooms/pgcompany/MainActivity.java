package nino.rooms.pgcompany;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import nino.rooms.pgcompany.fragments.BookmarkFragment;
import nino.rooms.pgcompany.fragments.HistoryFragment;
import nino.rooms.pgcompany.fragments.HomeFragment;
import nino.rooms.pgcompany.fragments.ProfileFragment;
import nino.rooms.pgcompany.utils.UpdateHelper;


public class MainActivity extends AppCompatActivity implements UpdateHelper.OnUpdateCheckListener {

    private static final String TAG = "MainActivity";


    //fragments

    private HomeFragment mHomeFragment;
    private BookmarkFragment mBookmarkFragment;
    private HistoryFragment mHistoryFragment;
    private ProfileFragment mSettingFragment;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String NotificationSubscriber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpdateHelper.with(this).onUpdateCheck(this).check();


        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

        assert mFirebaseUser != null;
        NotificationSubscriber = mFirebaseUser.getEmail();


        assert NotificationSubscriber != null;
        String UserName = NotificationSubscriber.replace("@gmail.com", "");


////        Register for Push Notifications
//        Pushbots.sharedInstance().registerForRemoteNotifications();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotifications", "MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfull";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                    }
                });

        FirebaseMessaging.getInstance().subscribeToTopic(UserName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Succesffull";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                    }
                });


        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);


        bottomNavigation.inflateMenu(R.menu.master_navigation_menu);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());


        mHomeFragment= new HomeFragment();
        mBookmarkFragment=new BookmarkFragment();
        mHistoryFragment = new HistoryFragment();
        mSettingFragment = new ProfileFragment();

        setFragment(mHomeFragment);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId())
                {
                    case R.id.home_fragment:

                        setFragment(mHomeFragment);

                        break;

                    case R.id.bookmark_fragment:

                        setFragment(mBookmarkFragment);

                        break;

                    case R.id.history_fragment:

                        setFragment(mHistoryFragment);

                        break;

                    case R.id.setting_fragment:

                        setFragment(mSettingFragment);

                        break;


                }
                return true;
            }
        });


    }



    public void setFragment(Fragment fragment)
    {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: main called");
        finishAffinity();
    }

    @Override
    public void onUpdateCheckListener(String urlApp) {

        AlertDialog alertDialog = new AlertDialog
                .Builder(this)
                .setTitle("New Version Available")
                .setMessage("Please update your app to receive the best possible experience!")
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=nino.rooms.pgcompany&hl=en"));
                        startActivity(intent);

                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();

        alertDialog.show();


    }
}
