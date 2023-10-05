package com.ms.hotel.service.services.impl;

import com.ms.hotel.service.configuration.AppsConstant;
import com.ms.hotel.service.entities.Hotel;
import com.ms.hotel.service.exceptions.ResourceNotFoundException;
import com.ms.hotel.service.repositories.HotelRepository;
import com.ms.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {

        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);

        return this.hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {

        return this.hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException(AppsConstant.HOTEL_NOT_FOUND + " : " + hotelId));
    }

    @Override
    public List<Hotel> getAllHotel() {

        return this.hotelRepository
                .findAll();
    }

    @Override
    public void deleteHotel(String hotelId) {

        Hotel hotel = this.hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException(AppsConstant.HOTEL_NOT_FOUND + " : " + hotelId));

        this.hotelRepository.delete(hotel);
    }
}
