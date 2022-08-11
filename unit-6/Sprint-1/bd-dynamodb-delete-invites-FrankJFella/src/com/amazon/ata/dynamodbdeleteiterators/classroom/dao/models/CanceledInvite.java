package com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models;

/**
 * Represents an invite to a canceled event. Unmodifiable.
 */
public class CanceledInvite extends Invite {
    /**
     * Constructs a CanceledInvite from an  Invite.
     * @param invite The invite construct the CanceledInvite from
     */
    public CanceledInvite(Invite invite) {
        super.setEventId(invite.getEventId());
        super.setMemberId(invite.getMemberId());
        super.setAttending(invite.isAttending());
        super.setCanceled(true);
    }

    @Override
    public void setEventId(String ignore) {
        throwException();
    }

    @Override
    public void setMemberId(String memberId) {
        throwException();
    }

    @Override
    public void setAttending(Boolean attending) {
        throwException();
    }

    @Override
    public void setCanceled(Boolean canceled) {
        throwException();
    }

    private void throwException() {
        throw new UnsupportedOperationException("Cannot modify a CanceledInvite");
    }


}
