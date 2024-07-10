import java.util.*;

    public class BaseConversion{
    
    public static int AnyBaseMultiplication(int n1, int n2, int b){
        int res =0;
        int power =1;
        while(n1!=0){
            int d1 = n1%10;
            n1/=10;
            int digitMultiplicationResult = multiplyDigit(n2,d1,b,power);
            res = AnyBaseAddition(res,digitMultiplicationResult,b);
            power*=10;
        }
        return res;
    }
    
    private static int multiplyDigit(int n2, int d1, int b,int power) {

        int res = 0;
        int carry=0;
        while(n2!=0 || carry!=0){
            int d2 =n2%10;
            n2/=10;
            res = res + (d2*d1+carry)%b * power;
            power*=10;
            carry = (d2*d1+carry)/b;
        }
        return res;
    }

    // n2 -n1
    public static int AnyBaseSubtraction(int n1,int n2,int b){
        int res = 0;
        int power = 1;
        int borrow = 0;

        while(n1!=0 || n2!=0){
            int d1 = n1%10;
            int d2 = n2%10;
            if(d2<d1+borrow){
                res = res + (d2+b-d1-borrow)*power;
                borrow=1;
            }
            else{
                res = res + (d2-d1-borrow)*power;
                borrow=0;
            }
            power*=10;
            n1/=10;
            n2/=10;
        }
        return res;
    }
    public static int AnyBaseAddition(int n1, int n2, int b){
        int sum = 0;
        int carry = 0;
        int power = 1;
        while(n1!=0 || n2!=0 || carry!=0){
            sum = sum + ((n1%10 + n2%10+ carry)%b) * power;
            carry = (n1%10 + n2%10 + carry)/b;
            n1/=10;
            n2/=10;
            power*=10;
        }
        return sum;
    }
        // b1 => b2
    public static int AnyBaseToAnyBase(int n, int b1, int b2){
        int decimalEquivalent = AnyBaseToDecimal(n, b1);
        int newBase = DecimalToAnyBase(decimalEquivalent, b2);
        return newBase;
    }
    public static int AnyBaseToDecimal(int n, int base){
        int decimal = 0;
        int power = 1;
        while(n!=0){
            decimal += n%10 * power;
            power*=base;
            n/=10;
        }
        return decimal;
    }

    public static int DecimalToAnyBase(int n, int base){
        int newBase=0;
        int power = 1;
        while(n!=0){
            newBase+=n%base*power;
            power*=10;
            n/=base;
        }
        return newBase;
    }
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(AnyBaseMultiplication(n1,n2,b));
    }
}
