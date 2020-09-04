package nino.rooms.pgcompany.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import nino.rooms.pgcompany.R;
import nino.rooms.pgcompany.SearchResultActivity;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";


    private Context mContext;


    @Override
    public void onAttach(@NonNull Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container,false);


        // Hide the status bar.
        View decorView = Objects.requireNonNull(getActivity()).getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        ImageView delhiImageView = view.findViewById(R.id.delhi);
        delhiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "We are not yet here. Coming Soon.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("city", "Noida");
                Objects.requireNonNull(getActivity()).startActivity(intent);


            }
        });

        ImageView noidaImageView = view.findViewById(R.id.noida);
        noidaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("city", "Noida");
                Objects.requireNonNull(getActivity()).startActivity(intent);

            }
        });

        ImageView ghaziabadImageView = view.findViewById(R.id.ghaziabad);
        ghaziabadImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "We are not yet here. Coming Soon.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("city", "Noida");
                Objects.requireNonNull(getActivity()).startActivity(intent);

            }
        });


        ImageView gurgaonImageView = view.findViewById(R.id.gurgaon);
        gurgaonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "We are not yet here. Coming Soon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("city", "Noida");
                Objects.requireNonNull(getActivity()).startActivity(intent);

            }
        });


        ImageView jaipurImageView = view.findViewById(R.id.jaipur);
        jaipurImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "We are not yet here. Coming Soon.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("city", "Noida");
                Objects.requireNonNull(getActivity()).startActivity(intent);

            }
        });

        ImageView kotaImageView = view.findViewById(R.id.kota);
        kotaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "We are not yet here. Coming Soon.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("city", "Noida");
                Objects.requireNonNull(getActivity()).startActivity(intent);


            }
        });

        ImageView bhopalImageView = view.findViewById(R.id.bhopal);
        bhopalImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "We are not yet here. Coming Soon.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("city", "Noida");
                Objects.requireNonNull(getActivity()).startActivity(intent);

            }
        });

        ImageView indoreImageView = view.findViewById(R.id.indore);
        indoreImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "We are not yet here. Coming Soon.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("city", "Noida");

                Objects.requireNonNull(getActivity()).startActivity(intent);

            }
        });

        //searchIcon setup
        final SearchView searchView = view.findViewById(R.id.search_icon);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("searchquery", "" + query);
                Objects.requireNonNull(getActivity()).startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });


        return view;


    }


}
