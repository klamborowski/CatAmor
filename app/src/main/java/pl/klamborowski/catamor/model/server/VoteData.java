package pl.klamborowski.catamor.model.server;

import org.simpleframework.xml.Element;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Artur on 23.10.2016.
 */
@Getter
@Setter
public class VoteData {
    @Element
    private Votes votes;
}
