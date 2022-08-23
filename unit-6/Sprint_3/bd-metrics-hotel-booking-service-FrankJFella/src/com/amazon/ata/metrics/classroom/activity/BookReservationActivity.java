package com.amazon.ata.metrics.classroom.activity;

import com.amazon.ata.metrics.classroom.dao.ReservationDao;
import com.amazon.ata.metrics.classroom.dao.models.Reservation;
import com.amazon.ata.metrics.classroom.metrics.MetricsConstants;
import com.amazon.ata.metrics.classroom.metrics.MetricsPublisher;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

import javax.inject.Inject;

/**
 * Handles requests to book a reservation.
 */
public class BookReservationActivity {

    private ReservationDao   reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a BookReservationActivity
     * @param reservationDao Dao used to create reservations.
     */
    @Inject
    public BookReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Creates a reservation with the provided details.
     * and update the BookedReservationCount metric
     *        and the ReservationRevenue metric
     * @param reservation Reservation to create.
     * @return
     */
    public Reservation handleRequest(Reservation reservation) {

        // Create a new reservation and store it in the response object
        Reservation response = reservationDao.bookReservation(reservation);

        // Increment the BookedReservationCount metric
        //                          enum-class-name.enum-name
        metricsPublisher.addMetric(MetricsConstants.BOOKED_RESERVATION_COUNT, 1, StandardUnit.Count);

        // Retrieve the totalCost of the Reservation and update the ReservationRevenue metric
        metricsPublisher.addMetric(MetricsConstants.RESERVATION_REVENUE,
                                   response.getTotalCost().doubleValue(), // Convert BigDecimal to double
                                   StandardUnit.None);

        return response;
    }
}
