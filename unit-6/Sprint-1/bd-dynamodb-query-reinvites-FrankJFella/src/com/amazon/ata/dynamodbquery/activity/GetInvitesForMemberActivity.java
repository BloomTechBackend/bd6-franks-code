package com.amazon.ata.dynamodbquery.activity;

import com.amazon.ata.dynamodbquery.dao.EventDao;
import com.amazon.ata.dynamodbquery.dao.InviteDao;
import com.amazon.ata.dynamodbquery.dao.models.CanceledInvite;
import com.amazon.ata.dynamodbquery.dao.models.Event;
import com.amazon.ata.dynamodbquery.dao.models.Invite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.inject.Inject;

/**
 * Handles requests to get invites for a given member.
 */
public class GetInvitesForMemberActivity {
    private InviteDao inviteDao;
    private EventDao eventDao;

    /**
     * Constructs an Activity with the given DAO.
     * @param inviteDao The InviteDao to use to fetch invites
     * @param eventDao The EventDao to use to fetch invites
     */
    @Inject
    public GetInvitesForMemberActivity(InviteDao inviteDao, EventDao eventDao) {
        this.inviteDao = inviteDao;
        this.eventDao = eventDao;
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
        List<Invite> invites = inviteDao.getInvitesSentToMember(memberId);
        if(invites.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> eventIds = new ArrayList<>();
        for (Invite invite : invites) {
            eventIds.add(invite.getEventId());
        }

        List<Event> events = eventDao.getEvents(eventIds);
        Map<String, Event> eventLookup = new HashMap<>();
        for (Event event : events) {
            eventLookup.put(event.getId(), event);
        }

        ListIterator<Invite> inviteIterator = invites.listIterator();
        while (inviteIterator.hasNext()) {
            Invite invite = inviteIterator.next();
            Event event = eventLookup.get(invite.getEventId());
            if (event.isCanceled()) {
                inviteDao.cancelInvite(invite.getEventId(), invite.getMemberId());
                // (or just call set(...)  )
                inviteIterator.remove();
                inviteIterator.add(new CanceledInvite(invite));
            }
        }

        return invites;
    }
}
