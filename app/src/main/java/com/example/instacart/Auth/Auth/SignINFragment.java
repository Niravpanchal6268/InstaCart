package com.example.instacart.Auth.Auth;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.instacart.Auth.Customer.MainDashbordAcitivity;
import com.example.instacart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignINFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignINFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignINFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignINFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignINFragment newInstance(String param1, String param2) {
        SignINFragment fragment = new SignINFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextInputLayout emailinput, passwordinput;
    String Email, Password;
    MaterialButton signinBtn;
    FirebaseAuth auth;

    String EmailCheck = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_i_n, container, false);
        //login firebase code
        auth = FirebaseAuth.getInstance();
        emailinput = view.findViewById(R.id.signin_email_input_id);
        passwordinput = view.findViewById(R.id.signin_password_input_id);
        signinBtn = view.findViewById(R.id.signin_btn_id);
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.loading_dialog);


        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email = emailinput.getEditText().getText().toString();
                Password = passwordinput.getEditText().getText().toString();


                if (Email.isEmpty()) {
                    emailinput.setError("Please fill the filed");

                } else {

                    emailinput.setError(null);
                    emailinput.setErrorEnabled(false);
                }
                if (Password.isEmpty()) {
                    passwordinput.setError("Please fill the filed");

                } else {

                    passwordinput.setError(null);
                    passwordinput.setErrorEnabled(false);

                }
                if (Email.isEmpty() || Password.isEmpty()) {

                    Toast.makeText(getContext(), "fill the filds", Toast.LENGTH_SHORT).show();
                } else if (!Email.matches(EmailCheck)) {
                    emailinput.setError("Enter valid Email");
                } else if (Password.length() < 8) {
                    passwordinput.setError("Use 8 characters or more for your password");
                } else {
                    emailinput.setError(null);
                    passwordinput.setError(null);
                    emailinput.setErrorEnabled(false);
                    passwordinput.setErrorEnabled(false);
                    checkEmail(Email);

                }


            }

            private void checkEmail(String email) {

                auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean ce = task.getResult().getSignInMethods().isEmpty();
                        if (ce) {
                            emailinput.setError("This Email is new please Sign up");

                        } else {
                            authsign();
                            dialog.show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e);
                        Toast.makeText(getContext(), "Error::" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }

            private void authsign() {
                Email = emailinput.getEditText().getText().toString().trim();
                Password = passwordinput.getEditText().getText().toString().trim();
                auth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "login successfull", Toast.LENGTH_SHORT).show();
                                    emailinput.setError(null);
                                    passwordinput.setError(null);
                                    emailinput.setErrorEnabled(false);
                                    passwordinput.setErrorEnabled(false);
                                    startActivity(new Intent(getContext(), MainDashbordAcitivity.class));
                                    getActivity().finish();
                                    dialog.dismiss();
                                } else {
                                    emailinput.setError("incorrect Email");
                                    passwordinput.setError("incorrect Email");
                                    dialog.dismiss();

                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(getContext(), "Error::" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("Error", "Error" + e.getMessage());
                            }
                        });

            }
        });


        return view;
    }


}