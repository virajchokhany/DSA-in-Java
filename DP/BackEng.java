package DP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BackEng {
    public static void display1D(int dp[]){
        for(int data : dp){
            System.out.print(data + " ");
        }
        System.out.println();
    }
    public static void display2D(int dp[][]){
        for(int d[] : dp){
            display1D(d);
        }
        System.out.println();
    }
    public static int LPSS(String s){
        int dp[][]=new int[s.length()][s.length()];
        int ans = LPSS(s,0,s.length()-1,dp);
            display2D(dp);
        System.out.println(LPSS_BackEng_AllSubSequence(s,0,s.length()-1,dp));
        return ans;
    }
    public static int LPSS(String s,int I,int J,int dp[][]){
        for(int gap=0;gap<s.length();gap++){
            for(int i=0,j=gap+i;j<s.length();i++,j++){
                if(i==j){
                    dp[i][j]=1;
                    continue;
                }
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=2+dp[i+1][j-1];  //LPSS(s,i+1,j-1,dp);
                }else{
                    dp[i][j]=Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[I][J];
    }
    public static String LPSS_BackEng(String s, int si, int ei, int dp[][]){
        if(si==ei) return s.charAt(ei)+"";
        else if(si>ei) return "";
        if(s.charAt(si)==s.charAt(ei)){
            return s.charAt(ei)+LPSS_BackEng(s, si+1, ei-1, dp) + s.charAt(ei);
        }else{
            if(dp[si][ei-1]>dp[si+1][ei]){
                return LPSS_BackEng(s, si, ei-1, dp);
            }else{
                return LPSS_BackEng(s, si+1, ei, dp);
            }
        }
    }
    public static ArrayList<String> LPSS_BackEng_AllSubSequence(String s, int si, int ei, int dp[][]){
        if(si==ei) {
            ArrayList<String> base = new ArrayList<>();
            base.add(s.charAt(ei)+"");
            return base;
        }
        else if(si>ei) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans = new ArrayList<>();
        if(s.charAt(si)==s.charAt(ei)){
            ArrayList<String> sans = LPSS_BackEng_AllSubSequence(s, si+1, ei-1, dp);
            for(String str : sans){
                ans.add(s.charAt(ei)+str+s.charAt(ei));
            }
            return ans;
        }
        else{
            if(dp[si][ei-1]>dp[si+1][ei]){
                return LPSS_BackEng_AllSubSequence(s, si, ei-1, dp);
            }else if(dp[si][ei-1]<dp[si+1][ei]){
                return LPSS_BackEng_AllSubSequence(s, si+1, ei, dp);
                
            }else{
                ArrayList<String> first = LPSS_BackEng_AllSubSequence(s, si, ei-1, dp);
                ArrayList<String> second = LPSS_BackEng_AllSubSequence(s, si+1, ei, dp);
                for(String str : first){
                    ans.add(str);
                }
                for(String str : second){
                    ans.add(str);
                }
                return ans;
            }
        }
    }


    static int maxGold_tabu(int SR, int SC, int dr, int dc, int board[][],int dir[][],int dp[][]){
        for(int sc=dc;sc>=0;sc--){
            for(int sr=dr;sr>=0;sr--){
                if(sc==dc){
                    dp[sr][sc]=board[sr][sc];
                    continue;
                }
                int ans = -(int)1e9;
                for(int d[]: dir){
                    int r = sr+d[0];
                    int c = sc+ d[1];
                    if(r>=0 && r<=dr && c>=0 && c<=dc){
                        ans = Math.max(ans, dp[r][c]);
                    }
                }
                dp[sr][sc]=ans + board[sr][sc];
            }
        }
        return dp[SR][SC];
    }
    static int maxGold(int n, int m, int board[][])
    {
        // code here
        int ans = -(int)1e9;
        int dp[][]=new int[n][m];
        int dir[][]=new int[][]{{-1,1},{0,1},{1,1}};
        maxGold_tabu(0,0,n-1,m-1,board,dir,dp);
        int startRow =0;
        for(int i=0;i<n;i++){
            if(dp[i][0]>ans){
                ans = dp[i][0];
                startRow = i;
            }
        }
        display2D(dp);
        System.out.println(maxGold_BackEng(startRow, 0, board,dp,dir));
        return ans;
    }
    public static String maxGold_BackEng(int sr, int sc, int board[][],int dp[][],int dir[][]){

        String path = "("+sr+","+sc+")";
        if(sc==board[0].length-1){
            return path;
        }
        path+=" -> ";
        for(int i=0;i<dir.length;i++){
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if(r>=0 && r<board.length && c>=0 && c<board[0].length && dp[r][c]==dp[sr][sc]-board[sr][sc]){
                path+=maxGold_BackEng(r, c, board, dp, dir);
                break;
            }
        }
        return path;
        
    }
    
    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for(String word : wordDict){
            set.add(word);
        }
        boolean dp[]=new boolean[s.length()+1];
        boolean ans = wordBreak_tabu(s,set,0,dp);
        for(int i=0;i<dp.length;i++){
            System.out.print(dp[i] + " ");
        }
        return ans;
    }
    public static boolean wordBreak_tabu(String s, HashSet<String> set, int IDX,boolean dp[]){
        for(int idx=s.length();idx>=0;idx--){
            if(idx==s.length()){
                dp[idx]=true;
                continue;
            }
            for(int i=idx;i<s.length();i++){
                String ss = s.substring(idx,i+1);
                if(set.contains(ss)){
                    Boolean ans = dp[i+1];  //wordBreak(s,set,i+1,dp);
                    if(ans==true){
                        dp[idx]=ans;
                        continue;
                    }
                }
            }
        }
        return dp[IDX];
    }
    public static void main(String[] args) {
        // String s = "abcbbad";
        // LPSS(s);
        // maxGold(3, 3, new int[][]{{1, 3, 3},
        // {2, 1, 4},
        // {0, 6, 4}});
        String s = "catsanddog";
        ArrayList<String> wordDict = new ArrayList<>(); //["cat","cats","and","sand","dog"];
        wordDict.add("cat");wordDict.add("cats");wordDict.add("and");wordDict.add("sand");wordDict.add("dog");
        wordBreak(s, wordDict);
        
    }
}
