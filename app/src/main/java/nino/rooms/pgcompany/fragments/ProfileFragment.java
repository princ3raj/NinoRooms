package nino.rooms.pgcompany.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import nino.rooms.pgcompany.MainActivity;
import nino.rooms.pgcompany.R;
import nino.rooms.pgcompany.UserAuthActivity;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    public MainActivity activity;
    private TextView log_out;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Hide the status bar.
        View decorView = Objects.requireNonNull(getActivity()).getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        log_out = view.findViewById(R.id.logout);
        setUpFireBaseListener();

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();


            }
        });


        return view;
    }


    private void setUpFireBaseListener() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {
                    Intent intent = new Intent(getActivity(), UserAuthActivity.class);
                    startActivity(intent);


                }

            }
        };
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener == null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);

        }
    }


}