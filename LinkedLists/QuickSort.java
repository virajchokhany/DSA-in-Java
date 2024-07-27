package LinkedLists;

public class QuickSort {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode random;
        ListNode(){}
        ListNode(ListNode node){
            this.val = node.val;
            this.next = node.next;
        }
        ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

    public static ListNode[] segregateByIndex(ListNode head,int idx){
        ListNode pivotNode =head;
        while(idx-->0){
            pivotNode=pivotNode.next;
        }
        ListNode small = new ListNode(),large = new ListNode(), sp=small,lp=large;
        ListNode curr= head;
        while(curr!=null){
            if(curr!=pivotNode){
                if(curr.val<=pivotNode.val){
                    sp.next=curr;
                    sp=sp.next;
                }else{
                    lp.next=curr;
                    lp=lp.next;
                }
            }
            curr = curr.next;
        }

        sp.next=null;
        lp.next=null;
        pivotNode.next=null;
        return new ListNode[]{small.next,pivotNode,large.next};
    }
    public static ListNode quickSort(ListNode head){

        return quickSort_(head)[0];
    }
    private static ListNode[] quickSort_(ListNode head) {
        if(head == null || head.next == null)   return new ListNode[]{head,head};
        int len = length(head);
        ListNode segregatedLists[] = segregateByIndex(head, len/2); //left pivot right
        ListNode left[] = quickSort_(segregatedLists[0]);
        ListNode right[] = quickSort_(segregatedLists[2]);
        ListNode list[]=mergeLists(left,right,segregatedLists[1]);
        return new ListNode[]{list[0],list[1]};
    }
    private static ListNode[] mergeLists(ListNode[] left, ListNode[] right, ListNode pivotNode)
    {
        ListNode head = null, tail = null;
        if(left[0]!=null && right[0]!=null){
            left[1].next=pivotNode;
            pivotNode.next=right[0];
            head = left[0];
            tail=right[1];
        }else if(left[0]!=null){
            left[1].next=pivotNode;
            head=left[0];
            tail=pivotNode;
        }else if(right[0]!=null){
            pivotNode.next = right[0];
            head=pivotNode;
            tail=right[1];
        }else{
            head=tail=pivotNode;
        }

        return new ListNode[]{head,tail};
    }
    private static int length(ListNode head) {
        int c = 0;
        while(head!=null){
            head=head.next;
            c++;
        }
        return c;
    }
    public static ListNode addLast(ListNode head, ListNode node){
        if(head==null)
            return node;
        else if(head.next==null)
        {
            head.next=node;
            return head;
        }
        ListNode c=head;
        while(c.next!=null){
            c=c.next;
        }
        c.next=node;
        return head;
    }
    public static void displayLinkedList(ListNode head){
        while(head!=null){
            System.out.print(head.val + " -> ");
            head=head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1 = addLast(l1, new ListNode(6));
        l1 = addLast(l1, new ListNode(6));
        l1 = addLast(l1, new ListNode(7));
        l1 = addLast(l1, new ListNode(8));

        l1 = addLast(l1, new ListNode(1));
        l1 = addLast(l1, new ListNode(1));
        l1 = addLast(l1, new ListNode(4));
        l1 = addLast(l1, new ListNode(5));
        
      
        
        l1 = addLast(l1, new ListNode(9));
        l1 = addLast(l1, new ListNode(9));
        displayLinkedList(l1);
        quickSort(l1);
        displayLinkedList(l1);
        

    }
}
