package com.springbootproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springbootproject.theData.Question;
import com.springbootproject.theData.theAnswer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;   // access Jackson Object to JSON converter classes
import com.fasterxml.jackson.databind.ObjectWriter;   // access Jackson Object to JSON converter classes

// Data may be sent with an HTTP request:
//
//     GET or DELETE - data is in the URL as a query parameter - ?name=value
//     POST or PUT   - data is in the request body of the request  - use @RequestBody in method parameters
//
// Java deals with objects, servers deal with JSON
// The Spring Boot will automatically convert between Java objects and JSON
//     for request and response data
//
// Java classes that are used in server request/response should be POJOs.
//
// A POJO has a default ctor, standard getters and setters
//        optional .equals(), .hashCode(), .toSting() overrides

// Receiving an HttpServletRequest object as the first parameter to a controller method
//     will allow access to HTTP request sent to the server.
//     methods associated with this object maybe used to gain access to information about the request
//     The logRequest method near the end of this file demonstrates accessing some of the information
//         and writing it to the server log.

@RestController  // Tell server there are controllers in this class
public class myControllers {

    // Method to handle HTTP GET requests for the root path ("/")
    // HttpServletRequest as the first parameter to teh method allows access to HTTP request information
    //             using the object name specified
    @GetMapping(value="/")
    public String aBoringName(HttpServletRequest theRequest) {  // method name does not matter - it's not used anywhere except error message

        logRequest(theRequest);   // Write HTTP request to log

       return "Attendance code for today is 4122";
    }

    // Method to handle HTTP GET requests for the path "/tuesday"
    // HttpServletRequest as the first parameter to teh method allows access to HTTP request information
    //             using the object name specified
    @GetMapping(value="/tuesday")
    public String nameDoesMatter(HttpServletRequest theRequest) {
        logRequest(theRequest);   // Write HTTP request to log
        return "Giang wanted this path";
    }

    // Method to handle HTTP GET requests for the path "/tuesday/bd6/unit6"
    // HttpServletRequest as the first parameter to teh method allows access to HTTP request information
    //             using the object name specified
    @GetMapping(value="/tuesday/bd6/unit6")
    public String whatEverYouWant(HttpServletRequest aRequest) {
        logRequest(aRequest);   // Write HTTP request to log
        return "Almost done.... getting ready to get employed";
    }

    // Method to handle HTTP POST requests for the path "/tuesday/bd6/unit6"
    // OK to have  the same URL, but it must be for a different HTTP request that other
    // HttpServletRequest as the first parameter to teh method allows access to HTTP request information
    //             using the object name specified
    @PostMapping(value="/tuesday/bd6/unit6")
    public String whatEverYouWant2(HttpServletRequest theRequest) {
        logRequest(theRequest);   // Write HTTP request to log
        return "POST Called to say: Almost done.... getting ready to get employed";
    }

    // Method to handle HTTP POST requests for path "/ask"
    //       with data in the request body
    @PostMapping (value="/ask")
    // HttpServletRequest as the first parameter to the method allows access to HTTP request information
    //             using the object name specified
    // @RequestBody tells the server to take the JSON from the request body of the request
    //              and instantiate an object of the class specified
    // Here we are saying take the JSON from the request and instantiate a Question class object
    // The throws JsonProcessingException is required due to use of Jackson ObjectWriter to convert object to JSON
    public theAnswer answerQuestion(HttpServletRequest aRequest, @RequestBody Question questionAsked) throws JsonProcessingException {

        logRequest(aRequest);                                             // Write HTTP request to log
        ObjectWriter ow = new ObjectMapper().writer();                    // Convert object received to JSON
        logMessage("with body: " + ow.writeValueAsString(questionAsked)); // add data from body of request to log

        theAnswer theAnswerToTheQuestion = new theAnswer(); // Instantiate object to be returned

        switch (questionAsked.getTheQuestion()) {
            case "Who is teaching Unit 6?":
                theAnswerToTheQuestion.setAnswerReturned("Frank");
                break;
            case "Who is teaching Unit 3?":
                theAnswerToTheQuestion.setAnswerReturned("Petar");
                break;
            case "Who is teaching Unit 5?":
                theAnswerToTheQuestion.setAnswerReturned("Tom");
                break;
            case "Who is teaching Unit 4?":
                theAnswerToTheQuestion.setAnswerReturned("Mauli");
                break;
            default:
                theAnswerToTheQuestion.setAnswerReturned("I Don't Know");
        }
        return theAnswerToTheQuestion;
    }

    // Method to handle HTTP GET requests for path "/ask"
    //     with required query parameter "unit=" and optional query parameter "cohort="
    ///
    // Valid endpoints:  /ask?unit=#
    //                   /ask?unit=#&cohort=whatever
    //
    @GetMapping (value="/ask") // Method to handle HTTP POST requests for path "/ask"
    // HttpServletRequest as the first parameter to teh method allows access to HTTP request information
    //             using the object name specified
    // @RequestParam tells the server to take the value from a query parameter in the URL
    //              and create a variable of the type specified (query parameters are String)
    // Here we are saying String assigned to unit= in the URL and put it in a int called unit
    //      and the cohort parameter is optional, but if present assign to a String called cohort
    public theAnswer magic8Ball(HttpServletRequest   aRequest,
                                @RequestParam int    unit,
                                @RequestParam (required=false) String cohort) {
        //                                    (default="value") instead of required

        logRequest(aRequest);   // Write HTTP request to log

        theAnswer theAnswerToTheQuestion = new theAnswer(); // Instantiate object to be returned

        String answerString = "For Cohort " + cohort + " the instructor is ";

        switch (unit) {
            case 6:
                theAnswerToTheQuestion.setAnswerReturned(answerString + "Frank");
                break;
            case 3:
                theAnswerToTheQuestion.setAnswerReturned(answerString + "Petar");
                break;
            case 5:
                theAnswerToTheQuestion.setAnswerReturned(answerString + "Tom");
                break;
            case 4:
                theAnswerToTheQuestion.setAnswerReturned(answerString + "Mauli");
                break;
            default:
                theAnswerToTheQuestion.setAnswerReturned(answerString + "I don't know");
        }
        return theAnswerToTheQuestion;
    }

    // Log request with timestamp
    private void logRequest(HttpServletRequest theRequest) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.A");
        String timeNow = now.format(formatter);

        System.out.printf("%s --> %4s %4s request for URL: %s%s\n",
                          timeNow
                        , theRequest.getProtocol()
                        , theRequest.getMethod()
                        , theRequest.getRequestURI()
                        , (theRequest.getQueryString() != null ? ("?" + theRequest.getQueryString()) : ""));

        }

    // log a message passed in as a parameter
    private void logMessage(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.A");
        String timeNow = now.format(formatter);

        System.out.printf("%s --> %s\n", timeNow, message);
    }

    }



