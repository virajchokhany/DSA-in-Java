public class client {
    public static void main(String[] args) throws Exception{
        int arr[]={2,0,-1,-9,7,10};
        heapSort hSort = new heapSort(arr, true);
        hSort.display();
    }
}
