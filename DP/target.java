package DP;

import java.util.Arrays;

public class target {
    public static void display(int dp[]){
        for(int d : dp){
            System.out.print(d+" ");
        }
        System.out.println();
    }
    public static void display2D(int dp[][]){
        for(int d[]:dp){
            display(d);
        }
        System.out.println();
    }
    public static int combination(int tar, int coins[],int idx,int dp[][]){
        if(tar==0) return dp[tar][idx]=1;
        if(dp[tar][idx]!=0) return dp[tar][idx];
        int sum = 0;
        for(int i=idx;i<coins.length;i++){
            if(tar-coins[i]>=0){
                sum+=combination(tar-coins[i], coins, i,dp);
            }
        }
        return dp[tar][idx]=sum;
    }
    public static int combination_tabu(int TAR, int coins[],int dp[]){
        dp[0]=1;
        for(int i=0;i<coins.length;i++){
            for(int tar = coins[i];tar<=TAR;tar++){
                dp[tar]+=dp[tar-coins[i]];
            }
        }
        return dp[TAR];
    }
    public static int permutation(int tar,int coins[]){
        if(tar==0) return 1;
        int sum = 0;
        for(int i=0;i<coins.length;i++){
            if(tar-coins[i]>=0){
                sum+=permutation(tar-coins[i], coins);
            }
        }
        return sum;
    }
    public static int permutation_tabu(int TAR,int coins[],int dp[]){
        for(int tar=0;tar<=TAR;tar++){
            if(tar==0){
                dp[tar]=1;
                continue;
            }
            int sum = 0;
            for(int i=0;i<coins.length;i++){
                if(tar-coins[i]>=0){
                    sum+=dp[tar-coins[i]];  //permutation(tar-coins[i], coins);
                }
            }
            dp[tar]=sum;
        }
        return dp[TAR];
    }

