package project2;

import java.util.Scanner;
import java.util.Random;

//Class Product - uses dimensions from A & B to fill C's dimensions 
class Product extends Thread {
      private int[][] A;
      private int[][] B;
      private int[][] C;
      private int rig,col;
      private int dim;

//Constructor
      public Product(int[][] A,int[][] B,int[][] C,int rig, int col,int dim_com)
      {
         this.A = A;    
         this.B = B;
         this.C = C;
         this.rig = rig;    
         this.col = col; 
         this.dim = dim_com;     
      }

     public void run()
     {
         for(int i=0; i < dim; i++)
         {
               C[rig][col] += A[rig][i]*B[i][col];        
         }      
          System.out.println("Thread " + rig + "," + col + " complete.");        
     }          
 }

 public class Project2Test 
 {
       public static void main(String[] args)
      {      
          Scanner keyboard = new Scanner(System.in);
          
          Random randomObj = new Random();
          int rand;
          System.out.print("Row of Matrix A: ");     
          int rA = keyboard.nextInt();
          System.out.print("Column of Matrix A: ");
          int cA = keyboard.nextInt();
          System.out.println("Row of Matrix B: " + cA);     
          int rB = cA;
          System.out.print("Column of Matrix B: ");
          int cB = keyboard.nextInt();
          System.out.println();

          if(cA != rB)
          {
               System.out.println("Error!!");
               System.exit(-1);
          }
          
         System.out.println("Matrix C will have dimensions of " + rA + " x " + cB);
       System.out.println();
       int[][] A = new int[rA][cA];
       int[][] B = new int[rB][cB];
       int[][] C = new int[rA][cB];
       Product[][] thrd = new Product[rA][cB];

 //Fill elements
 
        //Matrix A elements
       System.out.println("Elements of Matrix A:");
       System.out.println();
        for(int i=0;i<rA;i++)
         {
          for(int j=0;j<cA;j++)
          {
              rand = randomObj.nextInt((100 - 0));
              A[i][j]=rand;
              System.out.println(i+","+j+" = " + A[i][j]);
          }
         }        
         System.out.println();
         
         //Matrix B elements
         System.out.println("Insert B:");
         System.out.println();
          for(int i=0;i<rB;i++)
          {
           for(int j=0;j<cB;j++)
            {
             rand = randomObj.nextInt((100 - 0));
              B[i][j]=rand;
              System.out.println(i+","+j+" = " + B[i][j]);
            }        
          }
          System.out.println();

        for(int i=0;i<rA;i++)
        {
         for(int j=0;j<cB;j++)
          {
            thrd[i][j] = new Product(A,B,C,i,j,cA);
            thrd[i][j].start();
          }
        }

        for(int i=0;i<rA;i++)
        {
            for(int j=0;j<cB;j++)
            {
                try{
                    thrd[i][j].join();
                }
            catch(InterruptedException e){}
            }
        }        

//Print out Matricies
        
        System.out.println();
        //Matrix A
        System.out.println("Matrix A:");
        for(int i=0; i<rA; i++)
        {
            for(int j=0; j<cA; j++)
            {
                System.out.print(A[i][j] +" ");
            }
            System.out.println();
        }
        
        System.out.println();
        //Matrix B
        System.out.println("Matrix B:");
        for(int i=0; i<rB; i++)
        {
            for(int j=0; j<cB; j++)
            {
                System.out.print(B[i][j] +" ");
            }
            System.out.println();
        }
            
        System.out.println();
        //Matrix C
        System.out.println("Matrix C:");
        for(int i=0;i<rA;i++)
        {
            for(int j=0;j<cB;j++)
            {
                System.out.print(C[i][j]+" ");
            }    
            System.out.println();            
        }       
        System.out.println();
}      
}