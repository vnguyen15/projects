import java.io.*;
import java.util.*;
public class Calc_Test{
  public static void main(String[] args){
	  Scanner input = null;
	  PrintStream output = null;
    String expression = "";

    Calculator calc = new Calculator();
    try {
      input = new Scanner(new File("in4.txt")); 
		  output = new PrintStream(new File("out4.txt"));
		  } 
    catch (FileNotFoundException e) {
      System.out.println("Error opening file: " + e);
		}
    catch (SecurityException e) {
      System.out.println("Error Writing File: " + e);
    }
    
    while (input.hasNextLine()){
      
      expression = input.nextLine();
    
      if (expression.length() > 0){
        calc.evaluate(expression);
        output.println(calc);
      }
    }
  }
}