package pl.klamborowski.catamor.model.server;

import org.simpleframework.xml.Element;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Artur on 23.10.2016.
 */
@Getter
@Setter
public class CatVote {
    @Element(required = false)
    private Integer score;
    @Element(name = "image_id")
    private String  id;
    @Element(name = "action")
    private String  action;
    @Element(name = "sub_id")
    private String  subId;


}
