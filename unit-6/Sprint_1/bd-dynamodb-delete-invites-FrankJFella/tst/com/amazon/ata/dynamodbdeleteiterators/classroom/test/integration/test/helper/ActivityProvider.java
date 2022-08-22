package com.amazon.ata.dynamodbdeleteiterators.classroom.test.integration.test.helper;

import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CancelEventActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateEventActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateInviteActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.DeleteMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetEventActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetInviteActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetInvitesForMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dependency.DaggerServiceComponent;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dependency.ServiceComponent;

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
}
