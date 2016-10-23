package pl.klamborowski.catamor.model.server;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.simpleframework.xml.Element;

import pl.klamborowski.catamor.BR;

/**
 * Created by Artur on 23.10.2016.
 */
public class CatImage extends BaseObservable {
    @Element(name = "url")
    private String  url;
    @Element(name = "id")
    private String  id;
    @Element(name = "source_url")
    private String  sourceUrl;
    @Element(required = false)
    private int     score;
    @Element(required = false)
    private Boolean favourite;


    @Bindable
    //Data binding can't see lombok getters... https://github.com/rzwitserloot/lombok/issues/894
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        notifyPropertyChanged(BR.sourceUrl);
    }

    @Bindable
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        notifyPropertyChanged(BR.score);
    }

    @Bindable
    public boolean getFavourite() {
        return Boolean.TRUE.equals(favourite);
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
        notifyPropertyChanged(BR.favourite);
    }
}
