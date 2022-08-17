package com.amazon.ata.immutabilityandfinal.classroom.integration.helper;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.activity.ConvertPrimePhotoActivity;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.dependency.DaggerServiceComponent;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.dependency.ServiceComponent;

public final class ActivityProvider {
    private static final ServiceComponent DAGGER = DaggerServiceComponent.create();

    private ActivityProvider() {
    }

    public static ConvertPrimePhotoActivity provideConvertPhotoActivity() {
        return DAGGER.provideConvertPhotoActivity();
    }
}
