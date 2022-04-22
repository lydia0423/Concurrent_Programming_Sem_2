package Lecture;

public class Rec extends Quad {
    
    public Rec(int a, int b){ 
        super(a,b,a,b);   
    }
    
    public void area(){
        System.out.println("Area of Rec = "+ a * b);
    }
}
