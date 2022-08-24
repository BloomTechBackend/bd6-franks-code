package com.ata.lambdaexpressions.classroom.extension;

import java.util.List;

/**
 * The {@code java.util.List} interface has a few methods that take lambdas directly.  Can you use just
 * these methods to solve the problems below and make the tests in {@code LambdaListsTests} pass?
 *
 * All of these problems can be solved by just using the two methods below:
 *
 * list.replaceAll(lambda) -- calls the lambda once for each item in the list, storing the result back
 * into the list (or other type of collection).
 *
 * list.removeIf(lambda) -- calls the lambda once for each item in the collection, removing each item
 * where the lambda returns true.
 *
 * Remove the 'throw new UnsupportedOperationException();' and implement the correct logic.
 */
public class LambdaLists {

    /**
     * Given a list of integers, return a list where each integer is multiplied by 2.
     *
     *
     * doubling([1, 2, 3]) → [2, 4, 6]
     * doubling([6, 8, 6, 8, -1]) → [12, 16, 12, 16, -2]
     * doubling([]) → []
     */
    public List<Integer> doubling(List<Integer> nums) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of integers, return a list where each integer is multiplied with itself.
     *
     * square([1, 2, 3]) → [1, 4, 9]
     * square([6, 8, -6, -8, 1]) → [36, 64, 36, 64, 1]
     * square([]) → []
     */
    public List<Integer> square(List<Integer> nums) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of strings, return a list where each string has "*" added at its end.
     *
     * addStar(["a", "bb", "ccc"]) → ["a*", "bb*", "ccc*"]
     * addStar(["hello", "there"]) → ["hello*", "there*"]
     * addStar(["*"]) → ["**"]
     */
    public List<String> addStar(List<String> strings) {
        throw new UnsupportedOperationException();
    }

    /***
     * Given a list of strings, return a list where each string is replaced by 3 copies
     * of the string concatenated together.
     *
     * copies3(["a", "bb", "ccc"]) → ["aaa", "bbbbbb", "ccccccccc"]
     * copies3(["24", "a", ""]) → ["242424", "aaa", ""]
     * copies3(["hello", "there"]) → ["hellohellohello", "theretherethere"]
     */
    public List<String> copies3(List<String> strings) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of strings, return a list where each string has "y" added at its start and end.
     *
     * moreY(["a", "b", "c"]) → ["yay", "yby", "ycy"]
     * moreY(["hello", "there"]) → ["yhelloy", "ytherey"]
     * moreY(["yay"]) → ["yyayy"]
     */
    public List<String> moreY(List<String> strings) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * Given a list of integers, return a list where each integer is added to 1 and the result is multiplied by 10.
     *
     * math1([1, 2, 3]) → [20, 30, 40]
     * math1([6, 8, 6, 8, 1]) → [70, 90, 70, 90, 20]
     * math1([10]) → [110]
     */
    public List<Integer> math1(List<Integer> nums) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of strings, return a list where each string is converted to lower case.
     *
     * lower(["Hello", "Hi"]) → ["hello", "hi"]
     * lower(["AAA", "BBB", "ccc"]) → ["aaa", "bbb", "ccc"]
     * lower(["KitteN", "ChocolaTE"]) → ["kitten", "chocolate"]
     */
    public List<String> lower(List<String> strings) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of strings, return a list where each string has all its "x"s removed.
     *
     * noX(["ax", "bb", "cx"]) → ["a", "bb", "c"]
     * noX(["xxax", "xbxbx", "xxcx"]) → ["a", "bb", "c"]
     * noX(["x"]) → [""]
     */
    public List<String> noX(List<String> strings) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of integers, return a list of the integers, omitting any that are less than 0.
     *
     * noNeg([1, -2]) → [1]
     * noNeg([-3, -3, 3, 3]) → [3, 3]
     * noNeg([-1, -1, -1]) → []
     */
    public List<Integer> noNeg(List<Integer> nums) {
        throw new UnsupportedOperationException();
    }


    /**
     * Given a list of integers, return a list of the integers, omitting any that are between 13 and 19 inclusive.
     *
     * noTeen([12, 13, 19, 20]) → [12, 20]
     * noTeen([1, 14, 1]) → [1, 1]
     * noTeen([15]) → []
     */
    public List<Integer> noTeen(List<Integer> nums) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of strings, return a list of the strings, omitting any string length 4 or more.
     *
     * noLong(["this", "not", "too", "long"]) → ["not", "too"]
     * noLong(["a", "bbb", "cccc"]) → ["a", "bbb"]
     * noLong(["cccc", "cccc", "cccc"]) → []
     */
    public List<String> noLong(List<String> strings) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of strings, return a list of the strings, omitting any string that contains a "z".
     *
     * noZ(["aaa", "bbb", "aza"]) → ["aaa", "bbb"]
     * noZ(["hziz", "hzello", "hi"]) → ["hi"]
     * noZ(["hello", "howz", "are", "youz"]) → ["hello", "are"]
     */
    public List<String> noZ(List<String> strings) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of strings, return a list of the strings, omitting any string length 3 or 4.
     *
     * no34(["a", "bb", "ccc"]) → ["a", "bb"]
     * no34(["a", "bb", "ccc", "dddd"]) → ["a", "bb"]
     * no34(["ccc", "dddd", "apple"]) → ["apple"]
     */
    public List<String> no3Or4(List<String> strings) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of strings, return a list where each string has "y" added at its end, omitting any resulting
     * strings that contain "yy" as a substring anywhere.
     *
     * noYY(["a", "b", "c"]) → ["ay", "by", "cy"]
     * noYY(["a", "b", "cy"]) → ["ay", "by"]
     * noYY(["xx", "ya", "zz"]) → ["xxy", "yay", "zzy"]
     */
    public List<String> noYY(List<String> strings) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of non-negative integers, return a list of the integers multiplied by 2, omitting any of the
     * resulting integers whose right most digit is 2.
     *
     * two2([1, 2, 3]) → [4, 6]
     * two2([2, 6, 11]) → [4]
     * two2([0]) → [0]
     */
    public List<Integer> two2(List<Integer> nums) {
        throw new UnsupportedOperationException();
    }

    /**
     * Given a list of integers, return a list of the integers squared plus 10, omitting any of
     * the resulting numbers whose right most digit is 5 or 6.
     *
     * square56([3, 1, 4]) → [19, 11]
     * square56([1]) → [11]
     * square56([2]) → [14]
     */
    public List<Integer> square56(List<Integer> nums) {
        throw new UnsupportedOperationException();
    }

}