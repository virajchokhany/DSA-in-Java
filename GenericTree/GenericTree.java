package GenericTree;

import java.util.*;

import javax.swing.text.html.HTMLDocument.Iterator;

public class GenericTree {
    
    public static class Node{
        int data;
        ArrayList<Node> children = new ArrayList<>();
        public Node(int data) {
            this.data= data;
        }
    }
    public static int height(Node root){
        int mxHeight = -1;
        for(Node child : root.children){
            mxHeight = Math.max(mxHeight, height(child));
        }
        return mxHeight+1;

    }
    public static int sizeOfGT(Node root){
        if(root==null)  return 0;
        int size = 1;
        for(Node child:root.children){
            size+=sizeOfGT(child);
        }
        return size;
    }

    public static void traversalInGT(Node root){
        System.out.println("Node Pre "+ root.data);
        for(Node child : root.children){
            System.out.println("Edge Pre "+ root.data +"--"+child.data);
            traversalInGT(child);
            System.out.println("Edge Post "+ root.data +"--"+child.data);
        }
        System.out.println("Node Post "+root.data);
    }
    public static void levelOrderTraversal(Node root){
        if(root==null)  return;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while(queue.size()>0){
            Node node = queue.remove();
            System.out.print(node.data+" ");
            for(Node child : node.children){
                queue.add(child);
            }
        }
        return;
    }

    public static void levelOrderTraversalLinewise(Node root){
        if(root==null)  return;

        Queue<Node> mq = new ArrayDeque<>();
        Queue<Node> cq = new ArrayDeque<>();

        mq.add(root);
        while(mq.size()>0){
            while(mq.size()>0){
                Node node = mq.remove();
                System.out.print(node.data + " ");
                for(Node child : node.children){
                    cq.add(child);
                }
            }
            System.out.println();
            mq=cq;
            cq=new ArrayDeque<>();
        }
    }
    public static void levelOrderLinewiseZigZag(Node root){
        Stack<Node> ms = new Stack<>(), cs = new Stack<>();

        ms.push(root);
        boolean forward = true;
        while(ms.size()>0){
            while(ms.size()>0){
                Node top = ms.pop();
                System.out.print(top.data +" ");
                if(forward){
                    for(Node child : top.children){
                        cs.push(child);
                    }
                }else{
                    for(int i=top.children.size()-1;i>=0;i--){
                        cs.push(top.children.get(i));
                    }
                }
            }
            System.out.println();
            forward=!forward;
            ms=cs;
            cs=new Stack<>();
        }
    }
    public static void levelOrderLinewise2(Node root){
        if(root==null)  return;

        Queue<Node> queue = new ArrayDeque<>();
        int size = 1; 
        queue.add(root);
        while(queue.size()>0){
            size = queue.size();
            for(int i=1;i<=size;i++){
                Node top = queue.remove();
                System.out.print(top.data + " ");
                for(Node child : top.children){
                    queue.add(child);
                }
            }
            System.out.println();
        }
    }
    public static void mirrorAGenericTree(Node root){

       for(Node child : root.children){
        mirrorAGenericTree(child);
       }
       Collections.reverse(root.children);
    }

    public static void removeLeafFromGenericTree(Node root){
        for(int i= root.children.size()-1;i>=0;i--){
            Node child = root.children.get(i);
            if(child.children.size() == 0){
                root.children.remove(child);
            }
        }
        for(Node child : root.children){
            removeLeafFromGenericTree(child);
        }
    }
    public static void linearizeAGenericTree(Node root){

        for(Node child : root.children){
            linearizeAGenericTree(child);
        }
        for(int i = root.children.size() -1 ;i>=0;i--){
            if(i-1>=0){
                Node node = findLastofIMinus1(root.children.get(i-1));
                node.children.add(root.children.get(i));
                root.children.remove(i);
            }
        }
    }
    public static Node linearizeEfficient(Node root){
        if(root.children.size()==0) return root;

        Node lastNode = null;

        for(int i=root.children.size()-1;i>=0;i--){
            Node tail = linearizeEfficient(root.children.get(i));
            if(i==root.children.size()-1){
                lastNode = tail;
            }
            if(i+1<root.children.size()){
                tail.children.add(root.children.get(i+1));
                root.children.remove(i+1);
            }
        }

        return lastNode;
    }
    private static Node findLastofIMinus1(Node node) {
        if(node.children.size()==0) return node;
        return findLastofIMinus1(node.children.get(0));
    }
    public static boolean find(Node root,int data){
        if(root.data == data)   return true;

        for(Node child : root.children){
            boolean found = find(child,data);
            if(found) return true;
        }
        return false;
    }

    public static ArrayList<Node> nodeToRootPath(Node root, int data){
        if(root.data==data){
            ArrayList<Node> list = new ArrayList<>();
            list.add(root);
            return list;
        }
        for(Node child : root.children){
            ArrayList<Node> ntrPath = nodeToRootPath(child,data);
            if(ntrPath.size()>0){
                ntrPath.add(root);
                return ntrPath;
            }
        }
        return new ArrayList<>();
    }

