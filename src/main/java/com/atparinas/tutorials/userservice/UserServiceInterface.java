package com.atparinas.tutorials.userservice;

import com.atparinas.tutorials.ui.model.request.UserDetailsRequestModel;
import com.atparinas.tutorials.ui.model.response.UserRest;


public interface UserServiceInterface {

    UserRest createUser(UserDetailsRequestModel userDetails);
}
