package com.amazon.ata.dynamodbdeleteiterators.classroom.dao;

import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Event;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EventDaoTest {
    @InjectMocks
    private EventDao eventDao;

    @Mock
    private DynamoDBMapper mapper;

    @Captor
    ArgumentCaptor<Event> eventCaptor;

    @BeforeEach
    private void setup() {
        initMocks(this);
    }

    @Test
    void cancelEvent_onExistingEvent_cancelsButDoesntDeleteEvent() {
        // GIVEN
        // event ID to cancel
        String eventId = "1234";
        Event event = new Event();
        event.setId(eventId);
        when(mapper.load(Event.class, eventId)).thenReturn(event);

        // WHEN
        Event result = eventDao.cancelEvent(eventId);

        // THEN
        // save() is called, and Event has attribute called isCanceled which is true
        verify(mapper).save(eventCaptor.capture());
        assertTrue(eventCaptor.getValue().isCanceled());

        // delete() is never called
        verify(mapper, never()).delete(any());

        // resulting Event is marked canceled
        assertTrue(result.isCanceled(), "Expected canceled event to be marked canceled, but was not: " + result);
    }
}
