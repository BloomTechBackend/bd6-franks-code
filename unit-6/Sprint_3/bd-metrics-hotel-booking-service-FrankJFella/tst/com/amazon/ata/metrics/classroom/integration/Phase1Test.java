package com.amazon.ata.metrics.classroom.integration;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazon.ata.metrics.classroom.activity.BookReservationActivity;
import com.amazon.ata.metrics.classroom.activity.CancelReservationActivity;
import com.amazon.ata.metrics.classroom.activity.ModifyReservationActivity;
import com.amazon.ata.metrics.classroom.dao.ReservationDao;
import com.amazon.ata.metrics.classroom.dao.models.Reservation;
import com.amazon.ata.metrics.classroom.dao.models.UpdatedReservation;
import com.amazon.ata.metrics.classroom.integration.helper.TestDataProvider;
import com.amazon.ata.metrics.classroom.metrics.MetricsPublisher;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class Phase1Test {

    public static final String BOOKINGS_COUNT = "BookedReservationCount";
    public static final String CANCELATIONS_COUNT = "CanceledReservationCount";
    public static final String MODIFICATIONS_COUNT = "ModifiedReservationCount";

    private BookReservationActivity bookReservationActivity;
    private CancelReservationActivity cancelReservationActivity;
    private ModifyReservationActivity modifyReservationActivity;
    private MetricsPublisher metricsPublisher;
    private ReservationDao reservationDao;

    @BeforeEach
    private void setup() {
        metricsPublisher = spy(new MetricsPublisher(AmazonCloudWatchClientBuilder.standard().withRegion(Regions.US_EAST_2).build()));
        reservationDao = new ReservationDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2)));
        bookReservationActivity = new BookReservationActivity(reservationDao, metricsPublisher);
        cancelReservationActivity = new CancelReservationActivity(reservationDao, metricsPublisher);
        modifyReservationActivity = new ModifyReservationActivity(reservationDao, metricsPublisher);
    }

    @Test
    public void bookReservationActivity_createsReservation_logsBookingCountMetric() {

        // GIVEN
        Reservation reservation = TestDataProvider.buildReservationObject();

        // WHEN
        Reservation result = bookReservationActivity.handleRequest(reservation);

        // THEN
        verify(metricsPublisher, times(1)).addMetric(BOOKINGS_COUNT, 1, StandardUnit.Count);
    }

    @Test
    public void cancelReservationActivity_canceledReservation_logsCanceledCountMetric() {

        // GIVEN
        Reservation bookedReservation = bookReservationActivity.handleRequest(TestDataProvider.buildReservationObject());

        // WHEN
        Reservation result = cancelReservationActivity.handleRequest(bookedReservation.getReservationId());

        // THEN
        verify(metricsPublisher, times(1)).addMetric(CANCELATIONS_COUNT, 1, StandardUnit.Count);
    }

    @Test
    public void modifyReservationActivity_modifiedReservation_logsModifiedCountMetric() {
        // GIVEN
        Reservation bookedReservation = bookReservationActivity.handleRequest(TestDataProvider.buildReservationObject());

        // WHEN
        UpdatedReservation result = modifyReservationActivity.handleRequest(bookedReservation.getReservationId(),
            bookedReservation.getCheckInDate(), 7);

        // THEN
        verify(metricsPublisher, times(1)).addMetric(MODIFICATIONS_COUNT, 1, StandardUnit.Count);
    }

}
