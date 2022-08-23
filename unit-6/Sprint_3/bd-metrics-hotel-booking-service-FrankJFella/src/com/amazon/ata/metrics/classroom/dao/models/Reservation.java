package com.amazon.ata.metrics.classroom.dao.models;

import com.amazon.ata.metrics.classroom.convert.ZonedDateTimeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Represents a reservation.
 */
@DynamoDBTable(tableName = "Metrics-Reservations")
public class Reservation {

    private String reservationId;
    private String guestId;
    private ZonedDateTime checkInDate;
    private Integer numberOfNights;
    private Boolean isCanceled;
    private BigDecimal costPerNight;
    private BigDecimal totalCost;

    public Reservation() {

    }

    public Reservation(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.guestId = reservation.getGuestId();
        this.checkInDate = reservation.getCheckInDate();
        this.numberOfNights = reservation.getNumberOfNights();
        this.isCanceled = reservation.isCanceled();
        this.costPerNight = reservation.getCostPerNight();
        this.totalCost = reservation.getTotalCost();
    }

    @DynamoDBHashKey(attributeName = "reservationId")
    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    @DynamoDBAttribute(attributeName = "guestId")
    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    @DynamoDBAttribute(attributeName = "checkInDate")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    public ZonedDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(ZonedDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    @DynamoDBAttribute(attributeName = "numberOfNights")
    public Integer getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(Integer numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "isCanceled")
    public Boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    @DynamoDBAttribute(attributeName = "costPerNight")
    public BigDecimal getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(BigDecimal costPerNight) {
        this.costPerNight = costPerNight;
    }

    @DynamoDBAttribute(attributeName = "totalCost")
    public BigDecimal getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }


}
