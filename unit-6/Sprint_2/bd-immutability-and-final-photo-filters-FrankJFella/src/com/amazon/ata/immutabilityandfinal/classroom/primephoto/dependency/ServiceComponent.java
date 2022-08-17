package com.amazon.ata.immutabilityandfinal.classroom.primephoto.dependency;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.activity.ConvertPrimePhotoActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component()
public interface ServiceComponent {
    ConvertPrimePhotoActivity provideConvertPhotoActivity();
}
