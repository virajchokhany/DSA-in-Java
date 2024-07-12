package Recursion;

public class Recursion {

    public static int[] allIndicesOfAnArray(int a[],int x, int idx, int fsf)
    {
        if(idx == a.length)
            return new int[fsf];
        int ans[]= null;
        if(a[idx] == x)
        {
            ans = allIndicesOfAnArray(a,x,idx+1,fsf+1);
            ans[fsf] = idx;
        }
        else
            return allIndicesOfAnArray(a, x, idx+1, fsf);
           
        return ans;
    }
    public static void towerOfHanoi(int src, int dest , int inter,int n)
    {
        if(n==0)
            return;
        towerOfHanoi(src, inter, dest, n-1);
        System.out.println(n+"["+src+"->"+dest+"]");
        towerOfHanoi(inter, dest, src, n-1);
    }
    public static int powerLogarithmic(int x, int n)
    {
        if(n==0)
            return 1;
        int xn2 = powerLogarithmic(x, n/2);
        if(n%2==0)
            return xn2*xn2;
        else
            return xn2*xn2*x;
    }
    public static int powerLinear(int x, int n)
    {
        if(n==0)
            return 1;
        return x*powerLinear(x, n-1);
    }
    public static int factorial (int n){
        if(n==0 || n==1)
            return 1;
        return n*factorial(n-1);
    }
    public static void printDecreasingIncreasing(int n)
    {
        if(n==0)    return;
        System.out.println(n);
        printDecreasingIncreasing(n-1);
        System.out.println(n);
    }
    public static void main(String[] args) {
        // int fact = factorial(5);
        // System.out.println(fact);

        // System.out.println(powerLogarithmic(2, 5));

        // towerOfHanoi(10,11,12,4);

        int a [] =allIndicesOfAnArray(new int[]{1,2,3,3,3,3,9,0},10,0,0);
        System.out.println(a.length);
        for(int i=0;i<a.length;i++)
            System.out.print(a[i]+" ");
    }
}
