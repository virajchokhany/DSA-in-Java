package DP;

public class TwoPointerSet {
    // faith
    // recursive tree
    // recursive code -> memo
    // observation
    // tabulation
    // optimization

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
    public static int fibo_memo(int n, int dp[]){
        if(n<=1) return dp[n]=n;
        if(dp[n]!=0) return dp[n];
        return dp[n]=fibo_memo(n-1,dp)+fibo_memo(n-2,dp);
    }
    public static int fibo_tabu(int N, int dp[]){
        for(int n=0;n<=N;n++){
            if(n<=1){
                dp[n]=n;
                continue;
            }
            
            dp[n]=dp[n-1]+dp[n-2];//fibo_memo(n-1,dp)+fibo_memo(n-2,dp);
        }
        return dp[N];
    }
    public static int fibo_opti(int N){
        int a = 0, b = 1;
        for(int i=2;i<=N;i++){
            int sum = a+b;
            a=b;
            b=sum;
        }
        return b;
    }
    public static void fibo(){
        int n = 10 ;
        int dp[]=new int[n+1];
        System.out.println(fibo_opti(n));
        display(dp);
    }
    public static int getMazePath(int er, int ec, int dp[][],int dir[][]){
        if(er==0 && ec==0) return dp[er][ec]=1;
        int count = 0;
        if(dp[er][ec]!=0) return dp[er][ec];
        for(int d[]:dir){
            int r = er + d[0];
            int c = ec + d[1];
            if(r>=0 && c>=0 && r<dp.length && c<dp[0].length){
                count+=getMazePath(r, c, dp,dir);
            }
        }
        return dp[er][ec]=count;
    }
    public static int getMazePath_tabu(int ER, int EC,int dp[][], int dir[][]){
        for(int er=0;er<=ER;er++){
            for(int ec=0;ec<=EC;ec++){
                if(er==0 && ec==0) {
                    dp[er][ec]=1;
                    continue;
                }
                int count = 0;
                for(int d[]:dir){
                    int r = er + d[0];
                    int c = ec + d[1];
                    if(r>=0 && c>=0 && r<dp.length && c<dp[0].length){
                        count+=dp[r][c];    //getMazePath(r, c, dp,dir);
                    }
                }
                dp[er][ec]=count;
            }
        }
        return dp[ER][EC];
    }
    public static int getMazePathJump_tabu(int ER, int EC,int dp[][], int dir[][]){
        for(int er=0;er<=ER;er++){
            for(int ec=0;ec<=EC;ec++){
                if(er==0 && ec==0) {
                    dp[er][ec]=1;
                    continue;
                }
                int count = 0;
                for(int d[]:dir){
                    for(int rad =1;rad<Math.max(dp.length, dp[0].length);rad++){
                        int r = er + rad*d[0];
                        int c = ec + rad*d[1];
                        if(r>=0 && c>=0 && r<dp.length && c<dp[0].length){
                            count+=dp[r][c];    //getMazePath(r, c, dp,dir);
                        }else{
                            break;
                        }
                    }
                    
                }
                dp[er][ec]=count;
            }
        }
        return dp[ER][EC];
    }
    public static int uniquePathsWithObstacles(int board[][], int er, int ec, int dir[][], int dp[][]){
        if(er==0 && ec==0) return dp[er][ec]=1;
        if(dp[er][ec]!=0) return dp[er][ec];
        int count = 0;
        for(int d[] : dir){
            int r = er + d[0];
            int c = ec + d[1];
            if(r>=0 && c>=0 && r<board.length && c<board[0].length && board[r][c]!=1){
                count+=uniquePathsWithObstacles(board, r, c, dir,dp);
            }
        }
        return dp[er][ec]=count;
    }
    public static int uniquePathsWithObstacles_tabu(int board[][], int ER, int EC, int dir[][], int dp[][]){
        for(int er=0;er<=ER;er++){
            for(int ec=0;ec<=EC;ec++){
                if(er==0 && ec==0){
                    dp[er][ec]=1;
                    continue;
                }
                int count = 0;
                for(int d[] : dir){
                    int r = er + d[0];
                    int c = ec + d[1];
                    if(r>=0 && c>=0 && r<board.length && c<board[0].length && board[r][c]!=1){
                        count+=dp[r][c];    //uniquePathsWithObstacles(board, r, c, dir,dp);
                    }
                }
                dp[er][ec]=count;
            }
        }
        return dp[ER][EC];
    }
    public static int diceWays_tabu(int N, int dp[]){
        for(int n=0;n<=N;n++){
            if(n==0) {
                dp[n]=1;
                continue;
            }

            int count = 0;
            for(int i=1;i<=6;i++){
                if(n-i>=0){
                    count+=dp[n-i]; //diceWays(n-i,dp);
                }
            }
            dp[n]=count;
        }
        return dp[N];
    }
    public static int diceWays(int n, int dp[]){
        if(n==0) return dp[n]=1;
        if(dp[n]!=0) return dp[n];

        int count = 0;
        for(int i=1;i<=6;i++){
            if(n-i>=0){
                count+=diceWays(n-i,dp);
            }
        }
        return dp[n]=count;
    }
    public static int countWaysWithJump(int n, int arr[], int dp[]){
        if(n==0) return dp[n]=1;
        if(dp[n]!=0) return dp[n];
        int count = 0;
        for(int i=0;i<arr.length;i++){
            if(n-arr[i]>=0){
                count+=countWaysWithJump(n-arr[i], arr,dp);
            }
        }
        return dp[n]=count;
    }
    public static int countWaysWithJump_tabu(int N, int arr[], int dp[]){
        for(int n=0;n<=N;n++){
            if(n==0){
                dp[n]=1;
                continue;
            }
            
            int count = 0;
            for(int i=0;i<arr.length;i++){
                if(n-arr[i]>=0){
                    count+=dp[n-arr[i]];    //countWaysWithJump(n-arr[i], arr,dp);
                }
            }
            dp[n]=count;
        }
        return dp[N];
    }
    
