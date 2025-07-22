package com.angga.numgen.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angga.numgen.controller.TrackingResponse;
import com.angga.numgen.model.TrackingEntity;
import com.angga.numgen.repository.TrackingNumberRepository;

@Service
public class TrackingService {

    @Autowired
    private TrackingNumberRepository trackingNumberRepository;

    private final SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1, 1);

    public TrackingResponse generateTrackingNumber(
            String origin,
            String destination,
            String createdAtStr,
            UUID customerId,
            String customerName,
            String customerSlug,
            BigDecimal weight) {

        OffsetDateTime createdAt = OffsetDateTime.parse(createdAtStr);

        String trackingNumber;
        int attempts = 0;
        do {
            trackingNumber = generateCandidate(origin, destination);
            attempts++;
            if (attempts > 5) {
                throw new RuntimeException("Unable to generate unique tracking number after multiple attempts.");
            }
        } while (trackingNumberRepository.findByTrackingNumber(trackingNumber).isPresent());

        TrackingEntity entity = new TrackingEntity();
        entity.setTrackingNumber(trackingNumber);
        entity.setCreatedAt(createdAt);
        entity.setOriginCountryId(origin);
        entity.setDestinationCountryId(destination);
        entity.setCustomerId(customerId);
        entity.setCustomerName(customerName);
        entity.setCustomerSlug(customerSlug);
        entity.setWeight(weight);

        trackingNumberRepository.save(entity);

        return new TrackingResponse(
                trackingNumber,
                createdAt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        );
    }

    private String generateCandidate(String origin, String destination) {
        long id = idGenerator.nextId();
        String base36 = Long.toString(id, 36).toUpperCase();
        String prefix = (origin + destination).toUpperCase().replaceAll("[^A-Z0-9]", "");
        String candidate = prefix + base36;
        return candidate.length() > 16 ? candidate.substring(0, 16) : candidate;
    }
}
