package core.us.dto

import com.us.base.library.entities.abstracts.classes.DtoWithStatusAndCode
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.NonNull

import javax.validation.constraints.NotBlank

@CompileStatic
@ToString
class LanguageDto extends DtoWithStatusAndCode{

    @NonNull
    @NotBlank
    private String description

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }
}
