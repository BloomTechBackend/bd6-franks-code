package com.frank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExpressionsExamples {

    // These are static because they are processed in a static method - main()
    private static List<String>  names    = new ArrayList<>();

    private static List<Integer> numList  = new ArrayList<>();

    // the main() must be static
    public static void main(String[] args) {
        /***************************************************************************************
         * Perform Initial/Setup processing for the application
         **************************************************************************************/
        setup();      // Perform start of main processing

        System.out.println("\n-------- Display elements in a List ---------");

     // There are two versions of the forEach() method in Java
     //    1. Stream interface forEach()
     //    2. List version of forEach()  - preferred due to it safer and faster
     //                                    uses an iterator so if you add/remove elements - no exception
     //                                    iterators are slightly faster than non-iterators
     //                                    List is processed in entry sequence

     // names.forEach( (aName) -> {System.out.println("Name: " + aName);});  // List version of forEach()
        names.forEach(  aName  -> {System.out.println("Name: " + aName);});  // no () if only one parm to Lambda Expression
     // names.stream().forEach((aName) -> {System.out.println("Name: " + aName);});  // Stream interface forEach
        /***************************************************************************************
         * stream().map() Stream Interface function
         **************************************************************************************/
        System.out.println("\n-------- Showing first initials   ---------");

        // Create a list of the first initials from the element in names ArrayList
        List<String> firstInitials = names.stream().map((aName) -> {return (aName.substring(0,1));})
                                    .collect(Collectors.toList());

        firstInitials.forEach( (aName) -> {System.out.println("Name: " + aName);});

        /***************************************************************************************
         * stream().filter() Stream Interface function
         **************************************************************************************/
        System.out.println("\n-------- Removing Frank from List ---------");

        List<String> noFrank = names.stream().filter(aName -> {return (!aName.equals("Frank"));})
                                             .collect(Collectors.toList());

        noFrank.forEach( (aName) -> {System.out.println("Name: " + aName);});

        /***************************************************************************************
         * stream().filter() Stream Interface function
         **************************************************************************************/
        System.out.println("\n-------- Find values in ArrayList evenly divisble by 3 ---------");

        System.out.println("Values in numList ArrayList: ");
        numList.forEach( (aNum) -> {System.out.println("Value in List: " + aNum);});

        List<Integer> divBy3 = numList.stream().filter((aNum) -> {return (aNum % 3 == 0 ? true : false); }).collect(Collectors.toList());

        divBy3.forEach( (aNum) -> {System.out.println("\tnumber divisible by 3: " + aNum);});

        /***************************************************************************************
         * stream().reduce() Stream Interface function
         **************************************************************************************/
        System.out.println("\n-------- Using stream().reduce to sum an ArrayList   ---------");

        System.out.println("Values in numList ArrayList: ");
        numList.forEach( (aNum) -> {System.out.println("Value in List: " + aNum);});
        // Note {} mpt required in a Lambda expression if it is only one statement
        int listTotal = numList.stream().reduce(0, (sum, anElem) -> sum + anElem);
        System.out.println("Sum of elements in ArrayList is: " + listTotal);

        /***************************************************************************************
         * Arrays.reduce() Arrays method
         *
         * Stream interface methods only work on Collections class linear objects
         *                          they don't work on simple arrays
         *
         * The Arrays class provided stream(). methods similar to Stream interface methods
         *     for simple arrays
         **************************************************************************************/
        System.out.println("\n-------- Using Arrays.reduce() to sum an array   ---------");

        int numArray[]  = {10, 20, 30,40};
        for(int i=0; i < numArray.length; i++) {
            System.out.println("Array elem " + i + ": " + numArray[i]);
        }

        // The Arrays class provided a stream().reduce() method for a simple array
        int total = Arrays.stream(numArray).reduce(0, (sum, anElem) -> sum + anElem);
        System.out.println("Sum of elements in array is: " + total);


        takeDown();   // Perform end of main() processings
    } // End of main()

    // this method must be static because it is called by a static method
    public static void setup() {

        System.out.println("-".repeat(50) + "\nWelcome to Lambda Expressions Example\n" + "-".repeat(50));

        names.add("Frank");
        names.add("Kirk");
        names.add("Spock");
        names.add("Worf");
        names.add("Data");

        numList.add(100);
        numList.add(200);
        numList.add(300);
        numList.add(400);
        numList.add(500);
        numList.add(600);
    }

    public static void takeDown() {
        System.out.println("-".repeat(50) + "\nThanks for visiting Lambda Expressions Example\n" + "-".repeat(50));
    }

} // End if LambdaExpressionsExample class
