package core.us.dto

import com.us.base.library.entities.abstracts.classes.DtoWithStatusAndCode
import groovy.transform.CompileStatic
import groovy.transform.ToString
import jakarta.persistence.Column

@CompileStatic
@ToString
class CountryDto extends DtoWithStatusAndCode{

    private String description

    private String currencyCode

    private String languageCode

    String getDescription() {
        return description
    }

    String getCurrencyCode() {
        return currencyCode
    }

    void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode
    }

    String getLanguageCode() {
        return languageCode
    }

    void setLanguageCode(String languageCode) {
        this.languageCode = languageCode
    }

    void setDescription(String description) {
        this.description = description
    }

}