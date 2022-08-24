package com.ata.lambdaexpressions.classroom.extension;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaListsTest {

    private LambdaLists lambdaLists = new LambdaLists();

    @Test
    public void doublingTest1() {
        List<Integer> actual = lambdaLists.doubling(Arrays.asList(1, 2, 3));
        assertEquals(Arrays.asList(2, 4, 6), actual, String.format("Failed for input %s", Arrays.asList(1, 2, 3)));
    }

    @Test
    public void doublingTest2() {
        List<Integer> actual = lambdaLists.doubling(Arrays.asList(6, 8, 6, 8, -1));
        assertEquals(Arrays.asList(12, 16, 12, 16, -2), actual, String.format("Failed for input %s", Arrays.asList(6, 8, 6, 8, -1)));
    }

    @Test
    public void doublingTest3() {
        List<Integer> actual = lambdaLists.doubling(Arrays.asList());
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void doublingTest4() {
        List<Integer> actual = lambdaLists.doubling(Arrays.asList(5));
        assertEquals(Arrays.asList(10), actual, String.format("Failed for input %s", Arrays.asList(5)));
    }

    @Test
    public void squareTest1() {
        List<Integer> actual = lambdaLists.square(Arrays.asList(1, 2, 3));
        assertEquals(Arrays.asList(1, 4, 9), actual, String.format("Failed for input %s", Arrays.asList(1, 2, 3)));
    }

    @Test
    public void squareTest2() {
        List<Integer> actual = lambdaLists.square(Arrays.asList(6, 8, -6, -8, 1));
        assertEquals(Arrays.asList(36, 64, 36, 64, 1), actual, String.format("Failed for input %s", Arrays.asList(6, 8, -6, -8, 1)));
    }

    @Test
    public void squareTest3() {
        List<Integer> actual = lambdaLists.square(Arrays.asList());
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void squareTest4() {
        List<Integer> actual = lambdaLists.square(Arrays.asList(12));
        assertEquals(Arrays.asList(144), actual, String.format("Failed for input %s", Arrays.asList(12)));
    }

    @Test
    public void addStartTest1() {
        List<String> actual = lambdaLists.addStar(Arrays.asList("a", "bb", "ccc"));
        assertEquals(Arrays.asList("a*", "bb*", "ccc*"), actual, String.format("Failed for input %s", Arrays.asList("a", "bb", "ccc")));
    }

    @Test
    public void addStartTest2() {
        List<String> actual = lambdaLists.addStar(Arrays.asList("hello", "there"));
        assertEquals(Arrays.asList("hello*", "there*"), actual, String.format("Failed for input %s", Arrays.asList("hello\", \"there")));
    }

    @Test
    public void addStartTest3() {
        List<String> actual = lambdaLists.addStar(Arrays.asList("*"));
        assertEquals(Arrays.asList("**"), actual, String.format("Failed for input %s", Arrays.asList("*")));
    }

    @Test
    public void addStartTest4() {
        List<String> actual = lambdaLists.addStar(Arrays.asList());
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void addStartTest6() {
        List<String> actual = lambdaLists.addStar(Arrays.asList("empty", "string", ""));
        assertEquals(Arrays.asList("empty*", "string*", "*"), actual, String.format("Failed for input %s", Arrays.asList("empty", "string", "")));
    }

    @Test
    public void copies3Test1() {
        List<String> actual = lambdaLists.copies3(Arrays.asList("a", "bb", "ccc"));
        assertEquals(Arrays.asList("aaa", "bbbbbb", "ccccccccc"), actual, String.format("Failed for input %s", Arrays.asList("a", "bb", "ccc")));
    }

    @Test
    public void copies3Test2() {
        List<String> actual = lambdaLists.copies3(Arrays.asList("24", "a", ""));
        assertEquals(Arrays.asList("242424", "aaa", ""), actual, String.format("Failed for input %s", Arrays.asList("24", "a", "")));
    }

    @Test
    public void copies3Test3() {
        List<String> actual = lambdaLists.copies3(Arrays.asList());
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void copies3Test4() {
        List<String> actual = lambdaLists.copies3(Arrays.asList("no"));
        assertEquals(Arrays.asList("nonono"), actual, String.format("Failed for input %s", Arrays.asList("no")));
    }

    @Test
    public void copies3Test5() {
        List<String> actual = lambdaLists.copies3(Arrays.asList());
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void moreYTest1() {
        List<String> actual = lambdaLists.moreY(Arrays.asList("a", "b", "c"));
        assertEquals(Arrays.asList("yay", "yby", "ycy"), actual, String.format("Failed for input %s", Arrays.asList("a", "b", "c")));
    }

    @Test
    public void moreYTest2() {
        List<String> actual = lambdaLists.moreY(Arrays.asList("hello", "there"));
        assertEquals(Arrays.asList("yhelloy", "ytherey"), actual, String.format("Failed for input %s", Arrays.asList("hello", "there")));
    }

    @Test
    public void moreYTest3() {
        List<String> actual = lambdaLists.moreY(Arrays.asList("yay"));
        assertEquals(Arrays.asList("yyayy"), actual, String.format("Failed for input %s", Arrays.asList("yay")));
    }

    @Test
    public void moreYTest4() {
        List<String> actual = lambdaLists.moreY(Arrays.asList("", "a", "xx"));
        assertEquals(Arrays.asList("yy", "yay", "yxxy"), actual, String.format("Failed for input %s", Arrays.asList("", "a", "xx")));
    }

    @Test
    public void moreYTest5() {
        List<String> actual = lambdaLists.moreY(Arrays.asList());
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void math1Test1() {
        List<Integer> actual = lambdaLists.math1(new ArrayList<>(Arrays.asList(1, 2, 3)));
        assertEquals(Arrays.asList(20, 30, 40), actual, String.format("Failed for input %s", Arrays.asList(1, 2, 3)));
    }

    @Test
    public void math1Test2() {
        List<Integer> actual = lambdaLists.math1(new ArrayList<>(Arrays.asList(6, 8, 6, 8, 1)));
        assertEquals(Arrays.asList(70, 90, 70, 90, 20), actual, String.format("Failed for input %s", Arrays.asList(6, 8, 6, 8, 1)));
    }

    @Test
    public void math1Test3() {
        List<Integer> actual = lambdaLists.math1(new ArrayList<>(Arrays.asList(10)));
        assertEquals(Arrays.asList(110), actual, String.format("Failed for input %s", Arrays.asList(10)));
    }

    @Test
    public void math1Test4() {
        List<Integer> actual = lambdaLists.math1(new ArrayList<>(Arrays.asList()));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void math1Test5() {
        List<Integer> actual = lambdaLists.math1(new ArrayList<>(Arrays.asList(5, 10)));
        assertEquals(Arrays.asList(60, 110), actual, String.format("Failed for input %s", Arrays.asList(5, 10)));
    }

    @Test
    public void math1Test6() {
        List<Integer> actual = lambdaLists.math1(new ArrayList<>(Arrays.asList(-1, -2, -3, -2, -1)));
        assertEquals(Arrays.asList(0, -10, -20, -10, 0), actual, String.format("Failed for input %s", Arrays.asList(-1, -2, -3, -2, -1)));
    }

    @Test
    public void lowerTest1() {
        List<String> actual = lambdaLists.lower(Arrays.asList("Hello", "Hi"));
        assertEquals(Arrays.asList("hello", "hi"), actual, String.format("Failed for input %s", Arrays.asList("Hello", "Hi")));
    }

    @Test
    public void lowerTest2() {
        List<String> actual = lambdaLists.lower(Arrays.asList("AAA", "BBB", "ccc"));
        assertEquals(Arrays.asList("aaa", "bbb", "ccc"), actual, String.format("Failed for input %s", Arrays.asList("AAA", "BBB", "ccc")));
    }

    @Test
    public void lowerTest3() {
        List<String> actual = lambdaLists.lower(Arrays.asList("KitteN", "ChocolaTE"));
        assertEquals(Arrays.asList("kitten", "chocolate"), actual, String.format("Failed for input %s", Arrays.asList("KitteN", "ChocolaTE")));
    }

    @Test
    public void lowerTest4() {
        List<String> actual = lambdaLists.lower(Arrays.asList());
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void lowerTest5() {
        List<String> actual = lambdaLists.lower(Arrays.asList("EMPTY", ""));
        assertEquals(Arrays.asList("empty", ""), actual, String.format("Failed for input %s", Arrays.asList("EMPTY", "")));
    }

    @Test
    public void lowerTest6() {
        List<String> actual = lambdaLists.lower(Arrays.asList("aaX", "bYb", "Ycc", "ZZZ"));
        assertEquals(Arrays.asList("aax", "byb", "ycc", "zzz"), actual, String.format("Failed for input %s", Arrays.asList("aaX", "bYb", "Ycc", "ZZZ")));
    }

    @Test
    public void noXTest1() {
        List<String> actual = lambdaLists.noX(new ArrayList<>(Arrays.asList("ax", "bb", "cx")));
        assertEquals(Arrays.asList("a", "bb", "c"), actual, String.format("Failed for input %s", Arrays.asList("ax", "bb", "cx")));
    }

    @Test
    public void noXTest2() {
        List<String> actual = lambdaLists.noX(new ArrayList<>(Arrays.asList("xxax", "xbxbx", "xxcx")));
        assertEquals(Arrays.asList("a", "bb", "c"), actual, String.format("Failed for input %s", Arrays.asList("xxax", "xbxbx", "xxcx")));
    }

    @Test
    public void noXTest3() {
        List<String> actual = lambdaLists.noX(new ArrayList<>(Arrays.asList("X")));
        assertEquals(Arrays.asList(""), actual, String.format("Failed for input %s", Arrays.asList("X")));
    }

    @Test
    public void noXTest4() {
        List<String> actual = lambdaLists.noX(new ArrayList<>(Arrays.asList("")));
        assertEquals(Arrays.asList(""), actual, String.format("Failed for input %s", Arrays.asList("")));
    }

    @Test
    public void noXTest5() {
        List<String> actual = lambdaLists.noX(new ArrayList<>(Arrays.asList()));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void noXTest6() {
        List<String> actual = lambdaLists.noX(new ArrayList<>(Arrays.asList("the", "taxi")));
        assertEquals(Arrays.asList("the", "tai"), actual, String.format("Failed for input %s", Arrays.asList("the", "taxi")));
    }

    @Test
    public void noXTest7() {
        List<String> actual = lambdaLists.noX(new ArrayList<>(Arrays.asList("the", "xxtxaxixxx")));
        assertEquals(Arrays.asList("the", "tai"), actual, String.format("Failed for input %s", Arrays.asList("the", "xxtxaxixxx")));
    }

    @Test
    public void noXTest8() {
        List<String> actual = lambdaLists.noX(new ArrayList<>(Arrays.asList("this", "is", "the", "x")));
        assertEquals(Arrays.asList("this", "is", "the", ""), actual, String.format("Failed for input %s", Arrays.asList("this", "is", "the", "x")));
    }

    @Test
    public void noNegTest1() {
        List<Integer> actual = lambdaLists.noNeg(new ArrayList<>(Arrays.asList(1, -2)));
        assertEquals(Arrays.asList(1), actual, String.format("Failed for input %s", Arrays.asList(1, -2)));
    }

    @Test
    public void noNegTest2() {
        List<Integer> actual = lambdaLists.noNeg(new ArrayList<>(Arrays.asList(-3, -3, 3, 3)));
        assertEquals(Arrays.asList(3, 3), actual, String.format("Failed for input %s", Arrays.asList(-3, -3, 3, 3)));
    }

    @Test
    public void noNegTest3() {
        List<Integer> actual = lambdaLists.noNeg(new ArrayList<>(Arrays.asList(-1, -1, -1)));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList(-1, -1, -1)));
    }

    @Test
    public void noNegTest4() {
        List<Integer> actual = lambdaLists.noNeg(new ArrayList<>(Arrays.asList()));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void noNegTest5() {
        List<Integer> actual = lambdaLists.noNeg(new ArrayList<>(Arrays.asList(0, 1, 2)));
        assertEquals(Arrays.asList(0, 1, 2), actual, String.format("Failed for input %s", Arrays.asList(0, 1, 2)));
    }

    @Test
    public void noNegTest6() {
        List<Integer> actual = lambdaLists.noNeg(new ArrayList<>(Arrays.asList(3, -10, 1, -1, 4, -400)));
        assertEquals(Arrays.asList(3, 1, 4), actual, String.format("Failed for input %s", Arrays.asList(3, -10, 1, -1, 4, -400)));
    }

    @Test
    public void noNegTest7() {
        List<Integer> actual = lambdaLists.noNeg(new ArrayList<>(Arrays.asList(-1, 3, 1, -1, -10, -100, -111, 5)));
        assertEquals(Arrays.asList(3, 1, 5), actual, String.format("Failed for input %s", Arrays.asList(-1, 3, 1, -1, -10, -100, -111, 5)));
    }

    @Test
    public void noTeenTest1() {
        List<Integer> actual = lambdaLists.noTeen(new ArrayList<>(Arrays.asList(12, 13, 19, 20)));
        assertEquals(Arrays.asList(12, 20), actual, String.format("Failed for input %s", Arrays.asList(12, 13, 19, 20)));
    }

    @Test
    public void noTeenTest2() {
        List<Integer> actual = lambdaLists.noTeen(new ArrayList<>(Arrays.asList(1, 14, 1)));
        assertEquals(Arrays.asList(1, 1), actual, String.format("Failed for input %s", Arrays.asList(1, 14, 1)));
    }

    @Test
    public void noTeenTest3() {
        List<Integer> actual = lambdaLists.noTeen(new ArrayList<>(Arrays.asList(15)));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList(15)));
    }

    @Test
    public void noTeenTest4() {
        List<Integer> actual = lambdaLists.noTeen(new ArrayList<>(Arrays.asList(-15)));
        assertEquals(Arrays.asList(-15), actual, String.format("Failed for input %s", Arrays.asList(-15)));
    }

    @Test
    public void noTeenTest5() {
        List<Integer> actual = lambdaLists.noTeen(new ArrayList<>(Arrays.asList()));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void noTeenTest6() {
        List<Integer> actual = lambdaLists.noTeen(new ArrayList<>(Arrays.asList(0, 1, 2, -3)));
        assertEquals(Arrays.asList(0, 1, 2, -3), actual, String.format("Failed for input %s", Arrays.asList(0, 1, 2, -3)));
    }

    @Test
    public void noTeenTest7() {
        List<Integer> actual = lambdaLists.noTeen(new ArrayList<>(Arrays.asList(15, 17, 19, 21, 19)));
        assertEquals(Arrays.asList(21), actual, String.format("Failed for input %s", Arrays.asList(15, 17, 19, 21, 19)));
    }

    @Test
    public void noTeenTest8() {
        List<Integer> actual = lambdaLists.noTeen(new ArrayList<>(Arrays.asList(-16, 2, 15, 3, 16, 25)));
        assertEquals(Arrays.asList(-16, 2, 3, 25), actual, String.format("Failed for input %s", Arrays.asList(-16, 2, 15, 3, 16, 25)));
    }

    @Test
    public void noLongTest1() {
        List<String> actual = lambdaLists.noLong(new ArrayList<>(Arrays.asList("this", "not", "too", "long")));
        assertEquals(Arrays.asList("not", "too"), actual, String.format("Failed for input %s", Arrays.asList("this", "not", "too", "long")));
    }

    @Test
    public void noLongTest2() {
        List<String> actual = lambdaLists.noLong(new ArrayList<>(Arrays.asList("a", "bbb", "cccc")));
        assertEquals(Arrays.asList("a", "bbb"), actual, String.format("Failed for input %s", Arrays.asList("a", "bbb", "cccc")));
    }

    @Test
    public void noLongTest3() {
        List<String> actual = lambdaLists.noLong(new ArrayList<>(Arrays.asList("cccc", "cccc", "cccc")));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList("cccc", "cccc", "cccc")));
    }

    @Test
    public void noLongTest4() {
        List<String> actual = lambdaLists.noLong(new ArrayList<>(Arrays.asList()));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void noLongTest5() {
        List<String> actual = lambdaLists.noLong(new ArrayList<>(Arrays.asList("")));
        assertEquals(Arrays.asList(""), actual, String.format("Failed for input %s", Arrays.asList("")));
    }

    @Test
    public void noLongTest6() {
        List<String> actual = lambdaLists.noLong(new ArrayList<>(Arrays.asList("empty", "", "empty")));
        assertEquals(Arrays.asList(""), actual, String.format("Failed for input %s", Arrays.asList("empty", "", "empty")));
    }

    @Test
    public void noLongTest7() {
        List<String> actual = lambdaLists.noLong(new ArrayList<>(Arrays.asList("a")));
        assertEquals(Arrays.asList("a"), actual, String.format("Failed for input %s", Arrays.asList("a")));
    }

    @Test
    public void noLongTest8() {
        List<String> actual = lambdaLists.noLong(new ArrayList<>(Arrays.asList("aaaa", "bbb", "***", "333")));
        assertEquals(Arrays.asList("bbb", "***", "333"), actual, String.format("Failed for input %s", Arrays.asList("aaaa", "bbb", "***", "333")));
    }

    @Test
    public void noZTest1() {
        List<String> actual = lambdaLists.noZ(new ArrayList<>(Arrays.asList("aaa", "bbb", "aza")));
        assertEquals(Arrays.asList("aaa", "bbb"), actual, String.format("Failed for input %s", Arrays.asList("aaa", "bbb", "aza")));
    }

    @Test
    public void noZTest2() {
        List<String> actual = lambdaLists.noZ(new ArrayList<>(Arrays.asList("hziz", "hzello", "hi")));
        assertEquals(Arrays.asList("hi"), actual, String.format("Failed for input %s", Arrays.asList("hziz", "hzello", "hi")));
    }

    @Test
    public void noZTest3() {
        List<String> actual = lambdaLists.noZ(new ArrayList<>(Arrays.asList("hello", "howz", "are", "youz")));
        assertEquals(Arrays.asList("hello", "are"), actual, String.format("Failed for input %s", Arrays.asList("hello", "howz", "are", "youz")));
    }

    @Test
    public void noZTest4() {
        List<String> actual = lambdaLists.noZ(new ArrayList<>(Arrays.asList()));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void noZTest5() {
        List<String> actual = lambdaLists.noZ(new ArrayList<>(Arrays.asList("")));
        assertEquals(Arrays.asList(""), actual, String.format("Failed for input %s", Arrays.asList("")));
    }

    @Test
    public void noZTest6() {
        List<String> actual = lambdaLists.noZ(new ArrayList<>(Arrays.asList("x", "y", "z")));
        assertEquals(Arrays.asList("x", "y"), actual, String.format("Failed for input %s", Arrays.asList("x", "y", "z")));
    }

    @Test
    public void no3Or4Test1() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList("a", "bb", "ccc")));
        assertEquals(Arrays.asList("a", "bb"), actual, String.format("Failed for input %s", Arrays.asList("a", "bb", "ccc")));
    }

    @Test
    public void no3Or4Test2() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList("a", "bb", "ccc", "dddd")));
        assertEquals(Arrays.asList("a", "bb"), actual, String.format("Failed for input %s", Arrays.asList("a", "bb", "ccc", "dddd")));
    }

    @Test
    public void no3Or4Test3() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList("ccc", "dddd", "apple")));
        assertEquals(Arrays.asList("apple"), actual, String.format("Failed for input %s", Arrays.asList("ccc", "dddd", "apple")));
    }

    @Test
    public void no3Or4Test4() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList("this", "not", "too", "long")));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList("this", "not", "too", "long")));
    }

    @Test
    public void no3Or4Test5() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList("a", "bbb", "cccc", "xx")));
        assertEquals(Arrays.asList("a", "xx"), actual, String.format("Failed for input %s", Arrays.asList("a", "bbb", "cccc", "xx")));
    }

    @Test
    public void no3Or4Test6() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList("dddd", "ddd", "xxxxxxx")));
        assertEquals(Arrays.asList("xxxxxxx"), actual, String.format("Failed for input %s", Arrays.asList("dddd", "ddd", "xxxxxxx")));
    }

    @Test
    public void no3Or4Test7() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList()));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void no3Or4Test8() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList("")));
        assertEquals(Arrays.asList(""), actual, String.format("Failed for input %s", Arrays.asList("")));
    }

    @Test
    public void no3Or4Test9() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList("aaaa", "bbb", "*****", "333")));
        assertEquals(Arrays.asList("*****"), actual, String.format("Failed for input %s", Arrays.asList("aaaa", "bbb", "*****", "333")));
    }

    @Test
    public void no3Or4Test10() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList("empty", "", "empty")));
        assertEquals(Arrays.asList("empty", "", "empty"), actual, String.format("Failed for input %s", Arrays.asList("empty", "", "empty")));
    }

    @Test
    public void no3Or4Test11() {
        List<String> actual = lambdaLists.no3Or4(new ArrayList<>(Arrays.asList("a")));
        assertEquals(Arrays.asList("a"), actual, String.format("Failed for input %s", Arrays.asList("a")));
    }

    @Test
    public void noYYTest1() {
        List<String> actual = lambdaLists.noYY(new ArrayList<>(Arrays.asList("a", "b", "c")));
        assertEquals(Arrays.asList("ay", "by", "cy"), actual, String.format("Failed for input %s", Arrays.asList("a", "b", "c")));
    }

    @Test
    public void noYYTest2() {
        List<String> actual = lambdaLists.noYY(new ArrayList<>(Arrays.asList("a", "b", "cy")));
        assertEquals(Arrays.asList("ay", "by"), actual, String.format("Failed for input %s", Arrays.asList("a", "b", "cy")));
    }

    @Test
    public void noYYTest3() {
        List<String> actual = lambdaLists.noYY(new ArrayList<>(Arrays.asList("xx", "ya", "zz")));
        assertEquals(Arrays.asList("xxy", "yay", "zzy"), actual, String.format("Failed for input %s", Arrays.asList("xx", "ya", "zz")));
    }

    @Test
    public void noYYTest4() {
        List<String> actual = lambdaLists.noYY(new ArrayList<>(Arrays.asList("xx", "yay", "zz")));
        assertEquals(Arrays.asList("xxy", "zzy"), actual, String.format("Failed for input %s", Arrays.asList("xx", "yay", "zz")));
    }

    @Test
    public void noYYTest5() {
        List<String> actual = lambdaLists.noYY(new ArrayList<>(Arrays.asList("yyx", "y", "zzz")));
        assertEquals(Arrays.asList("zzzy"), actual, String.format("Failed for input %s", Arrays.asList("yyx", "y", "zzz")));
    }

    @Test
    public void noYYTest6() {
        List<String> actual = lambdaLists.noYY(new ArrayList<>(Arrays.asList("hello", "there")));
        assertEquals(Arrays.asList("helloy", "therey"), actual, String.format("Failed for input %s", Arrays.asList("hello", "there")));
    }

    @Test
    public void noYYTest7() {
        List<String> actual = lambdaLists.noYY(new ArrayList<>(Arrays.asList("ya")));
        assertEquals(Arrays.asList("yay"), actual, String.format("Failed for input %s", Arrays.asList("ya")));
    }

    @Test
    public void noYYTest8() {
        List<String> actual = lambdaLists.noYY(new ArrayList<>(Arrays.asList()));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void noYYTest9() {
        List<String> actual = lambdaLists.noYY(new ArrayList<>(Arrays.asList("")));
        assertEquals(Arrays.asList("y"), actual, String.format("Failed for input %s", Arrays.asList("")));
    }

    @Test
    public void noYYTest10() {
        List<String> actual = lambdaLists.noYY(new ArrayList<>(Arrays.asList("xx", "yy", "zz")));
        assertEquals(Arrays.asList("xxy", "zzy"), actual, String.format("Failed for input %s", Arrays.asList("xx", "yy", "zz")));
    }

    @Test
    public void two2Test1() {
        List<Integer> actual = lambdaLists.two2(new ArrayList<>(Arrays.asList(1, 2, 3)));
        assertEquals(Arrays.asList(4, 6), actual, String.format("Failed for input %s", Arrays.asList(1,2,3)));
    }

    @Test
    public void two2Test2() {
        List<Integer> actual = lambdaLists.two2(new ArrayList<>(Arrays.asList(2, 6, 11)));
        assertEquals(Arrays.asList(4), actual, String.format("Failed for input %s", Arrays.asList(2,6,11)));
    }

    @Test
    public void two2Test3() {
        List<Integer> actual = lambdaLists.two2(new ArrayList<>(Arrays.asList(0)));
        assertEquals(Arrays.asList(0), actual, String.format("Failed for input %s", Arrays.asList(0)));
    }

    @Test
    public void two2Test4() {
        List<Integer> actual = lambdaLists.two2(new ArrayList<>(Arrays.asList()));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList()));
    }

    @Test
    public void two2Test5() {
        List<Integer> actual = lambdaLists.two2(new ArrayList<>(Arrays.asList(1, 11, 111, 16)));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList(1,11,111,16)));
    }

    @Test
    public void two2Test6() {
        List<Integer> actual = lambdaLists.two2(new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11)));
        assertEquals(Arrays.asList(4, 6, 10, 14), actual, String.format("Failed for input %s", Arrays.asList(2, 3, 5, 7, 11)));
    }

    @Test
    public void two2Test7() {
        List<Integer> actual = lambdaLists.two2(new ArrayList<>(Arrays.asList(3, 1, 4, 1, 6, 99, 0)));
        assertEquals(Arrays.asList(6, 8, 198, 0), actual, String.format("Failed for input %s", Arrays.asList(3, 1, 4, 1, 6, 99, 0)));
    }

    @Test
    public void square56Test1() {
        List<Integer> actual = lambdaLists.square56(new ArrayList<>(Arrays.asList(3, 1, 4)));
        assertEquals(Arrays.asList(19, 11), actual, String.format("Failed for input %s", Arrays.asList(3,1,4)));
    }

    @Test
    public void square56Test2() {
        List<Integer> actual = lambdaLists.square56(new ArrayList<>(Arrays.asList(1)));
        assertEquals(Arrays.asList(11), actual, String.format("Failed for input %s", Arrays.asList(1)));
    }

    @Test
    public void square56Test3() {
        List<Integer> actual = lambdaLists.square56(new ArrayList<>(Arrays.asList(2)));
        assertEquals(Arrays.asList(14), actual, String.format("Failed for input %s", Arrays.asList(2)));
    }

    @Test
    public void square56Test4() {
        List<Integer> actual = lambdaLists.square56(new ArrayList<>(Arrays.asList(3)));
        assertEquals(Arrays.asList(19), actual, String.format("Failed for input %s", Arrays.asList(3)));
    }

    @Test
    public void square56Test5() {
        List<Integer> actual = lambdaLists.square56(new ArrayList<>(Arrays.asList(4)));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList(4)));
    }

    @Test
    public void square56Test6() {
        List<Integer> actual = lambdaLists.square56(new ArrayList<>(Arrays.asList(5)));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList(5)));
    }

    @Test
    public void square56Test7() {
        List<Integer> actual = lambdaLists.square56(new ArrayList<>(Arrays.asList(6)));
        assertEquals(Arrays.asList(), actual, String.format("Failed for input %s", Arrays.asList(6)));
    }

    @Test
    public void square56Test8() {
        List<Integer> actual = lambdaLists.square56(new ArrayList<>(Arrays.asList(7)));
        assertEquals(Arrays.asList(59), actual, String.format("Failed for input %s", Arrays.asList(7)));
    }

    @Test
    public void square56Test9() {
        List<Integer> actual = lambdaLists.square56(new ArrayList<>(Arrays.asList(3, -1, -4, 1, 5, 9)));
        assertEquals(Arrays.asList(19, 11, 11, 91), actual, String.format("Failed for input %s", Arrays.asList(3, -1, -4, 1, 5, 9)));
    }
}