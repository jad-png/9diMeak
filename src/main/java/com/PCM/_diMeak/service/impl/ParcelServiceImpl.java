package com.PCM._diMeak.service.impl;

import com.PCM._diMeak.dto.request.ParcelRequestDTO;
import com.PCM._diMeak.dto.response.ParcelResponseDTO;
import com.PCM._diMeak.exception.BadRequestException;
import com.PCM._diMeak.exception.ResourceNotFoundException;
import com.PCM._diMeak.mapper.ParcelMapper;
import com.PCM._diMeak.model.Carrier;
import com.PCM._diMeak.model.Parcel;
import com.PCM._diMeak.model.enums.ParcelStatus;
import com.PCM._diMeak.model.enums.Specialty;
import com.PCM._diMeak.repository.ParcelRepository;
import com.PCM._diMeak.repository.UserRepository;
import com.PCM._diMeak.service.interfaces.ParcelService;
import com.PCM._diMeak.utils.factory.ParcelFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    private final ParcelRepository repo;
    private final UserRepository userRepo;
    private final ParcelFactory factory;
    private final ParcelMapper mapper;

    @Override
    public ParcelResponseDTO createParcel(ParcelRequestDTO request) {
        Parcel parcel = factory.create(request);
        Parcel saved = repo.save(parcel);
        return mapper.toResponse(saved);
    }

    @Override
    public ParcelResponseDTO updateParcel(String id, ParcelRequestDTO request) {
        Parcel parcel = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parcel not found"));

        parcel.setWeight(request.getWeight());
        parcel.setDestinationAddress(request.getDestinationAddress());

        Parcel updated = repo.save(parcel);

        return mapper.toResponse(updated);
    }

    @Override
    public void deleteParcel(String id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Parcel not found");
        }
        repo.deleteById(id);
    }

    @Override
    public ParcelResponseDTO assignToCarrier(String parcelId, String carrierId) {
        Parcel parcel = repo.findById(parcelId)
                .orElseThrow(() -> new ResourceNotFoundException("Parcel not found"));

        Carrier carrier = (Carrier) userRepo.findById(carrierId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter not found"));

        if (parcel.getType() != carrier.getSpecialty()) {
            throw new BadRequestException(
                    "Incompatible specialty: parcel " + parcel.getType()
                            + " / transporter " + carrier.getSpecialty()
            );
        }

        parcel.setCarrierId(carrier.getId());
        parcel.setStatus(ParcelStatus.EN_TRANSIT);

        Parcel assigned = repo.save(parcel);

        return mapper.toResponse(assigned);
    }


    @Override
    public Page<ParcelResponseDTO> getAllParcels(Specialty type, ParcelStatus status, Pageable pageable) {
        if (type != null && status != null) {
            return repo.findByTypeAndStatus(type, status, pageable).map(mapper::toResponse);
        }
        return repo.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public Page<ParcelResponseDTO> getParcelById(String id, Pageable pageable) {
        return repo.findByCarrierId(id, pageable)
                .map(mapper::toResponse);
    }

    @Override
    public Page<ParcelResponseDTO> getMyParcels(Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String carrierId = auth.getName();

        return repo.findByCarrierId(carrierId, pageable)
                .map(mapper::toResponse);
    }

    @Override
    public ParcelResponseDTO updateStatus(String id, ParcelStatus status) {
        Parcel parcel = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Parcel not found"));

        parcel.setStatus(status);

        Parcel updated = repo.save(parcel);
        return mapper.toResponse(updated);
    }

    @Override
    public Page<ParcelResponseDTO> searchByAddress(String address, Pageable pageable) {
        return repo.findByDestinationAddressContainingIgnoreCase(address, pageable).map(mapper::toResponse);
    }
}
