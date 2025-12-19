package com.PCM._diMeak.repository;

import com.PCM._diMeak.model.Parcel;
import com.PCM._diMeak.model.enums.ParcelStatus;
import com.PCM._diMeak.model.enums.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepository extends MongoRepository<Parcel, String> {

    /**
     * Lists parcels with filters for ADMIN (all parcels).
     */
    Page<Parcel> findByTypeAndStatus(Specialty type, ParcelStatus status, Pageable pageable);

    /**
     * Lists parcels by destination address (for both ADMIN and TRANSPORTEUR).
     * The CARRIER restriction (carrierId) will be added dynamically by the Service layer.
     */
    Page<Parcel> findByDestinationAddressContainingIgnoreCase(String address, Pageable pageable);

    Page<Parcel> findByCarrierIdAndDestinationAddressContainingIgnoreCase(String carrierId, String address, Pageable pageable);

    /**
     * Finds parcels assigned to a specific carrier. Required for the TRANSPORTEUR view.
     */
    Page<Parcel> findByCarrierId(String carrierId, Pageable pageable);

    /**
     * Finds parcels assigned to a specific carrier, filtered by type and status.
     */
    Page<Parcel> findByCarrierIdAndTypeAndStatus(String carrierId, Specialty type, ParcelStatus status, Pageable pageable);



    Page<Parcel> findByStatus(ParcelStatus status, Pageable pageable);

}
