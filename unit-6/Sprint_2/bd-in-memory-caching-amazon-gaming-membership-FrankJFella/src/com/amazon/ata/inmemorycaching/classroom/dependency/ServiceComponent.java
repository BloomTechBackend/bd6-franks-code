package com.amazon.ata.inmemorycaching.classroom.dependency;

import com.amazon.ata.inmemorycaching.classroom.dependency.DaoModule;
import com.amazon.ata.inmemorycaching.classroom.activity.AddUserToGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.CheckUserInGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.CreateGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.GetGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.GetGroupsForUserActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.GetUsersInGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.RemoveUserFromGroupActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = DaoModule.class)
public interface ServiceComponent {
    AddUserToGroupActivity provideAddUserToGroupActivity();

    CheckUserInGroupActivity provideCheckUserInGroupActivity();

    CreateGroupActivity provideCreateGroupActivity();

    GetGroupActivity provideGetGroupActivity();

    GetGroupsForUserActivity provideGetGroupsForUserActivity();

    GetUsersInGroupActivity provideGetUsersInGroupActivity();

    RemoveUserFromGroupActivity provideRemoveUserFromGroupActivity();
}
