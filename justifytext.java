import java.io.*;
import java.util.*;


public class prog1 {

    public int calcCost(int start, int finish, String[] words){

        int lineLen = 0;
        int totalCost = 0;
        int whiteSpace = 0;
        for (int i = start; i <= finish; i++){
            lineLen += words[i].length() + 1;
        }

        lineLen--;
        whiteSpace = 60 - lineLen;
       
        if (whiteSpace < 0){
            totalCost = Integer.MAX_VALUE;
        } else if (whiteSpace == 0){
            totalCost = 0;
        } else {
            totalCost = (int)Math.pow(whiteSpace, 3);
        }

        return totalCost;
    }


    public String printLines(int[] p, String[] words){
        String s = "";
        int i = 0;
        int j;
        do {
            j = p[i];
            for (int l = i; l < j; l++){
                s = s.concat(words[l]+" ");
            }
            s = s.concat("\n");
            i = j;
        }while (j < words.length);

        return s;
    }


    public void replaceBlanks(String[] words) {



        int lc[][] = new int[words.length][words.length];

        for (int i = 0; i < words.length; i++){
            for (int j = i; j < words.length; j++){

                lc[i][j] = calcCost(i, j, words);
            }
        }

        int c[] = new int[words.length];
        int p[] = new int[words.length];
        for (int j = words.length - 1; j >= 0; j--){
            c[j] = lc[j][words.length-1];
            p[j] = words.length;
            for (int i = words.length - 1; i > j; i--){
                if (lc[j][i-1] == Integer.MAX_VALUE){
                    continue;
                }
                if (c[j] > c[i] + lc[j][i-1]){
                    c[j] = c[i] + lc[j][i-1];
                    p[j] = i;
                }
            }
        }
        System.out.println(c[0]);
        System.out.println(printLines(p, words));

    }



    public static void main(String[] args) throws FileNotFoundException {

        String content = new Scanner(new FileInputStream(args[0])).useDelimiter("\\Z+").next();
        String[] words = content.split("\\s+");

        new prog1().replaceBlanks(words);



    }
}

