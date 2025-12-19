package com.PCM._diMeak.controller;

import com.PCM._diMeak.dto.request.ParcelRequestDTO;
import com.PCM._diMeak.dto.response.ParcelResponseDTO;
import com.PCM._diMeak.model.enums.ParcelStatus;
import com.PCM._diMeak.model.enums.Specialty;
import com.PCM._diMeak.service.interfaces.ParcelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcels")
@RequiredArgsConstructor
public class ParcelController {
    private final ParcelService service;

    @PostMapping
    @PostAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParcelResponseDTO> createParcel(@Valid @RequestBody ParcelRequestDTO request) {
        ParcelResponseDTO resp = service.createParcel(request);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParcelResponseDTO> updateParcel(
            @PathVariable String id,
            @Valid @RequestBody ParcelRequestDTO request
    ) {
        ParcelResponseDTO resp = service.updateParcel(id, request);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteParcel(@PathVariable String id) {
        service.deleteParcel(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{parcelId}/assign/{carrierId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParcelResponseDTO> assignToCarrier(
            @PathVariable String parcelId,
            @PathVariable String carrierId
    ) {
        ParcelResponseDTO resp = service.assignToCarrier(parcelId, carrierId);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ParcelResponseDTO>> getAllParcels(
            @RequestParam(required = false) Specialty type,
            @RequestParam(required = false) ParcelStatus status,
            Pageable pageable
    ) {
        Page<ParcelResponseDTO> resp = service.getAllParcels(type, status, pageable);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/carrier/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CARRIER')")
    public ResponseEntity<Page<ParcelResponseDTO>> getParcelById(
            @PathVariable String id,
            Pageable pageable
    ) {
        Page<ParcelResponseDTO> resp = service.getParcelById(id, pageable);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CARRIER')")
    public ResponseEntity<Page<ParcelResponseDTO>> getMyParcels(Pageable pageable) {
        Page<ParcelResponseDTO> resp = service.getMyParcels(pageable);
        return ResponseEntity.ok(resp);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CARRIER')")
    public ResponseEntity<ParcelResponseDTO> updateStatus(
            @PathVariable String id,
            @RequestParam ParcelStatus status
    ) {
        ParcelResponseDTO resp = service.updateStatus(id, status);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CARRIER')")
    public ResponseEntity<Page<ParcelResponseDTO>> searchByAddress(
            @RequestParam String address,
            Pageable pageable
    ) {
        Page<ParcelResponseDTO> resp = service.searchByAddress(address, pageable);
        return ResponseEntity.ok(resp);
    }
}
