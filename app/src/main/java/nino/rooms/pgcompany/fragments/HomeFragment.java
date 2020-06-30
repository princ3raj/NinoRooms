package nino.rooms.pgcompany.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import nino.rooms.pgcompany.R;
import nino.rooms.pgcompany.SearchResultActivity;

public class HomeFragment extends Fragment {

    private Context mContext;

    private CircleImageView mDelhiImageView;


    @Override
    public void onAttach(@NonNull Context context) {
        mContext=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container,false);

//        View decorView = getActivity().getWindow().getDecorView();
//        // Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//        decorView.setSystemUiVisibility(uiOptions);


        // Hide the status bar.
        View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        mDelhiImageView = view.findViewById(R.id.delhi);
        mDelhiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("Delhi","Delhi");
                Objects.requireNonNull(getActivity()).startActivity(intent);



            }
        });

        return  view;


    }
}
