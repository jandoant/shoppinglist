package com.udacity.firebase.shoppinglistplusplus.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.User;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;
import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

public class CreateAccountActivity extends BaseActivity {

    private static final String LOG_TAG = CreateAccountActivity.class.getSimpleName();

    private FirebaseAuth mAuth;

    private ProgressDialog mAuthProgressDialog;
    private EditText mEditTextUsernameCreate, mEditTextEmailCreate, mEditTextPasswordCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        /**
         * Link layout elements from XML and setup the progress dialog
         */
        initializeScreen();

        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Link layout elements from XML and setup the progress dialog
     */
    public void initializeScreen() {

        /* Link EditTextFields */
        mEditTextUsernameCreate = (EditText) findViewById(R.id.edit_text_username_create);
        mEditTextEmailCreate = (EditText) findViewById(R.id.edit_text_email_create);
        mEditTextPasswordCreate = (EditText) findViewById(R.id.edit_text_password_create);

        /* Init Background Image based on Screen Orientation */
        LinearLayout linearLayoutCreateAccountActivity = (LinearLayout) findViewById(R.id.linear_layout_create_account_activity);
        initializeBackground(linearLayoutCreateAccountActivity);

        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getResources().getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setMessage(getResources().getString(R.string.progress_dialog_creating_user_with_firebase));
        mAuthProgressDialog.setCancelable(false);
    }

    /**
     * Override onCreateOptionsMenu to inflate nothing
     *
     * @param menu The menu with which nothing will happen
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /**
     * Open LoginActivity when user taps on "Sign in" textView
     */
    public void onSignInPressed(View view) {
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * Create new account using Firebase email/password provider
     */
    public void onCreateAccountPressed(View view) {

        /* Extract User Input from EditTextFields */
        String userName = mEditTextUsernameCreate.getText().toString().trim();
        String userEmail = mEditTextEmailCreate.getText().toString().trim();
        String userPassword = mEditTextPasswordCreate.getText().toString().trim();

        /*
        * Check if User Input is valid -
        * if so, create User in Firebase and show Progress Dialog
        * if not, show Error Messages
         */
        if (!Utils.isUserNameValid(userName)) {
            mEditTextUsernameCreate.setError("Please enter first and last name");
            mEditTextUsernameCreate.requestFocus();
        } else if (!Utils.isEmailValid(userEmail)) {
            mEditTextEmailCreate.setError("Not a valid Email");
            mEditTextEmailCreate.requestFocus();
        } else if (!Utils.isPasswordValid(userPassword)) {
            mEditTextPasswordCreate.setError("Password must have at least 5 Characters");
            mEditTextPasswordCreate.requestFocus();
        } else {
            mAuthProgressDialog.show();
            createUserAccount(userEmail, userPassword);
        }
    }

    /**
     * Creates a User Account in Firebase with an Email and Password the user enters into EditTextFields
     *
     * @param email    - the Email the User wants to register
     * @param password - The Password the User wants to use
     */
    private void createUserAccount(final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mAuthProgressDialog.dismiss();
                    Toast.makeText(CreateAccountActivity.this, "Thank you. Your account was successfully created",
                            Toast.LENGTH_SHORT).show();

                    User newUser = new User(email, mEditTextUsernameCreate.getText().toString());
                    createUserInFirebaseHelper(newUser);
                    openLoginActivity();
                } else {
                    mAuthProgressDialog.dismiss();
                    Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    Log.e(LOG_TAG, String.valueOf(task.getException()));

                    /* Error Message if entered Email is alReready registered in Firebase - Email must be unique */
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        mEditTextEmailCreate.setError("Email is already taken.");
                        mEditTextEmailCreate.requestFocus();
                    }

                    if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                        mEditTextPasswordCreate.setError("This is an insecure Password");
                        mEditTextPasswordCreate.requestFocus();
                    }
                }
            }
        });
    }

    private void createUserInFirebaseHelper(User newUser) {
        Firebase usersRef = new Firebase(Constants.FIREBASE_URL_USERS);

        //needed to retrieve User ID generated when the user created his account
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        /* Creates a User object in Firebase DB under the Unique User ID Node */
        if (user != null) {
            usersRef.child(user.getUid()).setValue(newUser);
        }
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Show error toast to users
     */
    private void showErrorToast(String message) {
        Toast.makeText(CreateAccountActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
