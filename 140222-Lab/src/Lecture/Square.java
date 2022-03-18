package Lecture;

public class Square extends Rec {
    public Square(int a){ 
        super(a,a);   
    }
    
    public void area(){
        System.out.println("Area of Square = " + a * a);
    }
}
