package com.amazon.ata.immutabilityandfinal.classroom.primephoto.converter;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.ConversionType;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

/**
 * Vends converter strategies.
 */
public final class ConverterStrategyMapper {
    private final Map<ConversionType, PrimePhotoConverter> converterMap = ImmutableMap.of(
        ConversionType.SEPIA, new SepiaConverter(),
        ConversionType.GREYSCALE, new GreyscaleConverter(),
        ConversionType.INVERSION, new InversionConverter()
    );

    @Inject
    public ConverterStrategyMapper() {}

    /**
     * Retrieves the strategy associated with the provided ConversionType.
     * @param conversionType the type of strategy requested
     * @return the associated strategy
     */
    public PrimePhotoConverter getImageConverter(final ConversionType conversionType) {
        return converterMap.get(conversionType);
    }

    /**
     * Retrieves all conversion strategies.
     * @return all conversion strategies
     */
    public Set<Map.Entry<ConversionType, PrimePhotoConverter>> getImageConverters() {
        return converterMap.entrySet();
    }
}
