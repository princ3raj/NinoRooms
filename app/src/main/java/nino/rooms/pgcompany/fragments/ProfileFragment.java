package nino.rooms.pgcompany.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import io.customerly.Customerly;
import nino.rooms.pgcompany.MainActivity;
import nino.rooms.pgcompany.R;
import nino.rooms.pgcompany.UserAuthActivity;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    public MainActivity activity;
    private TextView log_out;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseAuth mFirebaseAuth;

    private TextView PhoneNumber;
    private TextView EmailView;


    private TextView SupportButton;
    private String user;


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
        SupportButton = view.findViewById(R.id.supportbutton);
        PhoneNumber = view.findViewById(R.id.phone_number);
        EmailView = view.findViewById(R.id.email_id);


        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

        assert mFirebaseUser != null;
        user = mFirebaseUser.getEmail();


        SupportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Customerly.openSupport(Objects.requireNonNull(getActivity()));
                Customerly.setAttachmentsAvailable(false);
                Customerly.registerUser(user);

            }
        });

        PhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use format with "tel:" and phoneNumber created is
                // stored in u.
                Uri u = Uri.parse("tel:" + "9602723097");


                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try {
                    // Launch the Phone app's dialer with a phone
                    // number to dial a call.
                    startActivity(i);
                } catch (SecurityException s) {


                }

            }
        });

        EmailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:support@ninorooms.com")); // only email apps should handle this
                startActivity(intent);


            }
        });


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

                    Customerly.logoutUser();
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