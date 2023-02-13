package com.example.instacart.Auth.Auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.instacart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link SignUPFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class SignUPFragment extends Fragment {
    //
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public SignUPFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment SignUPFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static SignUPFragment newInstance(String param1, String param2) {
//        SignUPFragment fragment = new SignUPFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
    TextInputLayout name, email, password, confPassword, mobile;
    MaterialButton singup_btn;
    MaterialCheckBox termscheckbox;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String EmailCheck = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_u_p, container, false);

        name = view.findViewById(R.id.signup_name_input_id);
        email = view.findViewById(R.id.signup_email_input_id);
        password = view.findViewById(R.id.signup_password_input_id);
        confPassword = view.findViewById(R.id.signup_conform_password_input_id);
        mobile = view.findViewById(R.id.signup_mobile_input_id);
        singup_btn = view.findViewById(R.id.signup_btn_id);
        termscheckbox = view.findViewById(R.id.signup_terms_check_box_id);

        singup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getEditText().getText().toString();
                String Email = email.getEditText().getText().toString();
                String Mobile = mobile.getEditText().getText().toString();
                String Password = password.getEditText().getText().toString();
                String ConPassword = confPassword.getEditText().getText().toString();
                mAuth = FirebaseAuth.getInstance();
                if (Name.isEmpty()) {
                    name.setError("Fill the Name");
                } else {
                    name.setErrorEnabled(false);
                    name.setError(null);
                }
                if (Mobile.isEmpty()) {
                    mobile.setError("Fill the Mobile No");
                } else {
                    mobile.setErrorEnabled(false);
                    mobile.setError(null);
                }

                if (Email.isEmpty()) {
                    email.setError("Fill the Email");
                } else {
                    email.setErrorEnabled(false);
                    email.setError(null);
                }
                if (Password.isEmpty()) {
                    password.setError("Fill the Password");
                } else {
                    password.setErrorEnabled(false);
                    password.setError(null);
                }
                if (ConPassword.isEmpty()) {
                    confPassword.setError("Fill the confim Password");
                } else {
                    confPassword.setError(null);
                    confPassword.setErrorEnabled(false);
                }
                if (Password.equals(ConPassword)) {
                    confPassword.setError(null);
                    confPassword.setErrorEnabled(false);
                } else {
                    Toast.makeText(getContext(), "not match", Toast.LENGTH_SHORT).show();
                    confPassword.setError("Password not Match");

                }
                if (termscheckbox.isChecked()) {

                    termscheckbox.setError(null);

                } else {
                    Toast.makeText(getContext(), "check terms and conditions", Toast.LENGTH_SHORT).show();
                    termscheckbox.setError("Check terms and conditions");
                }

                if (Email.isEmpty() || Password.isEmpty() || Mobile.isEmpty() || ConPassword.isEmpty() || Mobile.isEmpty() || !termscheckbox.isChecked()) {

                    Toast.makeText(getContext(), "fill the filds", Toast.LENGTH_SHORT).show();

                } else if (!Email.matches(EmailCheck)) {
                    email.setError("This Email is not vaild");
                } else if (Password.length() < 8) {
                    password.setError("Use 8 characters or more for your password");
                    email.setError(null);
                    email.setErrorEnabled(false);
                } else {
                    password.setError(null);
                    password.setErrorEnabled(false);
                    email.setError(null);
                    email.setErrorEnabled(false);
                    CheckEmail();
                    Toast.makeText(getContext(), "checkemail", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return view;
    }

    private void CheckEmail() {
        mAuth.fetchSignInMethodsForEmail(email.getEditText().getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean checkemail = task.getResult().getSignInMethods().isEmpty();
                        if (checkemail) {
                            Toast.makeText(getContext(), "new user", Toast.LENGTH_SHORT).show();
                            UserAuthentication();
                        } else {
                            email.setError("This Email is  Taken.Try another");
                            Toast.makeText(getContext(), "old user", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void UserAuthentication() {
        String Name = name.getEditText().getText().toString().trim();
        String Email = email.getEditText().getText().toString().trim();
        String Mobile = mobile.getEditText().getText().toString().trim();
        String Password = password.getEditText().getText().toString().trim();
        CustomerInfoModel customerInfoModel = new CustomerInfoModel(Name, Mobile, Email, Password);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Customers");
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            reference.child(Name).setValue(customerInfoModel);
                            Toast.makeText(getContext(), "Sign up successfull ", Toast.LENGTH_SHORT).show();

                            name.getEditText().setText(null);
                            email.getEditText().setText(null);
                            mobile.getEditText().setText(null);
                            password.getEditText().setText(null);
                            confPassword.getEditText().setText(null);

                        } else {
                            Toast.makeText(getContext(), "Sign up fails", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        e.printStackTrace();
                        System.out.println(e);
                    }
                });


    }
}