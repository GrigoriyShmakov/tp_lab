4package tp_lab;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class lab1 {
	   
    public static void main (String[] args) 
    {
    	int n5=0;///�����������
    	
    
        Scanner scan = new Scanner(System.in);
        System.out.println("�������� ��������:");
        System.out.println("1 - �������� ��������� ��������");
        System.out.println("2 - ���������� ������������");
        System.out.println("3 - ���������� � �������");
        System.out.println("4 - �����");
        
        int flag = scan.nextInt();
        
        while(flag != 4)
        {
        	if(flag == 1)
        	{
        		System.out.println("�������� ��������� ���������");
        		System.out.println("������� ������ �������");
        		int n = scan.nextInt();
        		int st=0;
        		while(Math.pow(2,st)<n)
        			st++;
        		int r = (int)Math.pow(2,st);
        		
        		Matrix s = new Matrix();
        		
        		System.out.println("1 - �������������� ���������");
        		System.out.println("2 - ������ �������");
        		int rezhi = scan.nextInt(); 
        		
        		double[][] A = new double[r][r];
        		double[][] B = new double[r][r];
        		
        		if(rezhi == 1)
        		{
        			s.ZapolnRand(A,n,0,100);
        			s.ZapolnRand(B,n,0,100);
        		}
        		if(rezhi == 2)
        		{
                    System.out.println("������� 1 �������");
                    s.Zapoln(A,n);
                    System.out.println("������� 2 �������");
                    s.Zapoln(B,n);;
        		}
        		
        		System.out.println("������� 1");
    			s.Print(A);
    			System.out.println("������� 2");
    			s.Print(B);
    			
    			double[][] C = s.multiply(A, B);
    			double[][] D = s.fun1(A, B); // ������� ��� ��������
    			
                System.out.println("\n���������: ");
                s.Print(C);
                System.out.println("\n��������� ��������: ");
                s.Print(D);
        	}
        	if(flag == 2)
        	{
        		System.out.println("���������� ������������ �������");
        		System.out.println("������� ������ �������");
        		int n = scan.nextInt();
        		
        		Matrix s = new Matrix();
        		
        		System.out.println("1 - �������������� ���������");
        		System.out.println("2 - ������ �������");
        		int rezhi = scan.nextInt(); 
        		
        		double[][] A = new double[n][n];
        		
        		if(rezhi == 1)
        			s.ZapolnRand(A,n,0,100);
        		if(rezhi == 2)
        		{
                    System.out.println("������� �������");
                    s.Zapoln(A,n);
        		}
        		
        		System.out.println("��������� �������");
    			s.Print(A);

    			double rez =s.fun2(A, n);
    			System.out.println("������������ �������� �����: " + rez);
    			
    			rez=s.GaussDet(A, n);
    			System.out.println("������������ �����: " + rez);
        		
    			
        		
        	}
        	if(flag == 3)
        	{
        		System.out.println("���������� � �������");
        		System.out.println("������� ������ �������");
        		int n = scan.nextInt();
        		
        		System.out.println("������� ������� ��������� �������");
        		int st = scan.nextInt();
        		
        		Matrix s = new Matrix();
        		
        		System.out.println("1 - �������������� ���������");
        		System.out.println("2 - ������ �������");
        		int rezhi = scan.nextInt(); 
        		
        		double[][] A = new double[n][n];
        		double[][] Res = new double[n][n];
        		
        		if(rezhi == 1)
        			s.ZapolnRand(A,n,0,100);
        		if(rezhi == 2)
        		{
                    System.out.println("������� �������");
                    s.Zapoln(A,n);
        		}
        		
        		System.out.println("��������� �������");
    			s.Print(A);
    			
    			 System.out.println("\n���������: ");
    			Res = s.Degree(A, st);
    			s.Print(Res);
    			
    			Res = s.fun3(A, st);
    			System.out.println("\n��������� ��������: ");
                s.Print(Res);
        	}
        }
    }
}