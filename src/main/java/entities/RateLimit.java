package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RateLimit {

    private String saerchLimit;

    private int coreLimit;

    public int getCoreLimit() {
        return coreLimit;
    }

    public String getSaerchLimit() {
        return saerchLimit;
    }

    //see json file in ReadMe.txt
    //top level is resource
    //nested levels are core and search
    //with @JsonProperty then jackson knows to search for fields from the level named resource

    @JsonProperty("resources")
    private void unmarshallNested(Map<String, Object> resources) {
        //cast the Object to Integer
        Map<String, Integer> core =(Map<String, Integer>) resources.get("core"); //find all json data inside the field core
        coreLimit = core.get("limit");
        //cast the Object to String
        Map<String, String> search =(Map<String, String>) resources.get("search"); //find all json data inside the field search
        saerchLimit = String.valueOf(search.get("limit"));
    }
}
