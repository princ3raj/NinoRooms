package nino.rooms.pgcompany.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import nino.rooms.pgcompany.R;

public class HistoryFragment extends Fragment {

    private Context mContext;


    @Override
    public void onAttach(@NonNull Context context) {
        mContext=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_history, container,false);
        return  view;
    }
}