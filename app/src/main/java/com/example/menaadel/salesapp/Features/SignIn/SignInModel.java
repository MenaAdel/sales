package com.example.menaadel.salesapp.Features.SignIn;

import android.content.Context;

import com.example.menaadel.salesapp.DataStorage.sharedPreference.DataSharedPreference;
import com.example.menaadel.salesapp.R;
import com.example.menaadel.salesapp.Utils.Constants;

/**
 * Created by MenaAdel on 6/12/2018.
 */

public class SignInModel {

    private Context context;
    private DataSharedPreference preference;
    private SignInInteractor interactor;

    public SignInModel(Context context) {
        this.context = context;
        preference = new DataSharedPreference(context);
        if (context instanceof SignInInteractor)
            interactor = (SignInInteractor) context;
    }

    public void signIn(String email, String password){
        String sEmail =  preference.retrieveStringFromSharedPreference(Constants.USEREMAIL_KEY);
        String sPassword =  preference.retrieveStringFromSharedPreference(Constants.USERPASSWORD_KEY);

        if (email.isEmpty()) {
            interactor.OnError(1, R.string.email_empty_alert);
            return;
        }
        if(!email.equals(sEmail))
        {
            interactor.OnError(1, R.string.unvalid_input_email);
            return;
        }
        if (password.isEmpty()) {
            interactor.OnError(2, R.string.password_empty_alert);
            return;
        }
        if(!password.equals(sPassword))
        {
            interactor.OnError(2, R.string.unvalid_input_password);
            return;
        }

        interactor.OnSignupDataFilled();
    }
}
