package LinkedLists;
import java.util.*;

public class LinkedList {
    
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
    
    public static ListNode segregateEvenOddNodesInLinkedList(ListNode head)
    {
        ListNode odd = new ListNode(), even = new ListNode(), oddHead =odd,evenHead=even;

        while(head!=null){
            if(head.val%2==0){
                even.next=head;
                even = even.next;
            }else{
                odd.next=head;
                odd = odd.next;
            }
            head = head.next;
        }
        odd.next = null;
        even.next  = oddHead.next;
        return evenHead.next;
    }


    static ListNode  th=null, tt=null;

    public static ListNode reverseNodesInGroupsOfK(ListNode head, int k){
        if(head == null || head.next == null || k==0)
            return head;
        int length = lengthOfLinkedList(head);
        ListNode c = head, f=null;
        ListNode  oh = null, ot=null;
        while(length >=k)
        {
            int kCopy = k;
            while(kCopy-->0){
                f=c.next;
                c.next=null;
                addFirst(c);
                c=f;
            }
            length-=k;
            if(oh==null){
                oh=th;
                ot=tt;
            }else{
                ot.next=th;
                ot=tt;
            }
            th=tt=null;
        }
        if(length>0){
            ot.next=c;
        }
        return oh;
    }
    private static void addFirst(ListNode c) {
        if(tt==null){
            th=tt=c;
        }else{
           c.next=th;
           th=c;
        }
    }

    private static int lengthOfLinkedList(ListNode head) {
        int c=0;
        while(head!=null){
            c++;
            head = head.next;
        }
        return c;
    }

    public static ListNode copyListWithRandomPointers(ListNode head){
        if(head == null)    return null;
        ListNode curr = head;

        while(curr!=null){
            ListNode forw =curr.next;
            ListNode node = new ListNode(curr.val);
            curr.next = node;
            node.next = forw;
            curr=forw;
        }
        ListNode c1 = head,c2=c1.next;
        while(c1!=null){
            c2=c1.next;
            if(c1.random!=null)
                c2.random = c1.random.next;
            else
                c2.random = c1.random;
            c1=c1.next.next;
        }
        c1=head;c2=c1.next;
        ListNode nHead = c2;
        while(c1!=null){
            c1.next = c2.next;
            if(c2.next!=null)
                c2.next = c2.next.next;
            c1 = c1.next;
            c2=c2.next;
        }
        return nHead;
    }
    
