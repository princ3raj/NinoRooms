package nino.rooms.pgcompany;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.util.List;

import nino.rooms.pgcompany.adapters.CustomAdapter;
import nino.rooms.pgcompany.model.NinoRooms;
import nino.rooms.pgcompany.requests.RestApi;
import nino.rooms.pgcompany.requests.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchResultActivity extends AppCompatActivity {


    private static final String TAG = "SearchResultActivity";


    private Toolbar toolbar;

    //for filtering results
    private TextView mTop;
    private TextView mCheapest;
    private TextView mRating;
    private TextView mComfort;



    //Rooms_found

    private TextView RoomsFound;
    private int Rooms;

    //city name view
    private TextView CityName;
    private String City;

    private CustomAdapter adapter;
    private RecyclerView recyclerView;

    //progressBar
    ProgressDialog progressDialog;
    private NinoRooms NinoRooms;

    private ImageView mSearchRefreshIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        View decorView = getWindow().getDecorView();

        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        progressDialog = new ProgressDialog(SearchResultActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        recyclerView = findViewById(R.id.nino_rooms_view);
        mSearchRefreshIcon = findViewById(R.id.refresh_search);

        mSearchRefreshIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                TestRetrofitClient();
            }
        });


        //toolbar setup
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_svg_arrow_back);
        toolbar.setNavigationContentDescription("back");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        //filtering results by comfort and rating setup

        mTop = findViewById(R.id.top);
        mCheapest = findViewById(R.id.cheapest);
        mComfort = findViewById(R.id.comfort);
        mRating = findViewById(R.id.rating);

        //Rooms found Reference

        RoomsFound=findViewById(R.id.rooms_found);

        //City Name TextView Reference

        CityName=findViewById(R.id.city_name);


        mTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                TestRetrofitClient();
                mTop.setTextColor(Color.parseColor("#000000"));
                mCheapest.setTextColor(Color.parseColor("#C4C4C4"));
                mComfort.setTextColor(Color.parseColor("#C4C4C4"));
                mRating.setTextColor(Color.parseColor("#C4C4C4"));
            }
        });


        mCheapest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                TestRetrofitClient();
                mCheapest.setTextColor(Color.parseColor("#000000"));
                mTop.setTextColor(Color.parseColor("#C4C4C4"));
                mComfort.setTextColor(Color.parseColor("#C4C4C4"));
                mRating.setTextColor(Color.parseColor("#C4C4C4"));
            }
        });


        mComfort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                TestRetrofitClient();
                mComfort.setTextColor(Color.parseColor("#000000"));
                mCheapest.setTextColor(Color.parseColor("#C4C4C4"));
                mTop.setTextColor(Color.parseColor("#C4C4C4"));
                mRating.setTextColor(Color.parseColor("#C4C4C4"));
            }
        });

        mRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                TestRetrofitClient();
                mRating.setTextColor(Color.parseColor("#000000"));
                mCheapest.setTextColor(Color.parseColor("#C4C4C4"));
                mComfort.setTextColor(Color.parseColor("#C4C4C4"));
                mTop.setTextColor(Color.parseColor("#C4C4C4"));
            }
        });


        Intent intent = getIntent();
        City = intent.getStringExtra("Delhi");




        TestRetrofitClient();



    }




    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<NinoRooms> ninoRooms) {
        recyclerView = findViewById(R.id.nino_rooms_view);
        adapter = new CustomAdapter(this, ninoRooms);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchResultActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Rooms = adapter.getItemCount();
        RoomsFound.setText(MessageFormat.format("{0} Rooms Found", Rooms));
        CityName.setText(City);

    }


    private void TestRetrofitClient() {


        RestApi restApi = RetrofitClientInstance.getRetrofitInstance().create(RestApi.class);

        Call<List<NinoRooms>> call = restApi.getNinoRooms();

        call.enqueue(new Callback<List<NinoRooms>>() {
            @Override
            public void onResponse(Call<List<NinoRooms>> call, Response<List<NinoRooms>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
                Log.d(TAG, "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call<List<NinoRooms>> call, Throwable t) {
                Log.d(TAG, "onFailure: called");
                progressDialog.dismiss();

                Toast.makeText(SearchResultActivity.this, "Make sure you are connected to internet", Toast.LENGTH_SHORT).show();
            }
        });


    }


}

