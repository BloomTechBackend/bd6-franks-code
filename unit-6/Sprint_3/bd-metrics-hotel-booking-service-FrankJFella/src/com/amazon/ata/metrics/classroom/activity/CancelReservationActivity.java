package com.amazon.ata.metrics.classroom.activity;

import com.amazon.ata.metrics.classroom.dao.ReservationDao;
import com.amazon.ata.metrics.classroom.dao.models.Reservation;
import com.amazon.ata.metrics.classroom.metrics.MetricsConstants;
import com.amazon.ata.metrics.classroom.metrics.MetricsPublisher;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

import javax.inject.Inject;

/**
 * Handles requests to cancel a reservation.
 */
public class CancelReservationActivity {

    private ReservationDao reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a CancelReservationActivity
     * @param reservationDao Dao used to update reservations.
     */
    @Inject
    public CancelReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Cancels the given reservation
     * and update the CanceledReservationCount metric
     *        and the ReservationRevenue metric
     *
     * @param reservationId of the reservation to cancel.
     * @return canceled reservation
     */
    public Reservation handleRequest(final String reservationId) {

        Reservation response = reservationDao.cancelReservation(reservationId);

        // Increment the ConceledReservationCount metric
        //                          enum-class-name.enum-name
        metricsPublisher.addMetric(MetricsConstants.CANCELLED_COUNT, 1, StandardUnit.Count);

        // Retrieve the totalCost of the Reservation and update the ReservationRevenue metric
        //      the totalCost returned in the cancelled reservation is already a negative number
        //          so we don't need to make it negative to decrement the ReservationRevenue metric
        metricsPublisher.addMetric(MetricsConstants.RESERVATION_REVENUE,
                response.getTotalCost().doubleValue(), // Convert BigDecimal to double
                StandardUnit.None);

        return response;
    }
}
