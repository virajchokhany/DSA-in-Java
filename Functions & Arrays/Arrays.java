import java.util.*;

public class Arrays {

    public static void searchInSorted2DArray(int a[][],int key)
    {
        int r =0, c=a[0].length-1;
        boolean found=false;
        while(r>=0 && r<a.length && c>=0 && c<a[0].length)
        {
            if(a[r][c]==key)
            {
                System.out.println("Found at Row : "+ r +" Col : "+c);
                found=true;
                break;
            }
            else if (a[r][c]>key)
            {
                c--;
            }
            else
                r++;
        }
        if(!found)
            System.out.println("Value not found");
    }
    public static void saddlePoint(int a[][])
    {
        int row=-1,col=-1;
        for(int i=0;i<a.length;i++)
        {
            int min = a[i][0];
            col = 0; row = i;
            for(int j=0;j<a[i].length;j++)
            {
                if(min>a[i][j])
                {
                    col=j;
                    min= a[i][j];
                }
            }
            // check if a[i][col] is max in column col
            int max = a[i][col];
            for(int j = 0;j<a.length;j++)
            {
               if(j!=i)
               {
                   if(a[j][col]<max)
                   {

                   }else{
                       row=j;
                       break;
                   }
               }
            }
            if(row==i)
                break;
        }
        if(row==-1 || col==-1)
            System.out.println("No saddle point");
        else   
            System.out.println("Row : "+row+" Col : "+col);

    }

    public static void diagonalTraversal(int a[][])
    {
        int n=a.length;

        for(int diff = 0; diff<n ; diff++)
        {
            for(int i=0;i<n;i++)
            {
                if(i+diff<n)
                    System.out.print(a[i][i+diff]+" ");
            }
        }
        
    }

