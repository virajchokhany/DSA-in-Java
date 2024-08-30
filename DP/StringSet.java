package DP;

public class StringSet {
    // 72
    public int minDistance(String word1, String word2) {
        int dp[][]=new int[word1.length()+1][word2.length()+1];
        return minDist(word1,word1.length(),word2,word2.length(),dp);
    }
    public int minDist(String s, int i, String t, int j,int dp[][]){
        if(i==0 || j==0){
            if(i!=0){
                return dp[i][j]=i;
            }else{
                if(j==0){
                    return dp[i][j]=0;
                }else{
                    return dp[i][j]=j;
                }
            }
        }
        if(s.charAt(i-1)==t.charAt(j-1))
            return dp[i][j]=minDist(s, i-1, t, j-1,dp);
        else{
            int insert = minDist(s, i, t, j-1,dp);
            int del = minDist(s, i-1, t, j,dp);
            int replace = minDist(s, i-1, t, j-1,dp);
            return dp[i][j]=1 + Math.min(insert, Math.min(del, replace));
        }
        
    }
    public int minDist_tabu(String s, int I, String t, int J,int dp[][]){
        for(int i=0;i<=s.length();i++){
            for(int j=0;j<=t.length();j++){
                if(i==0 || j==0){
                    if(i!=0){
                        dp[i][j]=i;
                        continue;
                    }else{
                        if(j==0){
                            dp[i][j]=0;
                            continue;
                        }else{
                            dp[i][j]=j;
                            continue;
                        }
                    }
                }
                if(s.charAt(i-1)==t.charAt(j-1))
                    return dp[i][j]=dp[i-1][j-1];   //minDist(s, i-1, t, j-1,dp);
                else{
                    int insert = dp[i][j-1];    //minDist(s, i, t, j-1,dp);
                    int del = dp[i-1][j];   //minDist(s, i-1, t, j,dp);
                    int replace = dp[i-1][j-1]; //minDist(s, i-1, t, j-1,dp);
                    return dp[i][j]=1 + Math.min(insert, Math.min(del, replace));
                }
            }
        }
        return dp[I][J];
    }

