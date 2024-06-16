package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.FluffyPetServiceRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.ServiceChargeRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.ServiceImageRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.service.ServiceProviderRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.FluffyPetServiceResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.ServiceChargeResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.ServiceImageResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.service.ServiceProviderResponse;
import org.fluffy.pet.rms.resourcemanagement.model.service.FluffyPetService;
import org.fluffy.pet.rms.resourcemanagement.model.service.ServiceCharge;
import org.fluffy.pet.rms.resourcemanagement.model.service.ServiceImage;
import org.fluffy.pet.rms.resourcemanagement.model.service.ServiceProvider;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;

@Transformer
public class FluffyPetServiceTransformer {
    public FluffyPetServiceResponse convertModelToResponse(FluffyPetService fluffyPetService) {
        return FluffyPetServiceResponse
                .builder()
                .serviceType(fluffyPetService.getServiceType())
                .description(fluffyPetService.getDescription())
                .serviceImages(StreamUtils.emptyIfNull(fluffyPetService.getServiceImages()).map(this::convertModelToResponse).toList())
                .provider(convertModelToResponse(fluffyPetService.getProvider()))
                .charges(StreamUtils.emptyIfNull(fluffyPetService.getCharges()).map(this::convertModelToResponse).toList())
                .build();
    }

    public ServiceImageResponse convertModelToResponse(ServiceImage serviceImage) {
        return ServiceImageResponse
                .builder()
                .url(serviceImage.getUrl())
                .imageDescription(serviceImage.getImageDescription())
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
        return ServiceChargeResponse.builder().chargeType(serviceCharge.getChargeType()).amountInPaise(serviceCharge.getAmountInPaise()).build();
    }

    public FluffyPetService convertRequestToModel(FluffyPetServiceRequest fluffyPetServiceRequest) {
        return FluffyPetService
                .builder()
                .serviceType(fluffyPetServiceRequest.getServiceType())
                .description(fluffyPetServiceRequest.getDescription())
                .serviceImages(StreamUtils.emptyIfNull(fluffyPetServiceRequest.getServiceImages()).map(this::convertRequestToModel).toList())
                .provider(convertRequestToModel(fluffyPetServiceRequest.getProvider()))
                .charges(StreamUtils.emptyIfNull(fluffyPetServiceRequest.getCharges()).map(this::convertRequestToModel).toList())
                .build();
    }

    public ServiceImage convertRequestToModel(ServiceImageRequest serviceImageRequest) {
        return ServiceImage
                .builder()
                .url(serviceImageRequest.getUrl())
                .imageDescription(serviceImageRequest.getImageDescription())
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
        return ServiceCharge.builder().chargeType(serviceChargeRequest.getChargeType()).amountInPaise(serviceChargeRequest.getAmountInPaise()).build();
    }

    public void updateFluffyPetService(FluffyPetService fluffyPetService, FluffyPetServiceRequest fluffyPetServiceRequest) {
        fluffyPetService.setServiceType(fluffyPetServiceRequest.getServiceType());
        fluffyPetService.setDescription(fluffyPetServiceRequest.getDescription());
        fluffyPetService.setServiceImages(StreamUtils.emptyIfNull(fluffyPetServiceRequest.getServiceImages()).map(this::convertRequestToModel).toList());
        fluffyPetService.setProvider(convertRequestToModel(fluffyPetServiceRequest.getProvider()));
    }
}
