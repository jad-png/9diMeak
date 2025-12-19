package com.PCM._diMeak.model;

import com.PCM._diMeak.model.enums.ParcelStatus;
import com.PCM._diMeak.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "colis")
@TypeAlias("Parcel")
@Data
@NoArgsConstructor
public class Parcel {

    @Id
    private String id;

    private Specialty type;

    private Double weight;

    private String destinationAddress;

    private ParcelStatus status;

    private Instant createdAt;

    private String carrierId;

    public Parcel(String id, Specialty type, Double weight, String destinationAddress) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.destinationAddress = destinationAddress;
        this.status = ParcelStatus.EN_ATTENTE;
        this.createdAt = Instant.now();
    }
}