    public static boolean isCyclePresentInLinkedList(ListNode head)
    {
        if(head==null || head.next==null)   return false;
        ListNode slow=head,fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                return true;
        }
        return false;
    }

    public static ListNode meetingPointOfCycle(ListNode head)
    {
        if(head==null || head.next==null)   return null;
        ListNode slow=head,fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                return slow;
        }
        return null;
    }
    public static ListNode startingPointOfCycleInLinkedList(ListNode head){
        ListNode meetingPoint = meetingPointOfCycle(head);
        if(meetingPoint==null)  return null;
        while(head!=meetingPoint){
            head=head.next;
            meetingPoint=meetingPoint.next;
        }
        return head;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB == null)    return null;
        ListNode  curr = headA,tail = null;
        while(curr!=null){
            tail=curr;
            curr=curr.next;
        }
        tail.next=headB;
        ListNode meetingPoint = startingPointOfCycleInLinkedList(headA);
        tail.next=null;
        return meetingPoint;
    }

    public static ListNode addTwoLinkedLists(ListNode l1, ListNode l2){
        if(l1==null)    return l2;
        if(l2 == null)  return l1;

        l1 = reverseLinkedListIterative(l1);
        l2 = reverseLinkedListIterative(l2);

        ListNode d1=l1, d2=l2;
        ListNode l3 = new ListNode(), d3 =l3;

        int carry = 0;

        while(d1!=null || d2!=null || carry!=0){
            int sum = (d1!=null ? d1.val : 0) + (d2!=null ? d2.val : 0) + carry;
            int digit = sum%10;
            carry = sum/10;
            ListNode node = new ListNode(digit);
            d3.next = node;
            d3=d3.next;
            if(d1!=null)
                d1=d1.next;
            if(d2!=null)
                d2=d2.next;
        }
        d3 = l3.next;
        l3.next = null;
        l3=d3;
        l3 = reverseLinkedListIterative(l3);
        l1 = reverseLinkedListIterative(l1);
        l2 = reverseLinkedListIterative(l2);
        return l3;
        
    }

    public static ListNode subtractTwoLinkedLists(ListNode l1, ListNode l2){
        if(l1==null)    return l2;
        if(l2==null)    return l2;

        l1 = reverseLinkedListIterative(l1);
        l2 = reverseLinkedListIterative(l2);
        ListNode l3 = new ListNode(), d1=l1,d2=l2,d3=l3;
        int borrow = 0;

        while(l1!=null || l2!=null || borrow !=0)
        {
            int l1value = (l1!=null ? l1.val : 0);
            int l2value = (l2!=null ? l2.val : 0);
            int diff;
            if(l1value<l2value+borrow)
            {
                diff = l1value-l2value+10-borrow;
                borrow = 1;
            }else{
                diff = l1value-l2value-borrow;
                borrow = 0;
            }
            ListNode node = new ListNode(diff);
            d3.next = node;
            d3 =d3.next;
            if(l1!=null)    l1 = l1.next;
            if(l2!=null)    l2 = l2.next;

        }
        d3 = l3.next;
        l3.next = null;
        l3 = d3;
        l3 = reverseLinkedListIterative(l3);
        l1 = reverseLinkedListIterative(l1);
        l2 = reverseLinkedListIterative(l2);
        return l3;
    }
    private static ListNode multiplyListWithDigit(ListNode head, int digit){
        if(head == null)    return null;
        int carry = 0;
        ListNode dummy = new ListNode(), prev = dummy;
        while(head!=null || carry!=0)
        {
            int product = digit * (head!=null ? head.val : 0) + carry;
            ListNode node = new ListNode(product%10);
            carry = product/10;
            prev.next = node;
            prev=node;
            if(head!=null)
                head = head.next;
        }
        prev = dummy.next;
        dummy.next = null;
        return prev;
    }
    public static ListNode multiplyTwoLinkedLists(ListNode l1, ListNode l2){ // 12345 789
        if(l1==null)    return l2;
        if(l2==null)    return l1;

        l1 = reverseLinkedListIterative(l1); // 54321
        l2 = reverseLinkedListIterative(l2); // 987
        ListNode l3 = new ListNode(), d1 = l1, d2 = l2, d3=l3;
        int numberOfZeroesToAdd = 0;
        ListNode sum = null;
        while(d2!=null){
            ListNode list = multiplyListWithDigit(l1, d2.val);
            for(int i=1;i<=numberOfZeroesToAdd;i++){
                ListNode node = new ListNode(0);
                node.next = list;
                list = node;
            }
            numberOfZeroesToAdd++;
            list = reverseLinkedListIterative(list);
            sum = addTwoLinkedLists(sum, list);
            d2 = d2.next;
        }
        l1 = reverseLinkedListIterative(l1);
        l2 = reverseLinkedListIterative(l2);
        //sum = reverseLinkedListIterative(sum);
        return sum;

    }

    public static ListNode removeDuplicateNodesFromLinkedList(ListNode head){
        if(head == null || head.next == null)   return head;

        ListNode forw = head,dummy = head;

        while(head!=null){
            while(forw!=null && forw.val == head.val){
                ListNode next = forw.next;
                forw.next = null;
                forw = next;
            }
            head.next = forw;
            head = head.next;
        }

        return dummy;
    }

    public static ListNode removeDuplicateNodesUsingAddLast(ListNode head){
        ListNode th = null, tt = null;

        while(head!=null){
            ListNode next = head.next;
            head.next = null;
            if(tt==null){
                th = tt = head;
            }else{
                if(tt.val!=head.val){
                    tt.next = head;
                    tt= tt.next;
                }
            }
            head = next;
        }
        return th;
    }

    public static ListNode removeAllDuplicatesFromLInkedList(ListNode head){
        if(head==null || head.next == null) return head;

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode itr = dummy;

        ListNode curr = head.next;

        while(curr!=null){
            boolean flag=false;
            while(itr.next!=null && curr!=null && itr.next.val == curr.val){
                curr=curr.next;
                flag=true;
            }
            if(flag){
                itr.next = curr;
            }else{
                itr=itr.next;
            }
            if(curr!=null)
            curr=curr.next;
        }
        return dummy.next;
    }

    public static ListNode segregate01(ListNode head){
        if(head==null || head.next==null)   return head;
    
        ListNode zero = new ListNode(),one = new ListNode(), pz=zero,po=one;

        while(head!=null){
            if(head.val==0){
                pz.next=head;
                pz=pz.next;
            }else{
                po.next=head;
                po=head;
            }
            head=head.next;
        }
        po.next=null;
        pz.next=one.next;
        return zero.next;
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1 = addLast(l1, new ListNode(1));
        l1 = addLast(l1, new ListNode(1));
        l1 = addLast(l1, new ListNode(4));
        l1 = addLast(l1, new ListNode(5));
        
        l1 = addLast(l1, new ListNode(6));
        l1 = addLast(l1, new ListNode(6));
        l1 = addLast(l1, new ListNode(7));
        l1 = addLast(l1, new ListNode(8));

        
        l1 = addLast(l1, new ListNode(9));
        l1 = addLast(l1, new ListNode(9));
        // l1 = addLast(l1, new ListNode(18));
        // l1 = addLast(l1, new ListNode(19));
        // l1 = addLast(l1, new ListNode(20));
        // l1 = addLast(l1, new ListNode(201));
        // l1 = addLast(l1, new ListNode(2011));
        // l1 = reverseNodesInGroupsOfK(l1,3);
        l1 = removeDuplicateNodesUsingAddLast(l1);
        displayLinkedList(l1);
        // ListNode l2 = new ListNode(7);
        // l2 = addLast(l2, new ListNode(8));
        // l2 = addLast(l2, new ListNode(9));
        // //l2 = addLast(l2, new ListNode(9));
        // displayLinkedList(l2);
        // ListNode l3 = multiplyTwoLinkedLists(l1,l2);
        // displayLinkedList(l3);
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
