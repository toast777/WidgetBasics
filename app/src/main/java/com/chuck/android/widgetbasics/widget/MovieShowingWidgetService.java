package com.chuck.android.widgetbasics.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class MovieShowingWidgetService extends RemoteViewsService{
    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MovieShowingWidgetAdaptor(this.getApplicationContext());
    }
}
