package com.amazon.ata.dynamodbquery.dependency;

import com.amazon.ata.dynamodbquery.activity.CancelEventActivity;
import com.amazon.ata.dynamodbquery.activity.CreateEventActivity;
import com.amazon.ata.dynamodbquery.activity.CreateEventAnnouncementActivity;
import com.amazon.ata.dynamodbquery.activity.CreateInviteActivity;
import com.amazon.ata.dynamodbquery.activity.CreateMemberActivity;
import com.amazon.ata.dynamodbquery.activity.DeleteMemberActivity;
import com.amazon.ata.dynamodbquery.activity.GetEventActivity;
import com.amazon.ata.dynamodbquery.activity.GetEventAnnouncementsActivity;
import com.amazon.ata.dynamodbquery.activity.GetEventAnnouncementsBetweenDatesActivity;
import com.amazon.ata.dynamodbquery.activity.GetInviteActivity;
import com.amazon.ata.dynamodbquery.activity.GetInvitesForEventActivity;
import com.amazon.ata.dynamodbquery.activity.GetInvitesForMemberActivity;
import com.amazon.ata.dynamodbquery.activity.GetMemberActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = DaoModule.class)
public interface ServiceComponent {
    DeleteMemberActivity provideDeleteMemberActivity();
    CreateMemberActivity provideCreateMemberActivity();
    GetMemberActivity provideGetMemberActivity();

    GetInviteActivity provideGetInviteActivity();
    GetInvitesForMemberActivity provideGetInvitesForMemberActivity();
    GetInvitesForEventActivity provideGetInvitesForEventActivity();
    CreateInviteActivity provideCreateInviteActivity();

    GetEventActivity provideGetEventActivity();
    CreateEventActivity provideCreateEventActivity();
    CancelEventActivity provideCancelEventActivity();

    GetEventAnnouncementsActivity provideGetEventAnnouncementsActivity();
    GetEventAnnouncementsBetweenDatesActivity provideGetEventAnnouncementsBetweenDatesActivity();
    CreateEventAnnouncementActivity provideCreateEventAnnouncementActivity();
}
