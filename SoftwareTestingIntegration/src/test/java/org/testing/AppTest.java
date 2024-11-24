package org.testing;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testClimbStairsf_BaseCase0() {
        Map<Integer, Integer> memo = new HashMap<>();
        int result = UtilityMethods.climbStairsf(0, memo);
        assertEquals(1, result);
    }

    @Test
    public void testClimbStairsf_BaseCase1() {
        Map<Integer, Integer> memo = new HashMap<>();
        int result = UtilityMethods.climbStairsf(1, memo);
        assertEquals(1, result);
    }

    @Test
    public void testClimbStairsf_SmallInput() {
        Map<Integer, Integer> memo = new HashMap<>();
        int result = UtilityMethods.climbStairsf(2, memo);
        assertEquals(2, result);
    }

    @Test
    public void testClimbStairsf_LargerInput() {
        Map<Integer, Integer> memo = new HashMap<>();
        int result = UtilityMethods.climbStairsf(5, memo);
        assertEquals(8, result);
    }

    @Test
    public void testClimbStairsf_Memoization() {
        Map<Integer, Integer> memo = new HashMap<>();
        UtilityMethods.climbStairsf(5, memo);
        assertEquals(3, memo.get(3).intValue());
        assertEquals(5, memo.get(4).intValue());
    }

    @Test
    public void testClimbStairs_SmallInput() {
        App app = new App();
        int result = app.climbStairs(3);
        assertEquals(3, result);
    }

    @Test
    public void testClimbStairs_LargeInput() {
        App app = new App();
        int result = app.climbStairs(10);
        assertEquals(89, result);
    }

    @Test
    public void testClimbStairs_LargerInput() {
        App app = new App();
        int result = app.climbStairs(20);
        assertEquals(10946, result);
    }

    @Test
    public void testClimbStairs_Zero() {
        App app = new App();
        int result = app.climbStairs(0);
        assertEquals(1, result);
    }

    @Test
    public void testClimbStairs_One() {
        App app = new App();
        int result = app.climbStairs(1);
        assertEquals(1, result);
    }

    @Test
    public void testSingleElement() {
        int[] arr = {10};
        int result = new App().minimumEnergy(arr, arr.length);
        assertEquals(0, result);
    }

    @Test
    public void testTwoElements() {
        int[] arr = {10, 20};
        int result = new App().minimumEnergy(arr, arr.length);
        assertEquals(10, result);
    }
    @Test
    public void testThreeElements() {
        int[] arr = {10, 30, 20};
        int result = new App().minimumEnergy(arr, arr.length);
        assertEquals(10, result);
    }
    @Test
    public void testMultipleElements() {
        int[] arr = {10, 20, 10, 40};
        int result = new App().minimumEnergy(arr, arr.length);
        assertEquals(30, result);
    }

    @Test
    public void testIncreasingSequence() {
        int[] arr = {1, 2, 3, 4, 5};
        int result = new App().minimumEnergy(arr, arr.length);
        assertEquals(4, result);
    }

    @Test
    public void testDecreasingSequence() {
        int[] arr = {5, 4, 3, 2, 1};
        int result = new App().minimumEnergy(arr, arr.length);
        assertEquals(4, result);
    }

    @Test
    public void testLargeJump() {
        int[] arr = {10, 100, 10};
        int result = new App().minimumEnergy(arr, arr.length);
        assertEquals(0, result);
    }

    @Test
    public void testLargeArray() {
        int[] arr = {1, 100, 1, 100, 1};
        int result = new App().minimumEnergy(arr, arr.length);
        assertEquals(0, result);
    }
    @Test
    public void testSingleDay() {
        int[][] arr = {{10, 40, 70}};
        int result = new App().maximumPoints(arr, arr.length);
        assertEquals(70, result);
    }

    @Test
    public void testTwoDays() {
        int[][] arr = {
                {10, 40, 70},
                {20, 50, 30}
        };
        int result = new App().maximumPoints(arr, arr.length);
        assertEquals(120, result);
    }

    @Test
    public void testThreeDays() {
        int[][] arr = {
                {10, 40, 70},
                {20, 50, 30},
                {30, 60, 90}
        };
        int result = new App().maximumPoints(arr, arr.length);
        assertEquals(210, result);
    }

    @Test
    public void testAllZeroPoints() {
        int[][] arr = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int result = new App().maximumPoints(arr, arr.length);
        assertEquals(0, result);
    }

    @Test
    public void testVaryingPoints() {
        int[][] arr = {
                {10, 50, 20},
                {20, 60, 30},
                {40, 70, 80}
        };
        int result = new App().maximumPoints(arr, arr.length);
        assertEquals(160, result);
    }

    @Test
    public void testLargeInput() {
        int[][] arr = {
                {10, 40, 70},
                {20, 50, 30},
                {30, 60, 90},
                {40, 80, 100},
                {50, 90, 110}
        };
        int result = new App().maximumPoints(arr, arr.length);
        assertEquals(400, result);
    }

    @Test
    public void testSubsetExists() {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int target = 9;
        boolean result = App.isSubsetSum(arr, target);
        assertTrue(result);
    }

    @Test
    public void testSubsetDoesNotExist() {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int target = 30;
        boolean result = App.isSubsetSum(arr, target);
        assertFalse(result);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        int target = 0;
        boolean result = App.isSubsetSum(arr, target);
        assertTrue(result);
    }

    @Test
    public void testSingleElementEqualToTarget() {
        int[] arr = {7};
        int target = 7;
        boolean result = App.isSubsetSum(arr, target);
        assertTrue(result);
    }

    @Test
    public void testSingleElementNotEqualToTarget() {
        int[] arr = {5};
        int target = 10;
        boolean result = App.isSubsetSum(arr, target);
        assertFalse(result);
    }

    @Test
    public void testMultipleSubsets() {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 10;
        boolean result = App.isSubsetSum(arr, target);
        assertTrue(result);
    }

    @Test
    public void testNegativeNumbers() {
        int[] arr = {-3, 1, 2, -5, 4};
        int target = 0;
        boolean result = App.isSubsetSum(arr, target);
        assertTrue(result);
    }

    @Test
    public void testSwimInWater() {
        int[][] grid = {
                {0, 1, 2, 3},
                {3, 2, 1, 0},
                {2, 3, 4, 5},
                {1, 2, 3, 4}
        };
        App app = new App();
        assertEquals(4, app.swimInWater(grid));
    }

    @Test
    public void testSingleCellGrid() {
        int[][] grid = {
                {0}
        };
        App app = new App();
        assertEquals(-1, app.swimInWater(grid));
    }

    @Test
    public void testLargeGrid() {
        int[][] grid = new int[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                grid[i][j] = i + j;
            }
        }
        App app = new App();
        assertEquals(198, app.swimInWater(grid));
    }

    @Test
    public void testMinInsertions() {
        App app = new App();
        assertEquals(3, app.minInsertions("abcbaaaa"));
        assertEquals(0, app.minInsertions("a"));
        assertEquals(3, app.minInsertions("race"));
        assertEquals(4, app.minInsertions("abcde"));
    }

    @Test
    public void testEmptyString() {
        App app = new App();
        assertEquals(0, app.minInsertions(""));
    }
    @Test
    public void testEmptyMain() {
        App.main(null);
    }
    @Test
    public void testMinDistance() {
        App app = new App();
        assertEquals(3, app.minDistance("horse", "ros"));
        assertEquals(5, app.minDistance("intention", "execution"));
        assertEquals(0, app.minDistance("kitten", "kitten"));
        assertEquals(2, app.minDistance("flaw", "lawn"));
    }
    @Test
    public void testMaxProfit() {
        App app = new App();
        assertEquals(5, app.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(0, app.maxProfit(new int[]{7, 6, 4, 3, 1}));
        assertEquals(6, app.maxProfit(new int[]{1, 2, 4, 2, 5, 7}));
        assertEquals(7, app.maxProfit(new int[]{3, 1, 4, 6, 2, 8}));
    }

    @Test
    public void testOnSingleDay() {
        App app = new App();
        assertEquals(0, app.maxProfit(new int[]{5}));
    }

    @Test
    public void testConstantPrices() {
        App app = new App();
        assertEquals(0, app.maxProfit(new int[]{4, 4, 4, 4, 4}));
    }
    @Test
    public void testMinDistanceEmptyString() {
        App app = new App();
        assertEquals(0, app.minDistance("", ""));
        assertEquals(3, app.minDistance("", "abc"));
        assertEquals(3, app.minDistance("abc", ""));
    }
    @Test
    public void testMinAddToMakeValidValidString() {
        App app = new App();
        assertEquals(0, app.minAddToMakeValid("()"));
        assertEquals(0, app.minAddToMakeValid("(())"));
    }

    @Test
    public void testMinAddToMakeValidInvalidString() {
        App app = new App();
        assertEquals(1, app.minAddToMakeValid("("));
        assertEquals(1, app.minAddToMakeValid(")"));
        assertEquals(1, app.minAddToMakeValid("(()"));
        assertEquals(2, app.minAddToMakeValid("()))"));
        assertEquals(4, app.minAddToMakeValid("(((("));
        assertEquals(4, app.minAddToMakeValid("))))"));
    }

    @Test
    public void testMinAddToMakeValidMixedStrings() {
        App app = new App();
        assertEquals(1, app.minAddToMakeValid("(()))"));
        assertEquals(1, app.minAddToMakeValid("((())"));
        assertEquals(2, app.minAddToMakeValid("())(()"));
        assertEquals(4, app.minAddToMakeValid("))((())("));
    }

    @Test
    public void testMinAddToMakeValidEmptyString() {
        App app = new App();
        assertEquals(0, app.minAddToMakeValid(""));
    }

    @Test
    public void testMinAddToMakeValidLongString() {
        App app = new App();
        assertEquals(4, app.minAddToMakeValid("((())(())))((("));
        assertEquals(4, app.minAddToMakeValid("()(()))())(()))("));
    }
    App app = new App();
    @Test
    public void test_smallprime() {
        assertEquals(app.isPrime(2), true);
    }

    @Test
    public void test_nonprime() {
        assertEquals(app.isPrime(12), false);
    }

    @Test
    public void test_prime() {
        assertEquals(app.isPrime(19), true);
    }

    @Test
    public void test_invalid() {
        assertEquals(app.isPrime(-1), false);
    }
    @Test
    public void test_perfect_number() {
        assertEquals(App.isPerfect(28), true);
    }

    @Test
    public void test_nonperfect_number() {
        assertEquals(App.isPerfect(12), false);
    }

    @Test
    public void test_edge_case() {
        assertEquals(App.isPerfect(1), false);
    }

    @Test
    public void test_another_perfect_number() {
        assertEquals(App.isPerfect(496), true);
    }
    @Test
    public void test_harshad_number() {
        assertEquals(App.isHarshad(18), true);
    }

    @Test
    public void test_nonharshad_number() {
        assertEquals(App.isHarshad(23), false);
    }

    @Test
    public void test_edge_case_harshad() {
        assertEquals(App.isHarshad(0), false);
    }

    @Test
    public void test_larger_harshad_number() {
        assertEquals(App.isHarshad(27), true);
    }

    @Test
    public void test_happy_number() {
        assertEquals(App.isHappynumber(19), true);
    }

    @Test
    public void test_nonhappy_number() {
        assertEquals(App.isHappynumber(22), false);
    }

    @Test
    public void test_edge_case_happy() {
        assertEquals(App.isHappynumber(1), true);
    }

    @Test
    public void test_larger_happy_number() {
        assertEquals(App.isHappynumber(82), true);
    }

    @Test
    public void test_triangular_number_0() {
        assertEquals(App.isTriangular(0), true);
    }

    @Test
    public void test_nontriangular_number() {
        assertEquals(App.isTriangular(5), false);
    }

    @Test
    public void test_larger_triangular_number() {
        assertEquals(App.isTriangular(10), true);
    }

    @Test
    public void test_another_nontriangular_number() {
        assertEquals(App.isTriangular(696), false);
    }

    @Test
    public void test_nonpalindrome_number() {
        assertEquals(App.isPalindrome(456), false);
    }

    @Test
    public void test_single_digit_number() {
        assertEquals(App.isPalindrome(7), true);
    }

    @Test
    public void test_larger_palindrome_number() {
        assertEquals(App.isPalindrome(1221), true);
    }

    @Test
    public void test_another_nonpalindrome_number() {
        assertEquals(App.isPalindrome(12345), false);
    }

    @Test
    public void test_armstrong_number() {
        assertEquals(App.isArmstrong(153), true);
    }

    @Test
    public void test_nonarmstrong_number() {
        assertEquals(App.isArmstrong(1253), false);
    }

    @Test
    public void test_single_digit_armstrong_number() {
        assertEquals(App.isArmstrong(5), true);
    }

    @Test
    public void test_larger_armstrong_number() {
        assertEquals(App.isArmstrong(1634), true);
    }

    @Test
    public void test_another_nonarmstrong_number() {
        assertEquals(App.isArmstrong(123), false);
    }

    @Test
    public void test_perfect_square() {
        assertEquals(App.isPerfectSquare(16), true);
    }

    @Test
    public void test_nonperfect_square() {
        assertEquals(App.isPerfectSquare(10), false);
    }

    @Test
    public void test_smallest_perfect_square() {
        assertEquals(App.isPerfectSquare(1), true);
    }

    @Test
    public void test_larger_perfect_square() {
        assertEquals(App.isPerfectSquare(81), true);
    }

    @Test
    public void test_square_free_number() {
        assertEquals(App.isSquareFree(17), true);
    }

    @Test
    public void test_nonsquare_free_number() {
        assertEquals(App.isSquareFree(16), false);
    }

    @Test
    public void test_larger_square_free_number() {
        assertEquals(App.isSquareFree(19), true);
    }

    @Test
    public void test_smallest_square_free_number() {
        assertEquals(App.isSquareFree(1), true);
    }

    @Test
    public void test_narcissistic_number() {
        assertEquals(App.isNarcissistic(153), true);
    }

    @Test
    public void test_nonnarcissistic_number() {
        assertEquals(App.isNarcissistic(129), false);
    }

    @Test
    public void test_smallest_narcissistic_number() {
        assertEquals(App.isNarcissistic(0), true);
    }

    @Test
    public void test_larger_narcissistic_number() {
        assertEquals(App.isNarcissistic(9474), true);
    }
    @Test
    public void testPowerfulNumber() {
        assertEquals(App.isPowerful(16), true);
    }

    @Test
    public void testNonPowerfulNumber() {
        assertEquals(App.isPowerful(12), false);
    }

    @Test
    public void testSmallestPowerfulNumber() {
        assertEquals(App.isPowerful(1), true);
    }

    @Test
    public void testAnotherNonPowerfulNumber() {
        assertEquals(App.isPowerful(30), false);
    }
    @Test
    public void testAchillesNumber() {
        assertEquals(App.isAchillesNumber(72), true);
    }

    @Test
    public void testNonAchillesNumber() {
        assertEquals(App.isAchillesNumber(16), false);
    }

    @Test
    public void testAnotherAchillesNumber() {
        assertEquals(App.isAchillesNumber(392), true);
    }

    @Test
    public void testAnotherNonAchillesNumber() {
        assertEquals(App.isAchillesNumber(25), false);
    }
    @Test
    public void testStirling() {
        assertEquals(App.isStirling(5, 2), 15);
    }

    @Test
    public void testNegativeN() {
        assertEquals(App.isStirling(-1, 2), 1);
    }

    @Test
    public void testNEqualsK() {
        assertEquals(App.isStirling(3, 3), 1);
    }

    @Test
    public void testNLessThanK() {
        assertEquals(App.isStirling(2, 5), 0);
    }
    @Test
    public void testEulerianCase1() {
        assertEquals(App.isEulerian(3, 1), 4);
    }

    @Test
    public void testEulerianCase2() {
        assertEquals(App.isEulerian(4, 1), 11);
    }
}