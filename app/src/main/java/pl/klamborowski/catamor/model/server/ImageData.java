package pl.klamborowski.catamor.model.server;

import org.simpleframework.xml.ElementList;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Artur on 23.10.2016.
 */
@Getter
@Setter
public class ImageData {
    @ElementList(name = "images")
    List<CatImage> images;
}
