package pl.klamborowski.catamor.section.favourites;

import android.content.Intent;
import android.databinding.ObservableField;
import android.net.Uri;
import android.view.View;

import pl.klamborowski.catamor.model.server.CatImage;

/**
 * Created by Artur on 24.10.2016.
 */
public class FavouriteItemViewModel {
    public final ObservableField<CatImage> catImage = new ObservableField<>();

    public FavouriteItemViewModel(CatImage catImage) {
        this.catImage.set(catImage);
    }

    public void openCatInBrowser(View v) {
        if (catImage.get() == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(catImage.get().getSourceUrl()));
        v.getContext().startActivity(intent);
    }
}
