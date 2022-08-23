package com.amazon.ata.metrics.classroom.integration.helper;

import com.amazon.ata.metrics.classroom.dao.models.Reservation;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.UUID;

public class TestDataProvider {

    private static Random random = new Random();

    public static Reservation buildReservationObject() {

        Reservation result = new Reservation();
        BigDecimal costPerNight = new BigDecimal(random.ints(1, 30, 500).findFirst().getAsInt());
        Integer numberOfNights = random.ints(1, 1, 15).findFirst().getAsInt();
        result.setCostPerNight(costPerNight);
        result.setTotalCost(costPerNight.multiply(new BigDecimal(numberOfNights)));
        result.setCheckInDate(ZonedDateTime.now());
        result.setNumberOfNights(numberOfNights);
        result.setGuestId(UUID.randomUUID().toString());

        return result;
    }


}
