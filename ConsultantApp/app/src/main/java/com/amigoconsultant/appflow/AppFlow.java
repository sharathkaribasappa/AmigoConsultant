package com.amigoconsultant.appflow;

import android.content.Context;

import com.amigoconsultant.statemanager.StateManager;
import com.amigoconsultant.appflow.states.CalendarState;
import com.amigoconsultant.appflow.states.HomeState;
import com.amigoconsultant.appflow.states.InitializationState;
import com.amigoconsultant.appflow.states.NewCalendarEventState;
import com.amigoconsultant.appflow.states.NotificationsState;
import com.amigoconsultant.appflow.states.PatientsRecordState;
import com.amigoconsultant.appflow.states.ProfileState;
import com.amigoconsultant.appflow.states.RegistrationState;
import com.amigoconsultant.appflow.states.ScheduleState;
import com.amigoconsultant.appflow.states.SettingsState;
import com.amigoconsultant.appflow.states.TutorialState;
import com.amigoconsultant.appflow.states.WelcomeState;

import java.util.HashMap;

/**
 * Created by skaribasappa on 3/4/2017.
 */
public class AppFlow {
    private HashMap<String,String> mTransitions = new HashMap<>();

    public static final String EVENT_WELCOME = "EVENT_WELCOME";
    public static final String EVENT_INITIALIZATION = "EVENT_INITIALIZATION";
    public static final String EVENT_TUTORIAL = "EVENT_TUTORIAL";
    public static final String EVENT_REGISTRATION = "EVENT_REGISTRATION";
    public static final String EVENT_HOME = "EVENT_HOME";
    public static final String EVENT_SETTINGS = "EVENT_SETTINGS";
    public static final String EVENT_NOTIFICATIONS = "EVENT_NOTIFICATIONS";
    public static final String EVENT_PROFILE = "EVENT_PROFILE";
    public static final String EVENT_CALENDAR = "EVENT_CALENDAR";
    public static final String EVENT_PATIENTSHISTORY = "EVENT_PATIENTSHISTORY";
    public static final String EVENT_PERMISSIONS = "EVENT_PERMISSIONS";
    public static final String EVENT_SCHEDULE = "EVENT_CALENDARSCHEDULE";
    public static final String EVENT_ADDCALENDAREVENT = "EVENT_ADDCALENDAREVENT";

    private StateManager mStateManager;

    private static AppFlow ourInstance = new AppFlow();

    public static AppFlow getInstance() {
        return ourInstance;
    }

    private AppFlow() {

    }

    public void InitializeAppFlow(Context context) {
        mStateManager = new ConsultantManager(context);

        addTransition(WelcomeState.IDENTIFIER,EVENT_INITIALIZATION, InitializationState.IDENTIFIER);

        addTransition(InitializationState.IDENTIFIER,EVENT_HOME, HomeState.IDENTIFIER);
        addTransition(InitializationState.IDENTIFIER,EVENT_TUTORIAL, TutorialState.IDENTIFIER);

        addTransition(InitializationState.IDENTIFIER,EVENT_REGISTRATION, RegistrationState.IDENTIFIER);
        addTransition(TutorialState.IDENTIFIER,EVENT_REGISTRATION, RegistrationState.IDENTIFIER);

        addTransition(RegistrationState.IDENTIFIER,EVENT_HOME, HomeState.IDENTIFIER);

        addTransition(HomeState.IDENTIFIER,EVENT_SETTINGS, SettingsState.IDENTIFIER);
        addTransition(SettingsState.IDENTIFIER,EVENT_HOME, HomeState.IDENTIFIER);

        addTransition(HomeState.IDENTIFIER,EVENT_PROFILE, ProfileState.IDENTIFIER);
        addTransition(ProfileState.IDENTIFIER,EVENT_HOME, HomeState.IDENTIFIER);

        addTransition(HomeState.IDENTIFIER,EVENT_NOTIFICATIONS, NotificationsState.IDENTIFIER);
        addTransition(NotificationsState.IDENTIFIER,EVENT_HOME, HomeState.IDENTIFIER);

        addTransition(HomeState.IDENTIFIER,EVENT_CALENDAR, CalendarState.IDENTIFIER);
        addTransition(CalendarState.IDENTIFIER,EVENT_HOME, HomeState.IDENTIFIER);

        addTransition(CalendarState.IDENTIFIER,EVENT_ADDCALENDAREVENT, NewCalendarEventState.IDENTIFIER);
        addTransition(NewCalendarEventState.IDENTIFIER,EVENT_CALENDAR, CalendarState.IDENTIFIER);

        addTransition(HomeState.IDENTIFIER,EVENT_PATIENTSHISTORY, PatientsRecordState.IDENTIFIER);
        addTransition(PatientsRecordState.IDENTIFIER,EVENT_HOME, HomeState.IDENTIFIER);

        addTransition(HomeState.IDENTIFIER,EVENT_SCHEDULE, ScheduleState.IDENTIFIER);
        addTransition(ScheduleState.IDENTIFIER,EVENT_HOME, HomeState.IDENTIFIER);
    }

    private void addTransition(String fromState, String event, String toState) {
        mTransitions.put(event + "_" + fromState,toState);
    }

    public String getStateTransition(String event) {
        return mTransitions.get(event);
    }

    public StateManager getStateManager() {
        return mStateManager;
    }

    public boolean isSessionFlowInitialized() {
        return mStateManager != null;
    }

    public void clean() {
        mStateManager = null;
    }
}