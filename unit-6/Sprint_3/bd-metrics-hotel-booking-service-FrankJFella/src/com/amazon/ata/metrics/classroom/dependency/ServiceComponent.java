package com.amazon.ata.metrics.classroom.dependency;

import com.amazon.ata.metrics.classroom.activity.BookReservationActivity;
import com.amazon.ata.metrics.classroom.activity.CancelReservationActivity;
import com.amazon.ata.metrics.classroom.activity.ModifyReservationActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = {MetricModule.class, DaoModule.class})
public interface ServiceComponent {
    BookReservationActivity provideBookReservationActivity();
    CancelReservationActivity provideCancelReservationActivity();
    ModifyReservationActivity provideModifyReservationActivity();
}
