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




    //made this static so i could able
    // to access this variable in other class
    //as in this case i am accessing this variable in mainsliderAdapter
    private HistoryRepository mHistoryRepository;
    private HistoryRepository mBookMarkRepository;

    //bookmark setup variables
    private ImageButton mBookmarks;


    //back_arrow
    private ImageButton mBackArrow;
    public static String pg_image;
    public static String pg_image_two;
    public static String pg_image_three;

    //used this variable to implement odd
    //and even function
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




    //contact owner button setup
    //and make a action call through intent
    private TextView mContactOwner;

    //location setup variable
    private ImageButton mLocationCall;


    //for ninorooms searchobject
    private NinoRooms ninoRooms;


    //for ninorooms searchobject
    private NinoRooms mNinoRoomsHistoryObject;

    //for ninorooms bookmarks object
    private NinoRooms mBookmarksObject;
    private String bookmark;
    private String test = null;


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

            Log.d(TAG, "onCreate: searchDetails called");
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
            ninoRooms.setHistory("history");
            saveNewHistory(ninoRooms);
            bookmark = "true";
        }

        //this intent is coming from history
        //fragment and as per the intent action
        //setup the features
        //extracting the value from history objects;
        Intent intentHistory = getIntent();
        mNinoRoomsHistoryObject = intentHistory.getParcelableExtra("history");
        if (mNinoRoomsHistoryObject != null) {
            Log.d(TAG, "onCreate: history called");
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
            mBookMarkRepository = new HistoryRepository(this);
            test = "yeahOk";


        }

        //this intent is coming from bookmark
        //fragment and as per the intent action
        //setup the features
        //extracting the value from bookmark objects;
        Intent intentBookmark = getIntent();
        mBookmarksObject = intentBookmark.getParcelableExtra("bookmark");

        if (mBookmarksObject != null) {
            Log.d(TAG, "onCreate: bookmark called");
            //collecting all the properties values
            id = mBookmarksObject.getId();
            pg_name = mBookmarksObject.getPg_name();
            phone_number = mBookmarksObject.getPhone_number();
            location = mBookmarksObject.getLocation();
            details = mBookmarksObject.getDetails();
            ac_prices = mBookmarksObject.getAc_prices();
            non_ac_prices = mBookmarksObject.getNon_ac_prices();
            pg_image = mBookmarksObject.getPg_image();
            pg_image_two = mBookmarksObject.getPg_image_two();
            pg_image_three = mBookmarksObject.getPg_image_three();
            bookmark = null;
            mBookmarks = findViewById(R.id.bookmark);
            mBookmarks.setImageResource(R.drawable.ic_active_bookmark);


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


                    if (bookmark == null) {


                        if (test != null) {

                            Log.d(TAG, "run this block");
                            //setting history to blank and
                            //bookmark to bookmark, because we don't want duplicacy
                            //when historyadapter shows its data
                            //for more details, look at retrieveHistory from Historydao
                            //you will get to understand everything
                            mNinoRoomsHistoryObject.setHistory("");
                            mNinoRoomsHistoryObject.setBookmark("bookmark");
                            Uid = mNinoRoomsHistoryObject.getUid();
                            Uid = Uid + 19873;
                            mNinoRoomsHistoryObject.setUid(Uid);
                            Uid = Uid + 27989;
                            saveNewBookMark(mNinoRoomsHistoryObject);
                            mBookmarks.setImageResource(R.drawable.ic_active_bookmark);
                        }
                    } else {
                        Log.d(TAG, "onClick: this one called");
                        isClicked(true);
                    }


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


    //this function will get called
    //whenever user clicks bookmark button
    private void isClicked(boolean clicked) {
        if (clicked) {

            ninoRooms.setHistory("");
            ninoRooms.setBookmark("bookmark");
            saveNewHistory(ninoRooms);
            mBookmarks.setImageResource(R.drawable.ic_active_bookmark);

        } else {

            mBookmarks.setImageResource(R.drawable.ic_bookmark_unactive);

        }
    }

    //made this function,so we can call it
    //when user open roomdetails activity
    // coming from searchresultactivity
    private void saveNewHistory(NinoRooms ninoRooms) {

        mHistoryRepository.insertHistory(ninoRooms);
    }

    //made this function,so we can call it
    //when user open roomdetails activity
    // fragment coming from history
    private void saveNewBookMark(NinoRooms ninoRooms) {

        mBookMarkRepository.insertHistory(ninoRooms);
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


    }


}

