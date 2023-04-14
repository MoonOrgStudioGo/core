package core.us.dto

import com.us.base.library.entities.abstracts.classes.DtoWithStatusAndCode
import groovy.transform.CompileStatic
import groovy.transform.ToString

@CompileStatic
@ToString
class SettingsDto extends DtoWithStatusAndCode {

    private String description

    private String value

    private String key

    String getValue() {
        return value
    }

    void setValue(String value) {
        this.value = value
    }

    String getKey() {
        return key
    }

    void setKey(String key) {
        this.key = key
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }
}
