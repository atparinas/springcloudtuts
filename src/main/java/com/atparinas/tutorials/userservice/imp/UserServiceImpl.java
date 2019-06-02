package com.atparinas.tutorials.userservice.imp;

import com.atparinas.tutorials.shared.Utils;
import com.atparinas.tutorials.ui.model.request.UserDetailsRequestModel;
import com.atparinas.tutorials.ui.model.response.UserRest;
import com.atparinas.tutorials.userservice.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserServiceInterface {

    Utils utils;

    public UserServiceImpl(){

    }

    @Autowired
    public UserServiceImpl(Utils utils){
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {

        UserRest returnValue = new UserRest();

        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setLastName(userDetails.getLastName());

        returnValue.setUserId(utils.generateUserId());

        return returnValue;
    }
}
