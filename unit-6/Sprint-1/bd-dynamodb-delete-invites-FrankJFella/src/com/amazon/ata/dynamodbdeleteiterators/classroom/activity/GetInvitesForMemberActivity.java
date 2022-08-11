package com.amazon.ata.dynamodbdeleteiterators.classroom.activity;

import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.EventDao;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.InviteDao;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.CanceledInvite;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Event;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Invite;

import java.util.*;
import javax.inject.Inject;

/**
 * Handles requests to get invites for a given member.
 */
public class GetInvitesForMemberActivity {
    private InviteDao inviteDao;
    private EventDao  eventDao;     // Added for Phase 4

    /**
     * Constructs an Activity with the given DAO.
     * @param inviteDao The InviteDao to use to fetch invites
     */
    @Inject
    public GetInvitesForMemberActivity(InviteDao inviteDao, EventDao eventDao) {
        this.inviteDao = inviteDao;
        this.eventDao  = eventDao;   // Added for phase 4
    }

    /**
     * Fetches all invites sent to a given member.
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param memberId The ID of the member to fetch invites for
     * @return List of Invites sent to the member (if any found)
     */
    public List<Invite> handleRequest(final String memberId) {
        /************ original code *****************************************************
         * return inviteDao.getInvitesSentToMember(memberId);  // replaced by new code
         ************* original code ****************************************************/
        // Get the list invitations for member we are given
        List<Invite> invites = inviteDao.getInvitesSentToMember(memberId);
        // if there are no invites for the member return an empty List
        if(invites.isEmpty()) {
            return new ArrayList<>();
        }

        // Look through the Events a member is invited to and determine if the event is cancelled or not

        // create a list of eventIds from the invitations for a member
        List<String> eventIds = new ArrayList<>();  // hold the eventids the member is invited to
        for (Invite anInvite : invites) {           // Loop through the invitations for the memeber
            eventIds.add(anInvite.getEventId());    // Add the eventId from invitation to list of eventIds
        }

        // Find all the Events that the member is invited to using the list of eventIds created above
        List<Event> events = eventDao.getEvents(eventIds);

        // Go through the list of events for the member
        //    if event isCancelled - mark invitation as cancelled in the database
        //                           remove the invitation from the list of invitations for member
        //                           add a CancelledInvite to the list of invitations for the member

        // Store Events for the member in a Map so we can access by eventID
        //   Key   - eventId (match to the Invite eventId)
        //   Value - The Event
        Map<String, Event> eventLookup = new HashMap<>();
        for(Event anEvent : events) {                   // Loop through the events for the member
            eventLookup.put(anEvent.getId(), anEvent);  // Add the event to Map with the eventID as the key
        }

        // Go through list of Invites for member
        //    1. Find matching event in lookup Map.
        //    2. If Event is cancelled:
        //       a. Mark invitation as cancelled in the database
        //       b. Remove the Invite from the list of Invites for the member so it's not sent back
        //       c  Add a CancelledInvite to the list of Invites for the member so it's sent back as cancelled

        // Since we are removing and adding entries from the List as we go through it
        //       Use an Iterator to go through the list of invitations to avoid ConcurrentModificationException
        ListIterator<Invite> inviteIterator = invites.listIterator();   // Simple Iterator is OK too

        while(inviteIterator.hasNext()) {  // Go through list of Invites for member
            Invite currentInvite = inviteIterator.next();                 // Retrieve the next Invite from the list
            Event theEvent = eventLookup.get(currentInvite.getEventId()); // Find the Event for the Invite eventID
            if (theEvent.isCanceled()) {                                  // If Event is cancelled:
                inviteDao.cancelInvite(currentInvite.getEventId(),
                        currentInvite.getMemberId());          // Mark invitation as cancelled in the database
                inviteIterator.remove();                                      // Remove the Invite from the list of member invites
                inviteIterator.add(new CanceledInvite(currentInvite));        // Add current invite back to list as cancelled
            }
        }

        return invites;  // return the list of Event Invites for the member
    }
}
