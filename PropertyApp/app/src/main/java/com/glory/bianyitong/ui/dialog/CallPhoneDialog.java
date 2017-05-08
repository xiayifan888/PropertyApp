package com.glory.bianyitong.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;

/**
 * Created by lucy on 2016/11/28.
 */
public class CallPhoneDialog extends Dialog{
    private static CallPhoneDialog callPhoneDialog = null;
    private Context context = null;

    public CallPhoneDialog(Context context) {
        super(context);
        this.context = context;
    }

    public CallPhoneDialog(Context context, int theme) {
        super(context, theme);
    }

    public static CallPhoneDialog createDialog(final Context context,final String phone) {

        callPhoneDialog = new CallPhoneDialog(context, R.style.dialog);
        callPhoneDialog.setContentView(R.layout.dialog_callphone);
        //                customProgressDialog.setCancelable(false);
        callPhoneDialog.setCanceledOnTouchOutside(false);
        callPhoneDialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
//		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        TextView tvMsg = (TextView) callPhoneDialog.findViewById(R.id.tv_call_number);
        if (tvMsg != null) {
            tvMsg.setText(phone);
        }

        TextView yes = (TextView)callPhoneDialog.findViewById(R.id.tv_call_yes);
        TextView tv_no = (TextView)callPhoneDialog.findViewById(R.id.tv_call_cancel);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)); // 调用拨号盘
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//                callPhoneDialog.dismiss();

                try {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + phone);
                    intent.setData(data);
                    context.startActivity(intent);
                    callPhoneDialog.dismiss();
                } catch (SecurityException e) {
                    Log.e("resultString", "call: " + e);
                }

            }
        });
        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneDialog.dismiss();
            }
        });
        return callPhoneDialog;
    }

    /**
     * [Summary]
     * setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public CallPhoneDialog setMessage(final String strMessage) {
        TextView tvMsg = (TextView) callPhoneDialog.findViewById(R.id.tv_call_number);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }

        return callPhoneDialog;
    }

}
