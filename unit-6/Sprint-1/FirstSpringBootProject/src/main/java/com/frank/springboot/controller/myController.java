package com.frank.springboot.controller;

// This class hold controllers for our server app

import com.frank.springboot.model.DataReceived;
import com.frank.springboot.model.DataToBeReturned;
import org.springframework.web.bind.annotation.*;

@RestController // Tell the server this class contains controllers for RESTful HTTP requests
public class myController {

    // Data may be included with an HTTP request sent to the server:
    //
    //   GET or DELETE - the data is in a query parameter:   /URL?parameter=value
    //
    //   POST or PUT   - the data is in the request body



    // @GetMapping - identify a method to handle HTTP GET requests for a particular path

    @GetMapping (value="/")  // The method that follows will handle GET for root path "/"
    public String anyNameYouWant() {
        System.out.println("Method to handle GET for URL: '/' was invoked");
        return "Attendance code for today was 4122";
    }
    @GetMapping (value="/tommy")  // The method that follows will handle GET for root path "/"
    public String yourPick() {
        System.out.println("Method to handle GET for URL: '/tommy' was invoked");
        return "Tommy is confusing Frank cuz Frank is old";
    }
    // Handle the GET /abraham?frank=value
    //
    // To get the data out of the query parameter, use @RequestParam annotation in the method parameters
    //
    // @RequestParam data-type parameter-name  - Note the parameter name must match the query parm name

    @GetMapping (value="/abraham")  // The method that follows will handle GET for root path "/abraham?frank=value"
    public DataToBeReturned someBoringMethodName(@RequestParam int frank, @RequestParam String words) {
        System.out.println("Method to handle GET for URL: '/abraham' was invoked");
        return new DataToBeReturned(words, frank);
    }

    // Controller to handle POST for "/tommy"

    @PostMapping (value="/tommy")
    public String anyNameYouLike(@RequestBody DataReceived whatWeGot) {
        System.out.println("Method for POST with '/tommy' called");
        return whatWeGot.getDataSent();
    }
}
