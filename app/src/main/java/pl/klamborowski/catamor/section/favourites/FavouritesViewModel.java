package pl.klamborowski.catamor.section.favourites;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.orhanobut.logger.Logger;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.tatarka.bindingcollectionadapter.ItemView;
import pl.klamborowski.catamor.BR;
import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.api.GetFavouritesService;
import pl.klamborowski.catamor.api.ServiceFactory;
import pl.klamborowski.catamor.base.BaseCatViewModel;
import pl.klamborowski.catamor.config.Config;
import pl.klamborowski.catamor.databinding.FragmentFavourtiesBinding;
import pl.klamborowski.catamor.model.server.CatImage;
import pl.klamborowski.catamor.model.server.GetFavouritesResponse;
import pl.klamborowski.catamor.preferences.AccountManager;
import pl.klamborowski.catamor.util.DialogHelper;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Artur on 24.10.2016.
 */

public class FavouritesViewModel extends BaseCatViewModel<FragmentFavourtiesBinding> {
    public final ItemView                               itemView = ItemView.of(BR.viewModel, R.layout.item_fav);
    public final ObservableList<FavouriteItemViewModel> items    = new ObservableArrayList<>();

    @Override
    public void onViewModelCreated() {
        super.onViewModelCreated();


        DialogHelper.showProgress(getContext(), R.string.loading_favourites);
        ServiceFactory.createRetrofitService(GetFavouritesService.class)
                .getFavourites(Config.Api.API_KEY, AccountManager.getInstance().getAccount().getId())
                .flatMap(new Func1<GetFavouritesResponse, Observable<CatImage>>() {
                    @Override
                    public Observable<CatImage> call(GetFavouritesResponse getFavouritesResponse) {
                        return rx.Observable.from(getFavouritesResponse.getData().getImages());
                    }
                })
                .flatMap(new Func1<CatImage, Observable<FavouriteItemViewModel>>() {
                    @Override
                    public Observable<FavouriteItemViewModel> call(CatImage catImage) {
                        return rx.Observable.just(new FavouriteItemViewModel(catImage));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FavouriteItemViewModel>() {
                    @Override
                    public void onCompleted() {
                        DialogHelper.hideProgress(getContext());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, "");
                        DialogHelper.showDialog(getContext(), R.string.error_occurred,
                                SweetAlertDialog.ERROR_TYPE);
                    }

                    @Override
                    public void onNext(FavouriteItemViewModel favouriteItemViewModel) {
                        items.add(favouriteItemViewModel);
                    }
                });
    }
}
