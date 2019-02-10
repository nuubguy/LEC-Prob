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

    static int minimumTotal(Integer [][]triangle) {
        Integer [][] result = triangle.clone();
        int count = Integer.MAX_VALUE;

        if(triangle.length==1){
            return triangle[0][0];
        }

        for(int x=1;x<triangle.length;x++){
            if (x==triangle.length-1){

            }
            for(int z=0;z<triangle[x].length;z++){

                if(z==0){
                    result[x][z]=triangle[x-1][0] + triangle[x][z];
                }else if(z==triangle[x].length-1){
                    result[x][z]=triangle[x-1][triangle[x-1].length-1]+triangle[x][z];
                }else{
                    result[x][z] = Math.min(triangle[x-1][z-1] + triangle[x][z],triangle[x-1][z] + triangle[x][z]);
                }
                if(result[x][z]<count && x== triangle.length-1){
                    count = result[x][z];
                }
            }
        }

            System.out.println(Arrays.deepToString(result));
        return count;

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
        triangle.add(Arrays.asList(new Integer []{2,3}));
        triangle.add(Arrays.asList(new Integer []{1,-1,-3}));

        Integer[][] finalData = triangle.stream()
                .map(arr -> arr.toArray(Integer[]::new))
                .toArray(Integer[][]::new);


        triangle.add(Arrays.asList(new Integer []{    2}));
        triangle.add(Arrays.asList(new Integer []{   3, 4}));
        triangle.add(Arrays.asList(new Integer []{ 6, 5, 7}));
        triangle.add(Arrays.asList(new Integer []{4, 1, 8, 3}));

        System.out.println(minimumTotal(finalData));


        /*
                        {2}
                       {3,4}
                      {6,5,7}
                     {4,2,8,3}

                        {2}
                       {5,6}
                      {11,10,13}
                     {15,12,18,-2}
         */



//        triangle.add(Arrays.asList(new Integer []{6,5,7}));
//        triangle.add(Arrays.asList(new Integer []{4,1,8,3}));

//        System.out.println(minimumTotal(triangle,0,0));




    }
}
