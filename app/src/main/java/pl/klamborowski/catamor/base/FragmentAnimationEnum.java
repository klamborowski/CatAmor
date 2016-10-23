package pl.klamborowski.catamor.base;


import pl.klamborowski.catamor.R;

/**
 * Created by artur on 2015-06-09.
 */
public enum FragmentAnimationEnum {

    NONE(R.anim.none, R.anim.none),
    FROM_LEFT(R.anim.slide_in_left, R.anim.slide_out_right),
    FROM_RIGHT(R.anim.slide_in_right, R.anim.slide_out_left),
    FROM_TOP(R.anim.slide_in_top, R.anim.slide_out_bottom),
    FROM_BOTTOM(R.anim.slide_in_bottom, R.anim.slide_out_top);


    int enterAnimationResId;
    int exitAnimationResId;

    FragmentAnimationEnum(int enterAnimationResId, int exitAnimationResId) {
        this.enterAnimationResId = enterAnimationResId;
        this.exitAnimationResId = exitAnimationResId;
    }

    public int getEnterAnimationResId() {
        return enterAnimationResId;
    }

    public int getExitAnimationResId() {
        return exitAnimationResId;
    }
}
