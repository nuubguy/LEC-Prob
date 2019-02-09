import java.util.Arrays;
import java.util.Stack;

public class Check {

    static int surroundingGrid(String[][] grid, String wordToFind) {
        int count = 0;
        for (int a = 0; a < grid.length; a++) {
            for (int b = 0; b < grid[0].length; b++) {
                if (grid[a][b].matches(Character.toString(wordToFind.charAt(0)))) {
                    String[] direction = new String[8];
                    Arrays.fill(direction, Character.toString(wordToFind.charAt(0)));

                    for (int c = 1; c < wordToFind.length(); c++) {
                        if (b - c > -1) {
                            direction[0] += grid[a][b - c];
                        }
                        if (b + c < grid[0].length) {
                            direction[1] += grid[a][b + c];
                        }
                        if (a - c > -1) {
                            direction[2] += grid[a - c][b];
                        }
                        if (a + c < grid.length) {
                            direction[3] += grid[a + c][b];
                        }
                        if (b + c < grid[0].length && a + c < grid.length) {
                            direction[4] += grid[a + c][b + c];
                        }
                        if (b + c < grid[0].length && a - c > -1) {
                            direction[5] += grid[a - c][b + c];
                        }
                        if (b - c > -1 && a + c < grid.length) {
                            direction[6] += grid[a + c][b - c];
                        }
                        if (a - c > -1 && b - c > -1) {
                            direction[7] += grid[a - c][b - c];
                        }
                    }
                    for (int d = 0; d < direction.length; d++) {
                        if (direction[d].matches(wordToFind)) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

//    static int regionAndQoncuer(String [][]grid){
//
//        for(int x=0;x<grid.length;x++){
//            for(int y=0;y<grid[0].length;y++){
//                if(grid[x][y]=="."){
//                grid = fi
//                }
//            }
//        }
//        return 0;
//    }
//
//    Object [][] fillBoard(String[][]grid, int x , int y, int count){
//        for(int start = y ; start<grid.length;start++){
//
//
//            if(grid[x][y].equals(".")){
//                grid[x][y]=""+count;
//                if(x!=0 &&grid[x+1][y].equals(".")){
//
//                }
//            }
//        }
//    }


    static int getMaxArea(int heights[]) {

        Stack<Integer> s = new Stack<>();

        int max_area = 0;
        int tp;
        int area_with_top;


        int i = 0;
        while (i < heights.length) {

            if (s.empty() || heights[s.peek()] <= heights[i]) {
                s.push(i++);
                System.out.println(s.peek());
                int xo = heights[s.peek()];
            } else {
                tp = s.peek();
                s.pop();
                area_with_top = heights[tp] * (s.empty() ? i : i - s.peek() - 1);

                // update max area, if needed
                if (max_area < area_with_top)
                    max_area = area_with_top;
            }
        }

        while (s.empty() == false) {
            tp = s.peek();
            s.pop();
            int afterpeek = s.peek();
            int currentI = i;

            area_with_top = heights[tp] * (s.empty() ? i : i - s.peek() - 1);

            if (max_area < area_with_top)
                max_area = area_with_top;
        }

        return max_area;

    }

    static void printGrid(String[][]grid){
        for(int x=0;x<grid.length;x++){
            for(int y=0;y<grid[0].length;y++){
                System.out.print(grid[x][y]);
            }
            System.out.println();
        }
    }

    static String [][] zoningTheArea(String [][]grid){
        String [][]copyGrid = new String[12][15];
        int count =1;
        int begin =0;
        for(int x=0;x<grid.length;x++){
            for(int y=0;y<grid[0].length;y++) {
                if (x == 0) {
                    if (grid[x][y].matches("#")) {
                        copyGrid[x][y] = "#";
                    } else if ( y > 0 && grid[x][y].matches(".")|| grid[x][y].matches("[a-z]]")) {
                        if (copyGrid[x][y-1].matches("[0-9]")) {
                            copyGrid[x][y] = copyGrid[x][y-1];
                        }else if (copyGrid[x][y-1].matches("#")){
                            begin = y;
                            copyGrid[x][y] = Integer.toString(++count);
                        }
                    }
                } else {
                    if (grid[x][y].matches("#")) {
                        copyGrid[x][y] = "#";
                    } else if(y>0 && grid[x][y].matches(".")|| grid[x][y].matches("[a-z]]")){
                        if ((copyGrid[x-1][y].matches("[0-9]") && copyGrid[x-1][y].matches("[0-9]")) && Integer.parseInt(copyGrid[x-1][y])==Integer.parseInt(copyGrid[x][y-1])){
                            for(int f = begin;f<y;f++){
                                copyGrid[x][f] = copyGrid[x-1][y];
                            }
                            copyGrid[x][y] = copyGrid[x-1][y];
                        }else if (copyGrid[x][y-1].matches("[0-9]")){
                            copyGrid[x][y] = copyGrid[x][y-1];
                        }
                        else if ((copyGrid[x-1][y].matches("[0-9]"))){
                            copyGrid[x][y] = copyGrid[x-1][y];
                        }
                        else if (copyGrid[x][y-1].matches("#")){
                            begin = y;
                            copyGrid[x][y] = Integer.toString(++count);
                        }
                    }else if (grid[x][y].matches(".") || grid[x][y].matches("[a-z]]")){
                        copyGrid[x][y] = Integer.toString(++count);
                    }
                }
                System.out.print(copyGrid[x][y]);
            }
            System.out.println();
        }
        return copyGrid;
    }

    static int combinationSum4(int[] nums, int target) {
        if(target == 0){
            return 1;
        }else if (target<0){
            return 0;
        }
        int count =0;
        for(int x=0;x<nums.length;x++){
            if(target>=nums[x]){
                count+=combinationSum4(nums,target-nums[x]);
            }
        }
        return count;
    }

//    static int partition(int []arr, int start,int end){
//        int pivot = arr[end];
//        for(int x=start;x<=start-end;x++){
//            if(arr[x]<= pivot){
//                int temp = arr[x];
//                arr[x]=arr[start];
//                arr[start] = temp;
//                start++;
//            }
//        }
//
//        int temp =arr[end];
//        arr[end] = arr[start];
//        arr[start] = temp;
//
//        return start;
//    }





    public static int partition(int [] arr, int l,
                                int r)
    {
        int x = arr[r], i = l;
        for (int j = l; j <= r - 1; j++)
        {
            if (arr[j] <= x)
            {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
            }
        }

        int temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;

        return i;
    }
//
//    static int findNthSmallest(int []arr,int find){
//        return findLargest(arr,0,arr.length-1,arr.length-find+1);
//    }
//
    public static int findLargest(int[] arr, int start,
                                  int end, int find)
    {
        if (find > 0 && find <= end - start + 1)
        {
            int pos = partition(arr, start, end);

            if (pos-start == find-1)
                return arr[pos];

            if (pos-start > find-1)
                return findLargest(arr, start, pos-1, find);

            return findLargest(arr, pos+1, end, find-pos+start-1);
        }
        return Integer.MAX_VALUE;
    }

    static int findKthLargest(int []nums, int k){
        return findLargest(nums,0, nums.length-1,nums.length-k+1);
    }

//    static int  findLargest(int []nums,int start,int end,int find){
//        if(find >0 && find<= end - start +1){
//
//            int position = partition(nums,start,end);
//            if(position - start ==find -1){
//                return nums[position];
//            }
//
//            if(position - start >find -1){
//                findLargest(nums,start, position-1,find);
//            }
//            findLargest(nums,position+1,end,find - position + start - 1);
//
//        }
//        return -1;
//    }



    public static void main(String[] args) throws java.lang.Exception {

//        System.out.println(findKthLargest(new int []{8,1,3,7,3},2));
//        String [][] grid= new String[][]{
//                {"#","#","#","#","#","#","#","#","#","#","#",".",".",".","."},
//                {"#",".",".",".",".",".",".",".","c",".","#","#","#",".","."},
//                {"#","#","#","#",".",".",".",".",".",".","#",".","#",".","."},
//                {".","#",".","#","#","#","#","#","#","#","#",".","#",".","."},
//                {"#","#",".",".",".","#",".",".","b","#",".",".","#",".","."},
//                {"#",".","a",".","#",".",".",".","#",".",".",".","#","#","#"},
//                {"#","#","#","#",".","#",".","#","d","#","#","#",".",".","#"},
//                {".",".",".",".",".",".","#",".",".",".","e","#","x","x","#"},
//                {".","#",".",".",".",".","#","#","#","#","#","#","#","#","#"},
//                {".","#",".","x",".",".","#",".",".","#",".",".",".",".","."},
//                {".","#","#","#","#","#","#",".","c","#",".",".",".",".","."},
//                {".",".",".",".",".",".","#","#","#","#",".",".",".",".","."},
//        };
//
//
//        printGrid(grid);
//        String [][] res = zoningTheArea(grid);
//        combinationSum4(new int []{1,2},3);
    }

}
