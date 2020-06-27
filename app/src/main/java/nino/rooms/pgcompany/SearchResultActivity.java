package nino.rooms.pgcompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {


    private Toolbar toolbar;

    //for filtering results
    private TextView mTop;
    private TextView mCheapest;
    private TextView mRating;
    private TextView mComfort;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //toolbar setup
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationIcon(R.drawable.back_arrow_ios);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SearchResultActivity.this,MainActivity.class));

            }
        });


        //filtering results by comfort and rating setup

        mTop=findViewById(R.id.top);
        mCheapest=findViewById(R.id.cheapest);
        mComfort=findViewById(R.id.comfort);
        mRating=findViewById(R.id.rating);

        mTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTop.setTextColor(Color.parseColor("#000000"));
                mCheapest.setTextColor(Color.parseColor("#C4C4C4"));
                mComfort.setTextColor(Color.parseColor("#C4C4C4"));
                mRating.setTextColor(Color.parseColor("#C4C4C4"));
            }
        });


        mCheapest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheapest.setTextColor(Color.parseColor("#000000"));
                mTop.setTextColor(Color.parseColor("#C4C4C4"));
                mComfort.setTextColor(Color.parseColor("#C4C4C4"));
                mRating.setTextColor(Color.parseColor("#C4C4C4"));
            }
        });


        mComfort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mComfort.setTextColor(Color.parseColor("#000000"));
                mCheapest.setTextColor(Color.parseColor("#C4C4C4"));
                mTop.setTextColor(Color.parseColor("#C4C4C4"));
                mRating.setTextColor(Color.parseColor("#C4C4C4"));
            }
        });

        mRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRating.setTextColor(Color.parseColor("#000000"));
                mCheapest.setTextColor(Color.parseColor("#C4C4C4"));
                mComfort.setTextColor(Color.parseColor("#C4C4C4"));
                mTop.setTextColor(Color.parseColor("#C4C4C4"));
            }
        });
















    }
}
