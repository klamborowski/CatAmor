package pl.klamborowski.catamor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Artur on 23.10.2016.
 */
@Getter
@Setter
@AllArgsConstructor
public class Account {

    private String id;
    private String name;

    public String getName() {
        return name;
    }
}
