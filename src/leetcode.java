import com.sun.deploy.ref.Helpers;
import com.sun.xml.internal.ws.client.dispatch.DataSourceDispatch;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.CountDownLatch;

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

        board[x][y] ^=256;//--> wtf is this

        boolean result = (helperWordSearch(board, word, pointer + 1, x, y + 1)||helperWordSearch(board, word, pointer + 1, x, y - 1)||
                helperWordSearch(board, word, pointer + 1, x - 1, y)||helperWordSearch(board, word, pointer + 1, x + 1, y));

        board[x][y] ^= 256;
        return result;

    }

    static boolean powerOfTwo(int n){
        if (n==1){
            return true;
        }

        if (n%2!=0){
            return false;
        }

        return powerOfTwo(n/2);
    }


    static int arrayPartition(int [] nums){
        if (nums.length ==0){
            return 0;
        }
        Arrays.sort(nums);
        int count =0;
        for(int x=1;x<nums.length;x+=2){
            count += Math.min(nums[x],nums[x-1]);
        }
        return count;
    }

    static int longestPalindrome(String s){
        HashMap<Character,Integer> result = new HashMap<>();
        int count = 0;
        for(int x=0;x<s.length();x++){
            if (result.get(s.charAt(x))==null){
                result.put(s.charAt(x),1);
            }else{
                result.put(s.charAt(x),result.get(s.charAt(x))+1);
            }
        }

        Iterator map = result.entrySet().iterator();
        boolean one = false;
        while (map.hasNext()){
            Map.Entry pair = (Map.Entry)map.next();
            if((int)pair.getValue()==1 && one == false){
                count+=(int)pair.getValue();
                one = true;
            }else
            if ((int)pair.getValue()%2 ==1 && (int)pair.getValue()>1 && one== true){
                count+= (int)pair.getValue()-1;
            }else if ((int)pair.getValue()%2 ==1 && (int)pair.getValue()>1 && one== false){
                count+= (int)pair.getValue();
                one = true;
            }
            else
            if ((int)pair.getValue()%2 ==0){
                count+= (int)pair.getValue();
            }
        }
        return count;
    }

    static int solution(String S, int K) {
        String [] splitted = S.split("\\s+");

        return helperSolution(splitted,0,K,0,0,0);
    }
    //the idea will be the same like knapsack where the recursion only hapeen in the end
    //so we change the string into int to count all the character length
    // in the validation will change the count and the sms      this count for string
    static int helperSolution(String[]splitted,int index, int K,int count,int sms,int space){

        if (index == splitted.length && count+space-1>K){
            sms+=1;
            index-=1;
            count=0;
        }else if (index == splitted.length){
            return sms+1;
        }

        if (count+space-1 > K){
            index-=1;
            count=0;
            space=0;
            sms+=1;
        }else if (count+space-1 == K){
            count=0;
            space=0;
            sms+=1;
        }else{
            count+=splitted[index].length();
            index+=1;
            space+=1;
        }

        return helperSolution(splitted,index,K,count,sms,space);

//        if (stringsan.length()-1>K){
//            return helperSolution(splitted,index-1,K, "",sms+1);
//        }
//
//        if (stringsan.length()-1==K){
//            return helperSolution(splitted,index,K,"",sms+1);
//        }
//
//        if (index == splitted.length){
//            return sms;
//        }
//
//        return Math.min(helperSolution(splitted,index+1,K,stringsan+splitted[index]+" ",sms),
//                helperSolution(splitted,index+1,K,splitted[index],sms+1));
    }



    static int stringSubFixes(String word){
        int count =0;

        for(int x=0;x<word.length();x++){
            String temp = word.substring(x,word.length());
            for (int z=0;z<temp.length();z++){
                if (word.charAt(z)== temp.charAt(z)){
                    count++;
                }else{
                    break;
                }
            }
        }
    return count;
    }


    static int brokenCalc(int X, int Y) {
        int count =0;

        while(X<Y){
            if (Y%2==1){
                Y++;
            }else{
                Y/=2;
            }
            count++;
        }
        return count + X-Y;
    }


    //need more validation when using recursion
    static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0 ||matrix[0].length==0){
            return false;
        }
        int x =0,y = matrix[0].length-1;
        boolean result = false;


        while ( result== false ){
            if (x==matrix.length||y<0){
                break;
            }
            if (matrix[x][y]== target){
                return true;
            }else
            if (matrix[x][y]>target){
                y--;
            }else
            if(matrix[x][y]<target){
                x++;
            }
        }

        return result;
    }

    static int islandPerimeter(int [][]grid){

        int count =0;

        for(int x=0;x<grid.length;x++){
            int side =0;
            for(int y=0;y<grid[x].length;y++){
            }
        }

        return -1;
    }

    static int longestMountain(int []A){
        int result =0;
        boolean flagUp = false;
        boolean flagDown = false;
        int start = 0;
        int temp = 0;

        while(start<A.length-1){
            if (A[start]>A[start+1]){
                 temp++;
                 flagDown= true;
            }

            if (A[start]<A[start+1] && flagDown)
                flagUp = true;

            while (flagUp){
                temp++;
                if (temp>result)
                    result = temp;

                if (A[start]>A[start+1]){
                    temp=0;
                    break;
                }
                start++;
            }
            start++;
        }
//        int count=0;
//
//        for(int x=0;x<A.length-1;x++){
//            int temp =0;
//            boolean up= false;
//            boolean down = false;
//            for(int y=x+1;y<A.length;y++){
//                if(A[y-1]<A[y]&& down== false){
//                    temp++;
//                    up= true;
//                }
//                else if(A[y-1]>A[y]&& up==true){
//                    temp++;
//                    down= true;
//                }else{
//                    break;
//                }
//
//
//            }
//            count=(temp>count && up==true && down== true)?temp:count;
//        }
//        return count;
    }


