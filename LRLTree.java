/* Viet Nguyen
   Daniel Khieuson
   TCSS 342
   HW3
   Spring 2014
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


	public class LRLTree {
		Node root;
		Map<String, Integer> env; // enviroment
		Set<String> operators;  // set of operators
      String javaString;
      String indent = "        ";
      
		public LRLTree (String fileName) {
			createOperatorSet();
         javaString = "";
			env = new HashMap<String, Integer>();
			Scanner scan;
         
			try {
				scan = new Scanner(new File(fileName));
				root = createLRLTree(scan); 
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} 
      
      // create set of operators
      private void createOperatorSet() {
         operators = new HashSet<String>();
				operators.add("+");
				operators.add("-");
				operators.add("*");
				operators.add("/");
				operators.add("==");
				operators.add("<");
				operators.add("=");
				operators.add("if");
				operators.add("while");
				operators.add("block");
				operators.add("print");
      }
      
      /**
       *  This create a LRL tree from the input file
       *
       */
		public Node createLRLTree(Scanner scan) { 

			String str = scan.next();
         if (str.equals("(")) {
            str = scan.next(); // get next String , skip open parentheses
         }       
			if (!isOperator(str)) {
				Node newNode = new Node();
				newNode.data = str;
				newNode.left = null;
				newNode.right = null;
            
				return newNode;
				
			}else {
				Node newNode = new Node();
				newNode.data = str;
				newNode.left = createLRLTree(scan); 
            // This was in the class code if str is "print"
            // we got special case operator only take 1 subtree on the left
            newNode.right = str.equals("print") ? null : createLRLTree(scan);
				
            scan.next(); // skip close parenthese
				return newNode;
			}
		}
		
      /**
       *  This prints the entire tree that is the LRL 
       *
       */
		public void print() {
         print(root);
         System.out.println();
      }
      
      private void print(Node current) {
         if (current != null){         
            if (!isOperator(current.data)) {
               System.out.print(current.data + " ");               
            } else {
               System.out.print("(" + current.data + " ");
               print(current.left);               
               print(current.right);
               System.out.print(")");
            }        
         }         
		}
      
      /**
       *  This method converting LRL tree to runable java
       *
       */
      public String toJavaString() {
         
         javaString += "public class JavaCode {\n    public static void main(String[] args) { \n";
         for(String key : env.keySet()) {
             javaString += indent + "int " + key + "; \n";   
          }

         // call help method
         printJava(root);
         javaString += "    }\n";
         javaString += "}";
         
         return javaString;
      }
      
      // This help method do the actual job, converting LRL tree to java
      private void printJava(Node current) {
         if (current != null){         
            if (!isOperator(current.data)) {
               javaString += current.data + " ";               
            } else {

                if(current.data.equals("while") || current.data.equals("print") 
                      || current.data.equals("if")) {
                   if(current.data.equals("print"))
                      javaString += indent + "System.out.println(";
                   else 
                      javaString += indent + current.data + " ( ";           
                }
                if(current.data.equals("="))
                    javaString += indent;
                if (current.data.equals("+") || current.data.equals("-") 
                     || current.data.equals("*") || current.data.equals("/"))
                    javaString +="(";
                if (current.data.equals("block"))
                    javaString +=" { \n"; 
                    printJava(current.left);
                  
                if (current.data.equals("print"))
                    javaString += "); \n";
                if ( current.data.equals("if")) 
                    javaString += " ) \n";
                          
                if (current.data.equals("while"))
                    javaString += " ) "; 

                if(!current.data.equals("block") && !current.data.equals("while")
                        && !current.data.equals("print") && !current.data.equals("if")) 
                    javaString += current.data + " "; 
                 
                if (!current.data.equals("print")) {
                    printJava(current.right);
                    if (current.data.equals("+") || current.data.equals("-") 
                        || current.data.equals("*") || current.data.equals("/"))
                       javaString += " ) ";
                    if(current.data.equals("="))
                       javaString += "; \n";                
                }
                if (current.data.equals("block"))
                    javaString += indent + "} \n";
            }        
         }        
		}      

      /**
       *  This evaluate the LRT tree, excecute the tree to print output
       *
       */
      public void eval() {
         eval(this.root);
      }
      private int eval(Node current) {
         if (current == null){
             return 0;
         }
         if (current.data.equals("=")){ 
            env.put(current.left.data, eval(current.right));         
         } else if (current.data.equals("+")){  
             return eval(current.left) + eval(current.right);
         } else if (current.data.equals("-")) {
             return eval(current.left) - eval(current.right);           
         } else if (current.data.equals("*")) {
             return eval(current.left) * eval(current.right);
            
         } else if (current.data.equals("/")) {
             return eval(current.left) / eval(current.right);
         
         // if is true return 1, else return 0 
         } else if (current.data.equals("==")){
             return eval(current.left) == eval(current.right) ? 1 : 0;                    
         } else if (current.data.equals("<")) {
             return eval(current.left) < eval(current.right) ? 1 : 0;
            
         } else if (current.data.equals(">")) {
             return eval(current.left) > eval(current.right) ? 1 : 0;
             
         } else if(current.data.equals("if")) {
             if(eval(current.left) != 0) {
               return eval(current.right);
            }
         } else if(current.data.equals("while")) {
            while(eval(current.left) != 0) {
               eval(current.right);
            }
            
         } else if(current.data.equals("print")) {
             System.out.println(eval(current.left));
         } else if(current.data.equals("block")) {
             eval(current.left);
             eval(current.right);
             
         } else // maping variable key to its value
         {
             try { 
                 return Integer.parseInt(current.data); 
             } catch(NumberFormatException e) { 
                 return env.get(current.data); 
             }
         }
         
         return 0;                   
      }      

		private boolean isOperator(String str) {
					
			if (operators.contains(str))
				return true;
			return false;
		}
	} // end LRLTree class

   // node of a tree
class Node { 
	String data;
	Node left;
	Node right;
   int level;
}


