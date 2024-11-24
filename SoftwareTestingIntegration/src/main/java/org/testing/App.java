package org.testing;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class UtilityMethods {
    public static int climbStairsf(int n, Map<Integer, Integer> memo) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (!memo.containsKey(n)) {
            memo.put(n, climbStairsf(n-1, memo) + climbStairsf(n-2, memo));
        }
        return memo.get(n);
    }
    public static int solve(int day, int lastTask, int arr[][], int dp[][]){
        if(day == 0){
            int maxi = 0;
            for(int task = 0; task<3; task++){
                if(task!=lastTask)
                    maxi = Math.max(maxi, arr[0][task]);
            }return maxi;
        }

        if(dp[day][lastTask] != -1) return dp[day][lastTask];

        int maxi = 0;
        for(int task = 0; task<3; task++){
            if(task!=lastTask){
                int score = arr[day][task]+solve(day-1, task, arr, dp);
                maxi = Math.max(maxi, score);
            }
        }
        return dp[day][lastTask] = maxi;
    }

    public static int longestCommonSubsequence(char[] arr1, char[] arr2) {
        int n1 = arr1.length, n2 = arr2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }
    public static boolean checkIsSubsetSum(ArrayList<Integer> list, int arr[], int target, int start ){
        int sum=0;
        for(int i=0; i<list.size(); i++){
            sum +=list.get(i);
        }
        if(sum==target) return true;
        if(sum>target) return false;

        for(int i=start; i<arr.length; i++){
            list.add(arr[i]);
            if(checkIsSubsetSum(list,arr,target,i+1)) return true;
            list.remove(list.size()-1);
        }
        return false;
    }
    public boolean isEven(int num) {
        return num % 2 == 0;
    }

    public boolean isOdd(int num) {
        return num % 2 == 1;
    }

    public int numSquareSum(int n) {
        int squareSum = 0;

        while (n != 0) {
            squareSum += (n % 10) * (n % 10);
            n /= 10;
        }

        return squareSum;
    }

    public int power(int x, int y) {
        if (y == 0) {
            return 1;
        }
        if (y % 2 == 0) {
            int temp = power(x, y / 2);
            return temp * temp;
        }

        int temp = power(x, y / 2);
        return x * temp * temp;
    }

    public int order(int x) {
        int n = 0;

        while (x != 0) {
            n = n + 1;
            x = x / 10;
        }

        return n;
    }

    public double customSqrt(double x) {
        if (x == 0 || x == 1) {
            return x;
        }

        double guess = x / 2.0;

        while (true) {
            double newGuess = 0.5 * (guess + x / guess);

            if (Math.abs(newGuess - guess) < 1e-10) {
                return newGuess;
            }

            guess = newGuess;
        }
    }

    public boolean isPower(int a) {
        if (a == 1) {
            return true;
        }

        for (int i = 2; i < a; i++) {
            double val = Math.log(a) / Math.log(i);
            if (Math.abs(val - (int) val) < 0.00000001) {
                return true;
            }
        }

        return false;
    }
}

public class App {
    public int climbStairs(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return UtilityMethods.climbStairsf(n, memo);
    }
    public int minimumEnergy(int arr[],int N){
        //code here

        int dp[]=new int[N];
        for(int i=0;i<N;i++){
            dp[i]=-1;
        }

        dp[0]=0;
        for(int i=1;i<N;i++){

            int left=dp[i-1]+Math.abs(arr[i]-arr[i-1]);
            int right=Integer.MAX_VALUE;
            if(i>1)
                right=dp[i-2]+Math.abs(arr[i]-arr[i-2]);
            dp[i]=Math.min(left,right);
        }

        return dp[N-1];
    }

