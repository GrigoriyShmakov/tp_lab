4package tp_lab;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class lab1 {
	   
    public static void main (String[] args) 
    {
    	int n5=0;///комментарий
    	
    
        Scanner scan = new Scanner(System.in);
        System.out.println("Выберите алгоритм:");
        System.out.println("1 - Алгоритм умножения Штрасена");
        System.out.println("2 - Нахождение определителя");
        System.out.println("3 - Возведение в степень");
        System.out.println("4 - Выход");
        
        int flag = scan.nextInt();
        
        while(flag != 4)
        {
        	if(flag == 1)
        	{
        		System.out.println("Алгоритм умножения Штрассена");
        		System.out.println("Введите размер матрицы");
        		int n = scan.nextInt();
        		int st=0;
        		while(Math.pow(2,st)<n)
        			st++;
        		int r = (int)Math.pow(2,st);
        		
        		Matrix s = new Matrix();
        		
        		System.out.println("1 - автоматическая генерация");
        		System.out.println("2 - задать вручную");
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
                    System.out.println("Введите 1 матрицу");
                    s.Zapoln(A,n);
                    System.out.println("Введите 2 матрицу");
                    s.Zapoln(B,n);;
        		}
        		
        		System.out.println("Матрица 1");
    			s.Print(A);
    			System.out.println("Матрица 2");
    			s.Print(B);
    			
    			double[][] C = s.multiply(A, B);
    			double[][] D = s.fun1(A, B); // функция для проверки
    			
                System.out.println("\nРезультат: ");
                s.Print(C);
                System.out.println("\nРезультат проверки: ");
                s.Print(D);
        	}
        	if(flag == 2)
        	{
        		System.out.println("Нахождение определителя матрицы");
        		System.out.println("Введите размер матрицы");
        		int n = scan.nextInt();
        		
        		Matrix s = new Matrix();
        		
        		System.out.println("1 - автоматическая генерация");
        		System.out.println("2 - задать вручную");
        		int rezhi = scan.nextInt(); 
        		
        		double[][] A = new double[n][n];
        		
        		if(rezhi == 1)
        			s.ZapolnRand(A,n,0,100);
        		if(rezhi == 2)
        		{
                    System.out.println("Введите матрицу");
                    s.Zapoln(A,n);
        		}
        		
        		System.out.println("Введенная матрица");
    			s.Print(A);

    			double rez =s.fun2(A, n);
    			System.out.println("Определитель проверка равен: " + rez);
    			
    			rez=s.GaussDet(A, n);
    			System.out.println("Определитель равен: " + rez);
        		
    			
        		
        	}
        	if(flag == 3)
        	{
        		System.out.println("Возведение в степень");
        		System.out.println("Введите размер матрицы");
        		int n = scan.nextInt();
        		
        		System.out.println("Введите степень умножения матрицы");
        		int st = scan.nextInt();
        		
        		Matrix s = new Matrix();
        		
        		System.out.println("1 - автоматическая генерация");
        		System.out.println("2 - задать вручную");
        		int rezhi = scan.nextInt(); 
        		
        		double[][] A = new double[n][n];
        		double[][] Res = new double[n][n];
        		
        		if(rezhi == 1)
        			s.ZapolnRand(A,n,0,100);
        		if(rezhi == 2)
        		{
                    System.out.println("Введите матрицу");
                    s.Zapoln(A,n);
        		}
        		
        		System.out.println("Введенная матрица");
    			s.Print(A);
    			
    			 System.out.println("\nРезультат: ");
    			Res = s.Degree(A, st);
    			s.Print(Res);
    			
    			Res = s.fun3(A, st);
    			System.out.println("\nРезультат проверки: ");
                s.Print(Res);
        	}
        }
    }
}