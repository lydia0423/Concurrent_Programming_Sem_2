package Lecture;

public class MainClass {
    
    public static void fun(Rec a){
        a.area();
    }
    
    public static void main(String a[]){
        Rec r = new Rec(20,60);
        Square s =new Square(50);
        fun(r);
        fun(s);
        int c = Runtime.getRuntime().availableProcessors();
        System.out.println(c);  
    }
}
