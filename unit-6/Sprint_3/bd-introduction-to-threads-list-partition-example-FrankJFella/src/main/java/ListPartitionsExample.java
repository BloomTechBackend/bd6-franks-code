import com.google.common.collect.Lists;   // Access to Google Lists methods including partition()

import java.util.ArrayList;
import java.util.List;

// Demonstrate use of Google Guava Lists partition() method to divide an ArrayList into sub lists

public class ListPartitionsExample {
   private static final int LIST_SIZE   = 10;
   private static final int NUM_SUBLIST = 3;

   public static void main(String args[]) {
       System.out.println("\nWelcome to our a Google Lists.partition example!\n");
       List<String> aList = new ArrayList<>();

       System.out.println("Number of entries in original list: " + LIST_SIZE);
       System.out.println("   Number of elems in each sublist: " + NUM_SUBLIST + "\n");

       // adding elements to the ArrayLIst
       for(int i=0; i < LIST_SIZE; i++) {
        aList.add("word-" + i);
       }
       // Create sub lists from the ArrayList using the Guava Lists.partition() method
       //
       // A List containing the sub-lists is returned from Lists.partition()
       //        result of partition() is List of Lists of objects withteh Class of the original list
       //
       // in this example partition will return a List of a List of Strings
       //
       //                           Lists.partition(original-ArrayList, #-elems-in-sub-list
       List<List<String>> subList = Lists.partition(aList             , LIST_SIZE / NUM_SUBLIST);

       System.out.println("Number of elements in aList: " + aList.size());
       System.out.println("Number of sub lists created: " + subList.size());

       for(int i = 0; i < subList.size(); i++) {  // go through the List of Lists
           System.out.println("\nNumber of elements in subList #" + i + ": " + subList.get(i).size());
           // Each element in the sublist is a List
           for (String anElement : subList.get(i)) { // go  through each sublist
               System.out.println("\tElement: " + anElement);
           }
       }
   }  // end of main()
}
