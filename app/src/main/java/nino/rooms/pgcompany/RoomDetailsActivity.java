package nino.rooms.pgcompany;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import nino.rooms.pgcompany.adapters.MainSliderAdapter;
import nino.rooms.pgcompany.model.NinoRooms;
import nino.rooms.pgcompany.utils.PicassoImageLoadingService;
import ss.com.bannerslider.Slider;


public class RoomDetailsActivity extends AppCompatActivity {

    private static final String TAG = "RoomDetailsActivity";

    //    private ImageView mImageView;
    //
    public static String pg_image;







    //back_arrow
    private ImageButton mBackArrow;
    public static String pg_image_two;
    public static String pg_image_three;
    private Slider mSlider;
    //all views widgets variables
    private TextView mPg_name;
    private TextView mAc_price;
    private TextView mNon_ac_price;
    private TextView mDetails;
    private TextView mLocation;

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

//        extracting the value from parcelable object
        Intent intentAction = getIntent();
        NinoRooms ninoRooms = intentAction.getParcelableExtra("NinoRooms");

        //collecting all the properties values

        assert ninoRooms != null;
        int id = ninoRooms.getId();
        String pg_name = ninoRooms.getPg_name();
        String phone_number = ninoRooms.getPhone_number();
        String location = ninoRooms.getLocation();
        String details = ninoRooms.getDetails();
        String ac_prices = ninoRooms.getAc_prices();
        String non_ac_prices = ninoRooms.getNon_ac_prices();
        pg_image = ninoRooms.getPg_image();
        pg_image_two = ninoRooms.getPg_image_two();
        pg_image_three = ninoRooms.getPg_image_three();

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

//        //imageview
//        mImageView=findViewById(R.id.image_view);

//
//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.downloader(new OkHttp3Downloader(this));
//        builder.build().load(pg_image)
//                .error(R.drawable.ic_launcher_background)
//                .into(mImageView);

        Slider.init(new PicassoImageLoadingService(this));

        mSlider = findViewById(R.id.banner_slider1);
        mSlider.setAdapter(new MainSliderAdapter());























    }


}

