package BinaryTree;

import java.util.*;

public class BinaryTree {
    
    public static class Node {
        int data;
        Node left,right;
        public Node(int data) {
            this.data = data;
            left=right=null;
        }
    }
    public static class Pair
    {
        Node node;
        int state;
        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }
    public static Node construct(Integer arr[]){
        Node root = new Node(arr[0]);
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root,-1));
        
        for(int i=1;i<arr.length;i++){
            Pair top = st.peek();
            Node node = arr[i]!=null ? new Node(arr[i]):null;
            if(top.state==-1){
                top.state++;
                top.node.left = node;
            }else{
                top.node.right = node;
                st.pop();
            }
            if(arr[i]!=null)
                st.push(new Pair(node, -1));
        }

        return root;
    }
    public static void preorder(Node root){
        if(root==null)  return;
        System.out.println(root.data);
        preorder(root.left);
        preorder(root.right);
    }
    public static void postorder(Node root){
        if(root==null)  return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.data);
    }
    public static void inorder(Node root){
        if(root==null)  return;
        inorder(root.left);
        System.out.println(root.data);
        inorder(root.right);
    }
    public static void levelOrder(Node root){
        Queue<Node> queue = new ArrayDeque<>();
        int size = 0;
        queue.add(root);
        while(queue.size()>0){
            size = queue.size();
            for(int i=1;i<=size;i++){
                Node node = queue.remove();
                System.out.print(node.data+" ");
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            System.out.println();
        }
    }
    public static void iterativeTraversals(Node root){
        String preOrder="",inOrder="",postOrder="";
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 0));

        while(st.size()>0){
            Pair top = st.peek();
            if(top.state == 0){
                preOrder+=top.node.data+" ";
                top.state++;
                if(top.node.left!=null){
                    st.push(new Pair(top.node.left, 0));
                }
            }else if(top.state==1){
                inOrder+=top.node.data+" ";
                top.state++;
                if(top.node.right!=null){
                    st.push(new Pair(top.node.right,0));
                }

            }else{
                postOrder+=top.node.data+" ";
                st.pop();
            }
        }
        System.out.println("Preorder "+preOrder);
        System.out.println("Inorder : "+inOrder);
        System.out.println("Postorder : "+postOrder);
    }
    public static boolean find(Node root,int data){
        if(root==null)  return false;
        if(root.data == data)   return true;
        return find(root.left, data) || find(root.right,data);
    }
    public static ArrayList<Node> ndoeToRootPath(Node root, int data){
        if(root==null){
            return null;
        }
        if(root.data==data){
            ArrayList<Node> list = new ArrayList<>();
            list.add(root);
            return list;
        }

        ArrayList<Node> left = ndoeToRootPath(root.left, data);
        if(left!=null){
            left.add(root);
            return left;
        }
        ArrayList<Node> right = ndoeToRootPath(root.right, data);
        if(right!=null){
            right.add(root);
            return right;
        }
        return null;

    }
    public static void printKLevelsDown(Node root, int k){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int size = 0;
        while(queue.size()>0){
            size=queue.size();
            for(int i=1;i<=size;i++){
                Node node = queue.remove();
                if(k==0){
                    System.out.print(node.data + " ");
                }
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null)    queue.add(node.right);
            }
            k--;
            if(k<0) break;  
        }
    }
    public static void printKLevelsDownRecursive(Node root, int k, Node blocked){
        if(root == null || k<0 || root == blocked) return;
        if(k==0 ){
            System.out.print(root.data +" ");
            return;
        }
        printKLevelsDownRecursive(root.left, k-1,blocked);
        printKLevelsDownRecursive(root.right, k-1,blocked);

    }
    public static void printKLevelsFar(Node root, int k,int data){
        Node node = getNodeByData(root,data);
        ArrayList<Node> ntr = ndoeToRootPath(root, data);
        printKLevelsDownRecursive(node, k,null);
        if(ntr!=null){
            for(int i=1;i<=k;i++){
                printKLevelsDownRecursive(ntr.get(i), k-i, ntr.get(i-1));
            }
        }

    }
    private static Node getNodeByData(Node root, int data) {
        if(root==null)  return null;
        if(root.data==data) return root;
        var left = getNodeByData(root.left, data);
        if(left!=null)  return left;

        var right = getNodeByData(root.right, data);
        if(right!=null)return  right;
        return null;
    }
    
    public static void nodeToLeaf(Node root, int sum, int low, int high, String path){
        if(root == null){
            return;
        }
        if(root.left==null && root.right==null){
            if(sum+root.data>= low && sum+root.data<= high){
                System.out.println(path+root.data);
            }
            return ;
        }
        nodeToLeaf(root.left, sum+root.data, low, high, path+root.data+" ");
        nodeToLeaf(root.right, sum+root.data, low, high, path+root.data+" ");
    }

    public static Node transformToLeftCLone(Node root){
        if(root == null)    return null;

        Node left = transformToLeftCLone(root.left);
        Node right = transformToLeftCLone(root.right);
        Node node = new Node(root.data);
        node.left = left;
        root.left = node;
        root.right = right;
        return root;
    }

    public static Node transformBackFromLeftClone(Node root){
        if(root == null ) return root;

        Node left = transformBackFromLeftClone(root.left.left);
        Node right = transformBackFromLeftClone(root.right);

        Node node = root.left;
        node.left = node.right = null;
        root.left = left;
        root.right = right;
        return root;
    }
    public static void printSingleChildNodes(Node node, Node parent){
        if(node == null)  return;
        if(parent!=null && ((parent.left==null && parent.right!=null) || (parent.left!=null && parent.right==null))){
            System.out.println(node.data);
        }
        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);
    }
    public static class Diameter{
        public int diameter;
        public int height;
        public Diameter(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
        public Diameter() {
        }
    }
    public static Diameter diameterOfBinaryTree(Node node){
        if(node == null){
            return new Diameter(-1,0);
        }
        Diameter left = diameterOfBinaryTree(node.left);
        Diameter right = diameterOfBinaryTree(node.right);

        Diameter ans = new Diameter();
        ans.diameter = Math.max(left.diameter, Math.max(right.diameter, left.height+right.height+2));
        ans.height = left.height + right.height + 2;
        return ans;
    }

    public static class Tilt{
        int sumOfTilt;
        int sumOfSubtree;
    }

    public static Tilt tiltOfBinaryTree(Node node){
        if(node==null){
            return new Tilt();
        }
        Tilt left = tiltOfBinaryTree(node.left);
        Tilt right = tiltOfBinaryTree(node.right);
        Tilt ans = new Tilt();
        ans.sumOfSubtree = left.sumOfSubtree+right.sumOfSubtree+node.data;
        ans.sumOfTilt = left.sumOfTilt + right.sumOfTilt + Math.abs(left.sumOfSubtree-right.sumOfSubtree);
        return ans;
    }
    public static class BST{
        boolean isBst;
        int mx;
        int mn;
        public BST() {
            super();
        }
        public BST(boolean isbst, int max, int min) {
            this.isBst = isbst;
            mx = max;
            mn = min;
        }
    }
    public static BST isBST(Node node){
        if(node == null){
            return new BST(true,Integer.MIN_VALUE,Integer.MAX_VALUE);
        }
        BST left = isBST(node.left);
        BST right = isBST(node.right);
        BST ans = new BST();
        if(left.isBst && right.isBst && node.data>left.mx && node.data<right.mn){
            ans.isBst=true;
        }
        ans.mn = Math.min(node.data, Math.min(left.mn, right.mn));
        ans.mx = Math.min(node.data, Math.min(left.mx, right.mx));
        return ans;
    }

    public static class BalancedTree{
        boolean isBalanced = true;
        int nodes=0;
    }
 
    public static BalancedTree isBalancedTree(Node node){
        if(node == null){
            return new BalancedTree();
        }
        BalancedTree left = isBalancedTree(node.left);
        BalancedTree right = isBalancedTree(node.right);
        BalancedTree ans = new BalancedTree();
        if(left.isBalanced==false || right.isBalanced == false || Math.abs(left.nodes-right.nodes)>=2){
            ans.isBalanced = false;
        }
        ans.nodes=Math.max(left.nodes,right.nodes)+1;
        return ans;
    }
    public static class LargestBSTSubtree{
        Node node;
        int size=0;
        boolean isBST=true;
        int min=Integer.MAX_VALUE; 
        int max=Integer.MIN_VALUE;
    }

    public static LargestBSTSubtree largestBSTSubtree(Node root){
        if(root==null){
            return new LargestBSTSubtree();
        }
        LargestBSTSubtree left = largestBSTSubtree(root.left);
        LargestBSTSubtree right = largestBSTSubtree(root.right);
        LargestBSTSubtree ans = new LargestBSTSubtree();

        if(left.isBST && right.isBST && root.data>left.max && root.data<right.min){
            ans.isBST=true;
            ans.size = left.size+right.size+1;
            ans.node=root;
        }else{
            ans.isBST = false;
            if(left.size>right.size){
                ans.size=left.size;
                ans.node=left.node;
            }else if(right.size>left.size){
                ans.size=right.size;
                ans.node=right.node;
            }   
        }

        ans.min = Math.min(root.data,Math.min(left.min, right.min));
        ans.max = Math.max(root.data,Math.max(left.max, right.max));
        return ans;
    }
    public static Node constructTreeFromPreorderAndInorder(int pre[],int in[],int psi, int pei, int isi,int iei){
        if(psi>pei || isi>iei)  return null;
        if(psi==pei && isi==iei) return new Node(in[isi]);
        Node node = new Node(pre[psi]);
        int idx = findIndexInInorder(pre[psi],in);
        int elementsInLeft = idx - isi;
        node.left = constructTreeFromPreorderAndInorder(pre, in, psi+1, psi+elementsInLeft, isi, idx-1);
        node.right = constructTreeFromPreorderAndInorder(pre, in, psi+elementsInLeft+1, pei, idx+1, iei);
        return node;
    }
    private static int findIndexInInorder(int x, int[] in) {
        // TODO Auto-generated method stub
        int i=0;
        while(in[i]!=x)i++;
        return i;
    }
    public static Node constructTreeFromPostOrderAndInorder(int post[],int in[],int psi,int pei, int isi,int iei){
        if(psi<pei || iei<isi)  return null;
        if(psi==pei && iei==isi)    return new Node(in[iei]);
        Node node = new Node(post[psi]);
        int idx = findIndexInInorder(post[psi],in);
        int elementsInLeft = idx-isi;
        node.left = constructTreeFromPostOrderAndInorder(post, in, pei+elementsInLeft-1,pei, isi, idx-1);
        node.right = constructTreeFromPostOrderAndInorder(post, in, psi-1, pei+elementsInLeft, idx+1, iei);
        return node;
    }
    public static Node createBalancedBSTFromInorder(int in[],int s, int e){
        if(s>e) return null;
        int mid = (s+e)/2;
        Node node = new Node(in[mid]);
        node.left = createBalancedBSTFromInorder(in, s, mid-1);
        node.right = createBalancedBSTFromInorder(in, mid+1, e);
        return node;
    }

    public static Node constructBSTFromPreorder(int pre[]){
        Node root = null;

        for(int i=0;i<pre.length;i++){
            root = insertNodeInBST(pre[i],root);
        }

        return root;
    }
    private static Node insertNodeInBST(int x, Node root) {
        // TODO Auto-generated method stub
        if(root==null){
            return new Node(x);
        }
        if(root.data<x){
            root.right = insertNodeInBST(x, root.right);
        }else if(root.data>x){
            root.left = insertNodeInBST(x, root.left);
        }
        return root;
    }
    static int idx;
    public static Node constructBSTFromPostorder(int post[], int l, int r){
        if(idx<0 || post[idx]<l || post[idx]>r)  return null;
        Node node = new Node(post[idx--]);
        node.right = constructBSTFromPostorder(post, node.data, r);
        node.left = constructBSTFromPostorder(post, l, node.data);
        return node;
    }   
    static int camera = 0;
    public static int cameraInBinaryTree(Node root){
        if(root == null)    return 1;   
        int left = cameraInBinaryTree(root.left);
        int right = cameraInBinaryTree(root.right);
        if(left==-1 || right == -1){
            camera++;
            return 0;
        }
        else if(left== 0 || right==0){
            return 1;
        }
        return -1;
    }
    static class Robber{
        int include;
        int exclude;
    }
    public static Robber houseRobber(Node root){
        if(root==null)  return new Robber();
        Robber left = houseRobber(root.left);
        Robber right = houseRobber(root.right);

        Robber ans = new Robber();
        ans.include = root.data + left.exclude + right.exclude;
        ans.exclude = Math.max(left.include, left.exclude) + Math.max(right.include,right.exclude);
        return ans;
    }

    static class ZigZag{
        int forward=-1;
        int backward=-1;
        int path=-1;
    }
    public static ZigZag longestZigZagPath(Node root){
        if(root == null){
            ZigZag z = new ZigZag();
            return z;
        }
        ZigZag left = longestZigZagPath(root.left);
        ZigZag right = longestZigZagPath(root.right);
        ZigZag ans = new ZigZag();
        ans.forward = 1 + right.backward;
        ans.backward = 1 + left.forward;
        ans.path = Math.max(left.path, Math.max(right.path, Math.max(ans.forward, ans.backward)));
        return ans;
    }

    public static Node createBinaryTreeFromPreorderAndPostorder(int pre[], int post[], int presi, int preei, int postsi,int postei){
        if(presi>preei || postsi>postei)    return null;

        if(presi==preei  || postsi==postei) return new Node(post[postei]);
        Node node = new Node(pre[presi]);
        int idx = findIndex(pre[presi+1],post);
        int count = idx-postsi+1;
        node.left = createBinaryTreeFromPreorderAndPostorder(pre, post, presi+1, presi+count, postsi, idx);
        node.right = createBinaryTreeFromPreorderAndPostorder(pre, post, presi+count+1, preei, idx+1, postei-1);
        return node;
    }
    private static int findIndex(int x, int[] post) {
        // TODO Auto-generated method stub
        int i = 0;
        while(post[i]!=x)i++;
        return i;
    }

    public static Node constructBinaryTreeFromInorderAndLevelOrder(int in[],int level[], int isi, int iei){
        if(level.length == 0 || isi>iei) return null;
        if(isi==iei) return new Node(level[0]);
        Node node = new Node(level[0]);
        int idx = findIndex(level[0], in);
        HashSet<Integer> set = new HashSet<>();
        for(int i=isi;i<idx;i++)
            set.add(in[i]);
        int left[]=new int[idx-isi];
        int l=0,r=0;
        int right[]=new int[iei-idx];
        for(int i=1;i<level.length;i++){
            if(set.contains(level[i])){
                left[l++]=level[i];
                set.remove(level[i]);
            }else{
                right[r++]=level[i];
            }
        }
        node.left = constructBinaryTreeFromInorderAndLevelOrder(in, left, isi, idx-1);
        node.right = constructBinaryTreeFromInorderAndLevelOrder(in, right, idx+1, iei);
        return node;
    }
    static class ConstructBST{
        Node node;
        int leftRange;
        int rightRange;
        public ConstructBST(Node node, int left, int right) {
           this.node = node;
           leftRange=left;
           rightRange=right;
        }
        public ConstructBST() {
            super();
        }
    }
    public static Node constructBSTFromLevelOrder(int level[]){
        Queue<ConstructBST> queue = new ArrayDeque<>();
        Node root = new Node(level[0]);
        int i=1;
        queue.add(new ConstructBST(root,-(int)1e9,(int)1e9));
        while(i<level.length){
            Node node = new Node(level[i]);
            while(queue.size()>0){
                ConstructBST first = queue.peek();
                if(!((node.data>first.leftRange && node.data<first.node.data) || (node.data> first.node.data && node.data<first.rightRange))){ // attach to lc
                    queue.remove();
                }
                else{
                    break;
                }
            }
            ConstructBST first = queue.peek();
            ConstructBST constructBST = new ConstructBST();
            if(node.data>first.leftRange && node.data<first.node.data){ // attach to lc
                    first.node.left = node;
                    constructBST.rightRange = first.node.data;
                    constructBST.node = node;
                    constructBST.leftRange = first.leftRange;
            }else if(node.data> first.node.data && node.data<first.rightRange){ // attach as rc and remove
                    first.node.right = node;
                    constructBST.rightRange = first.rightRange;
                    constructBST.node = node;
                    constructBST.leftRange = first.node.data;
                    queue.remove();
            }
            
            queue.add(constructBST);
            i++;
        }

        return root;
    }
    public static StringBuilder serialise(Node root){
        if(root==null)  return new StringBuilder("null");
        StringBuilder sb = new StringBuilder();
        sb.append(root.data+",");
        sb.append(serialise(root.left));
        sb.append(",");
        sb.append(serialise(root.right));
        return sb;
    }
    public static Node deserialise(String s){
        String arr[]=s.split(",");
        Stack<Pair> st = new Stack<>();
        Node root = new Node(Integer.parseInt(arr[0]));
        st.push(new Pair(root, -1));
        for(int i=1;i<arr.length;i++){
            Node node = null;
            if(!arr[i].equals("null")){
                node = new Node(Integer.parseInt(arr[i]));
            }
            Pair p = st.peek();
            if(p.state==-1){
                p.state++;
                p.node.left=node;
            }else{
                p.node.right = node;
                st.pop();
            }
            if(!arr[i].equals("null")) st.push(new Pair(node, -1));
        }return root;
    }

    public static ArrayList<Node> leftViewOfBinaryTree(Node root){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int size = 0;
        ArrayList<Node> list = new ArrayList<>();
        while(queue.size()>0){
            size = queue.size();
            boolean isFirstNodeInLevel = true;
            for(int i=1;i<=size;i++){
                Node node = queue.remove();
                if(isFirstNodeInLevel){
                    list.add(node);
                    isFirstNodeInLevel = false;
                }
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null)    queue.add(node.right);
            }
        }
        return list;
    }

    public static ArrayList<Node> rightViewOfBinaryTree(Node root){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int size = 0;
        ArrayList<Node> list = new ArrayList<>();
        while(queue.size()>0){
            size = queue.size();
            boolean isFirstNodeInLevel = true;
            for(int i=1;i<=size;i++){
                Node node = queue.remove();
                if(isFirstNodeInLevel){
                    list.add(node);
                    isFirstNodeInLevel = false;
                }
                if(node.right!=null)    queue.add(node.right);
                if(node.left!=null){
                    queue.add(node.left);
                }
                
            }
        }
        return list;
    }

    public static List<List<Integer>> verticalOrderOfBinaryTree(Node root){
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(root,0));
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while(queue.size()>0){
            Pair p = queue.remove();
            min = Math.min(min, p.state);
            max = Math.max(max, p.state);
            if(map.containsKey(p.state)){
                map.get(p.state).add(p.node.data);
            }else{
                ArrayList<Integer> list = new ArrayList<>();
                list.add(p.node.data);
                map.put(p.state, list);
            }
            if(p.node.left!=null){
                queue.add(new Pair(p.node.left, p.state-1));
            }
            if(p.node.right!=null){
                queue.add(new Pair(p.node.right, p.state+1));
            }
        }
        for(int i=min;i<=max;i++){
            ans.add(map.get(i));
        }
        return ans;
    }

    public static ArrayList<Integer>[] verticalOrder2(Node root){
        int width[]=new int[2];
        widthOfBinaryTree(root,0,width);
        ArrayList<Integer> ans[]= new ArrayList[Math.abs(width[1]-width[0]+1)];

        Queue<Pair>  queue = new ArrayDeque<>();
        queue.add(new Pair(root, Math.abs(width[0])));
        while(queue.size()>0){
            Pair p = queue.remove();
            if(ans[p.state]==null){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(p.node.data);
                ans[p.state] = list;
            }else{
                ans[p.state].add(p.node.data);
            }
            if(p.node.left!=null){
                queue.add(new Pair(p.node.left, p.state-1));
            }
            if(p.node.right!=null){
                queue.add(new Pair(p.node.right, p.state+1));
            }
        }


        return ans;
    }
    private static void widthOfBinaryTree(Node root, int i, int width[]) {
        if(root==null) return;
        width[0]=Math.min(width[0], i);
        width[1]= Math.max(width[1], i);
        widthOfBinaryTree(root.left, i-1, width);
        widthOfBinaryTree(root.right, i+1, width);
    }

    public static ArrayList<Integer> bottomViewOfBinaryTree(Node root){
        int width[]=new int[2];
        widthOfBinaryTree(root,0,width);
       
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<Math.abs(width[1]-width[0]+1);i++)
            ans.add(null);
       

        Queue<Pair>  queue = new ArrayDeque<>();
        queue.add(new Pair(root, Math.abs(width[0])));
        while(queue.size()>0){
            Pair p = queue.remove();
            ans.set(p.state,p.node.data);
            if(p.node.left!=null){
                queue.add(new Pair(p.node.left, p.state-1));
            }
            if(p.node.right!=null){
                queue.add(new Pair(p.node.right, p.state+1));
            }
        }
        
        return ans;
    }

    public static ArrayList<Integer> topViewOfBinaryTree(Node root){
        int width[]=new int[2];
        widthOfBinaryTree(root,0,width);
       
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<Math.abs(width[1]-width[0]+1);i++)
            ans.add(null);
       

        Queue<Pair>  queue = new ArrayDeque<>();
        queue.add(new Pair(root, Math.abs(width[0])));
        while(queue.size()>0){
            Pair p = queue.remove();
            if(ans.get(p.state)==null)
                ans.set(p.state,p.node.data);
            if(p.node.left!=null){
                queue.add(new Pair(p.node.left, p.state-1));
            }
            if(p.node.right!=null){
                queue.add(new Pair(p.node.right, p.state+1));
            }
        }
        
        return ans;
    }
    public static void main(String[] args) {
        // Integer arr[]=new Integer[]{50,25,12,null,null,37,30,null,null,40,null,null,75,62,60,51,null,null,61,null,null,77,74,null,null,78,null,null,87,null,null};
        // Node root = construct(arr);
        // // nodeToLeaf(root, 0, 150, 250, "");
        // // System.out.println(tiltOfBinaryTree(root).sumOfTilt);
        // LargestBSTSubtree largestBSTSubtree = largestBSTSubtree(root);
        // System.out.println(largestBSTSubtree.node.data+"@"+largestBSTSubtree.size);

        // preorder(root);
        // postorder(root);
        // inorder(root);
        // levelOrder(root);
        // iterativeTraversals(root);
        // System.out.println(find(root, 69));
        // ArrayList<Node> ntr = ndoeToRootPath(root, 69);
        // if(ntr!=null){
        //     for(Node node : ntr){
        //         System.out.print(node.data+" ");
        //     }
        // }else{
        //     System.out.println("Element not found. NO Node to root path");
        // }

        // printKLevelsDownRecursive(root, 2,null);
        // printKLevelsFar(root, 2, 37);
        // int post[]=new int[]{7,8,3,9,10,4,1,11,5,6,2,0};
        // int in[]= new int[]{7,3,8,1,9,4,10,0,11,5,2,6};
        // //Node root = constructTreeFromPreorderAndInorder(pre, in, 0, pre.length-1, 0, in.length-1);
        // Node root = constructTreeFromPostOrderAndInorder(post, in, post.length-1, 0, 0, in.length-1);
        // System.out.println(root);
        // preorder(root);
        // System.out.println();
        // inorder(root);

        // int in[]=new int[]{9,12,14,17,19,23,50,54,67,72,76};
        // Node root = createBalancedBSTFromInorder(in, 0, in.length-1);
        // preorder(root);

        // int pre[]=new int[]{30,20,10,15,25,23,39,35,42};
        // Node root = constructBSTFromPreorder(pre);
        //inorder(root);

        // int post[]=new int[]{15,10,23,25,20,35,42,39,30};
        // idx = post.length-1;
        // Node root = constructBSTFromPostorder(post, -(int)1e9, (int)1e9);
        // inorder(root);

        // Node root= null;
        // camera = 0; 
        // if(cameraInBinaryTree(root)==-1)
        //     camera++;

        // int lo[]=new int[]{2,7,8,3,6,9,5,11,4};
        // int in[]=new int[]{3,7,5,6,11,2,8,4,9};
        // Node root = constructBinaryTreeFromInorderAndLevelOrder(in, lo, 0, in.length-1);
        // preorder(root);
        // System.out.println();
        // inorder(root);
        // System.out.println();
        // postorder(root);
        // int level[]=new int[]{50,17,72,12,23,54,76,9,14,19,67};
        // Node root = constructBSTFromLevelOrder(level);
        // inorder(root);
        // Integer arr[]=new Integer[]{8,3,1,null,null,6,4,null,null,7,null,null,10,null,14,13,null,null,null};
        // Node root = construct(arr);
        // String  serialised = serialise(root).toString();
        // System.out.println(serialised);
        // root = deserialise(serialised);
        // preorder(root);
        Integer arr[]=new Integer[]{7,3,1,0,null,null,2,null,null,6,4,null,5,null,null,null,12,9,null,11,10,null,15,null,null,null,13,null,null};
        Node root = construct(arr);
        // ArrayList<Node> rightView = rightViewOfBinaryTree(root);
        // for(Node node : rightView){
        //     System.out.print(node.data + " ");
        // }
        // ArrayList<Integer> verticalOrder[] = verticalOrder2(root);
        // for(ArrayList<Integer> list : verticalOrder){
        //     System.out.println(list);
        // }

        System.out.println(topViewOfBinaryTree(root));
    }

}
