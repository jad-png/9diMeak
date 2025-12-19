package com.PCM._diMeak.service.interfaces;

import com.PCM._diMeak.dto.request.ParcelRequestDTO;
import com.PCM._diMeak.dto.response.ParcelResponseDTO;
import com.PCM._diMeak.model.enums.ParcelStatus;
import com.PCM._diMeak.model.enums.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ParcelService {
    // ADMIN OPERATIONS
    ParcelResponseDTO createParcel(ParcelRequestDTO request);
    ParcelResponseDTO updateParcel(String id, ParcelRequestDTO request);

    Page<ParcelResponseDTO> getAllParcels(Specialty type, ParcelStatus status, Pageable pageable);
    Page<ParcelResponseDTO> getParcelById(String id, Pageable pageable);

    ParcelResponseDTO assignToCarrier(String parcelId, String carrierId);
    void deleteParcel(String id);

    // SHARED / CARRIER OPERATIONS
    Page<ParcelResponseDTO> getMyParcels(Pageable pageable);
    ParcelResponseDTO updateStatus(String id, ParcelStatus status);
    Page<ParcelResponseDTO> searchByAddress(String address, Pageable pageable);

}
