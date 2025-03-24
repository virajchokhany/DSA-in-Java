public class client {
    public static void main(String[] args) throws Exception{
        int arr[]={2,0,-1,-9,7,10};
        heap h = new heap(false);
        for(int i=0;i<arr.length;i++)
            h.add(arr[i]);
        // h.display();

        while(h.size()>0){
            System.out.print(h.remove()+" ");
        }
    }
}
