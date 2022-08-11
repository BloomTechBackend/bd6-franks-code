# Re-Invites

## Introduction

We're continuing to build our app that lets members create events (e.g. parties, book
protests, clubs, dining out). This activity will focus on members being able to view
announcements from event creators for events and event creators being able to retrieve 
the invites they have sent out.

We've added an additional entity since our previous lesson to provide more functionality
to our app: EventAnnouncements.  EventAnnouncements are announcements that event creators 
can post for their events to provide updates and additional information about their event.  

We'll be using four key entities in our app:
* Members: The people who can invite one another to events
    * Partition key: id
* Events: The events that people are being invited to
    * Partition key: id
* Invites: A request for a particular Member to attend a particular Event
    * Partition key: eventId
    * Sort key: memberId
* EventAnnouncements: Announcements posted for events
    * Partition key: eventId
    * Sort key: timePublished

The new functionality we are adding focuses on:
* EventAnnouncements
    * GetEventAnnouncements - retrieves all announcements for a particular event
    * GetEventAnnouncementsBetweenDates - retrieves announcements posted between specific dates for a particular event
* Events
    * A paginated API - GetInvitesForEventActivity

The code base follows the Activity-DAO-DynamoDBMapper pattern that we've come
to know and love. The various Activity classes each implement one operation
that our service supports. That Activity may depend on several of
`MemberDao`'s, `EventDao`'s, `InviteDao`'s, and `EventAnnoucementDao`'s methods to accomplish their
use cases. Each DAO is responsible for one model type, and only interacts
with that model's DynamoDB table.


You'll primarily be updating DAO and Activity code.

## Phase 0: Preliminaries

1. Create the tables we'll be using for this activity by running these aws CLI commands:
   ```none
   aws cloudformation create-stack --region us-west-2 --stack-name dynamodbquery-eventstable --template-body file://cloudformation/events_table.yaml --capabilities CAPABILITY_IAM
   aws cloudformation create-stack --region us-west-2 --stack-name dynamodbquery-invitestable --template-body file://cloudformation/invites_table.yaml --capabilities CAPABILITY_IAM
   aws cloudformation create-stack --region us-west-2 --stack-name dynamodbquery-memberstable --template-body file://cloudformation/members_table.yaml --capabilities CAPABILITY_IAM
   aws cloudformation create-stack --region us-west-2 --stack-name dynamodbquery-eventannouncementstable01 --template-body file://cloudformation/event_announcements_table.yaml --capabilities CAPABILITY_IAM
   ```
2. For verification, run the `Phase0Test` tests and make sure they all pass.

GOAL: Events, Invites, Members, and EventAnnouncements tables are all created in your AWS Account, and
the attributes make sense

Phase 0 is complete when:
- You understand the 4 data types and their relationships
- Events, Invites, Members, EventAnnouncements tables exist with some sample data
- `Phase0Test` tests pass


## Phase 1: Get all announcements for an event

Members want to view all the announcements posted for a particular event, so we'll be implementing
logic to retrieve all announcements for an event. This will be implemented in the 
`GetEventAnnouncementsActivity` class, which is already stubbed out for you.

The activity class will call the `getEventAnnouncements()` method in the `EventAnnouncementDao`.
This method has been declared for you, but you'll be implementing it!

1. Implement `getEventAnnouncements()` in `EventAnnouncementDao` to return all event announcements
   for the given `eventId`.   
2. Implement `GetEventAnnouncementsActivity`'s `handleRequest()` method. Ensure the tests in
   `GetEventAnnouncementsActivityTest` are passing.
3. Verify end-to-end using the integration test by running `Phase1Test`

GOAL: We can request a list of event announcements for a specific event.

Phase 1 is complete when:
- The above unit tests are passing
- `Phase1Test` tests pass


## Phase 2: Get Event Announcements Between Dates

Members want to be able to browse through event announcements published within certain timeframes 
to more easily find information from earlier announcements, so we'll be implementing logic to get 
event announcements that were posted between specified dates. This will be implemented in the 
`GetEventnnouncementsBetweenDatesActivity` class, which is already stubbed out for you.

The activity class will call the `getEventAnnouncementsBetweenDates()` method in the 
`EventAnnouncementDao`. This method has been declared for you, but you'll be implementing it!

1. Implement `getEventAnnouncementsBetweenDates()` in `EventAnnoucementDao` to return only events 
   whose `timePublished` are between the given `startDate` and `endDate`. You will need to use a 
   key condition expression to narrow the scope of your query.
   * NOTE: DynamoDB stores dates as Strings in our tables, so you will need to convert the `startDate` 
     and `endDate` into the proper string format when passing them into your query expression. Use 
     the provided `ZonedDateTimeConverter` class (under `com.amazon.ata.dynamodbquery.converter`) 
     to convert the `ZonedDateTime`s into `Strings`.
2. Implement `GetEventAnnouncementsBetweenDatesActivity`'s `handleRequest()` method.
3. Verify end-to-end using the integration test by running `Phase2Test`.
   
GOAL: We can request a list of announcements posted between particular dates for a particular event.

Phase 2 is complete when:
- The above unit tests are passing
- `Phase2Test` tests pass

## Phase 3: Pagination

Event organizers also want to be able to view all the invitations they've sent out.
Because there may be a large number of invitations sent out, and we want to support 
event organizers being able to view the invitations on smaller screens (like a mobile 
phone) or slower devices, we only want to load 10 invitations on a page at a time.

In the `InviteDao`, we'll call this operation, `getInvitesForEvent()`.
This method has been declared for you, but you'll implement it. Notice that it takes in 
both an `eventId` and `exclusiveStartMemberId`. `exclusiveStartMemberId` should be the 
the last memberId returned from the previous page, and you would use this along with the 
eventId when setting `withExclusiveStartKey` in your `DynamoDBQueryExpression`. When 
retrieving the first page of the query, `exclusiveStartMemberId` would be null, since 
there would be no previous page!

The Activity is also stubbed out in `GetInvitesForEventActivity`.

1. Implement `InviteDao.getInvitesForEvent()` to return the next 10 invites for
   that event ID, based on the passed `exclusiveStartMemberId`. (If `exclusiveStartMemberId`
   is null, you should be getting the first 10 invites in the table.)
2. Implement `GetInvitesForEventActivity`'s `handleRequest()` method.
3. Verify end-to-end using the integration test by running `Phase3Test`.

GOAL: We can request a list of paginated invites for a specific event.

Phase 3 is complete when:
- The above unit tests are passing
- `Phase3Test` tests pass


