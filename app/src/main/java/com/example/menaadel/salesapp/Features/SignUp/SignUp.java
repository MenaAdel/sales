package com.example.menaadel.salesapp.Features.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.menaadel.salesapp.DataStorage.sharedPreference.DataSharedPreference;
import com.example.menaadel.salesapp.Features.SalesScreen.SalesActivity;
import com.example.menaadel.salesapp.R;
import com.example.menaadel.salesapp.Features.SignIn.SignIn;
import com.example.menaadel.salesapp.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.menaadel.salesapp.Utils.Constants.FIRST_LOG_IN;

public class SignUp extends AppCompatActivity implements SignupInteractor{
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    String sEmail = "";
    String sPassword = "";
    DataSharedPreference preference;
    SignupModel signupModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = new DataSharedPreference(this);
        firstTimeCheck();
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        signupModel = new SignupModel(this);
    }

    public void signUp(){
        sEmail = edEmail.getText().toString().trim();
        sPassword = edPassword.getText().toString().trim();
        signupModel.signUp(sEmail, sPassword);

    }

    private void firstTimeCheck() {
        String first = preference.retrieveStringFromSharedPreference(FIRST_LOG_IN);
        if((!first.equals(""))){
            Intent i = new Intent(this, SalesActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void OnError(int errorFlag, int errorMsgResourseId) {
        switch (errorFlag) {
            case 1:
                edEmail.setError(getString(errorMsgResourseId));
                break;
            case 2:
                edPassword.setError(getString(errorMsgResourseId));
                break;
            default:
                break;
        }
    }

    @Override
    public void OnSignupDataFilled() {
        Intent startLogin = new Intent(SignUp.this, SignIn.class);
        startLogin.putExtra(Constants.USEREMAIL_KEY,sEmail);
        startLogin.putExtra(Constants.USERPASSWORD_KEY,sPassword);
        preference.saveStringToSharedPreference(Constants.USEREMAIL_KEY ,sEmail);
        preference.saveStringToSharedPreference(Constants.USERPASSWORD_KEY ,sPassword);
        startActivity(startLogin);
        finish();

    }

    @OnClick({R.id.btnRegister ,R.id.btnLogin})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnRegister:
                signUp();
                break;
            case R.id.btnLogin:
                startActivity(new Intent(this,SignIn.class));
                break;
            default:
                break;
        }
    }
}
