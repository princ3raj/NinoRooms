package nino.rooms.pgcompany;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import nino.rooms.pgcompany.fragments.BookmarkFragment;
import nino.rooms.pgcompany.fragments.HistoryFragment;
import nino.rooms.pgcompany.fragments.HomeFragment;
import nino.rooms.pgcompany.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {


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
