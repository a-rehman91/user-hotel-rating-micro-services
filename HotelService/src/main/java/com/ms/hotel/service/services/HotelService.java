package com.ms.hotel.service.services;

import com.ms.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);
    Hotel getHotel(String hotelId);
    List<Hotel> getAllHotel();
    void deleteHotel(String hotelId);
}
