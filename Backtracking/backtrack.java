import java.util.ArrayList;
import java.util.HashMap;

public class backtrack {

    public static void NQueens(int row, int n, boolean ldiag[],boolean rdiag[], boolean col[],String ans){
        if(row==n){
            System.out.println(ans);
            return;
        }
        for(int j=0;j<n;j++){
            if(isQueenSafe(row,ldiag,rdiag,col,n,j)){
                ldiag[row+j]=true;
                rdiag[row-j+n-1]=true;
                col[j]=true;
                NQueens(row+1, n, ldiag, rdiag, col, ans + "(" + row+"-"+j+"),");
                ldiag[row+j]=false;
                rdiag[row-j+n-1]=false;
                col[j]=false;
            }
        }
    }
    private static boolean isQueenSafe(int row, boolean[] ldiag, boolean[] rdiag, boolean[] col, int n,int j) {
        // TODO Auto-generated method stub
        if(col[j] || ldiag[row+j] || rdiag[row-j+n-1]) return false;
        return true;
    }
    public static void printAbbreviations(String s, int idx, String ans, int count){
        if(idx==s.length()){
            if(count!=0)System.out.println(ans+count);
            else System.out.println(ans);
            return;
        }
        if(count!=0)
            printAbbreviations(s, idx+1, ans + count + s.charAt(idx), 0);
        else 
            printAbbreviations(s, idx+1, ans + s.charAt(idx), count);
        printAbbreviations(s, idx+1, ans, count+1);
    }
    
