package HashMaps;

import java.util.PriorityQueue;

public class heap {

    public static void sortKSortedArray(int arr[], int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int idx =0;
        for(int i=0;i<arr.length;i++){
            pq.add(arr[i]);
            if(pq.size()>k){
                arr[idx++] = pq.remove();
            }
        }
        while(pq.size()>0){
            arr[idx++]=pq.remove();
        }
    }
    public static void main(String[] args) {
        int arr[]={6,5,3,2,8,10,9};
        sortKSortedArray(arr, 3);
        for(int val : arr){
            System.out.println(val);
        }
    }
}
