package com.nightonke.ex_05;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by 伟平 on 2015/11/13.
 */
public class MyDynamicBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        remoteViews.setTextViewText(R.id.widget_text, intent.getStringExtra("MESSAGE"));

        AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(
                context.getApplicationContext(), MyWidgetProvider.class), remoteViews);
    }
}
