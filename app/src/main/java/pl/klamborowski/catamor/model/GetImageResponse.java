package pl.klamborowski.catamor.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Artur on 23.10.2016.
 */
@Getter
@Setter
@Root(name = "response")
public class GetImageResponse {
    @Element(name = "data")
    private Data data;
}

