package com.amigoconsultant.statemanager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by skaribasappa on 3/4/2017.
 */
public abstract class BaseAppState {

    public abstract void initialize(StateManager stateManager, Context context);

    public abstract void reEnter(String event, Bundle data);

    public abstract void onEnter(String event, Bundle data);

    public abstract void onExit();

    public abstract View getView();

    public abstract int getUserInterfaceType();

    public abstract String getIdentifier();

    public abstract void handleExternalEvent(String event);

    public abstract boolean gotoNextStateOnEvent(String event, Bundle data);

    public abstract boolean canBeCached();

    public abstract void clean();
}