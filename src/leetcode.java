import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class leetcode {

    //longest common subsequence
    //complexity will be 2pow(n)
    static int longestCommonSubsequence(String firstWord,String secondWord, int firstWordLength, int secondWordLength){
        if(firstWordLength ==0 || secondWordLength ==0){
            return 0;
        }
        if (firstWord.charAt(firstWordLength-1) == secondWord.charAt(secondWordLength-1)){
            return 1 + longestCommonSubsequence(firstWord,secondWord,firstWordLength-1,secondWordLength-1);
        }else{
            return Math.max(longestCommonSubsequence(firstWord,secondWord,firstWordLength-1,secondWordLength),longestCommonSubsequence(firstWord,secondWord,firstWordLength,secondWordLength-1));
        }
    }

    //longest increasing
    //complexity will be 2pow(n)
    static int longestIncreasing(int []nums,int firstPointer,int secondPointer) {
        if(secondPointer==nums.length){
            return 0;
        }
        int taken=0;
        if(nums[secondPointer]>firstPointer){
            taken =1 + longestIncreasing(nums, nums[secondPointer],secondPointer+1);
            return taken;
        }else{
            return Math.max(taken,longestIncreasing(nums,firstPointer,secondPointer+1));
        }
    }

    //min path in triangle
    //loop all value of the array in every row

    static int minimumTotal(List<List<Integer>> triangle, int count,int row) {

        if(triangle.size()==0){
            return triangle.get(0).get(0);
        }

        if(row == triangle.size()){
            return count;
        }

        int o=Integer.MAX_VALUE;

        for(int x=0;x<triangle.get(row).size();x++){
            int result = minimumTotal(triangle,count + triangle.get(row).get(x),row+1);

            if(result<o){
                o = result;
            }
        }
        return o;
    }

    public static void main(String [] args){
        //test for longestCommonSubsequencce
//        String firstWord = "AGGTAB";
//        String secondWord = "GXTXAYB";
//        System.out.println(longestCommonSubsequence(firstWord,secondWord,firstWord.length(),secondWord.length()));

        //test for longest increasing
//        int [] sequence= new int []{1,8,7};
//        System.out.println(longestIncreasing(sequence,Integer.MIN_VALUE,0));

        //test for shortest path in triangle
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(new Integer []{-1}));
        triangle.add(Arrays.asList(new Integer []{-2,-3}));
//        triangle.add(Arrays.asList(new Integer []{6,5,7}));
//        triangle.add(Arrays.asList(new Integer []{4,1,8,3}));

        System.out.println(minimumTotal(triangle,0,0));




    }
}
