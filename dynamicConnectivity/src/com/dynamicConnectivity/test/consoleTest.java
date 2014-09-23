package com.dynamicConnectivity.test;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.dynamicConnectivity.algorithms.ConnectivityAlgorithm;
import com.dynamicConnectivity.algorithms.QuickFind;
import com.dynamicConnectivity.algorithms.QuickUnion;
import com.dynamicConnectivity.algorithms.WeightedQuickUnion;

public class consoleTest {

    public static void main(String[] args) {
        if(args.length<1) testConnObj(new QuickFind());
        else if("0".compareToIgnoreCase(args[0].trim())==0)testConnObj(new QuickFind());
        else if("1".compareToIgnoreCase(args[0].trim())==0)testConnObj(new QuickUnion());
        else if("2".compareToIgnoreCase(args[0].trim())==0)testConnObj(new WeightedQuickUnion());
    }
    private static void printConnObj(ConnectivityAlgorithm connObj){
        for(int i=0;i<connObj.length();i++){
            System.out.print(connObj.find(i));
            System.out.print(" ");
        }
        System.out.println("");
    }
    
    private static void testConnObj(ConnectivityAlgorithm connObj){
        int N=0;
        System.out.println("Please enter number of elements");
        Scanner input = new Scanner( System.in );
        try
        {
            N=input.nextInt();
        }
        catch(InputMismatchException exp1)
        {
            System.err.println("Number of Elements shold be integer");
            input.close();
            return;
        }
        connObj.Init(N);
        printConnObj(connObj);
        System.out.println("Please enter Union Operations in the form p q,empty line to, enter any none number to exit");
        
        try
        {
            while(true)
            {
            int p=input.nextInt();
            int q=input.nextInt();
            connObj.union(p, q);
            printConnObj(connObj);
            }
        }catch(Exception ex){
            System.out.println("Exiting");
            printConnObj(connObj);
        }
        input.close();

    }
}
