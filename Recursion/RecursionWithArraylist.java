package Recursion;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecursionWithArraylist 
{
    public static void printEncodings(String ques, String asf)
    {
        if(ques.length()==0)
        {
            System.out.println(asf);
            return;
        }
        if(ques.charAt(0) == '0')
            return;
        char ch = ques.charAt(0); //1
        printEncodings(ques.substring(1), asf+ ((char) ('a'+ (int)(ch-'1'))));

        if(ques.length()>=2)
        {
            String s = ques.substring(0,2);
            char ch2 = ques.charAt(1); // '2'
            int intEq = (int)(ch2-'0');     //2
            int intEq1 = (int)(ch-'0'); 
            if(intEq+10*intEq1 <=26)
            {
                printEncodings(ques.substring(2), asf + (char)((int)('a')+intEq+10*intEq1-1));
            }
        }
    }
    public static void printPermutation(String ques, String ans)
    {
        if(ques.length()==0)
        {
            System.out.println(ans);
            return;
        }
        for(int i=0;i<ques.length();i++)
        {
            printPermutation(ques.substring(0, i)+ques.substring(i+1), ans+ques.charAt(i));
        }
    }
    public static void printMazePaths(int sr, int sc, int dr, int dc, String asf, int jumps[])
    {
        if(sr == dr && sc == dc)
        {
            System.out.println(asf);
            return;
        }

        for(int i=0;i<jumps.length;i++)
        {
            // hor
            if(sc + jumps[i] <= dc)
            {
                printMazePaths(sr, sc + jumps[i], dr, dc, asf + "h" + jumps[i], jumps);
            }
            // ver 
            if(sr + jumps[i] <= dr)
            {
                printMazePaths(sr+jumps[i], sc, dr, dc, asf + "v" + jumps[i], jumps);
            }
            // diags
            if(sr + jumps[i] <=dr && sc+jumps[i]<=dc)
            {
                printMazePaths(sr+jumps[i], sc + jumps[i], dr, dc, asf + "d" + jumps[i], jumps);
            }
        }
    }
    public static void printStairPath(int n, String asf, int steps[])
    {
        if(n==0){
            System.out.println(asf);
            return;
        }
        for(int i=0;i<steps.length;i++){
            if(n-steps[i]>=0)
            {
                printStairPath(n-steps[i], asf+steps[i], steps);
            }
        }
    }
    public static void printKeypadCombo(String keypad[], String number, int idx, String asf)
    {
        if(idx == number.length())
        {
            System.out.println(asf);
            return;
        }
        int key = number.charAt(idx)-'0'; // 5
        for(int i=0;i<keypad[key].length();i++)
        {
            printKeypadCombo(keypad,number,idx+1,asf+keypad[key].charAt(i));
        }
    }
    public static void printSS(String s , int idx, String ans)
    {
        if(idx==s.length())
        {
            System.out.println(ans);
            return;
        }
        printSS(s, idx+1, ans+s.charAt(idx)); // yes
        printSS(s, idx+1, ans); // no
    }
    public static ArrayList<String> getMazePathsWithJump(int sr, int sc,int dr, int dc, int steps[])
    {
        if(sr == dr && sc==dc)
        {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        if(sr > dr || sc> dc)
            return new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();
        for(int i=0;i<steps.length;i++)
        {
            ArrayList<String> hor = getMazePathsWithJump(sr+steps[i], sc, dr, dc, steps);
            ArrayList<String> ver = getMazePathsWithJump(sr, sc + steps[i], dr, dc, steps);
            ArrayList<String> diag = getMazePathsWithJump(sr+steps[i], sc + steps[i], dr, dc, steps);
            for(String s : hor)
            {
                ans.add("h"+steps[i]+s);
            }
            for(String s : ver)
            {
                ans.add("v"+steps[i]+s);
            }
            for(String s : diag)
            {
                ans.add("d"+steps[i]+s);
            }
        }
        return ans;
        
    }
    public static ArrayList<String> getMazePath(int sr, int sc, int dr, int dc)
    {
        if(sr == dr && sc == dc)
        {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        if(sr > dr || sc > dc)
        {
            return new ArrayList<>();
        }
        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> hor = getMazePath(sr, sc+1, dr, dc); // h
        ArrayList<String> ver = getMazePath(sr+1, sc, dr, dc); // v
        for(String s : hor)
        {
            ans.add('h' +s);
        }
        for(String s : ver)
        {
            ans.add('v'+s);
        }
        return ans;
    }
    public static ArrayList<String> getStairPaths(int n, int steps[])
    {
        if(n==0)
        {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans = new ArrayList<>();
        for(int i=0;i<steps.length;i++)
        {
            if(n-steps[i]>=0)
            {
                ArrayList<String> rans = getStairPaths(n-steps[i], steps);
                for(String path : rans)
                {
                    ans.add(steps[i]+path);
                }
            }
        }
        return ans;
    }

    // "573"
    public static ArrayList<String> getKeypadCombination(String[] keypad, String s)
    {
        if(s.length()==0)
        {
            ArrayList<String> list = new ArrayList<>();
            list.add("");
            return list;
        }
        ArrayList<String> rans = getKeypadCombination(keypad, s.substring(1));
        ArrayList<String> ans = new ArrayList<>();

        int idx = s.charAt(0)-'0'; // 5
        String key = keypad[idx]; // mnop

        for(int i=0;i<key.length();i++)
        {
            for(String combo : rans)
            {
                ans.add(key.charAt(i)+combo);
            }
        }

        return ans;
    }
    public static ArrayList<String> getSS(String s)
    {
        if(s.length() == 0)
        {
            ArrayList<String> ans = new ArrayList<>();
            ans.add("");
            return ans;
        }
        ArrayList<String> ans  = getSS(s.substring(1));
        ArrayList<String> list = new ArrayList<>();
        for(String str : ans)
        {
            list.add(str);
            list.add(s.charAt(0) + str);
        }
        return list;
    }
    public static void main(String[] args) 
    {
        // System.out.println(getSS("abc"));
        String[] keypad = {"","abc","def","ghi","jkl","mnop","qrst","uv","wxyz",".;","?!"};
        String s = "573";

        // System.out.println(getKeypadCombination(keypad, s));

        // System.out.println(getStairPaths(4,new int[]{1,2,3,10}));
        // System.out.println(getMazePathsWithJump(0,0,2,2, new int[]{1,2,3}));
        // printSS("abc",0,"");
        // printKeypadCombo(keypad,s,0,"");
        // printStairPath(10,"",new int[]{1,2,3});
        // printMazePaths(0,0,2,2,"",new int[]{1,2,3});
        // printPermutation("abcdefghijklmnopqstuvwxyz","");
        printEncodings("920303","");
    }
}
