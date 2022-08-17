package com.frank;

import java.util.Arrays;

public class aClass {

        private int[] anArray;  // an Array is a reference

        public aClass(int[] intArray) {
                // Defensive copy of the reference we are passed to reference in this class
                //               .copyOf(source , size-of-new-array)
                anArray = Arrays.copyOf(intArray, intArray.length);
        }

        public int[] getAnArray() {
                // Defensive return of a reference from this class
                return Arrays.copyOf(anArray, anArray.length);
        }

        public void showClass() {
                System.out.println(("\nContents of array in aClass: "));
                for (int i = 0; i < anArray.length; i++) {
                        System.out.println("Element " + i + ": " + anArray[i]);
                }
        }
}
