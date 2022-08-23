package com.amazon.ata.metrics.classroom.activity;

import com.amazon.ata.metrics.classroom.dao.ReservationDao;
import com.amazon.ata.metrics.classroom.dao.models.UpdatedReservation;
import com.amazon.ata.metrics.classroom.metrics.MetricsConstants;
import com.amazon.ata.metrics.classroom.metrics.MetricsPublisher;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

import java.time.ZonedDateTime;
import javax.inject.Inject;

/**
 * Handles requests to modify a reservation
 */
public class ModifyReservationActivity {

    private ReservationDao reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Construct ModifyReservationActivity.
     * @param reservationDao Dao used for modify reservations.
     */
    @Inject
    public ModifyReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Modifies the given reservation
     * and update the ModifiedReservationCount metric
     *        and the ReservationRevenue metric
     *
     * @param reservationId Id to modify reservations for
     * @param checkInDate modified check in date
     * @param numberOfNights modified number of nights
     * @return UpdatedReservation that includes the old reservation and the updated reservation details.
     */
    public UpdatedReservation handleRequest(final String reservationId, final ZonedDateTime checkInDate,
                                            final Integer numberOfNights) {

        UpdatedReservation updatedReservation = reservationDao.modifyReservation(reservationId, checkInDate,
            numberOfNights);

        // Increment the ModifiedReservationCount metric
        //                          enum-class-name.enum-name
        metricsPublisher.addMetric(MetricsConstants.MODIFIED_COUNT, 1, StandardUnit.Count);

        // We need to update the ReservationRevenue metric with the change in revenue
        //    from the original reservation and modified

        // Calculate the difference between the revenue in the old reservation and the new one
        double revenueDifference = (updatedReservation.getModifiedReservation().getTotalCost())
                          .subtract(updatedReservation.getOriginalReservation().getTotalCost())
                          .doubleValue();
        // Log the metric
        metricsPublisher.addMetric(MetricsConstants.RESERVATION_REVENUE, revenueDifference, StandardUnit.None);

        return updatedReservation;
    }
}
