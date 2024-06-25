package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.FluffyPetServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.ServiceChargeRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.FluffyPetServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.ServiceChargeResponse;
import org.fluffy.pet.rms.resourcemanagement.model.common.ProviderIdentity;
import org.fluffy.pet.rms.resourcemanagement.model.service.FluffyPetService;
import org.fluffy.pet.rms.resourcemanagement.model.service.ServiceCharge;
import org.fluffy.pet.rms.resourcemanagement.model.service.ServiceProvider;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;

import java.util.UUID;

@Transformer
public class FluffyPetServiceTransformer {
    private final CommonTransformer commonTransformer;

    public FluffyPetServiceTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public FluffyPetServiceResponse convertModelToResponse(FluffyPetService fluffyPetService, ProviderIdentity providerIdentity) {
        return FluffyPetServiceResponse
                .builder()
                .serviceSubType(fluffyPetService.getServiceSubType())
                .description(fluffyPetService.getDescription())
                .providerIdentity(commonTransformer.convertModelToIdentity(providerIdentity))
                .charges(StreamUtils.emptyIfNull(fluffyPetService.getCharges()).map(this::convertModelToResponse).toList())
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

    public FluffyPetService convertRequestToModel(FluffyPetServiceRequest fluffyPetServiceRequest, String providerId) {
        return FluffyPetService
                .builder()
                .id(UUID.randomUUID().toString())
                .serviceSubType(fluffyPetServiceRequest.getServiceSubType())
                .description(fluffyPetServiceRequest.getDescription())
                .provider(ObjectUtils.transformIfNotNull(providerId, this::convertRequestToModel))
                .serviceProviderType(fluffyPetServiceRequest.getServiceProviderType())
                .charges(StreamUtils.emptyIfNull(fluffyPetServiceRequest.getCharges()).map(this::convertRequestToModel).toList())
                .build();
    }

    public ServiceProvider convertRequestToModel(String providerId) {
        return ServiceProvider
                .builder()
                .providerId(providerId)
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
        fluffyPetService.setServiceSubType(fluffyPetServiceRequest.getServiceSubType());
        fluffyPetService.setDescription(fluffyPetServiceRequest.getDescription());
    }
}
