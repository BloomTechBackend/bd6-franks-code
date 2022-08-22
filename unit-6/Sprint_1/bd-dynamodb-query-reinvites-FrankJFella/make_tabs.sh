#!/bin/bash
aws cloudformation create-stack --region us-west-2 --stack-name dynamodbquery-eventstable --template-body file://cloudformation/events_table.yaml --capabilities CAPABILITY_IAM
aws cloudformation create-stack --region us-west-2 --stack-name dynamodbquery-invitestable --template-body file://cloudformation/invites_table.yaml --capabilities CAPABILITY_IAM
aws cloudformation create-stack --region us-west-2 --stack-name dynamodbquery-memberstable --template-body file://cloudformation/members_table.yaml --capabilities CAPABILITY_IAM
aws cloudformation create-stack --region us-west-2 --stack-name dynamodbquery-eventannouncementstable01 --template-body file://cloudformation/event_announcements_table.yaml --capabilities CAPABILITY_IAM