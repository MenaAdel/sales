package com.example.menaadel.salesapp.Features.SignUp;

import android.content.Context;

import com.example.menaadel.salesapp.R;
import com.example.menaadel.salesapp.Utils.UtilsFunctions;


/**
 * Created by Mina-pc on 8/21/2017.
 */

public class SignupModel {
    private Context context;
    private SignupInteractor interactor;


    public SignupModel(Context context) {
        this.context = context;
        if (context instanceof SignupInteractor)
            interactor = (SignupInteractor) context;

    }

    public void signUp(String email, String password) {

        if (email.isEmpty()) {
            interactor.OnError(1, R.string.email_empty_alert);
            return;
        }
        if(!UtilsFunctions.isValidEmail(email))
        {
            interactor.OnError(1, R.string.unvalid_input_email);
            return;
        }
        if (password.isEmpty()) {
            interactor.OnError(2, R.string.password_empty_alert);
            return;
        }
        if(!UtilsFunctions.isSizeMoreThanOrEqualSixLettters(password))
        {
            interactor.OnError(2, R.string.unvalid_input_password);
            return;
        }

        interactor.OnSignupDataFilled();
    }
}
