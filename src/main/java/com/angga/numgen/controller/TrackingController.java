package com.angga.numgen.controller;

import com.angga.numgen.service.TrackingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/next-tracking-number")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping
    public TrackingResponse getTrackingNumber(@Valid @ModelAttribute TrackingRequest req) {
        return trackingService.generateTrackingNumber(
                req.getOrigin_country_id(),
                req.getDestination_country_id(),
                req.getCreated_at(),
                req.getCustomer_id(),
                req.getCustomer_name(),
                req.getCustomer_slug(),
                req.getWeight());
    }
}
