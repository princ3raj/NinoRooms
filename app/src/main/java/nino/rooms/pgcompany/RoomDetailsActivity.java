package nino.rooms.pgcompany;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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


    public static String pg_image;
    public static String pg_image_two;
    public static String pg_image_three;

    //used this variable to implement odd
    //and even function
    private int bookmark_increment = 0;


    //all variables are for searchactivity intent object
    private String phone_number;
    private String location;
    private String address;
    private String details;
    private String pg_name;
    private String ac_prices;
    private String non_ac_prices;
    private int Uid;


    //for ninorooms searchobject
    private NinoRooms ninoRooms;


    //for ninorooms searchobject
    private NinoRooms mNinoRoomsHistoryObject;

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
        //back_arrow
        ImageView backArrow = findViewById(R.id.arrow_back);
        backArrow.setOnClickListener(new View.OnClickListener() {
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
        String id;
        if (ninoRooms != null) {

            Log.d(TAG, "onCreate: searchDetails called");
            //collecting all the properties values
            pg_name = ninoRooms.getPg_name();
            phone_number = ninoRooms.getPhone_number();
            location = ninoRooms.getLocation();
            address = ninoRooms.getAddress();
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
            pg_name = mNinoRoomsHistoryObject.getPg_name();
            phone_number = mNinoRoomsHistoryObject.getPhone_number();
            location = mNinoRoomsHistoryObject.getLocation();
            address = mNinoRoomsHistoryObject.getAddress();
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
        //for ninorooms bookmarks object
        NinoRooms bookmarksObject = intentBookmark.getParcelableExtra("bookmark");

        if (bookmarksObject != null) {
            Log.d(TAG, "onCreate: bookmark called");
            //collecting all the properties values
            pg_name = bookmarksObject.getPg_name();
            phone_number = bookmarksObject.getPhone_number();
            location = bookmarksObject.getLocation();
            details = bookmarksObject.getDetails();
            address = bookmarksObject.getAddress();
            ac_prices = bookmarksObject.getAc_prices();
            non_ac_prices = bookmarksObject.getNon_ac_prices();
            pg_image = bookmarksObject.getPg_image();
            pg_image_two = bookmarksObject.getPg_image_two();
            pg_image_three = bookmarksObject.getPg_image_three();
            bookmark = null;
            mBookmarks = findViewById(R.id.bookmark);
            mBookmarks.setImageResource(R.drawable.ic_active_bookmark);


        }


        //attaching all views by referencing to its ids
        //all views widgets variables
        TextView pg_name1 = findViewById(R.id.pg_name);
        TextView ac_price = findViewById(R.id.ac_price);
        TextView non_ac_price = findViewById(R.id.non_ac_price);
        TextView details1 = findViewById(R.id.details);
        TextView address1 = findViewById(R.id.address);


        //set text to all views
        pg_name1.setText(pg_name);
        address1.setText(address);
        details1.setText(details);
        non_ac_price.setText(String.format("%s%s", getString(R.string.non_ac_price), non_ac_prices));
        ac_price.setText(String.format("%s%s", getString(R.string.ac_price), ac_prices));


        //slider initiliaztion and setup
        Slider.init(new PicassoImageLoadingService(this));
        //slider
        Slider slider = findViewById(R.id.banner_slider1);
        slider.setAdapter(new MainSliderAdapter());

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
        //contact owner button setup
        //and make a action call through intent
        TextView contactOwner = findViewById(R.id.contact_owner);
        contactOwner.setOnClickListener(new View.OnClickListener() {
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
        //location setup variable
        ImageButton locationCall = findViewById(R.id.location_call);
        locationCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + location);
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


}

