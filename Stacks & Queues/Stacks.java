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
    
    public static int[] NSL(int a[]){
        Stack<Integer> st = new Stack<>();
        int ans[]=new int[a.length];
        for(int i=0;i<a.length;i++){
            while(st.size()>0 && st.peek()>=a[i])
                st.pop();
            if(st.size() == 0){
                ans[i]=-1;
            }else{
                ans[i]=st.peek();
            }
            st.push(a[i]);
        }
        return ans;
    }
   
    public static int[] NSR(int a[]){
        Stack<Integer> st = new Stack<>();
        int ans[]=new int[a.length];
        for(int i=a.length-1;i>=0;i--){
            while(st.size()>0 && st.peek()>=a[i])
                st.pop();
            if(st.size()>0){
                ans[i]=st.peek();
            }else{
                ans[i]=-1;
            }
            st.push(a[i]);
        }
        return ans;
    }

    public static int[] stockSpan(int a[]){
        Stack<Integer> st = new Stack<>();
        int ans[]=new int[a.length];

        for(int i=0;i<a.length;i++){
            while(st.size()>0 && a[i]>=a[st.peek()])
                st.pop();
            if(st.size()==0){
                ans[i]=i+1;
            }else{
                ans[i]=i-st.peek();
            }
            st.push(i);
        }
        return ans;
    }
    public static int maxAreaHistogram(int a[])
    {
        int indexOFNSL[] = nearestSmallerToLeftIndex(a);
        int indexOfNSR[] = nearestSmallerToRightIndex(a);
        int maxArea = -1;
        for(int i=0;i<a.length;i++){
            maxArea = Math.max(maxArea, (indexOfNSR[i]-indexOFNSL[i]-1)*a[i]);
        }
        return maxArea;
    }
    private static int[] nearestSmallerToRightIndex(int[] a) {
        Stack<Integer> st = new Stack<>();
        int ans[]=new int[a.length];
        for(int i=a.length-1;i>=0;i--){
            while(st.size()>0 && a[i]<=a[st.peek()])
                st.pop();
            if(st.size()==0){
                ans[i]=a.length;
            }else{
                ans[i]=st.peek();
            }
            st.push(i);
        }
        return ans;
    }
    private static int[] nearestSmallerToLeftIndex(int[] a) {
        Stack<Integer> st = new Stack<>();
        int ans[]=new int[a.length];
        for(int i=0;i<a.length;i++){
            while(st.size()>0 && a[i]<=a[st.peek()])
                st.pop();
            if(st.size()==0){
                ans[i]=-1;
            }else{
                ans[i]=st.peek();
            }
            st.push(i);
        }
        return ans;
    }
    public static int maxAreaRectangleInBinaryMatrix(int a[][])
    {
        int height[]=new int[a[0].length];
        int area = -1;
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                if(a[i][j]==0){
                    height[j]=0;
                }else{
                    height[j]++;
                }
            }
            area = Math.max(area, maxAreaHistogram(height));
        }
        return area;
    }

    public static int rainWaterTrapping(int a[]){
        int maxL[]=new int[a.length];
        int maxR[]=new int [a.length];
        int mx = -1;
        for(int i=0;i<a.length;i++){
            maxL[i]=mx;
            mx = Math.max(mx, a[i]);
        }
        mx=-1;
        for(int i=a.length-1;i>=0;i--){
            maxR[i]=mx;
            mx = Math.max(mx, a[i]);
        }
        int water = 0;

        for(int i=0;i<a.length;i++){
            if(maxL[i]!=-1 && maxR[i]!=-1 && a[i]<=maxL[i] && a[i]<=maxR[i]){
                water+=Math.min(maxL[i], maxR[i])-a[i];
            }
        }
        return water;
    }
    public static int[] slidingWindowMaximum(int nums[],int k){
        int ans[]=new int[nums.length-k+1];
        int nge[]=new int[nums.length];
        Stack<Integer> st = new Stack<>();
        for(int i=nums.length-1;i>=0;i--){
            while(st.size()>0 && nums[i]>=nums[st.peek()])
                st.pop();
            if(st.size()==0){
                nge[i]=nums.length;
            }else{
                nge[i]=st.peek();
            }
            st.push(i);
        }
        
        for(int i=0;i<=nums.length-k;i++){
            int j= i ;
            while(nge[j]<i+k){
                j=nge[j];
            }
            ans[i]=nums[j];
        }
        return ans;
    }

    public static int infixEvaluation(String s){
        Stack<Character> operator = new Stack<>();
        Stack<Integer> operand = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==' ')    continue;
            else if(isOperand(s.charAt(i))){
                operand.push(Integer.parseInt(s.charAt(i)+""));
            }else{
                if(s.charAt(i) == '('){
                    operator.push(s.charAt(i));
                }else if(s.charAt(i) == ')'){
                    while(operator.peek()!='('){
                        Character op = operator.pop();
                        int second = operand.pop();
                        int first = operand.pop();
                        int result = evaluate(first, second, op);
                        operand.push(result);
                    }
                    operator.pop();
                }else{ // + - * /
                    while(operator.size()>0 && priority(s.charAt(i)) <= priority(operator.peek())){
                        Character op = operator.pop();
                        int second = operand.pop();
                        int first = operand.pop();
                        int result = evaluate(first,second,op);
                        operand.push(result);
                    }
                    operator.push(s.charAt(i));
                }
            }
        }
    
        while(operator.size()>0){
            Character op = operator.pop();
            int second = operand.pop();
            int first = operand.pop();
                        int result = evaluate(first,second,op);
                        operand.push(result);
        } 
        if(operand.size()==1)
            return operand.peek();
        else{
            int power=1;
            int ans = 0;
            while(operand.size()!=0){
                ans+=power*operand.pop();
                power*=10;
            }return ans;
        }
    }
    private static int evaluate(int f, int s, Character op) {
        if(op=='+'){
            return f+s;
        }else if(op=='-'){
            return f-s;
        }else if(op=='*'){
            return f*s;
        }else{
            return f/s;
        }
    }
    private static int priority(Character c){
        if(c=='+' || c=='-')    return 1;
        else if(c=='*' || c=='/')   return 2;else   return -1;
    }
    private static boolean isOperand(char charAt) {
        if(charAt >='0' && charAt<='9') return true;
        return false;
    }

    public static String infixToPostfix(String s, boolean isPostfix){
        Stack<String> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);

            if(ch=='('){
                operator.push(ch);
            }else if(ch==')'){
                while(operator.peek()!='('){
                    Character op = operator.pop();
                    String second = operand.pop();
                    String first = operand.pop();
                    if(isPostfix)
                        operand.push(first+second+op);
                    else
                        operand.push(op+first+second);
                }
                operator.pop();
            }else if(ch=='+' || ch=='-' || ch=='*' || ch=='/'){
                while(operator.size()>0 && operator.peek()!='(' && priority(ch) <= priority(operator.peek())){
                    Character op = operator.pop();
                    String second = operand.pop();
                    String first = operand.pop();
                    if(isPostfix)
                        operand.push(first+second+op);
                    else
                        operand.push(op+first+second);
                }
                operator.push(ch);
            }else{
                operand.push(ch+"");
            }
        }
        while(operator.size()>0){
            Character op = operator.pop();
            String second = operand.pop();
            String first = operand.pop();
            if(isPostfix)
                        operand.push(first+second+op);
                    else
                        operand.push(op+first+second);
        }
        return operand.peek();
    }
    public static int celebrityProblem(int a[][]){
        Stack<Integer> st= new Stack<>();
        int n = a.length;
        for(int i=0;i<n;i++){
            st.push(i);
        }
        while(st.size()>1){
            int f = st.pop();
            int s =st.pop();
            if(a[s][f]==1){
                st.push(f);
            }else{
                st.push(s);
            }
        }
        int x = st.pop();
        boolean isCeleb = true;
        for(int i=0;i<n;i++){
            if(i!=x){
                if(a[x][i]==1 || a[i][x]==0){
                    isCeleb = false;
                    break;
                }
            }
        }
        return isCeleb ? x : -1;
    }
    
    public static int postfixEvaluation(String s){
        Stack<Integer> st = new Stack<>();
        Stack<String> infix = new Stack<>();
        Stack<String> prefix = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch=='+' || ch=='-' || ch=='*' || ch=='/'){
                int second = st.pop();
                int first = st.pop();
                st.push(evaluate(first, second, ch));
                String secondInfix = infix.pop();
                String firstInfix = infix.pop();
                String secondPrefix = prefix.pop();
                String firstPrefix = prefix.pop();
                prefix.push(ch+firstPrefix+secondPrefix);
                infix.push("("+firstInfix+ch+secondInfix+")");
            }else{
                st.push(ch-'0');
                infix.push(ch+"");
                prefix.push(ch+"");
            }
        }
        System.out.println("Infix ->"+infix.pop());
        System.out.println("Prefix -> " + prefix.pop());
        return st.pop();
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(this,other) ->{
            return this[0]-other[0];
        });

        Stack<Integer[]> st = new S
    }
   
    public static void main(String[] args) {
        // int a[]= new int[]{6,2,5,4,5,1,6};
        // // int ans[]=stockSpan(a);
        // // for(int x:ans)
        // //     System.out.println(x);

        // System.out.println(maxAreaHistogram(a));
        // int a[][]=new int[][]{{0,1,1,0},{1,1,1,1},{1,1,1,1},{1,1,0,0}};
        // System.out.println(maxAreaRectangleInBinaryMatrix(a));
        // System.out.println(rainWaterTrapping(new int[]{3,0,0,2,0,4}));

        // System.out.println(infixEvaluation("42"));
        // System.out.println(infixToPostfix("a*(b-c)/d+e",false));
        System.out.println(postfixEvaluation("264*8/+3-"));
    }
}
