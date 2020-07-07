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

    private ImageView mDelhiImageView;

    private SearchView mSearchView;


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
        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        mDelhiImageView = view.findViewById(R.id.delhi);
        mDelhiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("Delhi", "Noida");
                Objects.requireNonNull(getActivity()).startActivity(intent);


            }
        });

        //searchIcon setup
        mSearchView = view.findViewById(R.id.search_icon);
        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "search is not enabled yet", Toast.LENGTH_SHORT).show();

            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(mContext, "Search has not been enabled yet", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(mContext, "Search by place section", Toast.LENGTH_SHORT).show();

                return false;
            }
        });


        return view;


    }


}
