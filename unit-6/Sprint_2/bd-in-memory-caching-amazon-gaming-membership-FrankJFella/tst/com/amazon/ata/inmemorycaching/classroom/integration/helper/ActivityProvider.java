package com.amazon.ata.inmemorycaching.classroom.integration.helper;

import com.amazon.ata.inmemorycaching.classroom.activity.AddUserToGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.CheckUserInGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.CreateGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.GetGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.GetGroupsForUserActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.GetUsersInGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.RemoveUserFromGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.dependency.DaggerServiceComponent;
import com.amazon.ata.inmemorycaching.classroom.dependency.ServiceComponent;

public final class ActivityProvider {
    private static final ServiceComponent DAGGER = DaggerServiceComponent.create();

    private ActivityProvider() {
    }

    public static CheckUserInGroupActivity provideCheckUserInGroupActivity() {
        return DAGGER.provideCheckUserInGroupActivity();
    }

    public static RemoveUserFromGroupActivity provideRemoveUserFromGroupActivity() {
        return DAGGER.provideRemoveUserFromGroupActivity();
    }

    public static CreateGroupActivity provideCreateGroupActivity() {
        return DAGGER.provideCreateGroupActivity();
    }

    public static AddUserToGroupActivity provideAddUserToGroupActivity() {
        return DAGGER.provideAddUserToGroupActivity();
    }

    public static GetGroupActivity provideGetGroupActivity() {
        return DAGGER.provideGetGroupActivity();
    }

    public static GetGroupsForUserActivity provideGetGroupsForUserActivity(){
        return DAGGER.provideGetGroupsForUserActivity();
    }

    public static GetUsersInGroupActivity provideGetUsersInGroupActivity(){
        return DAGGER.provideGetUsersInGroupActivity();
    }
}