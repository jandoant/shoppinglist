package com.udacity.firebase.shoppinglistplusplus.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;

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
        mEditTextUsernameCreate = (EditText) findViewById(R.id.edit_text_username_create);
        mEditTextEmailCreate = (EditText) findViewById(R.id.edit_text_email_create);
        mEditTextPasswordCreate = (EditText) findViewById(R.id.edit_text_password_create);
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
        String userName = mEditTextUsernameCreate.getText().toString().trim();
        String userEmail = mEditTextEmailCreate.getText().toString().trim();
        String userPassword = mEditTextPasswordCreate.getText().toString().trim();

        /*
        * Check if User Input is valid -
        * if so, create User in Firebase and show Progress Dialog
        * if not, show Error Messages
         */
        if (!isUserNameValid(userName)) {
            mEditTextUsernameCreate.setError("Please enter a Username");
            mEditTextUsernameCreate.requestFocus();
        } else if (!isEmailValid(userEmail)) {
            mEditTextEmailCreate.setError("Not a valid Email");
            mEditTextEmailCreate.requestFocus();
        } else if (!isPasswordValid(userPassword)) {
            mEditTextPasswordCreate.setError("Password must have at least 5 Characters");
            mEditTextPasswordCreate.requestFocus();
        } else {
            mAuthProgressDialog.show();
            createUserAccount(userEmail, userPassword);
        }
    }

    private boolean isUserNameValid(String userName) {

        return !TextUtils.isEmpty(userName);
    }

    /**
     * Clientside checks wether an Email is valid or not
     *
     * @param email - EMail to evaluate
     * @return true if EMail is valid, false if not
     */
    private boolean isEmailValid(String email) {
       /* Email must be entered and match Google's Pattern for valid Email*/
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    /**
     * Clientside checks wether a Password is valid or not
     * Requirements at this point: Password must be at least 5 characters long
     *
     * @param password
     * @return true if password is valid, false if not
     */
    private boolean isPasswordValid(String password) {
        /* Password must be at least 5 Characters long*/
        return password.length() > 5;
    }

    /**
     * Creates a User Account in Firebase with an Email and Password the user enters into EditTextFields
     *
     * @param eMail    - the Email the User wants to register
     * @param password - The Password the User wants to use
     */
    private void createUserAccount(String eMail, String password) {
        mAuth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mAuthProgressDialog.dismiss();
                    Toast.makeText(CreateAccountActivity.this, "Authentication successful",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mAuthProgressDialog.dismiss();
                    Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    Log.e(LOG_TAG, task.getException().getMessage());
                }
            }
        });
    }

    /**
     * Creates a new user in Firebase from the Java POJO
     */
    private void createUserInFirebaseHelper(final String encodedEmail) {
    }

    /**
     * Show error toast to users
     */
    private void showErrorToast(String message) {
        Toast.makeText(CreateAccountActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
