package com.example.menaadel.salesapp.Features.SignUp;

/**
 * Created by Mina-pc on 8/17/2017.
 */

public interface SignupInteractor {

    void OnError(int errorFlag, int errorMsgResourseId);// errorFlag  determine  field which has this error

    void OnSignupDataFilled(); // callback method which called when user fill all data fields

}
