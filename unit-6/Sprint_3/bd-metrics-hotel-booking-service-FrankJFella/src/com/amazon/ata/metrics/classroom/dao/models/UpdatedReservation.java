package com.amazon.ata.metrics.classroom.dao.models;

/**
 * Represents an update to an existing reservation.
 * It includes the original Reservation data and the new Reservation data after modifying.
 */
public class UpdatedReservation {

    private Reservation originalReservation;
    private Reservation modifiedReservation;

    public UpdatedReservation(Reservation originalReservation, Reservation modifiedReservation) {
        this.originalReservation = originalReservation;
        this.modifiedReservation = modifiedReservation;
    }

    public Reservation getOriginalReservation() {
        return originalReservation;
    }

    public void setOriginalReservation(Reservation originalReservation) {
        this.originalReservation = originalReservation;
    }

    public Reservation getModifiedReservation() {
        return modifiedReservation;
    }

    public void setModifiedReservation(Reservation modifiedReservation) {
        this.modifiedReservation = modifiedReservation;
    }
}
