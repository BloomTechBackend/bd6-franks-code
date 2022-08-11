package com.amazon.ata.dynamodbdeleteiterators.classroom.dependency;

import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CancelEventActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateEventActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateInviteActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.DeleteMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetEventActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetInviteActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetInvitesForMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetMemberActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the com.amazon.ata.dynamodbdeleteiterators.classroom.dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = DaoModule.class)
public interface ServiceComponent {
    DeleteMemberActivity provideDeleteMemberActivity();
    CreateMemberActivity provideCreateMemberActivity();
    GetMemberActivity provideGetMemberActivity();

    GetInviteActivity provideGetInviteActivity();
    GetInvitesForMemberActivity provideGetInvitesForMemberActivity();
    CreateInviteActivity provideCreateInviteActivity();

    GetEventActivity provideGetEventActivity();
    CreateEventActivity provideCreateEventActivity();
    CancelEventActivity provideCancelEventActivity();
}
