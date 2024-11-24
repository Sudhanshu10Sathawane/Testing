package org.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class AppTest {
    // Prime Number
    // #######################################################################################################################//
    @Test
    public void test_smallprime() {
        assertEquals(App.isPrime(2), true);
    }

    @Test
    public void test_nonprime() {
        assertEquals(App.isPrime(12), false);
    }

    @Test
    public void test_prime() {
        assertEquals(App.isPrime(19), true);
    }

    @Test
    public void test_invalid() {
        assertEquals(App.isPrime(-1), false);
    }
    // #######################################################################################################################//

    // Perfect Number
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Harshad Number
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Happy Number
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Triangular Number
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Palindrome Numbers
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Armstrong Numbers
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Perfect Squares
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Square Free Numbers
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Narcissistic Numbers
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Powerful Number
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Achilles Number
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Sterling Numbers
    // #######################################################################################################################//
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
    // #######################################################################################################################//

    // Eulerian Numbers
    // #######################################################################################################################//
    @Test
    public void testEulerianCase1() {
        assertEquals(App.isEulerian(3, 1), 4);
    }

    @Test
    public void testEulerianCase2() {
        assertEquals(App.isEulerian(4, 1), 11);
    }
}