    public static void shellRotate(int a[][], int s, int rotation)
    {
        int tli = s-1, tlj= s-1, bli= a.length - s, blj = s -1, tri = s-1, trj = a[0].length-s, bri = a.length-s, brj = a[0].length-s;
        rotation*=-1;
        int size = 2*(a.length-4*s+a[0].length+2);
        rotation = rotation % size;
        if(rotation<0)
            rotation = size + rotation;
        System.out.println(size);
        int b[]= new int[size];
        int k=0;
        int r =tli,c = tlj;
        while(r<=bli)
        {
            b[k++]=a[r][c];
            r++;
        }
        r--;
        c++;
        while(c<=brj)
        {
            b[k++]=a[r][c];
            c++;
        }
        c--;
        r--;
        while(r>=tri)
        {
            b[k++]=a[r][c];
            r--;
        }
        r++;
        c--;
        while(c>tlj)
        {
            b[k++]=a[r][c];
            c--;
        }

        r=tli;c=tlj;
        k = rotation;
        while(r<=bli)
        {
            a[r][c]=b[k];
            r++;
            k= (k+1)%size;
        }
        r--;
        c++;
        while(c<=brj)
        {
            a[r][c]=b[k];
            c++;
            k= (k+1)%size;
        }
        c--;
        r--;
        while(r>=tri)
        {
            a[r][c]=b[k];
            r--;
            k= (k+1)%size;
        }
        r++;
        c--;
        while(c>tlj)
        {
            a[r][c]=b[k];
            c--;
            k= (k+1)%size;
        }
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void rotateAnArray(char a[][])
    {
        int n=a.length;
        // columnwise reverse
        for(int i=0;i<n;i++)
        {
            int j =0,k=n-1;
            while(j<k)
            {
                char t=a[j][i];
                a[j][i]=a[k][i];
                a[k][i]=t;
                j++;
                k--;
            }
        }

        // transpose

        for(int i=0;i<n;i++)
        {
            for(int j=i;j<n;j++)
            {
                char t = a[i][j];
                a[i][j]=a[j][i];
                a[j][i]=t;
            }
        }

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(a[i][j]+" ");
            }System.out.println();
        }
    }
    public static void exitPointOfMatrix(int a[][])
    {
        int i=0,j=0,dir=0;
        int r =0,c=0;
        while(i>=0 && i<a.length && j>=0 && j<a[0].length)
        {
            r=i;
            c=j;
            if(a[i][j]==0)
            {
                if(dir==0)
                {
                    j++;
                }
                else if(dir==1)
                {
                    i++;
                }
                else if (dir==2)
                {
                    j--;
                }
                else if(dir==3)
                {
                    i--;
                }
            }
            else
            {
                if(dir==0)
                {
                    dir=1;
                    i++;
                }
                else if(dir==1)
                {
                    dir=2;j--;
                }
                else if (dir==2)
                {
                    i--;
                    dir=3;
                }
                else if(dir==3)
                {
                    j++;
                    dir=0;
                }
            } 
        }
        System.out.println("Exit row :" +r+" Exit col : "+c);
    }
    public static void spiralTraversal(int a[][])
    {
        int l=0,r=a[0].length-1,t=0,b=a.length-1,dir=0;

        while(l<=r && t<=b)
        {
            if(dir==0)
            {
                for(int i=t;i<=b;i++)
                {
                    System.out.println(a[i][l]);
                }
                l++;
            }
            else if(dir==1)
            {
                for(int i=l;i<=r;i++)
                    System.out.println(a[b][i]);
                b--;
            }
            else if(dir==2)
            {
                for(int i=b;i>=t;i--)
                    System.out.println(a[i][r]);
                r--;
            }
            else
            {  
                for(int i=r;i>=l;i--)
                    System.out.println(a[t][i]);
                t++;
            }
            dir=(dir+1)%4;
        }
    }

    public static void waveTraversal(int a[][])
    {
        boolean dir=true;
        for(int i=0;i<a[0].length;i++)
        {
            if(dir)
            {
                for(int j=0;j<a.length;j++)
                System.out.println(a[j][i]+" ");
                
            }else{
                for(int j=a.length-1;j>=0;j--)
                    System.out.println(a[j][i]);
            }
            dir=!dir;
        }
    }

    public static void matrixMultiplication(int a[][],int b[][]){
        int c[][]=new int[a.length][b[0].length];

        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<b[0].length;j++)
            {
                for(int k=0;k<b.length;k++)
                {
                    c[i][j]=c[i][j]+a[i][k]*b[k][j];
                }
            }
        }

        for(int i=0;i<c.length;i++){
            for(int j=0;j<c[0].length;j++){
                System.out.print(c[i][j]+ " ");
            }
            System.out.println();
        }
    }
    public static void firstOccurence(int a[],int key){
        int l=0,h=a.length-1,mid=0,fi=0;
        while(l<=h){
            mid = (h-l)/2+l;
            if(a[mid]==key)
            {
                fi=mid;
                h=mid-1;
            }
            else if(a[mid]>key){
                h=mid-1;
            }else{
                l=mid+1;
            }
        }
        System.out.println("First Occurence :"+fi);
    }
    public static void lastOccurence(int a[],int key){
        int l=0,h=a.length-1,mid=0,li=0;

        while(l<=h){
            mid = (h-l)/2+l;
            if(a[mid]==key){
                li=mid;
                l=mid+1;
            }else if(a[mid]>key){
                h=mid-1;
            }else{
                l=mid+1;
            }
        }
        System.out.println("Last index " + li);
    }
    public static void ceilAndFloor(int a[],int key){
        int l=0,h=a.length-1,mid=0;
        int ceil =0 ,floor =0 ;
        while(l<=h){
            mid =(h-l)/2+l;
            if(a[mid]==key){
                ceil=floor=a[mid];
                break;
            }else if(a[mid]>key){
                ceil=a[mid];
                h = mid-1;
            }else{
                floor=a[mid];
                l=mid+1;
            }
        }
        System.out.println("Ceil : "+ceil +" Floor : "+floor);
    }
    public static int binarySearch(int a[],int key){
        int l=0,h=a.length-1,mid;
        while(l<=h){
            mid = (h-l)/2+l;
            if(a[mid]==key)
                return mid;
            else if(a[mid]>key)
                h=mid-1;
            else
                l=mid+1;
        }
        return -1;
    }
    public static void rotateAnArray(int a[],int k){
        int n=a.length;
        k=k%n;
        if(k<0)
            k=n+k;
            reverseAnArray(a,0,k);
            reverseAnArray(a,k+1,a.length-1);
            reverseAnArray(a,0,a.length-1);
            for(int i=0;i<a.length;i++) 
                System.out.print(a[i]+" ");
    }
    public static void reverseAnArray(int a[],int i, int j){
        while(i<j){
            int t= a[i];
            a[i]=a[j];
            a[j]=t;
            i++;j--;
        }
    }
    public static void reverseAnArray(int a[]){
        int i=0,j=a.length-1;
        while(i<j){
            int t=a[i];
            a[i]=a[j];
            a[j]=t;
            i++;j--;
        }

    }
    // a-b
    public static void differenceOfArrays(int a[],int b[]){

        int c[]=new int[a.length];
        int i=a.length-1,j=b.length-1,k=c.length-1;
        int diff = 0;

        while(i>=0 || j>=0){
            int d1 = (i>=0 ? a[i] : 0);
            int d2 = (j>=0 ? b[j] : 0);
            if(d1<d2){
                c[k]=10+(i>=0 ? a[i] : 0)-(j>=0 ? b[j] : 0) -diff;
                diff=1;
            }else{
                c[k]=(i>=0 ? a[i] : 0)-(j>=0 ? b[j] : 0)-diff;
                diff=0;
            }
            i--;j--;k--;
        }

        for(int itr=k+1;itr<c.length;itr++){
            System.out.print(c[itr]+" ");
        }
    }
    public static void sumOfArrays(int a[],int b[]){
        int c[]= new int[Math.max(a.length, b.length)+1];
        int i=a.length-1,j=b.length-1,k=c.length-1;
        int carry =0;

        while(i>=0 || j>=0 || carry!=0){
            c[k]= ((i>=0 ? a[i] : 0) + (j>=0 ? b[j] : 0) + carry)%10;
            carry=((i>=0 ? a[i] : 0) + (j>=0 ? b[j] : 0) + carry)/10;
            i--;j--;k--;
        }

        for(int itr=k+1;itr<c.length;itr++){
            System.out.print(c[itr]);
        }
        
    }
    public static void barChart(int arr[]){
        int rows = arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]>rows)  
                rows=arr[i];
        }
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                if(i<rows-arr[j]){
                    System.out.print("  ");
                }else{
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // spiralTraversal(new int[][]{{11,12,13,14,15,16,17},{21,22,23,24,25,26,27},{31,32,33,34,35,36,37},{41,42,43,44,45,46,47},{51,52,53,54,55,56,57}});
        // exitPointOfMatrix(new int[][]{{0,0,0,1},{0,0,0,0},{1,0,0,1},{0,1,1,0}});

        // rotateAnArray(new char[][]{{'a','b','c','d'},{'e','f','g','h'},{'i','j','k','l'},{'m','n','o','p'}});

        //shellRotate(new int[][]{{11,12,13,14,15,16,17},{21,22,23,24,25,26,27},{31,32,33,34,35,36,37},{41,42,43,44,45,46,47},{51,52,53,54,55,56,57}},3,1);
        
        // saddlePoint(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}});
        searchInSorted2DArray(new int[][]{{1,2,3,7},{4,5,6,8}},4);
    }
    public static void sort(int[][] intervals, Stacks stacks, Object object) {
    }
}
