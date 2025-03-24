public class client {
    public static void main(String[] args) throws Exception{
        int arr[]={2,0,-1,-9,7,10};
        heap h = new heap(arr,false);
        while(h.size()>0){
            System.out.print(h.remove()+" ");
        }
        System.out.println(h.arr);
    }
}