    public static Node LCA(Node root, int d1, int d2){
        ArrayList<Node> p1 = nodeToRootPath(root, d1);
        ArrayList<Node> p2 = nodeToRootPath(root, d2);
        int i=p1.size()-1,j=p2.size()-1;
        while(i>=0 && j>=0){
            if(p1.get(i) != p2.get(j))  break;
            i--;j--;
        }
        return p1.get(i+1);
    }
    public static int distanceBetweenTwoNodes(Node root, int d1, int d2){
        ArrayList<Node> p1 = nodeToRootPath(root, d1);
        ArrayList<Node> p2 = nodeToRootPath(root, d2);
        int i = p1.size()-1,j=p2.size()-1;
        while(i>=0 && j>=0){
            if(p1.get(i)!=p2.get(j))    break;
            i--;j--;
        }
        i++;j++;
        return i+j;
    }
    public static boolean areTreesSimilar(Node r1, Node r2){
        if(r1.children.size() != r2.children.size())    return false;
        
        // have same no of children
        for(int i=0;i<r1.children.size();i++){
            boolean ans = areTreesSimilar(r1.children.get(i), r2.children.get(i));
            if(ans==false)  return false;
        }
        return true;
    }
    public static boolean areTreesMirrorInShape(Node n1, Node n2){
        if(n1.children.size()!=n2.children.size())  return false;

        for(int i=0;i<n1.children.size();i++){
            int j= n2.children.size()-1-i;
            if(!areTreesMirrorInShape(n1.children.get(i), n2.children.get(j)))  return false;
        }
        return true;
    }
    public static boolean isTreeSymmetric(Node root){
        return _isTreeSymmetric(root,root);
    }
    private static boolean _isTreeSymmetric(Node r1,Node r2) {
        // TODO Auto-generated method stub
        if((r1!=null && r2==null) || (r1==null && r2!=null))    return false;
        for(int i=0;i<r1.children.size();i++){
            if(!_isTreeSymmetric(r1.children.get(i), r2.children.get(r1.children.size()-1-i)))  return false;
        }
        return true;
    }
    static Node pre,succ;
    static int state;
    public static void predecessorAndSuccessor(Node root, int data){
        if(state==0){
            if(root.data!=data){
                pre = root;
            }else{
                state = 1;
            }
        }else if(state==1){
            succ=root;
            state=2;
        }
        for(Node child : root.children){
            predecessorAndSuccessor(child, data);
        }

    }
    static int ceil,floor;
    public static void ceilAndFloor(Node root, int data){
        if(root.data>data){
            ceil = Math.min(ceil, root.data);
        }else if (root.data<data){
            floor = Math.max(floor, root.data);
        }
        for(Node child : root.children){
            ceilAndFloor(child, data);
        }
    }
    public static Node constructTree(int arr[]){
        Stack<Node> st = new Stack<>();
        Node root = null;
    
        for(int x : arr){
            if(x==-1){
                st.pop();
                continue;
            }
            Node node = new Node(x);
            if(st.size() == 0){
                root = node;
            }else{
                Node top = st.peek();
                top.children.add(node);
            }
            st.push(node);
        }
        return root;
    }
    public static int kthLargest(Node root, int k){
        int val = Integer.MAX_VALUE;
        for(int i=1;i<=k;i++){
            floor=Integer.MIN_VALUE;
            ceilAndFloor(root, val);
            System.out.println(floor);
            val=floor;

        }
        return val;
    }
    public static class MaxSubTreeSum{
        Node node;
        int sum;
        int subTreeSum;
        public MaxSubTreeSum() {
            
        }
        public MaxSubTreeSum(Node node, int sum,int subTreeSum) {
            this.node=node;
            this.sum=sum;
            this.subTreeSum=subTreeSum;
        }
    }
    public static MaxSubTreeSum maxSubTreeSum(Node root){
        if(root.children.size()==0) return new MaxSubTreeSum(root,root.data,root.data);

        int mxSum = Integer.MIN_VALUE;

        MaxSubTreeSum ans = new MaxSubTreeSum();

        int totalSum = root.data;
        for(Node child : root.children){
            MaxSubTreeSum cans = maxSubTreeSum(child);
            if(cans.sum > mxSum){
                mxSum = cans.sum;
                ans.sum = cans.sum;
                ans.node = cans.node;
                ans.subTreeSum=cans.subTreeSum;
            }
            totalSum+=cans.subTreeSum;
        }
        if(totalSum > mxSum){
            ans.sum = totalSum;
            ans.node=root;
        }
        ans.subTreeSum = totalSum;
        return ans;
    }

