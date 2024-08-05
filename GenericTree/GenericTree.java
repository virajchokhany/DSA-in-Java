package GenericTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

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
    public static void main(String[] args) {
        int arr[]=new int[]{10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        Node root = constructTree(arr);
        // arr=new int[]{10,20,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
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

        ceil = Integer.MAX_VALUE;
        floor=Integer.MIN_VALUE;
        ceilAndFloor(root, 65);
        System.out.println("Ceil "+ceil+" Floor "+floor);
    }
}
