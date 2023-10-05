package com.ms.hotel.service.exceptions;

import com.ms.hotel.service.configuration.AppsConstant;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super(AppsConstant.RESOURCE_NOT_FOUND);
    }

    public ResourceNotFoundException(String message){

        super(message);
    }
}