    public int maximumPoints(int arr[][], int n) {
        // code here
        int dp[][] = new int[n][4];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<4; j++)
                dp[i][j] = -1;
        }
        return UtilityMethods.solve(n-1, 3, arr, dp);
    }
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        DSU uf = new DSU(N*N);
        int time = 0;
        while(!uf.isConnected(0, N*N-1)) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] > time) continue;
                    if (i < N-1 && grid[i+1][j]<=time) uf.union(i*N+j, i*N+j+N);
                    if (j < N-1 && grid[i][j+1]<=time) uf.union(i*N+j, i*N+j+1);
                }
            }
            time++;
        }
        return time - 1;
    }
    public int minInsertions(String s) {
        String sReverse = new StringBuilder(s).reverse().toString();
        int lcs = UtilityMethods.longestCommonSubsequence(s.toCharArray(), sReverse.toCharArray());
        return s.length() - lcs;
    }
    static Boolean isSubsetSum(int arr[], int target) {
        return UtilityMethods.checkIsSubsetSum(new ArrayList<>(),arr,target,0);
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    cost[i + 1][j + 1]++;
                }
            }
        }
        return cost[m][n];
    }
    public int maxProfit(int[] prices) {
        int lsf = Integer.MAX_VALUE;
        int op = 0;
        int pist = 0;

        for(int i = 0; i < prices.length; i++){
            if(prices[i] < lsf){
                lsf = prices[i];
            }
            pist = prices[i] - lsf;
            if(op < pist){
                op = pist;
            }
        }
        return op;
    }
    public int minAddToMakeValid(String S) {

        int runningSum = 0;
        int total = 0;
        for(int i =0;i<S.length();i++)
        {
            char c = S.charAt(i);
            if(c=='(')
                runningSum++;
            else if (c==')')
                runningSum--;

            if(runningSum<0)
            {
                total++;
                runningSum=0;
            }
        }

        return total + runningSum;
    }
    static UtilityMethods util= new UtilityMethods();
    public static boolean isPrime(int num) {
        if (util.isEven(num) && num != 2) {
            return false;
        }

        if (num > 1) {
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPerfect(int num) {
        int sumDivisors = 0;

        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sumDivisors += i;
            }
        }

        if (sumDivisors == num)
            return true;
        else
            return false;
    }
    // If a number is divisible by the sum of its digits, then it will be known as a
    // Harshad Number.
    public static boolean isHarshad(int num) {
        if (num == 0) {
            return false;
        }

        int remainder, totalSum = 0;
        int originalNum = num;

        while (num > 0) {
            remainder = num % 10;
            totalSum += remainder;
            num /= 10;
        }

        return originalNum % totalSum == 0;
    }

    // A number is called happy if it leads to 1 after a sequence of steps wherein
    // each step number
    // is replaced by the sum of squares of its digit that is if
    // we start with Happy Number and keep replacing it with digits square sum, we
    // reach 1.
    public static boolean isHappynumber(int num) {
        int slow = num;
        int fast = num;

        // Heavily Integration Testing as
        // it calls numSquareSum
        while (true) {
            slow = util.numSquareSum(slow);
            fast = util.numSquareSum(util.numSquareSum(fast));

            if (slow != fast) {
                continue;
            } else {
                break;
            }
        }

        if (slow == 1) {
            return true;
        } else {
            return false;
        }
    }

    // Triangular Numbers are those numbers which are obtained by continued
    // summation of the natural numbers 1, 2, 3, 4, 5,...
    public static boolean isTriangular(int num) {
        if (num == 0 || num == 1) {
            return true;
        }

        int triangularSum = 0;

        for (int i = 1; i <= num; i++) {
            triangularSum += i;

            if (triangularSum == num) {
                return true;
            }

            if (i == num) {
                return false;
            }
        }

        return false;
    }

    public static boolean isPalindrome(int num) {
        int originalNum = num;
        int reversedNum = 0;

        while (num > 0) {
            int digit = num % 10;
            reversedNum = reversedNum * 10 + digit;
            num = num / 10;
        }

        if (originalNum == reversedNum) {
            return true;
        } else {
            return false;
        }
    }

    // Given a number x, determine whether the given number is Armstrong number or
    // not.
    // # A positive integer of n digits is called an Armstrong number of order n
    // (order is number of digits) if.
    // # abcd... = pow(a,n) + pow(b,n) + pow(c,n) + pow(d,n) + ....
    public static boolean isArmstrong(int num) {
        // Heavily integration testing
        // calls power as well as order
        int digitCount = util.order(num);

        int temp = num;
        int sumOfPowers = 0;

        while (temp != 0) {
            int digit = temp % 10;
            sumOfPowers += util.power(digit, digitCount);
            temp /= 10;
        }

        if (sumOfPowers == num) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPerfectSquare(int num) {
        if (num <= 1) {
            return true;
        }

        int left = 1, right = num;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == num) {
                return true;
            } else if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    // A number is said to be square-free if no prime factor divides it more than
    // once, i.e., the largest power of a prime factor that divides n is one.
    public static boolean isSquareFree(int num) {
        if (num % 2 == 0) {
            num = num / 2;
        }

        if (num % 2 == 0) {
            return false;
        }

        // Function for Integration Testing, since
        // it calls customSqrt
        for (int i = 3; i <= util.customSqrt(num); i++) {
            if (num % i == 0) {
                num = num / i;
            }

            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    // Narcissistic Number is a number that is the sum of its own digits each raised
    // to the power of the number of digits
    public static boolean isNarcissistic(int num) {
        int numDigits = util.order(num);
        int originalNum = num;
        int totalSum = 0;

        while (originalNum != 0) {
            totalSum = totalSum + util.power(originalNum % 10, numDigits);
            originalNum = originalNum / 10;
        }

        if (num == totalSum) {
            return true;
        } else {
            return false;
        }
    }

    // A number n is said to be Powerful Number if for every prime factor p of it,
    // p2 also divides it
    public static boolean isPowerful(int n) {
        while (n % 2 == 0) {
            int power = 0;
            while (n % 2 == 0) {
                n /= 2;
                power += 1;
            }

            if (power == 1) {
                return false;
            }
        }

        int p = (int) util.customSqrt(n) + 1;
        for (int factor = 3; factor < p; factor += 2) {
            int power = 0;
            while (n % factor == 0) {
                n = n / factor;
                power += 1;
            }

            if (power == 1) {
                return false;
            }
        }

        return n == 1;
    }

    // An Achilles number is a number that is powerful
    // but not a perfect power
    public static boolean isAchillesNumber(int n) {
        if (isPowerful(n) && !util.isPower(n)) {
            return true;
        } else {
            return false;
        }
    }

    // Stirling Number of the second kind, S(n, k), is the number of ways of
    // splitting “n” items in “k” non-empty sets.
    // The formula used for calculating Stirling Number is:
    // S(n, k) = k* S(n-1, k) + S(n-1, k-1)
    public static int isStirling(int n, int k) {
        int originalN = n;
        int originalK = k;

        if (n <= 0) {
            return 1;
        } else if (k <= 0) {
            return 0;
        } else if (n == 0 && k == 0) {
            return -1;
        } else if (n != 0 && n == k) {
            return 1;
        } else if (n < k) {
            return 0;
        } else {
            int recursiveResult1 = isStirling(originalN - 1, originalK);
            return (originalK * recursiveResult1) + isStirling(originalN - 1, originalK - 1);
        }
    }

    // the Eulerian Number A(n, m),
    // is the number of permutations of the numbers 1 to n in which exactly m
    // elements are greater than previous element.
    public static int isEulerian(int n, int m) {
        if (m >= n || n == 0) {
            return 0;
        }

        if (m == 0) {
            return 1;
        }

        return ((n - m) * isEulerian(n - 1, m - 1) + (m + 1) * isEulerian(n - 1, m));
    }

    public static void main(String[] args) {}
}