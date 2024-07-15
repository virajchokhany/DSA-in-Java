package Recursion;

public class Backtracking {
    public static void knightTour(int chess[][],int move, int r, int c, int rows[],int cols[])
    {
        if(move == chess.length*chess.length)
        {
            chess[r][c]=move;
            for(int i=0;i<chess.length;i++){for(int j=0;j<chess.length;j++){
                System.out.print(chess[i][j] + " ");
            }System.out.println();}
            chess[r][c]=0;
            System.out.println();
            return;
        }

        chess[r][c]=move;
        for(int i=0;i<rows.length;i++)
        {
            if(r+rows[i]>=0 && r+rows[i]<chess.length && c+cols[i]>=0 && c+cols[i]<chess.length && chess[r+rows[i]][c+cols[i]] == 0)
            {
                knightTour(chess, move + 1, r+rows[i], c+cols[i], rows, cols);
            }
        }
        chess[r][c]=0;
    }
    public static void NQueens(int arr[][], int r, String ans)
    {
        if(r == arr.length)
        {
            System.out.println(ans);
            return;
        }
        for(int i=0;i<arr.length;i++)
        {
           if(IsQueenSafe(arr,r,i))
           {
               arr[r][i]=1;
               NQueens(arr, r+1, ans+r+i+"-");
               arr[r][i]=0;
           }
        }   
    }
    private static boolean IsQueenSafe(int[][] arr, int r, int c) 
    {
        // check column
        for(int row=r-1;row>=0;row--)
        {
            if(arr[row][c]==1)  return false;
        }
        // check rdiag

        for(int i = r-1, j=c+1;i>=0 && j<arr.length;i--,j++)
        {
            if(arr[i][j]==1)    return false;
        }

        // check ldiag

        for(int i = r-1,j=c-1;i>=0 && j>=0;i--,j--)
        {
            if(arr[i][j]==1)    return false;
        }
        return true;
    }
    public static void targetSumSubsets(int arr[],int target, int idx, String ans)
    {
        
        if(target == 0){
            System.out.println(ans);
            return;
        }if(idx == arr.length)   return;
        targetSumSubsets(arr, target-arr[idx], idx+1, ans+arr[idx]+" ");
        targetSumSubsets(arr, target, idx+1, ans);
    }
    public static void floodFill(int maze[][],int row,int col, String path)
    {
        if(row<0 || col<0 || row==maze.length || col == maze[0].length || maze[row][col]==1)
            return;
        else if (row == maze.length-1 && col == maze[0].length-1 )
        {
            System.out.println(path);
            return;
        }
        maze[row][col] = 1;
        floodFill(maze, row-1, col, path+"t");
        floodFill(maze, row, col-1, path+"l");
        floodFill(maze, row+1, col, path+"d");
        floodFill(maze, row, col + 1, path+"r");
        maze[row][col]=0;
    }
    public static void main(String[] args) {
        // floodFill(new int[][]{{0,0,0},{0,0,0},{0,0,0},{0,0,0}}, 0, 0, "");
        // targetSumSubsets(new int[]{10,20,30,40,50}, 60, 0, "");
        int n=4;
        // NQueens(new int[4][4], n, new int[n], new int[2*n-1],new int[2*n-1], 0, 0, "");
        // NQueens(new int[n][n], 0, "");
        int chess[][]=new int[5][5];
        int move = 1;
        int r=2,c = 2;
        int rows[] = new int[]{-2,-1,1,2,2,1,-1,-2}, cols[] = new int[]{1,2,2,1,-1,-2,-2,-1};

        knightTour(chess, move, r, c, rows, cols);
    }
}
