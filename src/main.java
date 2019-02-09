import java.util.*;

public class main {

    static String balancedBracket(String s){
        Stack<String>openBrackets = new Stack<>();

        if (s.length()%2==1||s.charAt(0)=='}'||s.charAt(0)==']'||s.charAt(0)==')'){
            return "NO";
        }

        for(int x=0;x<s.length();x++){
            if(s.charAt(x)==')'||s.charAt(x)=='}'||s.charAt(x)==']'){
                if((Character.toString(s.charAt(x)).equals(")") && !openBrackets.pop().equals("("))||
                        (Character.toString(s.charAt(x)).equals("}") && !openBrackets.pop().equals("{"))||
                        (Character.toString(s.charAt(x)).equals("]") && !openBrackets.pop().equals("["))){
                    return "NO";
                }
            }else{
                openBrackets.push(Character.toString(s.charAt(x)));
            }
        }

        if(openBrackets.size()!=0){
            return "NO";
        }else{
            return "YES";
        }
    }

    static int tripleStepIn(int finish){
        if(finish==0){
            return 1;
        }else if(finish<0){
            return 0;
        }
        return tripleStepIn(finish-1)+tripleStepIn(finish-2)+tripleStepIn(finish-3);
    }





    static int findMaximumBirthInYear(int [][]bornDeath){
        return 0;
    }

    static String parenthessBracket(String s){
        Stack<String>temp = new Stack<>();
        for(int x=0;x<s.length();x++){
            if(s.charAt(x)=='('||s.charAt(x)=='['||s.charAt(x)=='{'){
                temp.push(Character.toString(s.charAt(x)));
            }

            if(s.charAt(x)==')'||s.charAt(x)=='}'||s.charAt(x)==']'){
                if(temp.size()==0){
                    return "NO";
                }else if((s.charAt(x)==')' && !temp.pop().equals("("))||(s.charAt(x)==']' && !temp.pop().equals("["))||(s.charAt(x)=='}' && !temp.pop().equals("{"))){
                    return "NO";
                }

            }
        }
        if (temp.size()!=0){
            return "NO";
        }else{
            return "YES";
        }
    }

    public String findPalindrome(String sentence){
        return "";
    }

    static int gameOfTwoStack(int [] a,int [] b,int x){
         if(x==0){
             return 0;
         }
         int result =0;

         int lastIndexA = a[0];
         int lastIndexB = b[0];

         for (int z=1;z<=2;z++){
             if(z==1 && x-a[a.length-1]>0){
                 int temp = gameOfTwoStack(Arrays.copyOfRange(a,1,a.length),b,x-lastIndexA);

                 if (temp+1>result){
                     result = temp+1;
                 }
             }else if (z==2 && x-b[b.length-1]>0){
                 int temp = gameOfTwoStack(a,Arrays.copyOfRange(b,1,b.length),x-lastIndexB);

                 if (temp + 1 > result ){
                     result = temp + 1;
                 }
             }
         }
         return result;
    }



//    int findNumPath(int []coorStart, int []coorFinish){
//        if(coorStart[0]==coorFinish[0] && coorStart[1] == coorFinish[1]){
//            return 1;
//        }else if (coorStart[0]>coorFinish[0] || coorStart[1]>coorFinish[1]){
//            return 0;
//        }
//
//        return findNumPath(coorStart  )+
//    }









//    public int maximumValue(){
//        Stack<Integer>result = new Stack<>();
//
//    }

    static int surroundingGrid(){
        String wordToFind ="nana";


        String [][]grid = new String [][]{{"b","k"},{"a","a"},{"n","l"},{"a","i"},{"n","b"},{"a","r"},{"n","r"},{"a","r"}};
        int count =0;
        for(int a=0;a<grid.length;a++){
            for (int b =0;b<grid[0].length;b++){
                if(grid[a][b].matches(Character.toString(wordToFind.charAt(0)))){
                    String[] direction = new String[8];
                    Arrays.fill(direction,Character.toString(wordToFind.charAt(0)));

                    for (int c=1;c<wordToFind.length();c++){
                         if (b-c>-1){
                             direction[0]+=grid[a][b-c];
                         }
                        if (b+c<grid[0].length){
                            direction[1] += grid[a][b+c];
                        }
                        if (a-c>-1){
                            direction[2] += grid[a-c][b];
                        }
                        if (a+c<grid.length){
                            direction[3] += grid[a+c][b];
                        }
                        if (b+c<grid[0].length && a+c<grid.length){
                            direction[4]+=grid[a+c][b+c];
                        }
                        if (b+c<grid[0].length && a-c>-1){
                            direction[5] += grid[a-c][b+c];
                        }
                        if (b-c>-1 && a+c<grid.length){
                            direction[6] += grid[a+c][b-c];
                        }
                        if (a-c>-1 && b-c>-1){
                            direction[7] += grid[a-c][b-c];
                        }
                    }
                    for (int d=0;d<direction.length;d++){
                        if(direction[d].matches(wordToFind)){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

//    int castleOnTheGrid(String[][]grid,){
//
//    }

    public static void main(String []args){
        System.out.println(surroundingGrid());

    }
}