//
//    static boolean searchMatrixHelper(int [][]matrix, int target, int coorX, int coorY){
//        if (coorX == matrix.length || coorY == matrix[0].length){
//            return false;
//        }
//
//        if (matrix[coorX][coorY]== target){
//            return true;
//        }else if(matrix[coorX][coorY]>target){
//            return searchMatrixHelper(matrix,target,coorX,coorY-1);
//        }else{
//            return searchMatrixHelper(matrix,target,coorX+1,coorY);
//        }
//    }

    //palindrome partition
    static void pphelper(String s,int index,List<List<String>>lists,List<String>list){
        if (index== s.length()){
            List<String>result = new ArrayList<>(list);
            lists.add(result);
            return;
        }


        for(int x=index; x<s.length();x++){
            if (checkPalindrome(s.substring(index,x+1))){
                String check = s.substring(index,x+1);
                list.add(check);
                pphelper(s,x+1,lists,list);
                list.remove(list.size()-1);
            }
        }
    }

    static List<List<String>> palindromePartition(String s){
        List<List<String>>results = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        pphelper(s, 0, results, temp);

        return results;

    }

    static boolean checkPalindrome(String s){
        int start = 0;
        int end = s.length()-1;

        while (start<=end){
            if (s.charAt(start)!=s.charAt(end))
                return false;

            start++;
            end--;
        }
        return true;
    }
    //



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

/*        test word word search
        char[][] board = new char[][]{
                {'C','A','A'},
                {'A','A','A'},
                {'B', 'C', 'D'},
        };

        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A', 'D', 'E','E'},
        };
        System.out.println(exist(board, "ABCCED"));*/

        //power of two
//        System.out.println(powerOfTwo(8));

//        test SMS
//        System.out.println(solution("SMS SMS SMS",3));
//        System.out.println(solution("abc abcd abcd ab",8));
//        System.out.println(solution("SMS MESSAGES ARE REALLY SHORT",12));
//        System.out.println(solution("A BB C D FF",2));

/*
    test string subfixes
        System.out.println(stringSubFixes("aa"));
*/

/*
        test for longest palindrome
        System.out.println(longestPalindrome("cccccccdd"));
*/

//        System.out.println(brokenCalHelper(3,10,0,10-3));

/*
        test for search matrix
        int [][]result = new int [][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };


        int [][]result = new int [][]{{-5}};

        System.out.println(searchMatrix(new int [][]{{-1},{-1}},-2));
*/
//        System.out.println(brokenCalc(2,3));

        //longest mountain
        System.out.println(longestMountain(new int []{2,2,2,3,5}));

        //palindrome partition
        System.out.println(palindromePartition("aaa").toString());
    }
}
