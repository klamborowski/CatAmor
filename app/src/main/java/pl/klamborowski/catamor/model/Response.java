package pl.klamborowski.catamor.model;

import org.simpleframework.xml.Element;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Artur on 23.10.2016.
 */
@Getter
@Setter
public class Response {
    @Element
    private Data data;
}
