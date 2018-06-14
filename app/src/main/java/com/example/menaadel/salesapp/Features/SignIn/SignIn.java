package com.example.menaadel.salesapp.Features.SignIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.menaadel.salesapp.DataStorage.sharedPreference.DataSharedPreference;
import com.example.menaadel.salesapp.Features.SalesScreen.SalesActivity;
import com.example.menaadel.salesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.menaadel.salesapp.Utils.Constants.FIRST_LOG_IN;

public class SignIn extends AppCompatActivity implements SignInInteractor{

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edPassword)
    EditText edPassword;

    String sEmail = "", sPassword = "";
    SignInModel signInModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        signInModel = new SignInModel(this);
    }

    private void signIn(){
        sEmail = edEmail.getText().toString();
        sPassword = edPassword.getText().toString();

        signInModel.signIn(sEmail,sPassword);
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
        DataSharedPreference preference = new DataSharedPreference(this);
        preference.saveStringToSharedPreference(FIRST_LOG_IN, "first");
        startActivity(new Intent(this, SalesActivity.class));
        finish();
    }

    @OnClick({R.id.btnLogin})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnLogin:
                signIn();
                break;
            default:
                break;
        }
    }
}
