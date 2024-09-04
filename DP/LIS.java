package DP;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LIS {
    public static void display(int a[]){
        for(int val : a){
            System.out.print(val +" ");
        }
        System.out.println();
    }
    public static int lengthOfLIS(int a[],int ei,int dp[]){
        int mx = 0;
        for(int i=ei-1;i>=0;i--){
            if(a[ei]>a[i]){
                mx = Math.max(mx,lengthOfLIS(a, i,dp));
            }
        }
        return dp[ei] = mx + 1;
    }
    public static int lengthOfLIS_tabu(int a[],int dp[]){
        int ans = -(int)1e9;
        for(int ei=0;ei<a.length;ei++){
            int mx = 0;
            for(int i=ei-1;i>=0;i--){
                if(a[ei]>a[i]){
                    mx = Math.max(mx,dp[i]);
                }
            }
            dp[ei] = mx + 1;
            ans = Math.max(ans,dp[ei]);
        }
        return ans;
    }
    
    public static int longestBitonicSequence(int a[]){
        int lis[]=new int[a.length];
        lengthOfLIS_tabu(a, lis);
        int lds[]=new int[a.length];
        longestDecreasingSequence_tabu(a,lds);
        int ans = 0;
        for(int i=0;i<a.length;i++){
            if(lis[i]!=1 && lds[i]!=1){
                ans = Math.max(ans, lis[i]+lds[i]-1);
            }
        }
        return ans;
    }
    public static int longestDecreasingSequence_tabu(int a[],int dp[]){
        int ans = 0;
        for(int si=a.length-1;si>=0;si--){
            int mxLen = 0;
            for(int i=si+1;i<a.length;i++){
                if(a[si]>a[i]){
                    mxLen = Math.max(mxLen, dp[i]);
                }
            }
            dp[si] = mxLen + 1;
            ans = Math.max(ans, dp[si]);
        }
        return ans;
    }
    public int longestDecreasingSequence(int a[],int si){
        int mxLen = 0;
        for(int i=si+1;i<a.length;i++){
            if(a[si]>a[i]){
                mxLen = Math.max(mxLen, longestDecreasingSequence(a, i));
            }
        }
        return mxLen + 1;
    }
    public int longestDecreasingSequence(int a[]){
        int mxLen = 0;
        for(int i=0;i<a.length;i++){
            mxLen = Math.max(mxLen, longestDecreasingSequence(a, i));
        }
        return mxLen;
    }
    
    public static int maxSumIncreasingSubSequence(int a[],int dp[]){
        int ans = -(int)1e9;
        for(int ei=0;ei<a.length;ei++){
            int mx = 0;
            for(int i=ei-1;i>=0;i--){
                if(a[ei]>a[i]){
                    mx = Math.max(mx,dp[i]);
                }
            }
            dp[ei] = mx + a[ei];
            ans = Math.max(ans,dp[ei]);
        }
        return ans;
    }
    
    public static int maxSumIncreasingSubSequence_RL(int a[],int dp[]){
        int ans = -(int)1e9;
        for(int ei=a.length;ei>=0;ei--){
            int mx = 0;
            for(int i=ei+1;i<a.length;i++){
                if(a[ei]>a[i]){
                    mx = Math.max(mx,dp[i]);
                }
            }
            dp[ei] = mx + a[ei];
            ans = Math.max(ans,dp[ei]);
        }
        return ans;
    }
    public static int maxSumBitonicSubsequence(int a[]){
        int dp[]=new int[a.length];
        maxSumIncreasingSubSequence(a,dp);
        int dp1[]=new int[a.length];
        maxSumIncreasingSubSequence_RL(a,dp1);
        int ans = 0;
        for(int i=0;i<a.length;i++){
            ans = Math.max(ans, dp[i]+dp1[i]-a[i]);
        }
        return ans;
    }
    
    // minimum deletion required to make array sorted

    public static int minDeletion(int a[]){
        int dp[]=new int[a.length];
        int mx = -(int)1e9;
        for(int i=0;i<a.length;i++){
            for(int j=i-1;j>=0;j--){
                if(a[i]>=a[j]){
                    dp[i]=Math.max(dp[i], dp[j]);
                }
            }
            dp[i]++;
            mx = Math.max(mx,dp[i]);
        }
        return a.length-mx;
    }
    static class Pair{
        int x,y;
        public Pair(int x,int y) {
            this.x=x;
            this.y=y;
        }
        public Pair() {
        }
    }
    public static int maxBridges(int a[],int b[]){
        Pair p[]=new Pair[a.length];
        for(int i=0;i<a.length;i++){
            p[i]=new Pair(a[i],b[i]);
        }
        
        Arrays.sort(p,(x,y)->{
            return x.x-y.x;
        });

        int dp[]=new int[a.length];
        int ans = 0;
        for(int i=0;i<a.length;i++){
            for(int j=i-1;j>=0;j--){
                if(p[i].x>p[j].x && p[i].y>p[j].y){
                    dp[i]= Math.max(dp[i], dp[j]);
                }
            }
            dp[i]++;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(a,b)->{
            return a[0]-b[0];
        });
        int dp[]=new int[envelopes.length];
        int ans = 0;
        for(int i=0;i<envelopes.length;i++){
            for(int j=i-1;j>=0;j--){
                if(envelopes[i][0] >  envelopes[j][0] && envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i]++;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    public static void main(String[] args) {
        // int a[]={0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15,14};
        // int dp[]=new int[a.length];
        // int mxLen = 0;
        // mxLen = lengthOfLIS_tabu(a, dp);
        // display(dp);
        // System.out.println(mxLen);
        // System.out.println(minDeletion(new int[]{1,3,2,2,2,2}));
        int a[]={8,1,4,3,5,2,6,7};
        int b[]={1,2,3,4,5,6,7,8};
        // int a[]={6,4,2,1};
        // int b[]={2,3,6,5};
        System.out.println(maxBridges(a, b));
    }
}
