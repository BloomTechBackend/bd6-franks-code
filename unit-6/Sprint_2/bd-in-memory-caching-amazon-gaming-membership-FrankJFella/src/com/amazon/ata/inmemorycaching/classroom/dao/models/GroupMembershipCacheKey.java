package com.amazon.ata.inmemorycaching.classroom.dao.models;

import java.util.Objects;

// This defines the Cache key we will be using in our caching processing
//
// Due to our search requiring more than one value - userID and groupID
//     We need a class to hold the search values
// In OOP whenever you need to treat multiple values as a single unit - make a class

// The cache key is a unique value used to determine if a piece of data is in the cache
//
// A typical cache key is a POJO with ctor(s), getters, setters (unless immutable)
//           and optionally equals(), hashCode(), toString() and clone() overrides
//
// It's usually also a good idea to make the cache key class immutable (thread-safe)
//      in case it's used in a concurrent programming environment

public final class GroupMembershipCacheKey {

    // cache key values
    private final String userId;
    private final String groupId;

    // Using final on a reference parameter ensures the method cannot change the parameter
    public GroupMembershipCacheKey(final String userId, final String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

// No setters in this class because it is immutable

    public String getUserId() {
        return userId;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        GroupMembershipCacheKey request = (GroupMembershipCacheKey) obj;

        return userId.equals(request.userId) && groupId.equals(request.groupId);
    }
}
