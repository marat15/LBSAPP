/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package createprofile2;
import java.util.Scanner;
/**
 *
 * @author Marik
 */
public class CreateProfile2 {

    
    
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in); //Creat the Scanner
        
        double principal;
        double rate;
        double interest;
        
        System.out.print("Enter the initial investment : ");
        principal = stdin.nextDouble();
        
        System.out.print("Enter the annual interest rate (decimal, not percent!): ");
        rate = stdin.nextDouble();
        
        interest = principal*rate;
        principal = principal + interest;
        
        System.out.print("The value of the investment after one year is: ");
        System.out.println(principal);
    }
    
}
