package nino.rooms.pgcompany;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nino.rooms.pgcompany.adapters.MainSliderAdapter;
import nino.rooms.pgcompany.model.NinoRooms;
import nino.rooms.pgcompany.persistence.HistoryRepository;
import nino.rooms.pgcompany.utils.PicassoImageLoadingService;
import ss.com.bannerslider.Slider;


public class RoomDetailsActivity extends AppCompatActivity {

    private static final String TAG = "RoomDetailsActivity";

    public static String pg_image;


    //made this static so i could able
    // to access this variable in other class
    //as in this case i am accessing this variable in mainsliderAdapter
    private HistoryRepository mHistoryRepository;
    //bookmark setup variables
    private ImageButton mBookmarks;


    //back_arrow
    private ImageButton mBackArrow;
    public static String pg_image_two;
    public static String pg_image_three;
    private int bookmark_increment = 0;
    //slider
    private Slider mSlider;


    //all views widgets variables
    private TextView mPg_name;
    private TextView mAc_price;
    private TextView mNon_ac_price;
    private TextView mDetails;
    private TextView mLocation;

    //all variables are for searchactivity intent object
    private String phone_number;
    private String location;
    private String details;
    private String pg_name;
    private String id;
    private String ac_prices;
    private String non_ac_prices;
    private int Uid;

//
//    //
//    //all variables are for searchactivity intent object
//    private String phone_number_history;
//    private String location_history;
//    private String details_histroy;
//    private String pg_name_history;
//    private int id_history;
//    private String ac_prices_history;
//    private String non_ac_prices_history;


    //contact owner button setup
    //and make a action call through intent
    private TextView mContactOwner;

    //location setup variable
    private ImageButton mLocationCall;


    //for ninorooms searchobject
    private NinoRooms ninoRooms;


    //for ninorooms searchobject
    private NinoRooms mNinoRoomsHistoryObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        //back_arrow reference
        mBackArrow = findViewById(R.id.arrow_back);
        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //this intent is coming from CustomAdapter
        //an adapter of SearchResultActivity
        //extracting the value from parcelable object
        Intent intentAction = getIntent();
        ninoRooms = intentAction.getParcelableExtra("NinoRooms");
        if (ninoRooms != null) {
            //collecting all the properties values
            id = ninoRooms.getId();
            pg_name = ninoRooms.getPg_name();
            phone_number = ninoRooms.getPhone_number();
            location = ninoRooms.getLocation();
            details = ninoRooms.getDetails();
            ac_prices = ninoRooms.getAc_prices();
            non_ac_prices = ninoRooms.getNon_ac_prices();
            pg_image = ninoRooms.getPg_image();
            pg_image_two = ninoRooms.getPg_image_two();
            pg_image_three = ninoRooms.getPg_image_three();
            mHistoryRepository = new HistoryRepository(this);
            saveNewHistory(ninoRooms);
        }

        //this intent is coming from history
        //fragment and as per the intent action
        //setup the features
        //extracting the value from history objects;
        Intent intentHistory = getIntent();
        mNinoRoomsHistoryObject = intentHistory.getParcelableExtra("object");
        Log.d(TAG, "onCreate: " + mNinoRoomsHistoryObject);
        if (mNinoRoomsHistoryObject != null) {
            Uid = mNinoRoomsHistoryObject.getUid();
            id = mNinoRoomsHistoryObject.getId();
            pg_name = mNinoRoomsHistoryObject.getPg_name();
            phone_number = mNinoRoomsHistoryObject.getPhone_number();
            location = mNinoRoomsHistoryObject.getLocation();
            details = mNinoRoomsHistoryObject.getDetails();
            ac_prices = mNinoRoomsHistoryObject.getAc_prices();
            non_ac_prices = mNinoRoomsHistoryObject.getNon_ac_prices();
            pg_image = mNinoRoomsHistoryObject.getPg_image();
            pg_image_two = mNinoRoomsHistoryObject.getPg_image_two();
            pg_image_three = mNinoRoomsHistoryObject.getPg_image_three();
        }





        //attaching all views by referencing to its ids
        mPg_name = findViewById(R.id.pg_name);
        mAc_price = findViewById(R.id.ac_price);
        mNon_ac_price = findViewById(R.id.non_ac_price);
        mDetails = findViewById(R.id.details);
        mLocation = findViewById(R.id.location);


        //set text to all views
        mPg_name.setText(pg_name);
        mLocation.setText(location);
        mDetails.setText(details);
        mNon_ac_price.setText(String.format("%s%s", getString(R.string.non_ac_price), non_ac_prices));
        mAc_price.setText(String.format("%s%s", getString(R.string.ac_price), ac_prices));


        //slider initiliaztion and setup
        Slider.init(new PicassoImageLoadingService(this));
        mSlider = findViewById(R.id.banner_slider1);
        mSlider.setAdapter(new MainSliderAdapter());

        //bookmark accessing and set up active and inactive feature
        mBookmarks = findViewById(R.id.bookmark);

        mBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (bookmark_increment % 2 == 0) {
                    isClicked(true);
                    // TODO insertion opeartion


                } else {
                    isClicked(false);
                    // TODO deletion operation
                }

                bookmark_increment++;

            }
        });


        //contact owner setup
        mContactOwner = findViewById(R.id.contact_owner);
        mContactOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Use format with "tel:" and phoneNumber created is
                // stored in u.
                Uri u = Uri.parse("tel:" + phone_number);


                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try {
                    // Launch the Phone app's dialer with a phone
                    // number to dial a call.
                    startActivity(i);
                } catch (SecurityException s) {
                    Toast.makeText(RoomDetailsActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                }

            }
        });


        //Location Call setup
        mLocationCall = findViewById(R.id.location_call);
        mLocationCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(location);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


    }

    private void isClicked(boolean clicked) {
        if (clicked) {
            mBookmarks.setImageResource(R.drawable.ic_active_bookmark);
        } else {
            mBookmarks.setImageResource(R.drawable.ic_bookmark_unactive);
        }

    }


    private void saveNewHistory(NinoRooms ninoRooms) {

        mHistoryRepository.insertHistory(ninoRooms);
        Log.d(TAG, "saveNewHistory: " + ninoRooms.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: on called");
        ninoRooms = null;
        Log.d(TAG, "onStop: called" + ninoRooms);
    }
}