    public static void mazePath(){
        int r = 3, c=3;
        int dp[][]=new int[r][c];
        int dir[][]=new int[][]{{-1,0},{-1,-1},{0,-1}};
        System.out.println(getMazePathJump_tabu(r-1, c-1, dp, dir));
        display2D(dp);
    }
    public static void diceWays(){
        int n = 10;
        int dp[]=new int[n+1];
        System.out.println(diceWays_tabu(n, dp));
        display(dp);
    }
    public static void countWays(){
        int arr[]=new int[]{2,3,1,5};
        int n = 10;
        int dp[]=new int[n+1];
        System.out.println(countWaysWithJump_tabu(n, arr, dp));
        display(dp);
    }
    public static int minCostPath(int sr,int sc, int dr, int dc, int board[][],int dir[][],int dp[][]){
        if(sr==dr && sc==dc) return dp[sr][sc]=board[sr][sc];
        if(dp[sr][sc]!=0) return dp[sr][sc];
        int ans = (int)1e9;
        for(int d[]: dir){
            int r=sr+d[0];
            int c = sc+d[1];
            if(r>=0 && r<=dr && c>=0 && c<=dc){
                ans=Math.min(ans, minCostPath(r, c, dr, dc, board, dir,dp));
            }
        }
        return dp[sr][sc]=ans + board[sr][sc];
    }
    public static int minCostPath_tabu(int SR,int SC, int dr, int dc, int board[][],int dir[][],int dp[][]){
        for(int sc=dc;sc>=SC;sc--){
            for(int sr=dr;sr>=SR;sr--){
                if(sr==dr && sc==dc){
                    dp[sr][sc]=board[sr][sc];
                    continue;
                }
                
                int ans = (int)1e9;
                for(int d[]: dir){
                    int r=sr+d[0];
                    int c = sc+d[1];
                    if(r>=0 && r<=dr && c>=0 && c<=dc){
                        ans=Math.min(ans, dp[r][c]);
                    }
                }
                dp[sr][sc]=ans + board[sr][sc];
            }
        }
        return dp[SR][SC];
    }
   
    
    public static void main(String[] args) {
        int board[][]=new int[][]{{1,2,3},{4,8,2},{1,5,3}};
        int dir[][]=new int[][]{{0,1},{1,0},{1,1}};
        int dp[][]=new int[board.length][board[0].length];
        System.out.println(minCostPath_tabu(0, 0, board.length-1, board[0].length-1, board, dir, dp));
        display2D(dp);
    }
}
