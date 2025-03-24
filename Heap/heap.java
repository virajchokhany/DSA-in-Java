import java.util.ArrayList;

public class heap{

    // Data Members
    ArrayList<Integer> arr;
    int noOfEle;
    boolean isMaxheap;

    // Constructors
    public heap() {
        this(true);
    }
    public heap(boolean isMaxheap) {
        this.isMaxheap = isMaxheap;
        this.arr = new ArrayList<>();
        this.noOfEle = 0;
    }
    public heap(int arr[],boolean isMaxheap){
        this(isMaxheap);
        initialise(arr);
    }
    private int compareTo(int i, int j){
        if(this.isMaxheap){
            return (this.arr.get(i)-this.arr.get(j));
        }else{
            return (this.arr.get(j)-this.arr.get(i));
        }
    }
    private void swap(int i, int j){
        int a = this.arr.get(i);
        int b = this.arr.get(j);
        this.arr.set(i, b);
        this.arr.set(j, a);
    }
    // Helper Functions
    private void downHeapify(int pi){
        int maxIdx = pi, lci=2*pi+1,rci= 2*pi+2;

        if(lci<this.noOfEle && compareTo(lci,maxIdx)>0){
            maxIdx = lci;
        }
        if(rci<this.noOfEle && compareTo(rci,maxIdx)>0){
            maxIdx = rci;
        }
        if(pi!=maxIdx){
            swap(pi,maxIdx);
            downHeapify(maxIdx);
        }
    }
    private void upHeapify(int ci){
        int pi =  (ci-1)/2;
        if(pi!=ci){
            int maxIdx = ci;
            if(compareTo(maxIdx, pi)>0){
                swap(pi,maxIdx);
                upHeapify(pi);
            }
        }
    }
    private void initialise(int arr[]){
        for(int i=arr.length-1;i>=0;i--){
            this.arr.add(arr[i]);
        }
        this.noOfEle = this.arr.size();
        for(int i=noOfEle-1;i>=0;i--){
            downHeapify(i);
        }
    }

    // Basic function

    public int size(){
        return noOfEle;
    }
    private void heapUnderFlow() throws Exception{
        if(noOfEle==0)
            throw new Exception("HeapUnderflowException");
    }
    public int peek() throws Exception{
        heapUnderFlow();
        return this.arr.get(0);
    }
    public void display(){
        for(int ele:this.arr){
            System.out.print(ele+" ");
        }
    }
    // DS Functions

    public void add(int val){
        this.arr.add(val);
        this.noOfEle++;
        upHeapify(this.noOfEle-1);
    }

    public int remove() throws Exception{
        heapUnderFlow();
        int val = this.arr.get(0);
        swap(0,this.noOfEle-1);
        this.noOfEle--;
        downHeapify(0);
        return val;
    }

}