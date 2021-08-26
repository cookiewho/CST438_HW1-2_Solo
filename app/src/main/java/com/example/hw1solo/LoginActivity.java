package com.example.hw1solo;

import static android.graphics.Color.parseColor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    private  TextView alertBox;
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private List<String> validUsernames = new ArrayList<>();
    String correctPassword = BuildConfig.TEST_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        alertBox = findViewById(R.id.alertBox);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<User>> call = jsonPlaceHolderApi.getUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()) {
                    alertBox.setText("Code: "+ response.code());
                }
                List<User> users = response.body();
                for (User user: users) {
                    validUsernames.add(user.getUsername());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                alertBox.setText(t.getMessage());
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertBox.setText("");
                alertBox.setBackgroundColor(parseColor("#80000000"));
                username.setBackgroundColor(parseColor("#80000000"));
                password.setBackgroundColor(parseColor("#80000000"));


                if (usernameCheck(validUsernames, username.getText().toString())){
                    if(passwordCheck(correctPassword, password.getText().toString())){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else{
                        alertBox.setText("Password is invalid");
                        alertBox.setBackgroundColor(parseColor("#F7FF282C"));
                        password.setBackgroundColor(parseColor("#F7FF282C"));
                    }
                }
                else {
                    alertBox.setText("Username is invalid");
                    alertBox.setBackgroundColor(parseColor("#F7FF282C"));
                    username.setBackgroundColor(parseColor("#F7FF282C"));
                }
            }
        });




    }

    public static boolean usernameCheck(List<String> validUsernames, String inputUsername){
        for (String validUsername: validUsernames) {
            if (validUsername.equals(inputUsername)) {
                return true;
            }
        }
        return false;
    }
    public static boolean passwordCheck(String systemPassword, String inputPassword){
        if (systemPassword.equals(inputPassword)) {
            return true;
        }
        return false;
    }
}