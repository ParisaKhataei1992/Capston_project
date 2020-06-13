package ca.i3th.capstonii.Classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class AlertMsg {
    public static Context context;
    private String title, msg, positive, negative;
    AlertMsg(Context context, String title, String msg, String positive, String negative) {
        this.context = context;
        this.msg = msg;
        this.positive = positive;
        this.negative = negative;
        this.title = title;
    }
    public void showAlert () {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.context);
        alert.setTitle(this.title);
        alert.setMessage(this.msg);
        alert.setPositiveButton(this.positive, new DialogInterface.OnClickListener() {



            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setNegativeButton(this.negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create().show();
    }
}
