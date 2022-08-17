package com.amazon.ata.immutabilityandfinal.classroom.primephoto.activity;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.converter.ConverterStrategyMapper;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.converter.PrimePhotoConverter;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.converter.concurrent.ConcurrentConverter;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.exception.PhotoConversionClientException;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.util.PrimePhotoUtil;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.exception.PhotoConversionServiceException;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.PrimePhoto;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.ConversionType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.inject.Inject;

/**
 * Applies the requested conversions to the provided image, returning a list of file locations where the
 * converted images can be found.
 */
public class ConvertPrimePhotoActivity {

    private final ConverterStrategyMapper converterMapper;

    @Inject
    public ConvertPrimePhotoActivity(ConverterStrategyMapper converterMapper) {
        this.converterMapper = converterMapper;
    }

    /**
     * Applies the requested conversions on the file located at the provided filePath. A new image will be generated
     * for each conversion.
     *
     * If more than one conversion is requested, multiple threads will be used to execute each conversion.
     * @param filePath the location of the file to convert
     * @param conversionTypes a list of conversions to apply to the image
     * @return a list of file paths containing the converted images
     */
    public List<String> handleRequest(final String filePath, final List<ConversionType> conversionTypes) {

        if (conversionTypes == null || conversionTypes.isEmpty()) {
            throw new PhotoConversionClientException("A list of conversions must be provided. Provided conversions: " +
                conversionTypes);
        }

        // Read image into memory
        PrimePhoto image = PrimePhotoUtil.fromFile(filePath);

        // Run each requested conversion on the image
        // The multi-threaded code below will call concurrentConverter.run
        for (ConversionType conversion : conversionTypes) {
            PrimePhotoConverter converter = converterMapper.getImageConverter(conversion);
            ConcurrentConverter concurrentConverter =
                new ConcurrentConverter(converter, image, new File(filePath).getName());

            // execute the ConcurrentConverter on a new thread
            submitConcurrentConverter(concurrentConverter);
        }

        // collects the results from the ConcurrentConverter objects we executed
        List<String> results = gatherResults();
        return results;
    }

    // This is a different way to submit a runnable. We will learn about ExecutorService next unit.
    //
    // For now, you just need to know that `executorService.submit(concurrentConverter, null)` will
    // start a thread to execute the provided runnable
    //
    // Rather than using logic that joins threads as a way to wait for the thread to complete, we get
    // back a Future object that we can use to force our code to wait on the result. These Future
    // objects will also come in handy when our concurrent code returns us a value. For now this one is
    // going to return void since we are executing a Runnable, and run() has a void return type
    //
    // We are going to store these futures along with which runnable generated it so we can collect
    // our results later.
    private void submitConcurrentConverter(ConcurrentConverter concurrentConverter) {
        Future<Void> future = executorService.submit(concurrentConverter, null);
        conversionResults.put(concurrentConverter, future);
    }

    // Here we loop through the map containing our ConcurrentConverters, getting the converted image location
    // for each conversion we applied.
    //
    // For each ConcurrentConverters, we use the associated Future to make sure the run method has completed.
    // The call future.get() "blocks". It forces our code to sit and wait until the run method completes.
    // Once we have guaranteed that the run method has finished we know that ConcurrentConverter.convertedImageLocation
    // is populated with the image location and we call 'getConvertedImageLocation' to retrieve it.
    private List<String> gatherResults() {
        List<String> convertedFiles = new ArrayList<>();
        try {
            for (Map.Entry<ConcurrentConverter, Future<Void>> entry : conversionResults.entrySet()) {
                // Wait for the converter to finish
                Future<Void> future = entry.getValue();
                future.get();

                // Collect the resulting convertedImageLocation and add it to our results
                ConcurrentConverter converter = entry.getKey();
                convertedFiles.add(converter.getConvertedImageLocation());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new PhotoConversionServiceException("Unable to process image conversions.", e);
        }
        shutDownExecutorService();
        return convertedFiles;
    }

    // This is an action a service would take when shutting down. We don't have a service running, and so we
    // will shut it down when the activity is complete.
    private void shutDownExecutorService() {
        executorService.shutdownNow();
    }

    private final Map<ConcurrentConverter, Future<Void>> conversionResults = new HashMap<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
}
