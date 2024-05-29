package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.clinic.ClinicRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.doctor.AddressRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.infrastructure.ServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.clinic.ClinicResponse;
import org.fluffy.pet.rms.resourcemanagement.model.common.Address;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Clinic;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Service;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;

@Transformer
public class ClinicTransformer {
    public Clinic convertRequestToModel(ClinicRequest clinicRequest){
        return Clinic
                .builder()
                .clinicName(clinicRequest.getName())
                .description(clinicRequest.getDescription())
                .address(ObjectUtils.transformIfNotNull(clinicRequest.getAddress(), this::convertRequestToModel))
                .phoneNumber(clinicRequest.getPhoneNumber())
                .operatingHours(clinicRequest.getOperatingHours())
                .servicesOffered(StreamUtils.emptyIfNull(clinicRequest.getServicesOffered()).map(this::convertRequestToModel).toList())
                .build();
    }
    public Service convertRequestToModel(ServiceRequest serviceRequest) {
        return Service
                .builder()
                .serviceGroup(serviceRequest.getServiceGroup())
                .serviceSubGroup(serviceRequest.getServiceSubGroup())
                .petCategory(serviceRequest.getPetCategory())
                .build();
    }

    public Address convertRequestToModel(AddressRequest addressRequest){
        return Address
                .builder()
                .street(addressRequest.getStreet())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .country(addressRequest.getCountry())
                .zipCode(addressRequest.getZipCode())
                .build();
    }

    public ClinicResponse convertModelToResponse(Clinic clinic){
        return ClinicResponse
                .builder()
                .name(clinic.getClinicName())
                .address(clinic.getAddress())
                .phoneNumber(clinic.getPhoneNumber())
                .openingHours(clinic.getOperatingHours())
                .servicesOffered(clinic.getServicesOffered())
                .build();
    }

    public void updateClinic(Clinic clinic, ClinicRequest clinicRequest){
        clinic.setClinicName(clinicRequest.getName());
        clinic.setDescription(clinicRequest.getDescription());
        clinic.setAddress(ObjectUtils.transformIfNotNull(clinicRequest.getAddress(), this::convertRequestToModel));
        clinic.setPhoneNumber(clinicRequest.getPhoneNumber());
        clinic.setOperatingHours(clinicRequest.getOperatingHours());
        clinic.setServicesOffered(StreamUtils.emptyIfNull(clinicRequest.getServicesOffered()).map(this::convertRequestToModel).toList());
    }
}
