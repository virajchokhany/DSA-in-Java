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

        int post[]=new int[]{15,10,23,25,20,35,42,39,30};
        idx = post.length-1;
        Node root = constructBSTFromPostorder(post, -(int)1e9, (int)1e9);
        inorder(root);
    }
}