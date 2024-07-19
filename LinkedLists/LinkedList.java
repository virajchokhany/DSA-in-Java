package LinkedLists;
import java.util.*;

public class LinkedList {
    
    public static class ListNode{
        int val;
        ListNode next;
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

    public static ListNode reverseLinkedListRecursive(ListNode head){
        if(head == null || head.next == null)   return head;
        ListNode newHead = reverseLinkedListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static ListNode reverseLinkedListIterative(ListNode head){
        if(head  == null || head.next == null)  return head;

        ListNode prev = null, curr = head, forw = null;
        while(curr!=null){
            forw= curr.next;
            curr.next=prev;
            prev=curr;
            curr=forw;
        }
        return prev;
    }
    
    public static ListNode midNode(ListNode head){
        if(head ==null || head.next == null)    return head;

        ListNode slow=head,fast=head;
        while(fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        return slow;
    }
    
    public boolean isPalindrome(ListNode head) {
        ListNode mid = midNode(head);
        ListNode firstList = head;
        ListNode secondList = mid.next;
        ListNode secondHead = secondList;
        mid.next = null;
        secondList = reverseLinkedListIterative(secondList);
        boolean isPal = true;
        while(secondList!=null){
            if(secondList.val !=firstList.val){
                isPal=false;
                break;
            }
            secondList = secondList.next;
            firstList= firstList.next;
        }
        secondHead = reverseLinkedListIterative(secondHead);
        mid.next=secondHead;
        return isPal;
    }
    
    public static ListNode secondMidNode(ListNode head){
        if(head ==null || head.next == null)    return head;
        ListNode slow=head,fast=head;
        while(fast !=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public static ListNode foldOfLinkedList(ListNode head){
        if(head == null || head.next == null)   return head;
        ListNode mid = midNode(head);
        ListNode secondList = mid.next;
        mid.next = null;
        secondList = reverseLinkedListIterative(secondList);
        ListNode s=head,f=null, t = secondList, u=null;
        while(s!=null && t!=null){  // t!=null
            f=s.next;
            u=t.next;
            s.next=t;
            t.next=f;
            s=f;
            t=u;
        }
        return head;

    }
    
    public static ListNode unfoldOfLinkedList(ListNode head){
        if(head==null || head.next==null)   return head;
        ListNode c=head,f=head.next;
        ListNode secondHead=head.next;
        while(f!=null && f.next!=null){
            c.next=c.next.next;
            f.next=f.next.next;
            c=c.next;
            f=f.next;
        }
        c.next=null;
        secondHead=reverseLinkedListIterative(secondHead);
        c.next=secondHead;
        return head;
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

    public static ListNode mergeTwoLinkedLists(ListNode l1, ListNode l2){
        if(l1==null)    return l2;
        else if(l2==null)   return l1;

        ListNode dummy = new ListNode();
        ListNode start = dummy;
        ListNode a = l1, c=l2;

        while(a!=null && c!=null){
            if(c.val<a.val){
                dummy.next=c;
                c=c.next;
            }
            else{
                dummy.next=a;
                a=a.next;
            }
            dummy=dummy.next;
        }
        if(a==null){
            dummy.next=c;
        }else{
            dummy.next=a;
        }
        return start.next;
    }
    
    // NK^2
    public static ListNode mergeKSortedLinkedLists(ListNode lists[]){
        if(lists.length == 0)
            return null;
        ListNode l1=null,l2=null;
        for(int i=0;i<lists.length;i++){
            l2= lists[i];
            l1 = mergeTwoLinkedLists(l1, l2);

        }
        return l1;
    }
    
    // NlogK
    public static ListNode mergeKSortedLinkedLists(ListNode lists[], int start, int end){
        if(start>end)   return null;
        else if(start==end) return lists[start];
        int mid = (start+end)/2;
        ListNode h1 = mergeKSortedLinkedLists(lists, start, mid);
        ListNode h2 = mergeKSortedLinkedLists(lists, mid+1, end);
        return mergeTwoLinkedLists(h1, h2);
    }
    
    public static ListNode mergeSortLinkedList(ListNode head){
        if(head==null || head.next == null) return head;
        ListNode mid = midNode(head);
        ListNode nHead = mid.next;
        mid.next = null;
        head = mergeSortLinkedList(head);
        nHead = mergeSortLinkedList(nHead);
        return mergeTwoLinkedLists(head, nHead);
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1 = addLast(l1, new ListNode(7));
        l1 = addLast(l1, new ListNode(2));
        l1 = addLast(l1, new ListNode(6));
        l1 = addLast(l1, new ListNode(3));
        l1 = addLast(l1, new ListNode(5));
        l1 = addLast(l1, new ListNode(4));
        l1 = mergeSortLinkedList(l1);
        displayLinkedList(l1);

        // ListNode l2 = new ListNode(0);
        // l2 = addLast(l2, new ListNode(0));
        // l2 = addLast(l2, new ListNode(1));
        // l2 = addLast(l2, new ListNode(1));
        // l2 = addLast(l2, new ListNode(1));
        
        // l2 = addLast(l2, new ListNode(2));
        // l2 = addLast(l2, new ListNode(2));
        // l2 = addLast(l2, new ListNode(4));
        // displayLinkedList(l2);

        // ListNode l3 = new ListNode(0);
        // l3 = addLast(l3, new ListNode(0));
        // l3 = addLast(l3, new ListNode(0));
        // l3 = addLast(l3, new ListNode(0));
        // l3 = addLast(l3, new ListNode(5));
        // l3 = addLast(l3, new ListNode(5));
        // l3 = addLast(l3, new ListNode(6));
        // displayLinkedList(l3);

        // ListNode mergedList = mergeKSortedLinkedLists(new ListNode[]{l1,l2,l3},0,2);
        // displayLinkedList(mergedList);
    }   
}
