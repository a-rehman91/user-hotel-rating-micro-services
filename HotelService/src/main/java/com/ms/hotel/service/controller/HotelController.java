package com.ms.hotel.service.controller;

import com.ms.hotel.service.configuration.AppsConstant;
import com.ms.hotel.service.entities.Hotel;
import com.ms.hotel.service.payloads.ApiResponse;
import com.ms.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppsConstant.API + "/" + AppsConstant.APP_VERSION + "/" + AppsConstant.HOTEL_BASE_URL)
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.hotelService
                        .create(hotel));
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotel_id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("hotel_id") String hotelId){

        return ResponseEntity.ok(this.hotelService
                .getHotel(hotelId));
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel(){

        return ResponseEntity.ok(this.hotelService
                .getAllHotel());
    }

    @DeleteMapping("/{hotel_id}")
    public ResponseEntity<ApiResponse> deleteHotel(@PathVariable("hotel_id") String hotelId){

        this.hotelService.deleteHotel(hotelId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppsConstant.HOTEL_DELETED, true, HttpStatus.OK), HttpStatus.OK);
    }
}
