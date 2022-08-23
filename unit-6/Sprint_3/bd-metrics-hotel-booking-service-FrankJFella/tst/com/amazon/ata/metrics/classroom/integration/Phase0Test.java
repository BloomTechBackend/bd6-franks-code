package com.amazon.ata.metrics.classroom.integration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.fail;

public class Phase0Test {
    private static final String TABLE_NAME_PREFIX = "Metrics-";
    private static final String RESERVATIONS_TABLE_NAME = TABLE_NAME_PREFIX + "Reservations";

    private final DynamoDB client = new DynamoDB(AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_EAST_2).build());

    @ParameterizedTest
    @ValueSource(strings = {RESERVATIONS_TABLE_NAME})
    void expectedTable_exists(String tableName) {
        assertTableExists(tableName);
    }

    private void assertTableExists(String tableName) {
        for (Table table : client.listTables()) {
            if (table.getTableName().equals(tableName)) {
                return;
            }
        }
        fail(String.format("Did not find expected table, '%s'", tableName));
    }
}

