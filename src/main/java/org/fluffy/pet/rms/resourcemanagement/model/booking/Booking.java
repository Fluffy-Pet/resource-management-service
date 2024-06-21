package org.fluffy.pet.rms.resourcemanagement.model.booking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.BookingStatus;
import org.fluffy.pet.rms.resourcemanagement.model.BaseEntity;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Document(MongoConstants.BOOKING_TABLE)
public class Booking extends BaseEntity {
    private BookingStatus bookingStatus;

    private BookingService service;

    private UserInfo userInfo;
}
