package com.ms.user.service.external.services;

import com.ms.user.service.configuration.AppsConstant;
import com.ms.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = AppsConstant.HOTEL_SERVICE)
public interface HotelService {

    @GetMapping(AppsConstant.API + "/" + AppsConstant.APP_VERSION + "/" + AppsConstant.HOTEL_BASE_URL + "/{hotel_id}")
    Hotel getHotel(@PathVariable("hotel_id") String hotelId);
}
