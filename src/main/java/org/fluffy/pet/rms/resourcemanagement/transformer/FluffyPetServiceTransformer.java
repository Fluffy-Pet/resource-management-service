package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.FluffyPetServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.ServiceChargeRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.ServiceProviderRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.FluffyPetServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.ServiceChargeResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.ServiceProviderResponse;
import org.fluffy.pet.rms.resourcemanagement.model.service.FluffyPetService;
import org.fluffy.pet.rms.resourcemanagement.model.service.ServiceCharge;
import org.fluffy.pet.rms.resourcemanagement.model.service.ServiceProvider;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;

import java.util.UUID;

@Transformer
public class FluffyPetServiceTransformer {
    public FluffyPetServiceResponse convertModelToResponse(FluffyPetService fluffyPetService) {
        return FluffyPetServiceResponse
                .builder()
                .serviceType(fluffyPetService.getServiceType())
                .description(fluffyPetService.getDescription())
                .provider(ObjectUtils.transformIfNotNull(fluffyPetService.getProvider(), this::convertModelToResponse))
                .charges(StreamUtils.emptyIfNull(fluffyPetService.getCharges()).map(this::convertModelToResponse).toList())
                .build();
    }

    public ServiceProviderResponse convertModelToResponse(ServiceProvider serviceProvider) {
        return ServiceProviderResponse
                .builder()
                .providerId(serviceProvider.getProviderId())
                .providerType(serviceProvider.getProviderType())
                .build();
    }

    public ServiceChargeResponse convertModelToResponse(ServiceCharge serviceCharge) {
        return ServiceChargeResponse
                .builder()
                .chargeType(serviceCharge.getChargeType())
                .amountInPaise(serviceCharge.getAmountInPaise())
                .durationType(serviceCharge.getDurationType())
                .build();
    }

    public FluffyPetService convertRequestToModel(FluffyPetServiceRequest fluffyPetServiceRequest) {
        return FluffyPetService
                .builder()
                .id(UUID.randomUUID().toString())
                .serviceType(fluffyPetServiceRequest.getServiceType())
                .description(fluffyPetServiceRequest.getDescription())
                .provider(ObjectUtils.transformIfNotNull(fluffyPetServiceRequest.getProvider(), this::convertRequestToModel))
                .charges(StreamUtils.emptyIfNull(fluffyPetServiceRequest.getCharges()).map(this::convertRequestToModel).toList())
                .build();
    }

    public ServiceProvider convertRequestToModel(ServiceProviderRequest serviceProviderRequest) {
        return ServiceProvider
                .builder()
                .providerId(serviceProviderRequest.getProviderId())
                .providerType(serviceProviderRequest.getProviderType())
                .build();
    }

    public ServiceCharge convertRequestToModel(ServiceChargeRequest serviceChargeRequest) {
        return ServiceCharge
                .builder()
                .chargeType(serviceChargeRequest.getChargeType())
                .amountInPaise(serviceChargeRequest.getAmountInPaise())
                .durationType(serviceChargeRequest.getDurationType())
                .build();
    }

    public void updateFluffyPetService(FluffyPetService fluffyPetService, FluffyPetServiceRequest fluffyPetServiceRequest) {
        fluffyPetService.setServiceType(fluffyPetServiceRequest.getServiceType());
        fluffyPetService.setDescription(fluffyPetServiceRequest.getDescription());
        fluffyPetService.setProvider(ObjectUtils.transformIfNotNull(fluffyPetServiceRequest.getProvider(), this::convertRequestToModel));
    }
}
