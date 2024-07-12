package Recursion;

import java.util.ArrayList;

public class RecursionWithArraylist 
{

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
        // String[] keypad = {"","abc","def","ghi","jkl","mnop","qrst","uv","wxyz",".;","?!"};
        // String s = "573";

        // System.out.println(getKeypadCombination(keypad, s));

        System.out.println(getStairPaths(4,new int[]{1,2,3,10}));
    }
}
