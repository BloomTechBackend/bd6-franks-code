package com.amazon.ata.metrics.classroom.dao;

import com.amazon.ata.metrics.classroom.dao.models.Reservation;
import com.amazon.ata.metrics.classroom.dao.models.UpdatedReservation;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.inject.Inject;

/**
 * Manages access to Reservation items.
 */
public class ReservationDao {

    private final DynamoDBMapper mapper;

    /**
     * Creates a ReservationDao with the given DDB mapper.
     *
     * @param mapper DynamoDBMapper
     */
    @Inject
    public ReservationDao(final DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Creates a reservation.
     *
     * @param reservation The reservation to create.
     */
    public Reservation bookReservation(Reservation reservation) {
        if (StringUtils.isBlank(reservation.getReservationId())) {
            reservation.setReservationId(UUID.randomUUID().toString());
        }
        if (reservation.getTotalCost() == null) {
            reservation.setTotalCost(calculateTotalCost(reservation.getCostPerNight(), reservation.getNumberOfNights()));
        }
        mapper.save(reservation);
        return reservation;
    }

    /**
     * Cancels the given reservation.
     * @param reservationId reservationId associated with the reservation to cancel.
     * @return the canceled reservation.
     */
    public Reservation cancelReservation(final String reservationId) {

        Reservation reservationToCancel = mapper.load(Reservation.class, reservationId);
        reservationToCancel.setCanceled(true);
        BigDecimal totalCost = reservationToCancel.getTotalCost();
        // convert cost to negative to indicate refund
        BigDecimal refund = totalCost.negate();
        reservationToCancel.setTotalCost(refund);
        mapper.save(reservationToCancel);

        return reservationToCancel;
    }

    /**
     * Modifies the given reservation.
     *
     * @param reservationId ReservationId of the reservation to modify
     * @param checkInDate check in date for the modified reservation.
     * @param numberOfNights number of nights for the modified reservation.
     * @return the modified reservation
     */
    public UpdatedReservation modifyReservation(final String reservationId, final ZonedDateTime checkInDate,
                                         final Integer numberOfNights) {

        final Reservation originalReservation = mapper.load(Reservation.class, reservationId);
        final Reservation modifiedReservation = new Reservation(originalReservation);

        modifiedReservation.setCheckInDate(checkInDate);
        modifiedReservation.setNumberOfNights(numberOfNights);
        modifiedReservation.setTotalCost(calculateTotalCost(modifiedReservation.getCostPerNight(), numberOfNights));

        mapper.save(modifiedReservation);

        return new UpdatedReservation(originalReservation, modifiedReservation);
    }

    /**
     * Retrieves a reservation by reservationId.
     *
     * @param reservationId The reservationId associated with the reservation.
     * @return reservation for given reservationId
     */
    public Reservation getReservation(final String reservationId) {
        return mapper.load(Reservation.class, reservationId);
    }

    /**
     * Helper method to calculate total cost of reservation.
     *
     * @param costPerNight cost per night for a reservation. Taxes and other fees are already included in this cost.
     * @param numberOfNights number of nights for a reservation
     * @return total cost of reservation
     */
    private BigDecimal calculateTotalCost(BigDecimal costPerNight, Integer numberOfNights) {
        return costPerNight.multiply(new BigDecimal(numberOfNights));
    }
}
