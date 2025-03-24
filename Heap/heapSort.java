public class heapSort {
    int arr[];
    int noOfEle;
    boolean asc;
    public heapSort(int arr[], boolean desc) {
        this.arr = arr;
        this.asc = desc;
        initialise();
        while(this.noOfEle>0){
            remove();
        }
    }
    public void display(){
        for(int ele:this.arr){
            System.out.print(ele + " ");
        }
    }
    private void swap(int i,int j){
        int t = this.arr[i];
        this.arr[i]=this.arr[j];
        this.arr[j]=t;
    }
    private int compareTo(int i, int j){
        if(this.asc==false){
            return arr[i]-arr[j];
        }else{
            return arr[j]-arr[i];
        }
    }
    private void downHeapify(int pi){
        int lci = 2*pi+1;
        int rci = 2*pi+2;
        int maxIdx = pi;
        if(lci<this.noOfEle && compareTo(lci,maxIdx)>0)
            maxIdx = lci;
        if(rci< this.noOfEle && compareTo(rci,maxIdx)>0)
            maxIdx = rci;
        if(pi!=maxIdx){
            swap(pi,maxIdx);
            downHeapify(maxIdx);
        }        
    }

    private void initialise(){
        this.noOfEle = this.arr.length;
        for(int i=arr.length-1;i>=0;i--){
            downHeapify(i);
        }
    }
    private void heapUnderFlowException() throws Exception{
        if(this.noOfEle==0)
            throw new Exception("HeapUnderFlowException");
    }
    public int remove() {
        //heapUnderFlowException();
        int val = arr[0];
        swap(0,this.noOfEle-1);
        this.noOfEle--;
        downHeapify(0);
        return val;
    }
}
