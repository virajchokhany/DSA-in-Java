import java.util.*;

public class Stacks {

    public static int[] NGL(int a[])
    {
        int ans[]=new int[a.length];
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<a.length;i++){
            while(st.size()>0 && a[i]>st.peek()){
                st.pop();
            }
            if(st.size()>0){
                ans[i]=st.peek();
            }else{
                ans[i]=-1;
            }
            st.push(a[i]);
        }
        return ans;
    }
    public static int[] NGR(int a[])
    {
        int ans[]=new int[a.length];
        Stack<Integer> st = new Stack<>();
        for(int i=a.length-1;i>=0;i--)
        {
            while(st.size()>0 && a[i]>st.peek()){
                st.pop();
            }
            if(st.size()>0){
                ans[i]=st.peek();
            }else{
                ans[i]=-1;
            }
            st.push(a[i]);
        }
        return ans;
    }
    public static void main(String[] args) {
        int a[]= new int[]{1,3,2,4};
        int ans[]=NGL(a);
        for(int x:ans)
            System.out.println(x);
    }
}
