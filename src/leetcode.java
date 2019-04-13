import com.sun.deploy.ref.Helpers;
import com.sun.xml.internal.ws.client.dispatch.DataSourceDispatch;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class leetcode {

      static public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }

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

    static boolean perfectNumber(int number){
        int num =0;
        for(int x=1;x*x<=number;x++){
            if(number%x==0){
                num+=x;
                if(x * x !=number){
                    num+=number/x;
                }
            }
        }
        return num-number==number;
    }





    static int longestMountain(int []A){
        int ans=0,base=0;
        while(base<A.length){
            int end = base;

            if(end +1<A.length && A[end]<A[end+1]){
                while(end +1<A.length && A[end]<A[end+1])
                    end++;

                if(end+1<A.length && A[end]>A[end+1]){
                    while(end +1<A.length && A[end]>A[end+1])
                        end++;
                    ans = Math.max(ans,end-base+1);
                }
            }
            base = Math.max(end,base + 1);
        }
        return ans;
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

    static TreeNode invertTree(TreeNode root){
          Queue<TreeNode>trees = new LinkedList<>();
          trees.add(root);

          while (trees.size()!=0){
              TreeNode currentRoot = trees.peek();
              if(currentRoot.left!=null)
                  trees.add(currentRoot.left);
              if (currentRoot.right!=null)
                  trees.add(currentRoot.right);

              TreeNode temp = currentRoot.left;
              currentRoot.left = currentRoot.right;
              currentRoot.right = temp;

              trees.poll();
          }

          return root;
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

    static int bitwiseComplement(int N) {
          if (N==0){
              return 0;
          }else if(N==1){
              return 1;
          }


          int count =N;
          String temp ="";
          while (count!=0){
              if ((count & 1)==1){
                  temp = "0"+temp;
              }else{
                  temp = "1"+temp;
              }
              count>>=1;
          }
//        int base = 10;
//        System.out.println(Integer.toBinaryString(base));
//        return -1;
        return Integer.parseInt(temp,2);
    }

    static int minDominoRotations(int[] A, int[] B) {
        return -1;
    }

    static boolean minValue(String S){
//          Stack<Character>temp = new Stack<>();
//          char current = ' ';
//          for(int x=0;x<S.length();x++){
//             if (x==0 && S.charAt(x)=='a'){
//                 current+=S.charAt(x);
//             }else if (x==0 && S.charAt(x)=='b'||S.charAt(x)=='c'){
//                 return false;
//             }
//
//             if (current == "ahahahaha".to){
//
//             }
//
//          }
//
//          return false;
        return false;
    }

    static public List<String> commonChars(String[] A) {
          List<String>result = new ArrayList<>();
          int []current = new int [26];
          for(int j=0;j<A[0].length();j++){
              current[A[0].charAt(j)-'a']++;
          }

          for(int x=1;x<A.length;x++){
              int []temp = new int [26];
              for(int z=0;z<A[x].length();z++){
                  temp[A[x].charAt(z)-'a']++;
              }
              for(int i=0;i<26;i++){
                  if (current[i]>temp[i]){
                      current[i]= temp[i];
                  }
              }
          }

          for(int a=0;a<26;a++){
              for(int x=0;x<current[a];x++){
                  result.add(Character.toString((char)(a+'a')));
              }
          }

//        List<String> ans = new ArrayList<>();
//        // Common characters dictionary
//        int[] dict = new int[26];
//        for (int j = 0; j < A[0].length(); j++) {
//            dict[A[0].charAt(j) - 'a']++;
//        }
//        for (int i = 1; i < A.length; i++) {
//            // Dictionary of the current word
//            int[] curr = new int[26];
//            for (int j = 0; j < A[i].length(); j++) {
//                curr[A[i].charAt(j) - 'a']++;
//            }
//            // Update the common dictionary
//            for (int j = 0; j < 26; j++) {
//                if (curr[j] < dict[j]) dict[j] = curr[j];
//            }
//        }
//        for (int i = 0; i < 26; i++) {
//            for (int j = 0; j < dict[i]; j++) {
//                ans.add(Character.toString((char) ('a' + i)));
//            }
//        }
//        return ans;
        return result;
    }

    static public boolean canThreePartsEqualSum(int[] A) {
        int count =0;

        for(int x=0;x<A.length;x++){
            count+=A[x];
        }

        if(count%3!=0)
            return false;

        count= count/3;
        int countEquals=0;
        int reset =0;

        for(int z=0;z<A.length;z++){
            reset+=A[z];
            if (reset==count) {
                countEquals++;
                reset = 0;
            }
        }
        return countEquals==3;
    }

    public void gameOfLife(int[][] board) {
        for(int x=0;x<board.length;x++){
            for(int z=0;z<board[0].length;z++){

            }
        }
    }
//    public boolean checkGolHelper(int x, int y){
//        if(x>0 && y>0){
//
//        }
//    }

    //

    static boolean increasingTriplet(int[] nums) {

        if(nums.length == 0)
            return false;

        int min = Integer.MAX_VALUE;
        int second_min = Integer.MAX_VALUE;

        int i=0;

        while(i < nums.length){

            if(nums[i] < min)
                min = nums[i];

            else if (nums[i] == min || nums[i] == second_min){

            }

            else if(nums[i] < second_min)
                second_min = nums[i];

            else
                return true;

            i++;

        }

        return false;
    }

    static boolean lemonadeChange(int[] bills) {
        int five =0;
        int ten =0;

        for(int bill:bills){
            if (bill==5)
                five++;

            else if (bill == 10 && five==0){
                return false;
            }else if(bill == 10 && five!=0){
                five--;
                ten++;
            }else if (bill==20 && ten>0 && five>0){
                five--;
                ten--;
            }else if (bill==20 && ten==0 && five>=3){
                five-=3;
            }else{
                return false;
            }
        }
        return true;
    }


    //convert binary to decimal
    static public List<Boolean> prefixesDivBy5(int[] A){
        int k = 0;
        List<Boolean> ans = new ArrayList<>();
        for (int a : A) {
            k = (k << 1 | a) % 5;
            ans.add(k == 0);
        }
        return ans;
    }


    static public int maxProfit(int[] prices, int fee) {
        /*int low =Integer.MIN_VALUE, high = Integer.MIN_VALUE;
        int result =0;
        for(int x=0;x<prices.length;x++){
            if(low !=Integer.MIN_VALUE && high==Integer.MIN_VALUE && prices[x]-low>fee){
                high = prices[x];
            }
            if(high!= Integer.MIN_VALUE && prices[x]>high){
                high = prices[x];
            }
            if(high!=Integer.MIN_VALUE && (high- prices[x]>fee || x==prices.length-1)){
                result = high - low - fee;
                high = Integer.MIN_VALUE;
                low = Integer.MIN_VALUE;
            }
            low = (low!=Integer.MIN_VALUE)?Math.min(low,prices[x]):prices[x];
        }*/
                  int profit = 0;
        Integer lo = null, hi = null, n = prices.length;
        for (int i = 0; i < n; i++) {
            if (lo != null && hi == null && prices[i] - lo > fee)
                hi = prices[i]; // buy in
            if (hi != null && prices[i] > hi)
                hi = prices[i]; // update highest
            if (hi != null && (hi - prices[i] > fee || i == n - 1)) {
                profit += hi - lo - fee;
                hi = null;
                lo = null;
            }

            lo = lo != null ? Math.min(lo, prices[i]) : prices[i];
        }
        return profit;
//        return result;
    }

    static boolean isSubsequence(String s, String t) {
        int sLength= s.length()-1;
        int tLength = t.length()-1;

        while(sLength>=0 && tLength>=0){
              if (s.charAt(sLength)==t.charAt(tLength)){
                  sLength--;
                  tLength--;
              }else{
                  tLength--;
              }
          }
        if (sLength==-1)
            return true;
        return false;
    }

    static int numRescueBoats(int[] people, int limit) {
        int count =0;
        int firstPointer =0;
        int secondPointer = people.length-1;

        Arrays.sort(people);

        while(firstPointer<=secondPointer){
            count++;
            if (people[firstPointer]+people[secondPointer]<=limit){
                secondPointer--;
            }
            firstPointer++;
        }

        return count;
    }

    public String predictPartyVictory(String senate) {
        int rad =0;
        int dire=0;

        for(int x=0;x<senate.length();x++){
            if(senate.charAt(x)=='R')
                rad++;
            if(senate.charAt(x)=='D')
                dire++;

        }
        if (rad>dire){
            return "Radiant";
        }else if(rad<dire){
            return "Dire";
        }

        return Character.toString(senate.charAt(0));
    }

    static public boolean canVisitAllRooms(List<List<Integer>> rooms) {
          boolean []door = new boolean[rooms.size()];
          door[0] =true;
          Stack<Integer>key = new Stack<>();
          key.push(0);

          while (!key.empty()){
              int index = key.pop();

              for(int temp :rooms.get(index)){
                  if (!door[temp]){
                      door[temp]= true;
                      key.push(temp);
                  }
              }
          }
          for (boolean result :door)
              if (!result)
                  return false;

        return true;
    }

    static public int maxAreaOfIsland(int [][]grid, int x, int y){

          if (x==-1||y==-1||x==grid.length||y==grid[0].length){
              return 0;
          }

          if (grid[x][y]==0 || grid[x][y]==-1)
            return 0;

        if (grid[x][y]==1){
            grid[x][y]=-1;
        }
        return 1+maxAreaOfIsland(grid,x,y+1)+maxAreaOfIsland(grid,x,y-1)+
                maxAreaOfIsland(grid,x+1,y)+maxAreaOfIsland(grid,x-1,y);
    }

    static public int maxAreaOfIsland(int[][] grid) {
        int count =0;
        for(int x=0;x<grid.length;x++){
            for(int y=0;y<grid[0].length;y++){
                if (grid[x][y]==1)
                    count= Math.max(count,maxAreaOfIsland(grid,x,y));
            }
        }
        return -1;
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
//        System.out.println(solution("A BB
//
//        C D FF",2));

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

//        //longest mountain
//        System.out.println(longestMountain(new int []{2,3,3,2,0,2}));
//
//        //palindrome partition
//        System.out.println(palindromePartition("aaa").toString());

        //perfect number
//        System.out.println(perfectNumber(28));

        //invert binary tree
//        TreeNode mainTree = new TreeNode(4);
//        mainTree.left = new TreeNode(2);
//        mainTree.left.left = new TreeNode(1);
//        mainTree.left.right = new TreeNode(3);
//        mainTree.right = new TreeNode(7);
//        mainTree.right.left = new TreeNode(6);
//        mainTree.right.right = new TreeNode(9);
//
//        invertTree(mainTree);
//        System.out.println(bitwiseComplement(0));

         //common character
//        System.out.println(commonChars(new String[]{"bella","label","roller"}));

//        System.out.println(canThreePartsEqualSum(new int []{0,2,1,-6,6,7,9,-1,2,0,1}));
//        System.out.println(increasingTriplet(new int[]{1,0,0,10,0,0,1000}));

//        System.out.println(lemonadeChange(new int []{5,5,10,10,20}));

//        System.out.println(prefixesDivBy5(new int []{1,1,1,0,1}));
//        System.out.println(maxProfit(new int []{1, 3, 7, 5, 10, 3},3));

//        System.out.println(isSubsequence("axc","ahbgdc"));
//        System.out.println(numRescueBoats(new int []{1,2},3));

        List<List<Integer>>abc = new ArrayList<>();
        List <Integer>abc1 = new ArrayList<>();
        abc1.add(1);
        List <Integer>abc2 = new ArrayList<>();
        abc2.add(2);
        List <Integer>abc4 = new ArrayList<>();
        List <Integer>abc3 = new ArrayList<>();
        abc2.add(3);
        abc.add(abc1);
        abc.add(abc2);
        abc.add(abc3);

        System.out.println(canVisitAllRooms(abc));

      }
}
