package com.amazon.ata.metrics.classroom.integration;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazon.ata.metrics.classroom.activity.BookReservationActivity;
import com.amazon.ata.metrics.classroom.activity.CancelReservationActivity;
import com.amazon.ata.metrics.classroom.activity.ModifyReservationActivity;
import com.amazon.ata.metrics.classroom.dao.ReservationDao;
import com.amazon.ata.metrics.classroom.dao.models.Reservation;
import com.amazon.ata.metrics.classroom.dao.models.UpdatedReservation;
import com.amazon.ata.metrics.classroom.metrics.MetricsPublisher;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class Phase2Test {

    private static final String REVENUE = "ReservationRevenue";
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
    public void bookReservationActivity_createsReservation_logsRevenueMetric() {

        // GIVEN
        Reservation reservation = new Reservation();
        BigDecimal totalCost = new BigDecimal("150");
        reservation.setTotalCost(totalCost);

        // WHEN
        Reservation result = bookReservationActivity.handleRequest(reservation);

        // THEN
        verify(metricsPublisher, times(1)).addMetric(REVENUE, totalCost.doubleValue(), StandardUnit.None);
    }

    @Test
    public void cancelReservationActivity_canceledReservation_logsRevenueMetric() {
        // GIVEN
        Reservation reservation = new Reservation();
        BigDecimal totalCost = new BigDecimal("120");
        reservation.setTotalCost(totalCost);
        Reservation bookedReservation = bookReservationActivity.handleRequest(reservation);

        // WHEN
        Reservation result = cancelReservationActivity.handleRequest(bookedReservation.getReservationId());

        // THEN
        verify(metricsPublisher, times(1)).addMetric(REVENUE, result.getTotalCost().doubleValue(), StandardUnit.None);
    }

    @Test
    public void modifyReservationActivity_extendedReservation_logsRevenueMetric() {
        // GIVEN
        Reservation reservation = new Reservation();
        BigDecimal costPerNight = new BigDecimal("75");
        reservation.setCostPerNight(costPerNight);
        ZonedDateTime checkInDate = ZonedDateTime.parse("2012-06-30T12:30:40Z[UTC]");
        reservation.setCheckInDate(checkInDate);
        reservation.setNumberOfNights(5);
        Reservation bookedReservation = bookReservationActivity.handleRequest(reservation);

        // WHEN
        UpdatedReservation result = modifyReservationActivity.handleRequest(bookedReservation.getReservationId(),
            checkInDate, 7);

        // THEN
        BigDecimal costDifference = result.getModifiedReservation().getTotalCost().subtract(result.getOriginalReservation().getTotalCost());
        verify(metricsPublisher, times(1)).addMetric(REVENUE, costDifference.doubleValue(), StandardUnit.None);
    }

    @Test
    public void modifyReservationActivity_shortenedReservation_logsRevenueMetric() {
        // GIVEN
        Reservation reservation = new Reservation();
        BigDecimal totalCost = new BigDecimal("100");
        reservation.setCostPerNight(totalCost);
        ZonedDateTime checkInDate = ZonedDateTime.parse("2012-06-30T12:30:40Z[UTC]");
        reservation.setCheckInDate(checkInDate);
        reservation.setNumberOfNights(5);
        Reservation bookedReservation = bookReservationActivity.handleRequest(reservation);

        // WHEN
        UpdatedReservation result = modifyReservationActivity.handleRequest(bookedReservation.getReservationId(),
            checkInDate, 3);

        // THEN
        BigDecimal costDifference = result.getModifiedReservation().getTotalCost().subtract(result.getOriginalReservation().getTotalCost());
        verify(metricsPublisher, times(1)).addMetric(REVENUE, costDifference.doubleValue(), StandardUnit.None);
    }
}