    public static class Diameter{
        int diameter;
        int height;
    }
    public static Diameter diameterOfGT(Node root){
        Diameter ans = new Diameter();

        int maxChildHeight = -1,secondMaxChildHeight = -1;

        for(Node child : root.children){
            Diameter cans = diameterOfGT(child);
            if(cans.diameter>ans.diameter){
                ans.diameter = cans.diameter;
            }
            if(cans.height>maxChildHeight){
                secondMaxChildHeight = maxChildHeight;
                maxChildHeight = cans.height;
            }else if (cans.height>secondMaxChildHeight && cans.height<maxChildHeight){
                secondMaxChildHeight = cans.height;
            }
        }
        ans.height = maxChildHeight+1;
        if(maxChildHeight+secondMaxChildHeight + 2 > ans.diameter){
            ans.diameter = maxChildHeight + secondMaxChildHeight + 2;
        }
        return ans;
    }

    public static class Pair{
        int state;
        Node node;
        public Pair(int state,Node node) {
            this.state=state;
            this.node=node;
        }
    }
    public static void iterativePreAndPostOrder(Node root){
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(-1,root));
        String preOrder = "";
        String postOrder = "";
        while(st.size()>0){
            if(st.peek().state==-1){
                preOrder+=st.peek().node.data+" ";
                st.peek().state= st.peek().state+1;
            }else if(st.peek().state == st.peek().node.children.size()){
                postOrder+=st.peek().node.data+" ";
                st.pop();
            }else{
                st.peek().state= st.peek().state+1;
                st.push(new Pair(-1,st.peek().node.children.get(st.peek().state-1)));
            }
        }
        System.out.println("Preorder : "+preOrder);
        System.out.println("Postorder : "+postOrder);
    }
    public static class TreeIterable implements Iterable<Node>
    {
        static Node root;
        public TreeIterable(Node root) {
            this.root=root;
        }
        public java.util.Iterator<Node> iterator() {
            // TODO Auto-generated method stub
            java.util.Iterator<Node> obj= new TreeIterablePreorder(root);
            return obj;
        }
    }
    public static class TreeIterablePreorder implements java.util.Iterator<Node>{
        static Stack<Pair> st = new Stack<>();
        static Node current;

        public TreeIterablePreorder(Node root) {
            st.push(new Pair(-1,root));
            next();
        }
        @Override
        public boolean hasNext() {
            if(current==null)   return false;
            return true;
        }
        @Override
        public Node next() {
            Node rval = current;
            current = null;
            while(st.size()>0){
                Pair top = st.peek();
                if(top.state==-1){
                    top.state=0;
                    current = top.node;
                    break;
                }else if(top.state == top.node.children.size()){
                    st.pop();
    
                }else{
                    top.state++;
                    st.push(new Pair(-1, top.node.children.get(top.state-1)));
                }
            }
            
            
            return rval;
        }

    }
    public static void main(String[] args) {
        //int arr[]=new int[]{10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        int []arr=new int[]{10,20,-50,-1,-60,-1,-1,30,-70,-1,80,-110,-1,120,-1,-1,90,-1,-1,40,-100,-1,-1,-1};
        //int arr[]= new int[]{111,-110,-1,120,-1,-1};
        Node root = constructTree(arr);
        // Node root2 = constructTree(arr);
        // levelOrderLinewiseZigZag(root);
        // System.out.println(sizeOfGT(root));
        // System.out.println(height(root));
        // traversalInGT(root);
        // levelOrderTraversalLinewise(root);
        // mirrorAGenericTree(root);
        // linearizeEfficient(root);
        // levelOrderLinewise2(root);
        // System.out.println(height(root));

        // ArrayList<Node> list = nodeToRootPath(root,110);
        // for(Node node : list){
        //     System.out.print(node.data +"->");
        // }
        // Node lca = LCA(root, 110, 80);
        // System.out.println("Lowest Common Ancestor "+lca.data);
        // int dist = distanceBetweenTwoNodes(root, 70, 110);
        // System.out.println("Distance between 2 nodes "+dist);

        // System.out.println(areTreesSimilar(root, root2));

        // pre=null;succ=null;state=0;
        // predecessorAndSuccessor(root, 110);
        // System.out.println("Pre "+pre.data);
        // System.out.println("Succ "+succ.data);

        // ceil = Integer.MAX_VALUE;
        // floor=Integer.MIN_VALUE;
        
        // ceilAndFloor(root, Integer.MAX_VALUE);
        // System.out.println(floor);

        // System.out.println(kthLargest(root, 3));

        // MaxSubTreeSum ans = maxSubTreeSum(root);
        // System.out.println("MAX SUM "+ans.sum+ " Node : "+ans.node.data);

        // System.out.println(diameterOfGT(root).diameter);
        // iterativePreAndPostOrder(root);

        TreeIterable tree = new TreeIterable(root);
        java.util.Iterator<Node> itr = tree.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next().data);
        }

    }

}
