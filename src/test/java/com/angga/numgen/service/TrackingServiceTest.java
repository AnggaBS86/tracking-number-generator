package com.angga.numgen.service;

import com.angga.numgen.controller.TrackingResponse;
import com.angga.numgen.model.TrackingEntity;
import com.angga.numgen.repository.TrackingNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackingServiceTest {

    private TrackingNumberRepository trackingNumberRepository;
    private TrackingService trackingService;

    @BeforeEach
    void setUp() {
        trackingNumberRepository = mock(TrackingNumberRepository.class);
        trackingService = new TrackingService();
        // Use reflection to inject mock, since field is not constructor/setter injected
        var field = TrackingService.class.getDeclaredFields()[0];
        field.setAccessible(true);
        try {
            field.set(trackingService, trackingNumberRepository);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void generateTrackingNumber_ShouldReturnValidResponse() {
        String origin = "ID";
        String destination = "SG";
        String createdAtStr = OffsetDateTime.now().toString();
        UUID customerId = UUID.randomUUID();
        String customerName = "Angga";
        String customerSlug = "angga";
        BigDecimal weight = new BigDecimal("1.234");

        when(trackingNumberRepository.findByTrackingNumber(anyString())).thenReturn(Optional.empty());

        TrackingResponse response = trackingService.generateTrackingNumber(
                origin, destination, createdAtStr, customerId, customerName, customerSlug, weight
        );

        assertNotNull(response);

        ArgumentCaptor<TrackingEntity> captor = ArgumentCaptor.forClass(TrackingEntity.class);
        verify(trackingNumberRepository).save(captor.capture());
        TrackingEntity savedEntity = captor.getValue();
        assertEquals(origin, savedEntity.getOriginCountryId());
        assertEquals(destination, savedEntity.getDestinationCountryId());
        assertEquals(customerId, savedEntity.getCustomerId());
        assertEquals(customerName, savedEntity.getCustomerName());
        assertEquals(customerSlug, savedEntity.getCustomerSlug());
        assertEquals(weight, savedEntity.getWeight());
    }

    @Test
    void generateTrackingNumber_WhenDuplicate_ShouldRetryUpTo5Times() {
        String origin = "MY";
        String destination = "ID";
        String createdAtStr = OffsetDateTime.now().toString();

        when(trackingNumberRepository.findByTrackingNumber(anyString()))
                .thenReturn(Optional.of(new TrackingEntity()))
                .thenReturn(Optional.of(new TrackingEntity()))
                .thenReturn(Optional.of(new TrackingEntity()))
                .thenReturn(Optional.of(new TrackingEntity()))
                .thenReturn(Optional.of(new TrackingEntity()));

        assertThrows(RuntimeException.class, () ->
            trackingService.generateTrackingNumber(
                origin, destination, createdAtStr,
                UUID.randomUUID(), "Test", "test", BigDecimal.ONE
            )
        );

        verify(trackingNumberRepository, times(5)).findByTrackingNumber(anyString());
    }
}
