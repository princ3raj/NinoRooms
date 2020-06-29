package nino.rooms.pgcompany;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import nino.rooms.pgcompany.adapters.SliderAdapterExample;
import nino.rooms.pgcompany.model.NinoRooms;
import nino.rooms.pgcompany.requests.RestApi;
import nino.rooms.pgcompany.requests.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "position";
    private static final String TAG = "RoomDetailsActivity";
    //progressBar
    ProgressDialog progressDialog;
    //sliderView
    private SliderView sliderView;
    private SliderAdapterExample adapter;
    //back_arrow_button
    private ImageButton mBackArrowButton;

    //position
    private String position;
    private int mainPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);


        progressDialog = new ProgressDialog(RoomDetailsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        Intent intent = getIntent();
        position = intent.getStringExtra(Intent.EXTRA_TEXT);

        mainPosition = Integer.parseInt(position);

        Log.d(TAG, "onCreate: called" + position);

        mBackArrowButton = findViewById(R.id.back_arrow);

        mBackArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        TestRetrofitClient();


    }


    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<NinoRooms> NinoRooms) {
        sliderView = findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(this, mainPosition);
        adapter.renewItems(NinoRooms);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3000);


    }


    private void TestRetrofitClient() {

        RestApi restApi = RetrofitClientInstance.getRetrofitInstance().create(RestApi.class);

        Call<List<NinoRooms>> call = restApi.getNinoRooms();

        call.enqueue(new Callback<List<NinoRooms>>() {
            @Override
            public void onResponse(Call<List<NinoRooms>> call, Response<List<NinoRooms>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<NinoRooms>> call, Throwable t) {
                Log.d(TAG, "onFailure: called");
                progressDialog.dismiss();
                Toast.makeText(RoomDetailsActivity.this, "Make sure you are connected to internet", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
