package com.ugarsoft.chyma.iwin.activities.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ugarsoft.chyma.iwin.R;
import com.ugarsoft.chyma.iwin.activities.RegisterWebViewActivity;
import com.ugarsoft.chyma.iwin.models.AppUser;
import com.ugarsoft.chyma.iwin.models.ResponseObject;
import com.ugarsoft.chyma.iwin.utils.DBService;
import com.ugarsoft.chyma.iwin.utils.EmailUtil;
import com.ugarsoft.chyma.iwin.utils.FontUtil;
import com.ugarsoft.chyma.iwin.utils.KeyboardUtil;
import com.ugarsoft.chyma.iwin.utils.MessageUtil;
import com.ugarsoft.chyma.iwin.utils.Pref;
import com.ugarsoft.chyma.iwin.utils.WebServiceImpl;
import com.j256.ormlite.dao.Dao;

public class AccessActivity extends BaseActivity {
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button registerButton;
    private TextView register;
    private TextView login;
    private TextView newEmail;
    private TextView newPassword;
    private TextView confirmPassword;
    private TextView username;
    private TextView fullname;
    private TextView confirmEmail;
    private TextView phonenumber;
    private View access;

    private View loginView;
    private View registerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        setUpNavigationBar();
        setupViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle("Login");
    }

    private void setupViews() {
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        register = (TextView) findViewById(R.id.registerTextView);
        login = (TextView) findViewById(R.id.loginTextView);
        newEmail = (TextView) findViewById(R.id.newEmailEditText);
        newPassword = (TextView) findViewById(R.id.newPasswordEditText);
        confirmPassword = (TextView) findViewById(R.id.confirmPasswordEditText);
        fullname = (TextView) findViewById(R.id.nameEditText);
        confirmEmail = (TextView) findViewById(R.id.confirmEmailEditText);
        phonenumber = (TextView) findViewById(R.id.phoneEditText);
        username = (TextView) findViewById(R.id.usernameEditText);
        loginView = findViewById(R.id.loginView);
        registerView = findViewById(R.id.registerView);
        access = findViewById(R.id.accessView);

        KeyboardUtil.setupUI(access, AccessActivity.this);

        FontUtil.setDefaultTypeFace(this, email, password, registerButton, loginButton,
                register, newEmail, newPassword, confirmPassword,username);

        registerView.setVisibility(View.GONE);
        loginView.setVisibility(View.VISIBLE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTitle("Login");
                registerView.setVisibility(View.GONE);
                loginView.setVisibility(View.VISIBLE);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(WebServiceImpl.deviceIsConnected(AccessActivity.this)) {
                    setTitle("Register");
                    Intent intent = new Intent(AccessActivity.this, RegisterWebViewActivity.class);
                    intent.setData(Uri.parse("http://iwin.org.ng/index.php/component/users/?view=registration"));
                    startActivity(intent);
                }else{
                    Toast.makeText(AccessActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                    MessageUtil.showAlert(AccessActivity.this, "Please provide your username and password");
                    return;
                } else {
                    new Loginizer(email.getText().toString(), password.getText().toString()).execute();
                }

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInputs();
            }
        });
    }

    private void checkInputs() {
        if(fullname.getText().length() == 0){
            MessageUtil.showAlert(AccessActivity.this, "Please enter your full name");
        } else if(username.getText().length() == 0){
            MessageUtil.showAlert(AccessActivity.this, "Please enter your full name");
        } else if(newEmail.getText().length() == 0){
            MessageUtil.showAlert(AccessActivity.this, "Please enter your full name");
        } else if(confirmEmail.getText().length() == 0){
            MessageUtil.showAlert(AccessActivity.this, "Please confirm your email");
        } else if(newPassword.getText().length() == 0){
            MessageUtil.showAlert(AccessActivity.this, "Please enter your password");
        } else if(confirmPassword.getText().length() == 0){
            MessageUtil.showAlert(AccessActivity.this, "Please confirm your password");
        } else if(phonenumber.getText().length() == 0){
            MessageUtil.showAlert(AccessActivity.this, "Please enter your phone number");
        } else if(!EmailUtil.validate(newEmail.getText().toString())){
            MessageUtil.showAlert(AccessActivity.this, "Please enter a valid email");
        } else if(!newEmail.getText().toString().equalsIgnoreCase(confirmEmail.getText().toString())){
            MessageUtil.showAlert(AccessActivity.this, "Emails do not match");
        }  else if(!newPassword.getText().toString().equalsIgnoreCase(confirmPassword.getText().toString())){
            MessageUtil.showAlert(AccessActivity.this, "Passwords do not match");
        }
        else{
            new RegisterUser().execute();
        }
    }

    private class Loginizer extends AsyncTask<Void, String, String> {
        ProgressDialog dialog;
        String username;
        String password;
        AppUser appUser;

        public Loginizer(String email, String password) {
            this.username = email;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(AccessActivity.this, "", "signing in, please wait");
            loginButton.setEnabled(false);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String msg = null;
            try {
                ResponseObject responseObject = WebServiceImpl.loin(username, password);
                if (responseObject != null) {
                     appUser = responseObject.getAppUser();
                    if (appUser != null) {
                        publishProgress("setting up, please wait...");
                        DBService dbService = DBService.getInstance(AccessActivity.this);
                        try {
                            Dao<AppUser, Long> dao = dbService.getDao(AppUser.class);
                            dao.delete(dao.queryForAll());
                            dao.create(appUser);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                msg = e.getMessage();
            }
            return msg;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            StringBuilder builder = new StringBuilder();
            for (String s : values) {
                builder.append(s + "\n");
            }
            MessageUtil.showAlert(AccessActivity.this, builder.toString());
        }

        @Override
        protected void onPostExecute(String message) {
            super.onPostExecute(message);
            loginButton.setEnabled(true);
            dialog.dismiss();
            if (message != null) {
                MessageUtil.showAlert(AccessActivity.this, message);
            } else {
                Pref.saveUser(AccessActivity.this, appUser);
                Intent intent = new Intent(AccessActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }

    @Override
    public int getMenuItemType() {
        return NAVIGATION_MENU_ITEM_LOGIN;
    }

    private class RegisterUser extends AsyncTask <Void, String, String>{

        ProgressDialog dialog;
        String username;
        String password;
        AppUser appUser;

        public RegisterUser() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(AccessActivity.this, "", "signing in, please wait");
            loginButton.setEnabled(false);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return null;
        }
    }
}
