package com.amazon.ata.dynamodbquery.test.integration.test.helper;

import com.amazon.ata.dynamodbquery.activity.CancelEventActivity;
import com.amazon.ata.dynamodbquery.activity.CreateEventActivity;
import com.amazon.ata.dynamodbquery.activity.CreateEventAnnouncementActivity;
import com.amazon.ata.dynamodbquery.activity.CreateInviteActivity;
import com.amazon.ata.dynamodbquery.activity.CreateMemberActivity;
import com.amazon.ata.dynamodbquery.activity.DeleteMemberActivity;
import com.amazon.ata.dynamodbquery.activity.GetEventAnnouncementsActivity;
import com.amazon.ata.dynamodbquery.activity.GetEventAnnouncementsBetweenDatesActivity;
import com.amazon.ata.dynamodbquery.activity.GetInvitesForEventActivity;
import com.amazon.ata.dynamodbquery.activity.GetEventActivity;
import com.amazon.ata.dynamodbquery.activity.GetInviteActivity;
import com.amazon.ata.dynamodbquery.activity.GetInvitesForMemberActivity;
import com.amazon.ata.dynamodbquery.activity.GetMemberActivity;
import com.amazon.ata.dynamodbquery.dependency.DaggerServiceComponent;
import com.amazon.ata.dynamodbquery.dependency.ServiceComponent;

public final class ActivityProvider {
    private static final ServiceComponent DAGGER = DaggerServiceComponent.create();

    private ActivityProvider() {
    }

    public static DeleteMemberActivity provideDeleteMemberActivity() {
        return DAGGER.provideDeleteMemberActivity();
    }
    public static CreateMemberActivity provideCreateMemberActivity() {
        return DAGGER.provideCreateMemberActivity();
    }
    public static GetMemberActivity provideGetMemberActivity() {
        return DAGGER.provideGetMemberActivity();
    }

    public static GetInviteActivity provideGetInviteActivity() {
        return DAGGER.provideGetInviteActivity();
    }
    public static GetInvitesForMemberActivity provideGetInvitesForMemberActivity() {
        return DAGGER.provideGetInvitesForMemberActivity();
    }

    public static GetInvitesForEventActivity provideGetInvitesForEventActivity() {
        return DAGGER.provideGetInvitesForEventActivity();
    }
    public static CreateInviteActivity provideCreateInviteActivity() {
        return DAGGER.provideCreateInviteActivity();
    }

    public static GetEventActivity provideGetEventActivity() {
        return DAGGER.provideGetEventActivity();
    }
    public static CreateEventActivity provideCreateEventActivity() {
        return DAGGER.provideCreateEventActivity();
    }
    public static CancelEventActivity provideCancelEventActivity() {
        return DAGGER.provideCancelEventActivity();
    }

    public static GetEventAnnouncementsActivity provideGetEventAnnouncementsActivity() {
        return DAGGER.provideGetEventAnnouncementsActivity();
    }
    public static GetEventAnnouncementsBetweenDatesActivity provideGetEventAnnouncementsBetweenDatesActivity() {
        return DAGGER.provideGetEventAnnouncementsBetweenDatesActivity();
    }
    public static CreateEventAnnouncementActivity provideCreateEventAnnouncementActivity() {
        return DAGGER.provideCreateEventAnnouncementActivity();
    }
}
