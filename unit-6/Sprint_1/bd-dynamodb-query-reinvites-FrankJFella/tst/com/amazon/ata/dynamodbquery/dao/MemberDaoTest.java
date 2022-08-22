package com.amazon.ata.dynamodbquery.dao;

import com.amazon.ata.dynamodbquery.dao.models.Member;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MemberDaoTest {
    @InjectMocks
    private MemberDao memberDao;

    @Mock
    private DynamoDBMapper mapper;

    @BeforeEach
    private void setup() {
        initMocks(this);
    }

    @Test
    void deletePermanently_withMemberId_resultsInDynamoDbDeleteRequest() {
        // GIVEN
        String memberId = "GONNADELETE";

        // WHEN
        memberDao.deletePermanently(memberId);

        // THEN
        // delete() is called.
        // NOTE: Can also use Captor to ensure the right memberId is present
        verify(mapper, times(1)).delete(any(Member.class));
    }
}
