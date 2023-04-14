package core.us.domains


import com.us.base.library.entities.abstracts.classes.BasicEntity
import groovy.transform.CompileStatic
import groovy.transform.ToString
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.MappedEntity

import javax.validation.constraints.NotBlank

@CompileStatic
@ToString
@MappedEntity("core_language")
@Introspected
class Language extends BasicEntity{

    static final String TABLE_NAME = "core_language"
    static final String ENTITY_NAME = "Language"

    @NonNull
    @NotBlank
    private String description

    @Override
    String toString(){

        return "Entity: LANGUAGE - id: " + getId() + " description: " + getDescription() + " code: " + getCode() + " lastUpdatedDate: " + getLastUpdatedDate() + " insertDate: " + getInsertDate()
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    //GETTER AND SETTER
    static String getTableName(){
        return TABLE_NAME
    }

    static String getEntityName(){
        return ENTITY_NAME
    }
}