package pl.klamborowski.catamor.model;

import org.simpleframework.xml.Element;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Artur on 23.10.2016.
 */
@Getter
@Setter
@Element(name = "image")
public class CatImage {
    @Element(name = "url")
    private String  url;
    @Element(name = "id")
    private String  id;
    @Element(name = "source_url")
    private String  sourceUrl;
    @Element(required = false)
    private Integer score;
    @Element(required = false)
    private Boolean favourite;

    public boolean getFavourite() {
        return Boolean.TRUE.equals(favourite);
    }

    //Data binding can't see lombok getters... https://github.com/rzwitserloot/lombok/issues/894
    public String getUrl() {
        return url;
    }


}
