package nino.rooms.pgcompany;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class RoomDetailsActivity extends AppCompatActivity {
    //caraousel used variables
    CarouselView carouselView;
    //positions taker variable
    private String position;
    private int mainPosition;
    private int[] sampleImages;
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    //back_arrow
    private ImageButton mBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        //extracting position values from passed intents
        // and then converting string value to int
        Intent intent = getIntent();
        position = intent.getStringExtra(Intent.EXTRA_TEXT);
        //mainPosition=Integer.parseInt(position);


        //caraousel implementation
        sampleImages = new int[]{R.drawable.apartment_resized, R.drawable.apartment_resized, R.drawable.apartment_resized, R.drawable.apartment_resized, R.drawable.apartment_resized};
        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        //back_arrow reference
        mBackArrow = findViewById(R.id.arrow_back);
        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
