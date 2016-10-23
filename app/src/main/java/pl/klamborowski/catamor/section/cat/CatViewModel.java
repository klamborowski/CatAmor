package pl.klamborowski.catamor.section.cat;

import android.content.Intent;
import android.databinding.ObservableField;
import android.net.Uri;
import android.view.View;

import com.orhanobut.logger.Logger;

import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.api.ChangeFavouriteStatusService;
import pl.klamborowski.catamor.api.GetImageService;
import pl.klamborowski.catamor.api.ServiceFactory;
import pl.klamborowski.catamor.api.VoteOnCatService;
import pl.klamborowski.catamor.base.BaseCatViewModel;
import pl.klamborowski.catamor.config.Config;
import pl.klamborowski.catamor.databinding.ActivityCatBinding;
import pl.klamborowski.catamor.model.server.CatImage;
import pl.klamborowski.catamor.model.server.GetImageResponse;
import pl.klamborowski.catamor.model.server.VoteOnCatResponse;
import pl.klamborowski.catamor.preferences.AccountManager;
import pl.klamborowski.catamor.util.DialogHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Artur on 23.10.2016.
 */

public class CatViewModel extends BaseCatViewModel<ActivityCatBinding> {
    private static final int                       MAX_SCORE = 10;
    public final         ObservableField<CatImage> cat       = new ObservableField<>();

    @Override
    public void onViewModelCreated() {
        super.onViewModelCreated();
        showNextCat();

    }

    public void onLikeClicked(View v) {
        if (cat.get() != null) {
            if (cat.get().getScore() != 10) {
                voteOnCat(cat.get().getId());
            } else {
                showNextCat();
            }
        }
    }

    private void voteOnCat(String catId) {
        DialogHelper.showProgress(getContext(), R.string.sending_much_love_to_this_cat);
        ServiceFactory.createRetrofitService(VoteOnCatService.class)
                .vote(Config.Api.API_KEY, AccountManager.getInstance().getAccount().getId(),
                        catId, MAX_SCORE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VoteOnCatResponse>() {
                    @Override
                    public void onCompleted() {
                        showNextCat();
                        DialogHelper.hideProgress(getContext());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, "");
                    }

                    @Override
                    public void onNext(VoteOnCatResponse voteOnCatResponse) {
                        cat.get().setScore(voteOnCatResponse.getData().getVotes().getVote().getScore());
                    }
                });
    }

    public void onIgnoreClicked(View v) {
        if (cat.get() != null) {
            showNextCat();
        }
    }

    public void onFavBtnClicked(View v) {
        if (cat.get() != null) {
            changeFavStatus(cat.get().getId());
        }

    }

    private void changeFavStatus(String catId) {
        final boolean isFavourite = cat.get().getFavourite();

        DialogHelper.showProgress(getContext(), isFavourite ?
                R.string.removing_from_fav : R.string.adding_to_fav);

        ChangeFavouriteStatusService.Action action = isFavourite ?
                ChangeFavouriteStatusService.Action.REMOVE
                : ChangeFavouriteStatusService.Action.ADD;

        ServiceFactory.createRetrofitService(ChangeFavouriteStatusService.class)
                .vote(Config.Api.API_KEY, AccountManager.getInstance().getAccount().getId(),
                        catId, action)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        DialogHelper.hideProgress(getContext());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, "");
                    }

                    @Override
                    public void onNext(Void voteOnCatResponse) {
                        cat.get().setFavourite(!isFavourite);
                    }
                });
    }

    public void openCatInBrowser(View v) {
        if (cat.get() == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(cat.get().getSourceUrl()));
        getContext().startActivity(intent);
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
