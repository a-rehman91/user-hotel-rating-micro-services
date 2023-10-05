package com.ms.user.service.exceptions;

import com.ms.user.service.configuration.AppsConstant;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super(AppsConstant.RESOURCE_NOT_FOUND);
    }

    public ResourceNotFoundException(String message){

        super(message);
    }
}
