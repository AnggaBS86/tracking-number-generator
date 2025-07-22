package com.angga.numgen.repository;

import org.springframework.stereotype.Repository;
import com.angga.numgen.model.TrackingEntity;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TrackingNumberRepository extends JpaRepository<TrackingEntity, Long> {
    Optional<TrackingEntity> findByTrackingNumber(String trackingNumber);
}
