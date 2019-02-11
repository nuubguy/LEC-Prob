import com.sun.deploy.ref.Helpers;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.List;

public class leetcode {

    //longest common subsequence
    //complexity will be 2pow(n)
    static int longestCommonSubsequence(String firstWord, String secondWord, int firstWordLength, int secondWordLength) {
        if (firstWordLength == 0 || secondWordLength == 0) {
            return 0;
        }
        if (firstWord.charAt(firstWordLength - 1) == secondWord.charAt(secondWordLength - 1)) {
            return 1 + longestCommonSubsequence(firstWord, secondWord, firstWordLength - 1, secondWordLength - 1);
        } else {
            return Math.max(longestCommonSubsequence(firstWord, secondWord, firstWordLength - 1, secondWordLength), longestCommonSubsequence(firstWord, secondWord, firstWordLength, secondWordLength - 1));
        }
    }

    //longest increasing
    //complexity will be 2pow(n)
    static int longestIncreasing(int[] nums, int firstPointer, int secondPointer) {
        if (secondPointer == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[secondPointer] > firstPointer) {
            taken = 1 + longestIncreasing(nums, nums[secondPointer], secondPointer + 1);
            return taken;
        } else {
            return Math.max(taken, longestIncreasing(nums, firstPointer, secondPointer + 1));
        }
    }

    //minimum step in triangle
    static int minimumTotal(List<List<Integer>> triangle) {
        int count = Integer.MAX_VALUE;
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        for (int x = 1; x < triangle.size(); x++) {
            for (int z = 0; z < triangle.get(x).size(); z++) {
                if (z == 0) {
                    triangle.get(x).set(z, triangle.get(x - 1).get(0) + triangle.get(x).get(z));
                } else if (z == triangle.get(x).size() - 1) {
                    triangle.get(x).set(z, triangle.get(x - 1).get(triangle.get(x - 1).size() - 1) + triangle.get(x).get(z));
                } else {
                    triangle.get(x).set(z, Math.min(triangle.get(x - 1).get(z - 1) + triangle.get(x).get(z), triangle.get(x - 1).get(z) + triangle.get(x).get(z)));
                }

                if (triangle.get(x).get(z) < count && x == triangle.size() - 1) {
                    count = triangle.get(x).get(z);
                }
            }
        }
        return count;
    }

    //count island
    static int numIslands(char[][] grid) {

        int count = 0;

        for (int x = 0; x < grid.length; x++) {
            for (int z = 0; z < grid[x].length; z++) {
                if (grid[x][z] == '1') {
                    count++;
                    helperNumIsland(grid, x, z);

                    System.out.println(Arrays.deepToString(grid));
                }
            }
        }
        return count;
    }

    //helper for count island
    static void helperNumIsland(char[][] grid, int x, int y) {

        if (x < 0 || y < 0 || x == grid.length || y == grid[0].length || grid[x][y] == '0') {
            return;
        }

        if (grid[x][y] == '1') {
            grid[x][y] = 'x';
        } else {
            return;
        }
        System.out.println(Arrays.deepToString(grid));

        helperNumIsland(grid, x + 1, y);
        helperNumIsland(grid, x - 1, y);
        helperNumIsland(grid, x, y + 1);
        helperNumIsland(grid, x, y - 1);
    }

    //word search
    static boolean exist(char[][] board, String word) {
        boolean result = false;

        for (int x = 0; x < board.length; x++) {
            for (int z = 0; z < board[0].length; z++) {
                if (board[x][z] == word.charAt(0)) {
                    result = helperWordSearch(board, word, 0, x, z);
                    if (result) {
                        return result;
                    }
                }
            }
        }

        return result;
    }

    static boolean helperWordSearch(char[][] board, String word, int pointer, int x, int y) {

        if (pointer== word.length()){
            return true;
        }

        if (x == board.length || x < 0 || y == board[0].length || y < 0 ) {
            return false;
        }
        if (board[x][y]!= word.charAt(pointer)){
            return false;
        }

        board[x][y] ^=256;

        boolean result = (helperWordSearch(board, word, pointer + 1, x, y + 1)||helperWordSearch(board, word, pointer + 1, x, y - 1)||
                helperWordSearch(board, word, pointer + 1, x - 1, y)||helperWordSearch(board, word, pointer + 1, x + 1, y));

        board[x][y] ^= 256;
        return result;

    }


    public static void main(String[] args) {
/*        test for longestCommonSubsequencce
        String firstWord = "AGGTAB";
        String secondWord = "GXTXAYB";
        System.out.println(longestCommonSubsequence(firstWord,secondWord,firstWord.length(),secondWord.length()));

        test for longest increasing
        int [] sequence= new int []{1,8,7};
        System.out.println(longestIncreasing(sequence,Integer.MIN_VALUE,0));*/



/*        test for shortest path in triangle
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(new Integer []{    2}));
        triangle.add(Arrays.asList(new Integer []{   3, 4}));
        triangle.add(Arrays.asList(new Integer []{ 6, 5, 7}));
        triangle.add(Arrays.asList(new Integer []{4, 1, 8, 3}));

        System.out.println(minimumTotal(triangle));
       */

/*        test to count island
        char [][]board = new char[][]{
                {'0','0','0','0','0'}

        };
        System.out.println(numIslands(board));*/

//        test word word search
//        char[][] board = new char[][]{
//                {'C','A','A'},
//                {'A','A','A'},
//                {'B', 'C', 'D'},
//        };

//        char[][] board = new char[][]{
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A', 'D', 'E','E'},
//        };
//        System.out.println(exist(board, "ABCCED"));


    }
}
