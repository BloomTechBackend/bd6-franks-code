package com.amazon.ata.metrics.classroom.dependency;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Provides AmazonCloudWatch instance for metric publishing.
 */
@Module
public class MetricModule {

    /**
     * Creates and returns an AmazonCloudWatch instance for the appropriate region.
     * @return a DynamoDBMapper
     */
    @Singleton
    @Provides
    public AmazonCloudWatch provideAmazonCloudWatch() {
        return AmazonCloudWatchClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
    }
}
