package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.EmailInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.MobileInput;
import org.fluffy.pet.rms.resourcemanagement.model.common.Email;
import org.fluffy.pet.rms.resourcemanagement.model.common.Mobile;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;

@Transformer
public class CommonTransformer {
    public Mobile convertInputToMobile(MobileInput mobileInput) {
        return Mobile.builder()
                .countryCode(ObjectUtils.transformIfNotNull(mobileInput, MobileInput::countryCode))
                .mobileNumber(ObjectUtils.transformIfNotNull(mobileInput, MobileInput::mobileNumber))
                .build();
    }

    public Email convertInputToEmail(EmailInput emailInput) {
        return Email.builder()
                .emailId(ObjectUtils.transformIfNotNull(emailInput, EmailInput::emailId))
                .build();
    }
}
