package LinkedLists;
import java.util.*;

public class LinkedList {
    
    public static class ListNode{
        int val;
        ListNode next;
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
            System.out.print(head.val + " ");
            head=head.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head = addLast(head, new ListNode(2));
        head = addLast(head, new ListNode(3));
        head = addLast(head, new ListNode(4));
        head = addLast(head, new ListNode(5));
        head = addLast(head, new ListNode(6));
        //head = addLast(head, new ListNode(7));
        displayLinkedList(head);
        head = foldOfLinkedList(head);
        displayLinkedList(head);
        head = unfoldOfLinkedList(head);
        displayLinkedList(head);
    }   
}
