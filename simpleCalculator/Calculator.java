/*
 *  Course:		      TCSS143 A
 *  Name:				Viet Nguyen
 *  Instructor:		Mr. Schuessler
 *  Assignment:		Programming Assignment 4
 *  
 *  File Name:			Calculator.java
 */
 
 /**
	Represents a Caculator
	
	@author Viet Nguyen
	@version autumn 2013
*/

import java.io.*;
import java.util.*;

public class Calculator{
   private Element el;             // data to be pushed/popped on the stack 
   private Stack<Element> stack;   // The stack to hold the elements 
   private String expression;      // The expression String which is passed to 
                                   // method evaluate from the calling program 
   private double result;    // holds the final result 
   
   //constructor
   public Calculator(){ 
      el = new Element();
      stack = new Stack<Element>();
      expression ="";
      result = 0.0;
   }
   
   /**
     Receives an expression String as an argument and evaluate the result
     
     @param expression store tokens (operators/operands/symbols)
     @return the final result of the expression
   */
   public double evaluate(String expression) {
      
      // read string as input
      this.expression = expression;
      Scanner input = new Scanner(expression);
      char c = ' ';
      
      // go through all the tokens and store tokens/results in the right order of stack
      while(input.hasNext()) {
         Element el = new Element();
         double operand = 0.0;
         char operator = ' ';
         if(input.hasNextDouble()) {
             operand = input.nextDouble();
             el.setOperand(operand);
             stack.push(el);
         }else{
            c = input.next().charAt(0);
         
             if(c == '*' || c == '/' || c == '-' || c == '+') {
                el.setOperator(c);
                stack.push(el);
             }else if (c == ')') {
                el.setOperand(do3Pops());
                stack.push(el);
             }
         }   
      }
      
      // compute all the result from the elements by calling method do3Pops until get final result
      while(stack.size() > 2){
         el.setOperand(do3Pops());
         stack.push(el);
      } 
      result = stack.pop().getOperand();
      return result;
   }
   
   /**
     Pop 3 elements off stack, compute the result then return it
   */
   public double do3Pops() {
      double operand2 = stack.pop().getOperand();
      char operator = stack.pop().getOperator();
      double operand1 = stack.pop().getOperand();
      if(operator == '+')
         result = operand1 + operand2;
      else if(operator == '-')
         result = operand1 - operand2;
      else if(operator == '*')
         result = operand1 * operand2;
      else result = operand1 / operand2;
      
      return result;
   }
   
   /**
      @return display expression input and final result.
   */
   public String toString() {
      return expression + " = " + result;
   }
} // end of Calculator Class.

/**
  Element Class that represent the elments of Stack that in the Calculator
  
*/
class Element{ 
   
   // fields
   double operand; // Holds numberic values to be pushed on the stack 
   char operator;  // Holds operator symbols to be pushed on the stack 
   
   /**
     contructing the element object that contains operand and operators(symbols).
   */
   public Element(){ 
      operand = 0.0; 
      operator = ' '; 
   } 
   
   // accessors
   /**
     Access value of operator
   */ 
   public char getOperator() {
      return operator;
   }
   
   /**
     Access value of operand
   */ 
   public double getOperand() {
      return operand;
   }
   
   // mutators
   /**
     change the operator
   */
   public void setOperator(char operator) {
      this.operator = operator;
   }
   
   /**
     set new value of operand
   */
   public void setOperand(double operand) {
      this.operand = operand;
   }
   

} // End of class Element 