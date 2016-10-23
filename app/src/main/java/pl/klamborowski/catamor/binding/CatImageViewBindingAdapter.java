package pl.klamborowski.catamor.binding;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created on 2016-09-16.
 */
public class CatImageViewBindingAdapter {
    @BindingAdapter("android:src")
    public static void setImageBitmap(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(SimpleDraweeView view, String imageUrl) {
        view.setImageURI(imageUrl);
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view, int resourceId) {
        view.setImageResource(resourceId);
    }

    @BindingAdapter({"fresco:placeholderImage"})
    public static void setPlaceholderImage(SimpleDraweeView view, Drawable drawable) {
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        hierarchy.setPlaceholderImage(drawable);
    }

    @BindingAdapter({"fresco:roundingBorderColor"})
    public static void setRoundingBorderColor(SimpleDraweeView view, int colorResId) {
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        RoundingParams roundingParams = hierarchy.getRoundingParams();
        roundingParams.setBorder(colorResId, roundingParams.getBorderWidth());
//        hierarchy.setRoundingParams(roundingParams);
    }
}
