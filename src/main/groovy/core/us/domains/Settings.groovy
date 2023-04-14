package core.us.domains

import com.us.base.library.entities.abstracts.classes.BasicEntity
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@CompileStatic
@ToString
@MappedEntity("core_settings")
@Introspected
class Settings extends BasicEntity {

    static final String TABLE_NAME = "core_settings"
    static final String ENTITY_NAME = "Settings"

    @NotNull
    @NotBlank
    private String description

    @NotNull
    @NotBlank
    private String value

     @NotNull
    @NotBlank
    private String key

    @Override
    String toString() {

        return "Entity: SETTING - id = " + getId() + " code = " + getCode() + " description = " + getDescription() + " insertDate =  " + getInsertDate() + " lastUpdatedDate = " + getLastUpdatedDate()
    }

    // GETTERS AND SETTERS
    static String getTableName() {
        return TABLE_NAME
    }

    static String getEntityName() {
        return ENTITY_NAME
    }

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