    public static void crosswordPuzzle(String words[], char board[][]){
        crosswordPuzzle(words, board,0);
    }
    public static void display(char board[][]){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static void crosswordPuzzle(String[] words, char[][] board, int idx) {
        if(idx==words.length){
            display(board);
            return;
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='-' || board[i][j]==words[idx].charAt(0)){
                    if(wordCanBePlacedHorizontally(words[idx],board,i,j)){
                        boolean vis[]=new boolean[words[idx].length()];
                        placeWordHorizontally(words[idx],board,i,j,vis);
                        crosswordPuzzle(words, board, idx+1);
                        unplaceWordHorizontally(words[idx],board,i,j,vis);
                    }
                    if(wordCanBePlacedVertically(words[idx],board,i,j)){
                        boolean vis[]=new boolean[words[idx].length()];
                        placeWordVertically(words[idx],board,i,j,vis);
                        crosswordPuzzle(words, board, idx+1);
                        unplaceWordVertically(words[idx],board,i,j,vis);
                    }
                }
            }
        }
    }
    private static void unplaceWordVertically(String word, char[][] board, int i, int j, boolean[] vis) {
        for(int idx = 0; idx<word.length();idx++){
            if(vis[idx]){
                board[i+idx][j]='-';
            }
        }
    }
    private static void placeWordVertically(String word, char[][] board, int i, int j, boolean vis[]) {
        for(int idx = 0; idx<word.length();idx++){
            if(board[i+idx][j]=='-'){
                vis[idx]=true;
            }
            board[i+idx][j] = word.charAt(idx);
        }
    }
    private static boolean wordCanBePlacedVertically(String word, char[][] board, int i, int j) {
        if(i-1>=0 && board[i-1][j]!='+') return false;
        if(i+word.length() < board.length && board[i+word.length()][j]!='+')return false;
        for(int idx = 0;idx<word.length();idx++){
            if(board[i+idx][j]=='-' || board[i+idx][j]==word.charAt(idx)){

            }else{
                return false;
            }
        }
        return true;
    }
    private static void unplaceWordHorizontally(String word, char[][] board, int i, int j, boolean[] vis) {
        for(int idx = 0; idx<word.length();idx++){
            if(vis[idx]){
                board[i][j+idx]='-';
            }
        }
    }
    private static void placeWordHorizontally(String word, char[][] board, int i, int j, boolean[] vis) {
        for(int idx = 0; idx<word.length();idx++){
            if(board[i][j+idx]=='-'){
                vis[idx]=true;
            }
            board[i][j+idx] = word.charAt(idx);
        }
    }
    
    private static boolean wordCanBePlacedHorizontally(String word, char[][] board, int i, int j) {
        if(j-1>=0 && board[i][j-1]!='+') return false;
        if(j+word.length() < board.length && board[i][j+word.length()]!='+')return false;
        for(int idx = 0;idx<word.length();idx++){
            if(board[i][j+idx]=='-' || board[i][j+idx]==word.charAt(idx)){

            }else{
                return false;
            }
        }
        return true;
    }
    private static String getUnique(String s, HashMap<Character,Integer> map){
        String unique="";
        for(int i=0;i<s.length();i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), -1);
                unique+=s.charAt(i);
            }
        }
        return unique;
    }
    public static void solveCryptoPuzzle(String s1, String s2, String s3){
        HashMap<Character, Integer> map = new HashMap<>();
        String unique = "";
        unique+=getUnique(s1, map);
        unique+=getUnique(s2, map);
        unique+=getUnique(s3, map);
        boolean vis[]=new boolean[10];
        solveCryptoPuzzle(map,unique,0,s1, s2, s3,vis);
    }
    private static int getValue(String s, HashMap<Character,Integer> map){
        int power = 1, sum = 0;

        for(int i=s.length()-1;i>=0;i--){
            sum=sum + (power*map.get(s.charAt(i)));
            power*=10;
        }
        return sum;
    }
    private static void solveCryptoPuzzle(HashMap<Character, Integer> map, String unique, int i, String s1, String s2,
            String s3,boolean vis[]) {
        if(i==unique.length()){
            if(getValue(s1,map) + getValue(s2, map) == getValue(s3, map)){
                System.out.println(map);
            }
            return;
        }
        char ch = unique.charAt(i);
        for(int n=0;n<=9;n++){
            if(vis[n]==false){
                vis[n]=true;
                map.put(ch, n);
                solveCryptoPuzzle(map, unique, i+1, s1, s2, s3, vis);
                map.put(ch, -1);
                vis[n]=false;
            }
        }
        
    }

    public static void wordPatternMatch(String s, String pattern, HashMap<Character,String> map,int pidx, int sidx){
        if(pidx==pattern.length() || sidx == s.length()){
            if(pidx==pattern.length() && sidx==s.length()){
                System.out.println(map);
            }
            return;
        }
        String mappedString = map.get(pattern.charAt(pidx));
        if(mappedString==null){
            for(int i = sidx;i<s.length();i++){
                map.put(pattern.charAt(pidx), s.substring(sidx, i+1));
                wordPatternMatch(s,pattern,map,pidx+1,i+1);
                map.remove(pattern.charAt(pidx));
            }
        }else{
            int len = mappedString.length();
            if(s.length() - sidx >= len){
                String left = s.substring(sidx, sidx + len);
                if(left.equals(mappedString)){
                    wordPatternMatch(s, pattern, map, pidx+1, sidx+len);
                }
            }
        }
    }
    static int min = (int)1e9;
    static String minString = "";
    public static void tugOfWar(int arr[],int idx, ArrayList<Integer> l1, ArrayList<Integer> l2, int sos1, int sos2){
        if(idx == arr.length){
            int diff = Math.abs(sos2-sos1);
            if(diff<min){
                min = diff;
                minString = l1 + " "+ l2;
            }
            return;
        }
        int n = arr.length;
        // add to l1
        if(l1.size()<(n+1)/2){
            l1.add(arr[idx]);
            tugOfWar(arr, idx+1, l1, l2, sos1 + arr[idx], sos2);
            l1.remove(l1.size()-1);
        }
        if(l2.size()<(n+1)/2){
            l2.add(arr[idx]);
            tugOfWar(arr, idx+1, l1, l2, sos1 , sos2 + arr[idx]);
            l2.remove(l2.size()-1);
        }
    }
    public static void tugOfWar(int arr[]){
        tugOfWar(arr, 0, new ArrayList<>(), new ArrayList<>(), 0, 0);
    }

    public static void printPermutations(int boxes[],int ci, int ti){
        if(ci>ti){
            for(int i=0;i<boxes.length;i++){
                if(boxes[i]==0){
                    System.out.print("_");
                }else{
                    System.out.print(boxes[i]);
                }
            }
            System.out.println();
            return;
        }
        for(int i=0;i<boxes.length;i++){
            if(boxes[i]==0){
                boxes[i]=ci;
                printPermutations(boxes, ci+1, ti);
                boxes[i]=0;
            }
        }
    }

    public static void printCombincation(int ci,int ti, int start, char boxes[]){
        if(ci>ti){
            for(int i=0;i<boxes.length;i++){
                System.out.print(boxes[i]);
            }
            System.out.println();
            return;
        }
        for(int i=start;i<boxes.length;i++){
           
                boxes[i]='i';
                printCombincation(ci+1, ti, i+1, boxes);
                boxes[i]='\0';
            
        }
    }

    public static void printPermutations2(int cb, int tb, int ssf, int ts, boolean vis[], String asf){
        if(cb> tb){
            if(ssf==ts){
                System.out.println(asf);
            }
            return;
        }
        for(int i=1;i<=ts;i++){
            if(vis[i]==false){
                vis[i]=true;
                printPermutations2(cb+1, tb, ssf + 1, ts, vis, asf+i);
                vis[i]=false;
            }
        }
        printPermutations2(cb+1, tb, ssf, ts, vis,asf+"-"); // dont house any number
    }
    public static void main(String[] args) {
        // printAbbreviations("pep", 0, "", 0);
        // NQueens(0, 4, new boolean[7], new boolean[7], new boolean[4], "");
        // char board[][]= {{'+','-','+'},{'-','-','-'},{'+','-','+'}};
        // crosswordPuzzle(new String[]{"ant","and"}, board);
        // solveCryptoPuzzle("send", "more", "money");
        // wordPatternMatch("dogcatdog", "abc", new HashMap<>(), 0,0);
        // tugOfWar(new int[]{1,2,3,4,5,6});
        // System.out.println(minString);
        // printPermutations(new int[4], 1,2);
        // printCombincation(1, 2,0, new char[4]);

        printPermutations2(1, 4, 0, 2, new boolean[3], "");
    }
    
}
