package nino.rooms.pgcompany;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import nino.rooms.pgcompany.fragments.BookmarkFragment;
import nino.rooms.pgcompany.fragments.HistoryFragment;
import nino.rooms.pgcompany.fragments.HomeFragment;
import nino.rooms.pgcompany.fragments.SettingFragment;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


   private BottomNavigationView bottomNavigation;

   //fragments

    private HomeFragment mHomeFragment;
    private BookmarkFragment mBookmarkFragment;
    private HistoryFragment mHistoryFragment;
    private SettingFragment mSettingFragment;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        bottomNavigation=findViewById(R.id.bottom_navigation);


        bottomNavigation.inflateMenu(R.menu.master_navigation_menu);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());


        mHomeFragment= new HomeFragment();
        mBookmarkFragment=new BookmarkFragment();
        mHistoryFragment=new HistoryFragment();
        mSettingFragment=new SettingFragment();

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

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();




    }


}
