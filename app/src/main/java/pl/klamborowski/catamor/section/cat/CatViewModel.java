package pl.klamborowski.catamor.section.cat;

import android.databinding.ObservableField;
import android.view.View;

import com.orhanobut.logger.Logger;

import pl.klamborowski.catamor.api.GetImageService;
import pl.klamborowski.catamor.api.ServiceFactory;
import pl.klamborowski.catamor.base.BaseCatViewModel;
import pl.klamborowski.catamor.config.Config;
import pl.klamborowski.catamor.databinding.ActivityCatBinding;
import pl.klamborowski.catamor.model.CatImage;
import pl.klamborowski.catamor.model.GetImageResponse;
import pl.klamborowski.catamor.preferences.AccountManager;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Artur on 23.10.2016.
 */

public class CatViewModel extends BaseCatViewModel<ActivityCatBinding> {
    public final ObservableField<CatImage> cat = new ObservableField<>();

    @Override
    public void onViewModelCreated() {
        super.onViewModelCreated();
        showNextCat();

    }

    public void onLikeClicked(View v) {
        if (cat.get() != null) {
            showNextCat();
        }
    }

    public void onDislikeClicked(View v) {
        if (cat.get() != null) {
            showNextCat();
        }
    }

    public void onFavBtnClicked(View v) {
        if (cat.get() != null) {
            // TODO: 23.10.2016 implement fav function
//        changeFavStatus();
        }

    }

    private void showNextCat() {
        cat.set(null);

        ServiceFactory.createRetrofitService(GetImageService.class)
                .getImage(Config.Api.API_KEY, GetImageService.Format.XML.getFormatString(),
                        AccountManager.getInstance().getAccount().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetImageResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, "");
                    }

                    @Override
                    public void onNext(GetImageResponse getImageResponse) {
                        cat.set(getImageResponse.getData().getImages().getImage());
                    }
                });
    }
}
