package com.amazon.ata.immutabilityandfinal.classroom.activity;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.activity.ConvertPrimePhotoActivity;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.converter.ConverterStrategyMapper;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.exception.PhotoConversionClientException;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.ConversionType;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConvertPrimePhotoActivityTest {

    private static final List<ConversionType> SINGLE_CONVERSION_LIST = ImmutableList.of(ConversionType.SEPIA);
    private static final List<ConversionType> MULTIPLE_CONVERSIONS_LIST =  ImmutableList.of(
        ConversionType.SEPIA, ConversionType.GREYSCALE);
    private static String TEST_IMAGE_FILE_PATH = "tst/resources/this_is_a_test.png";

    private ConvertPrimePhotoActivity activity;

    private ConverterStrategyMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new ConverterStrategyMapper();
        activity = new ConvertPrimePhotoActivity(mapper);
    }


    @Test
    public void handleRequest_invalidImagePath_throwsException() {
        // WHEN && THEN
        assertThrows(PhotoConversionClientException.class,
            () -> activity.handleRequest("badImagePath.jpg", SINGLE_CONVERSION_LIST),
            "Expected handleRequest to throw a client exception when provided an invalid file path");
    }

    @Test
    public void handleRequest_emptyConversionTypesList_throwsException() {
        // WHEN && THEN
        assertThrows(PhotoConversionClientException.class,
            () -> activity.handleRequest(TEST_IMAGE_FILE_PATH, Collections.emptyList()),
            "Expected handleRequest to throw a client exception when provided an invalid file path");
    }

    @Test
    public void handleRequest_nullConversionTypesList_throwsException() {
        // WHEN && THEN
        assertThrows(PhotoConversionClientException.class,
            () -> activity.handleRequest(TEST_IMAGE_FILE_PATH, null),
            "Expected handleRequest to throw a client exception when provided an invalid file path");
    }

    @Test
    public void handleRequest_singleConversion_returnsSingleConvertedFile() {
        // WHEN
        List<String> results = activity.handleRequest(TEST_IMAGE_FILE_PATH, SINGLE_CONVERSION_LIST);

        // THEN
        assertEquals(1, results.size(), "Expected a single converted file.");
        assertTrue(results.get(0).contains(SINGLE_CONVERSION_LIST.get(0).toString()), "Expected the result file to " +
            "be for the requested conversion: " + SINGLE_CONVERSION_LIST.get(0).toString());
    }

    @Test
    public void handleRequest_multipleConversions_returnsMultipleConvertedFiles() {
        // WHEN
        List<String> results = activity.handleRequest(TEST_IMAGE_FILE_PATH, MULTIPLE_CONVERSIONS_LIST);

        // THEN
        assertEquals(MULTIPLE_CONVERSIONS_LIST.size(), results.size(), "Expected a single converted file.");
        for(ConversionType conversion : MULTIPLE_CONVERSIONS_LIST) {
            Optional<String> fileToInspectOptional = results.stream()
                .filter(fileName -> fileName.contains(conversion.toString())).findFirst();

            assertTrue(fileToInspectOptional.isPresent(), String.format("ConvertPrimePhotoActivity results missing " +
                "requested %s conversion", conversion.name()));
        }
    }
}
