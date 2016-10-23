package pl.klamborowski.catamor.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pl.klamborowski.catamor.R;

/**
 * Created by Artur on 2016-03-02.
 */
public class DialogHelper {

    private static final HashMap<Context, SweetAlertDialog> dialogs = new HashMap<>();


    private static SweetAlertDialog.OnSweetClickListener confirmClickListener = new SweetAlertDialog.OnSweetClickListener() {
        @Override
        public void onClick(SweetAlertDialog sweetAlertDialog) {
            sweetAlertDialog.dismiss();
        }
    };

    public static void showProgress(Context context, @StringRes int titleResId) {
        SweetAlertDialog sweetAlertDialog = getSweetAlertDialog(context);

        sweetAlertDialog
                .setTitleText(context.getString(titleResId))
                .setContentText("")
                .changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.show();
    }

    @NonNull
    private static SweetAlertDialog getSweetAlertDialog(Context context) {
        SweetAlertDialog sweetAlertDialog = dialogs.get(context);
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(context);
            dialogs.put(context, sweetAlertDialog);
        }
        return sweetAlertDialog;
    }

    public static void hideProgress(Context context) {
        SweetAlertDialog sweetAlertDialog = getSweetAlertDialog(context);
        sweetAlertDialog.hide();
    }

    public static void changeProgressDialog(Context context, String contentText, int dialogType) {
        changeProgressDialog(context, contentText, dialogType, confirmClickListener);
    }

    public static void changeProgressDialog(Context context, @StringRes int contentId, int dialogType) {
        changeProgressDialog(context, contentId, dialogType, confirmClickListener);
    }

    public static void changeProgressDialog(Context context, @StringRes int contentId, int dialogType, SweetAlertDialog.OnSweetClickListener confirmClickListener) {
        changeProgressDialog(context, context.getString(contentId), dialogType, confirmClickListener);
    }

    public static void changeProgressDialog(Context context, String contentText, int dialogType,
                                            SweetAlertDialog.OnSweetClickListener confirmClickListener) {
        SweetAlertDialog sweetAlertDialog = getSweetAlertDialog(context);
        sweetAlertDialog
                .setContentText(contentText)
                .setConfirmText(context.getString(R.string.dialog_ok))
                .setConfirmClickListener(confirmClickListener)
                .changeAlertType(dialogType);
        if (!sweetAlertDialog.isShowing()) sweetAlertDialog.show();
    }

    public static void showDialog(Context context, int msgId, int type) {
        showDialog(context, context.getString(msgId), type);
    }

    public static void showDialog(Context context, String msg, int type) {
        showDialog(context, msg, type, null);
    }

    public static void showDialog(Context context, String msg, int type, SweetAlertDialog.OnSweetClickListener onConfirmClickListener) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, type);
        if (msg.length() > 30) sweetAlertDialog.setContentText(msg).setTitleText("");
        else sweetAlertDialog.setTitleText(msg);
        sweetAlertDialog.changeAlertType(type);
        if (onConfirmClickListener != null)
            sweetAlertDialog.setConfirmClickListener(onConfirmClickListener);
        sweetAlertDialog.setCancelable(true);
        sweetAlertDialog.show();
    }
}
