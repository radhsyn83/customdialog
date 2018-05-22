package id.syndro.simpledialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class SimpleDialog {
    private ProgressDialog mProgressDialog;

    public void showDialog(final Activity context) {
        mProgressDialog = new ProgressDialog(context);
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();

        mProgressDialog.setContentView(R.layout.custom_progressdialog);
        mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                context.finish();
            }
        });
    }

    public void stopDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public void textDialog(final Activity context, String msg, Boolean finishOnClose) {
        if (finishOnClose) {
            MaterialDialog mMaterialDialog = new MaterialDialog.Builder(context)
                    .customView(R.layout.custom_textDialog, false)
                    .positiveText("Close")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            context.finish();
                        }
                    })
                    .cancelable(true)
                    .cancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            context.finish();
                        }
                    })
                    .show();

            View view = mMaterialDialog.getView();
            TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
            tv_msg.setText(msg);
        } else {
            MaterialDialog mMaterialDialog = new MaterialDialog.Builder(context)
                    .customView(R.layout.custom_textDialog, false)
                    .positiveText("Close")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .show();

            View view = mMaterialDialog.getView();
            TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
            tv_msg.setText(msg);
        }
    }
}
