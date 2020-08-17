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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import nino.rooms.pgcompany.MainActivity;
import nino.rooms.pgcompany.R;
import nino.rooms.pgcompany.model.User;

public class RegistrationFragment extends Fragment {

    private static final String TAG = "RegistrationFragment";


    private EditText mEmail, mPassword, mFullname, mPhoneNumber;
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    public RegistrationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();


        mEmail = view.findViewById(R.id.email_id);
        mPassword = view.findViewById(R.id.password);
        mFullname = view.findViewById(R.id.fullname);
        mPhoneNumber = view.findViewById(R.id.phone_number);
        TextView register = view.findViewById(R.id.register);
        progressDialog = new ProgressDialog(getContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Registering Please Wait...");
                progressDialog.show();
                registerUser();
            }
        });


        return view;
    }


    private void registerUser() {

        //getting email and password from edit texts
        final String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        final String phonenumber = mPhoneNumber.getText().toString().trim();
        final String fullname = mFullname.getText().toString().trim();


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

        if (TextUtils.isEmpty(fullname)) {
            Toast.makeText(getContext(), "Please enter the name", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(phonenumber)) {
            Toast.makeText(getContext(), "Please enter the phone number", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return;
        }

        if (phonenumber.length() != 10) {
            Toast.makeText(getContext(), "Enter a valid phone number", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            return;
        }

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if (task.isSuccessful()) {

                            User user = new User(
                                    fullname,
                                    phonenumber,
                                    email
                            );
                            FirebaseDatabase.getInstance().getReference("")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                    } else {
                                        Toast.makeText(getContext(), "Registration Failed", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });


                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Registration Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            Objects.requireNonNull(getContext()).startActivity(intent);


                        } else {
                            progressDialog.dismiss();
                            Log.e(TAG, "onComplete: Failed=" + Objects.requireNonNull(task.getException()).getMessage());
                            Toast.makeText(getContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


}
