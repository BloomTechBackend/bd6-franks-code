package com.amazon.ata.inmemorycaching.classroom.dao;

import com.amazon.ata.inmemorycaching.classroom.dao.models.GroupMembershipCacheKey;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

// This will manage calls to cache manager for data from the application
// It should/will mimic the methods calls from the application to the original DAO
// We need to have the method:
//         public boolean isUserInGroup(final String userId, final String groupId)

// We using the Google Guava cache manager for this example
// If the data requested is not found in the cache the original method will be called



public class GroupMembershipCachingDao {

    // Define a reference to the cache to be used
    //                    cache-key-object      , data-object-stored
    private LoadingCache<GroupMembershipCacheKey, Boolean> theCache;

    // Constructor for the CachingDAO - receives the original DAO as a parameter
    //        The name delegateDao is a commonn name for the parameter, but could be anything
    @Inject
    public GroupMembershipCachingDao(final GroupMembershipDao pedro) {
        // Instantiate a cache and assign it to the reference to the cache data member
        this.theCache = CacheBuilder.newBuilder()
                                    .maximumSize(20000)  // Maximum number of entries in the cache
                                    .expireAfterWrite(3, TimeUnit.HOURS) // Evict entries 3 hours after 1st written
                                    .build(CacheLoader.from(pedro::isUserInGroup)); // go build the cache
                                                                                          // with the delegateDao
                                                                                          //  isUserInGroup
                        // the delegateDao must have a method called isUserInGroup() that receives a cache-key object
    }
// method to search the cache for what the application is looking for
// It should mimic the methods calls from the application to the original DAO:
//         public boolean isUserInGroup(final String userId, final String groupId)
   public boolean isUserInGroup(final String userId, final String groupId){
        // have the cache manager search the cache for the userId, groupId they want
        // search the cache using the get() method (instead of getUnchecked()) we might get an exception
       return theCache.getUnchecked(new GroupMembershipCacheKey(userId,groupId));
   }
}
