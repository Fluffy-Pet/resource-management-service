package org.fluffy.pet.rms.resourcemanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoConstants {
    public static final String ID = "_id";

    public static final String STATUS = "_status";

    public static final String VERSION = "_version";

    public static final String CREATED_AT = "_createdAt";

    public static final String UPDATED_AT = "_updatedAt";

    public static final String CREATED_BY = "_createdBy";

    public static final String UPDATED_BY = "_updatedBy";

    public static final String MOBILE = "mobile";

    public static final String EMAIL = "email";

    public static final String INFRASTRUCTURE_TABLE = "infrastructure";

    public static final String USER_TABLE = "user";
    public static final String DOCTOR_TABLE = "doctor_table";
    public static final String SPECIALIZATION = "specialization";
    public static final String EXPERIENCE = "experience";
    public static final String CLINIC_ADDRESS = "clinicAddress";
    public static final String LICENSE_NUMBER ="licenseNumber" ;
    public static final String VOLUNTEER_TABLE = "volunteer_table";
    public static final String AVAILABILITY = "availability";
    public static final String SKILLS = "skills";
    public static final String AREA_OF_OPERATION = "areaOfOperation";
    public static final String VETERINARY_CLINIC_TABLE = "veterinary_clinic_table";
    public static final String CLINIC_NAME = "clinicName";
    public static final String ADDRESS = "address";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String SERVICES_OFFERED = "servicesOffered";
    public static final String OPERATING_HOURS = "operatingHours";
    public static final String INFORMATION = "information";
    public static final String SERVED_ORGANIZATIONS = "servedOrganizations";
    public static final String DOCUMENT = "documents";
    public static final String ASSOCIATED_CLINICS = "associatedClinics";
}
