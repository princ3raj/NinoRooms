package nino.rooms.pgcompany.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import nino.rooms.pgcompany.MainActivity;
import nino.rooms.pgcompany.R;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";

    private EditText mEmail;
    private EditText mPassword;
    private TextView mSignIn;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ProgressDialog progressDialog;


    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mEmail = view.findViewById(R.id.login_email);
        mPassword = view.findViewById(R.id.login_password);
        mSignIn = view.findViewById(R.id.login);
        progressDialog = new ProgressDialog(getContext());


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(getContext(), "You are logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), MainActivity.class));
                    getActivity().finish();

                }

            }
        };


        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Logging you in...");
                progressDialog.show();
                loginUser();

            }
        });


        return view;
    }


    private void loginUser() {

        //getting email and password from edit texts
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Please enter email", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Please enter password", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return;
        }

        //creating a new user
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            getContext().startActivity(intent);
                            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                            Toast.makeText(getContext(), "email or password incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }
}