    public static Boolean isSubsetSum(int N, int arr[], int sum,boolean dp[][]){
        if(sum==0) return true;
        else if(N==0) return false;
        // code here
        boolean res = false;
        if(sum-arr[N-1]>=0){
            res = res || isSubsetSum(N-1, arr, sum-arr[N-1],dp);
        }
        res = res || isSubsetSum(N-1, arr, sum,dp);
        return res;
    }
    public static Boolean isSubsetSum_dp(int N, int arr[], int Sum,boolean dp[][]){
        for(int n=0;n<=N;n++){
            for(int sum=0;sum<=Sum;sum++){
                if(sum==0) {
                    dp[n][sum]=true;
                    continue;
                }
                else if(n==0) {
                    dp[n][sum]=false;  
                    continue;
                } 
                // code here
                boolean res = false;
                if(sum-arr[n-1]>=0){
                    res = res || dp[n-1][sum-arr[n-1]];//isSubsetSum(N-1, arr, sum-arr[N-1],dp);
                }
                res = res || dp[n-1][sum];
                dp[n][sum]=res;
            }
        }
        System.out.println(isSubsetSum_BackEng(N, Sum,arr, dp,""));
        return dp[N][Sum];
    }
    private static int isSubsetSum_BackEng(int n, int sum, int arr[],  boolean[][] dp, String psf) {
        if(n==0 || sum==0){
            if(sum==0){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count =0;
        if(dp[n-1][sum])
            count+=isSubsetSum_BackEng(n-1, sum,arr ,dp, psf);
        if(sum-arr[n-1]>=0 && dp[n-1][sum-arr[n-1]])
            count+=isSubsetSum_BackEng(n-1, sum-arr[n-1], arr, dp, psf +arr[n-1]+" ");
        return count;
    }
    public static int linearEquation(int arr[],int target, int n,int dp[][]){
        if(target==0) return dp[n][target]=1;
        if(dp[n][target]!=-1) return dp[n][target];
        int sum =0;
        for(int i=n;i>0;i--){
            if(target-arr[i-1]>=0)
                sum+=linearEquation(arr, target-arr[i-1], i,dp);
        }
        return dp[n][target]=sum;
    }

    public static int linearEquation_Dp(int arr[],int target,int dp[]){
        
        dp[0]=1;
        for(int i=1;i<=arr.length;i++){
            for(int tar=0;tar<=target;tar++){
                if(tar-arr[i-1]>=0){
                    dp[tar]+=dp[tar-arr[i-1]];
                }
            }
        }
        return dp[target];
    }
    public static int linearEquation(int coeffs[],int rhs){
        // int dp[][]=new int[coeffs.length+1][rhs+1];
        // for(int d[]:dp){
        //     Arrays.fill(d,-1);
        // }
        // int ans = linearEquation(coeffs, rhs,coeffs.length,dp);
        // display2D(dp);
        // return ans;
        return linearEquation_Dp(coeffs, rhs, new int[rhs+1]);
    }
    public static int unboundedKnapsack(int val[],int wt[],int W,int N,int dp[][]){
        for(int n=0;n<=N;n++){
            for(int w=0;w<=W;w++){
                if(w==0 || n==0){
                    dp[n][w]=0;
                    continue;
                }
                int ans = -(int)1e9;
                if(w-wt[n-1]>=0){
                    ans = Math.max(ans, dp[n][w-wt[n-1]]+val[n-1]);
                }
                ans = Math.max(ans, dp[n-1][w]);
                dp[n][w]= ans;
            }
        }
        return dp[N][W];
    }


    public static int findTargetSumWays_dp(int[] nums, int N,int Sum,int Target,int dp[][]) {
        for(int n=0;n<=N;n++){
            for(int sum=0;sum<=2*Sum;sum++){
                if(n==0){
                    if(Target==sum){
                        dp[n][sum]=1;
                        continue;
                    }
                    dp[n][sum]=0;
                    continue;
                }
                int count = 0;
                if(sum+nums[n-1]<=2*Sum)
                count+=dp[n-1][sum+nums[n-1]];//     target,dp);// postive
                if(sum-nums[n-1]>=0)
                count+=dp[n-1][sum-nums[n-1]];//findTargetSumWays(nums,n-1,sum-nums[n-1],target,dp);// negative
                dp[n][sum]=count;
            }
        }
        return dp[N][Sum];
    }
    public static int findTargetSumWays(int[] nums, int n,int sum,int target,int dp[][]) {
        if(n==0){
            if(target==sum){
                return dp[n][sum]=1;
            }
            return dp[n][sum]=0;
        }
        if(dp[n][sum]!=-1) return dp[n][sum];
        int count = 0;
        count+=findTargetSumWays(nums,n-1,sum+nums[n-1],target,dp);// postive
        count+=findTargetSumWays(nums,n-1,sum-nums[n-1],target,dp);// negative
        return dp[n][sum]=count;
    }
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int ele:nums){
            sum+=ele;
        }
        if(target>sum || target<-sum) return 0;
        int n = nums.length;
        int dp[][]=new int[n+1][2*sum+1];
        for(int d[]:dp){
            Arrays.fill(d,-1);
        }
        int ans = findTargetSumWays(nums,n,sum,sum+target,dp);
        display2D(dp);
        return ans;
    }
    public static boolean canPartitionKSubsets(int arr[],int k,int idx,int ssf,int tar,boolean vis[])
    {
        if(ssf>tar)
            return false;
        if(k==0)
            return true;
        if(ssf==tar)
            return canPartitionKSubsets(arr,k-1,0,0,tar,vis);
        boolean res=false;
        for(int i=idx;i<arr.length;i++)
        {
            if(vis[i]==false)
            {
                vis[i]=true;
                res=res||canPartitionKSubsets(arr,k,0,ssf+arr[i],tar,vis);
                vis[i]=false;
            }
        }
        return res;
    }
    public static boolean canPartitionKSubsets(int[] arr, int k) {
        
        int n=arr.length;
        int sum=0;
        for(int ele:arr)    sum+=ele;
        if(sum%k!=0)return false;
        return canPartitionKSubsets(arr,k,0,0,sum/k,new boolean[arr.length]);
    }
    public double knightProbability(int n, int k, int row, int column,int dir[][],double dp[][][]) {
        if(k==0) return dp[k][row][column]=1;
        if(dp[k][row][column]!=0.0) return dp[k][row][column];
        double prb = 0;
        for(int d[]:dir){
            int r = row+d[0];
            int c = column+d[1];
            if(r>=0 && r<n && c>=0 && c<n){
                prb+= (knightProbability(n,k-1,r,c,dir,dp)/8);
            }
        }
        return dp[k][row][column]=prb;
    }
    public double knightProbability_Dp(int n, int K, int Row, int Column,int dir[][],double dp[][][]) {
        for(int k=0;k<=K;k++){
            for(int row=0;row<=n;row++){
                for(int column=0;column<=n;column++){
                    if(k==0) {
                        dp[k][row][column]=1;
                        continue;
                    }
                    double prb = 0;
                    for(int d[]:dir){
                        int r = row+d[0];
                        int c = column+d[1];
                        if(r>=0 && r<n && c>=0 && c<n){
                            prb+= (dp[k-1][r][c]/8);
                        }
                    }
                    dp[k][row][column]=prb;
                }
            }
        }
        return dp[K][Row][Column];
    }
    public double knightProbability(int n, int k, int row, int column) {
        int dir[][]={{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2}};
        double dp[][][]=new double[k+1][n+1][n+1];
        return knightProbability_Dp(n,k,row,column,dir,dp);
    }
    public static void main(String[] args) {
        // int coins[]={2,3,5,7};
        // int target = 10;
        //System.out.println(permutation_tabu(target, coins,new int[target+1]));
        // System.out.println(combination_tabu(target, coins,0, new int[target+1][coins.length]));
        // int dp[][]=new int[target+1][coins.length];

        // System.out.println(combination(target, coins, 0,dp));
        // // display2D(dp);
        // System.out.println(linearEquation(new int[]{2,2,3}, 4));
        // int val[]={1,4,5,7};
        // int wt[]={1,3,4,5};
        // int w = 8;
        // System.out.println(unboundedKnapsack(val, wt, w, val.length,new int[val.length+1][w+1]));
        int nums[]={605,454,322,218,8,19,651,2220,175,710,2666,350,252,2264,327,1843};
        int k=4;
        System.out.println(canPartitionKSubsets(nums, k));
    }
}