    public int numDistinct(String s, String t) {
        int dp[][]=new int[s.length()+1][t.length()+1];
        return numDistinct(s,s.length(), t, t.length(),dp);
    }
    public int numDistinct(String s, int i, String t, int j,int dp[][]){
        if(i==0 || j==0){
            if(j==0) return dp[i][j]=1;
            else return dp[i][j]=0;
        }

        if(s.charAt(i-1) == t.charAt(j-1)){
            return dp[i][j]=numDistinct(s, i-1, t, j-1,dp) + numDistinct(s, i-1, t, j,dp);
        }else{
            return dp[i][j]=numDistinct(s, i-1, t, j,dp);
        }
    }
    public int numDistinct_tabu(String s, int I, String t, int J,int dp[][]){
        for(int i=0;i<=s.length();i++){
            for(int j=0;j<=t.length();j++){
                if(i==0 || j==0){
                    if(j==0){
                        dp[i][j]=1;
                    }
                    else{
                        dp[i][j]=0;
                    }
                    continue;
                }
        
                if(s.charAt(i-1) == t.charAt(j-1)){
                     dp[i][j]=dp[i-1][j-1]+dp[i-1][j];    //numDistinct(s, i-1, t, j-1,dp) + numDistinct(s, i-1, t, j,dp);
                }else{
                     dp[i][j]=dp[i-1][j]; //numDistinct(s, i-1, t, j,dp);
                }
            }
        }
        return dp[I][J];
        
    }
    public boolean isMatch(String s, String p) {
        boolean dp[][]=new boolean[s.length()+1][p.length()+1];
        return isMatch(s,s.length(),p,p.length(),dp);
    }
    public String removeStar(String s){
        if(s.length()==0) return s;

        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        int i=1;
        while(i<s.length()){
            while(i<s.length() && s.charAt(i)=='*' && sb.charAt(sb.length()-1)=='*'){
                i++;
            }
            if(i<s.length())
                sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();
    }

    public boolean isMatch(String s, int i, String p, int j,boolean dp[][]){
        if(i==0 || j==0){
            if(i==0 && j==0) return dp[i][j]=true;
            else if(i==0 && j!=0){
                if(j==1 && p.charAt(0)=='*') return true;
                else return false;
            }
            else return dp[i][j]=false;
        }
        
        char ch = p.charAt(j-1);
        if(ch=='*'){
            return isMatch(s, i-1, p, j, dp) || isMatch(s, i, p, j-1, dp);
        }else if(ch=='?'){
            return dp[i][j]=isMatch(s, i-1, p, j-1,dp);
        }else{
            if(s.charAt(i-1)!=p.charAt(j-1)) return dp[i][j]=false;
            else return dp[i][j]=isMatch(s, i-1, p, j-1,dp);
        }
    }

    public boolean isMatch_tabu(String s, int I, String p, int J,boolean dp[][]){
        for(int i=0;i<=s.length();i++){
            for(int j=0;j<=p.length();j++){
                if(i==0 || j==0){
                    if(i==0 && j==0) dp[i][j]=true;
                    else if(i==0 && j!=0){
                        if(j==1 && p.charAt(0)=='*') dp[i][j]= true;
                        else dp[i][j]=false;
                    }
                    else  dp[i][j]=false;
                }
                
                char ch = p.charAt(j-1);
                if(ch=='*'){
                    dp[i][j]=dp[i-1][j] || dp[i][j-1];  //isMatch(s, i-1, p, j, dp) || isMatch(s, i, p, j-1, dp);
                }else if(ch=='?'){
                    dp[i][j]=dp[i-1][j-1];  //isMatch(s, i-1, p, j-1,dp);
                }else{
                    if(s.charAt(i-1)!=p.charAt(j-1))  dp[i][j]=false;
                    else  dp[i][j]=dp[i-1][j-1];//isMatch(s, i-1, p, j-1,dp);
                }
            }
        }
        return dp[I][J];
        
    }
    

    public boolean isMatch_10(String s, String p){
        p = removeStar(p);
        return isMatch_10(s,s.length(), p, p.length(),new Boolean[s.length()+1][p.length()+1]);
    }
    public boolean isMatch_10(String s, int i, String p, int j, Boolean dp[][]){
        if(i==0 && j==0){
            return dp[i][j]=true;
        }
        else if(i!=0 && j==0){
            return dp[i][j]=false;
        }
        else if(i==0 && j!=0){
            if(p.charAt(j-1)=='*'){
                return dp[i][j]=isMatch_10(s, i, p, j-2,dp);
            }else{
                return dp[i][j]=false;
            }
        }
        if(dp[i][j]!=null) return dp[i][j];
        char ch = p.charAt(j-1);
        if(ch=='.'){
            return dp[i][j]=isMatch_10(s, i-1, p, j-1,dp);
        }else if(ch=='*'){
            return dp[i][j]=isMatch_10(s, i, p, j-2,dp) || ((s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2)=='.') && isMatch_10(s, i-1, p, j,dp));
        }else{
            if(s.charAt(i-1)==p.charAt(j-1)){
                return dp[i][j]=isMatch_10(s, i-1, p, j-1,dp);
            }else{
                return dp[i][j]=false;
            }
        }
    }

    public boolean isMatch_10_tabu(String s, int I, String p, int J, boolean dp[][]){
        for(int i=0;i<=s.length();i++){
            for(int j=0;j<=p.length();j++){
                if(i==0 && j==0){
                    dp[i][j]=true;
                    continue;
                }
                else if(i!=0 && j==0){
                     dp[i][j]=false;
                     continue;
                }
                else if(i==0 && j!=0){
                    if(p.charAt(j-1)=='*'){
                        dp[i][j]=dp[i][j-2];    //isMatch_10(s, i, p, j-2,dp);
                        continue;
                    }else{
                        dp[i][j]=false;
                        continue;
                    }
                }
                char ch = p.charAt(j-1);
                if(ch=='.'){
                     dp[i][j]=dp[i-1][j-1];   //isMatch_10(s, i-1, p, j-1,dp);
                }else if(ch=='*'){
                     dp[i][j]=dp[i][j-2] || ((s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2)=='.') && dp[i-1][j]);
                }else{
                    if(s.charAt(i-1)==p.charAt(j-1)){
                         dp[i][j]=dp[i-1][j-1];
                    }else{
                         dp[i][j]=false;
                    }
                }
            }
        }
        return dp[I][J];
        
    }
    

    


}
