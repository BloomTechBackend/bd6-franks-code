package com.amazon.ata.inmemorycaching.classroom.dao;

import com.amazon.ata.inmemorycaching.classroom.dao.models.Group;
import com.amazon.ata.inmemorycaching.classroom.dao.models.GroupType;
import com.amazon.ata.inmemorycaching.classroom.exception.GroupNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.UUID;
import javax.inject.Inject;

/**
 * Manages access to Group items.
 */
public class GroupDao {

    private final DynamoDBMapper mapper;

    /**
     * Creates an GroupDao with the given DDB mapper.
     * @param mapper DynamoDBMapper
     */
    @Inject
    public GroupDao(final DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Creates a group with the provided name and type. An ID is generated for the group.
     * @param groupName the name of the group to be created
     * @param groupType the type of the group to be created
     * @return the persisted group
     */
    public Group createGroup(String groupName, GroupType groupType) {
        Group group = new Group();
        group.setId(UUID.randomUUID().toString());
        group.setName(groupName);
        group.setType(groupType);

        mapper.save(group);

        return group;
    }

    /**
     * Retrieve the group from the database with the provided groupId. Throws a GroupNotFoundException
     * if no group is found by the provided id.
     * @param groupId the id of the group to retrieve
     * @return the group with the provided id
     */
    public Group getGroup(final String groupId) {
        Group group = new Group();
        group.setId(groupId);

        group = mapper.load(group);
        if (group == null) {
            throw new GroupNotFoundException("No group was found for groupId: " + groupId);
        }

        return group;
    }
}
