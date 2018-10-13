package tp_lab;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Matrix {
	
	//Вывод матрицы
		public void Print(double[][] A) // Function generates the random matrix 
		{
			int n = A.length;
			for (int i = 0; i < n; i++) 
			{	
				for (int j = 0; j < n; j++) 
					System.out.print(A[i][j] + " ");
				System.out.println(" ");
			} 
		}
	
	//Заполнение матрицы рандом 
	public void ZapolnRand(double[][] A, int n, int a1, int a2)
	{
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < n; j++) 
				A[i][j] = ThreadLocalRandom.current().nextInt(a1, a2 + 1);
	}
		
	//Заполнение матрицы рандом 
		public void Zeros(double[][] A, int n)
		{
			for (int i = 0; i < n; i++) 
				for (int j = 0; j < n; j++) 
					A[i][j] = 0;
		}
			
		
	//Заполнение матрицы рандом 
	public void Zapoln(double[][] A, int n)
	{
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
            	A[i][j] = scan.nextInt();
	}
		
	//Умножение штрассена
    public double[][] multiply(double[][] A, double[][] B)
    {        
        int n = A.length;
        double[][] R = new double[n][n];
        
        if (n == 1)
            R[0][0] = A[0][0] * B[0][0];
        
        else
        {
        	double[][] A11 = new double[n/2][n/2];
        	double[][] A12 = new double[n/2][n/2];
        	double[][] A21 = new double[n/2][n/2];
        	double[][] A22 = new double[n/2][n/2];
        	double[][] B11 = new double[n/2][n/2];
        	double[][] B12 = new double[n/2][n/2];
        	double[][] B21 = new double[n/2][n/2];
        	double[][] B22 = new double[n/2][n/2];
 
            //Разделительная матрица А на 4 половины 
            split(A, A11, 0 , 0);
            split(A, A12, 0 , n/2);
            split(A, A21, n/2, 0);
            split(A, A22, n/2, n/2);
            
            //Разделительная матрица B на 4 половины
            split(B, B11, 0 , 0);
            split(B, B12, 0 , n/2);
            split(B, B21, n/2, 0);
            split(B, B22, n/2, n/2);
 
            /** 
              M1 = (A11 + A22)(B11 + B22)
              M2 = (A21 + A22) B11
              M3 = A11 (B12 - B22)
              M4 = A22 (B21 - B11)
              M5 = (A11 + A12) B22
              M6 = (A21 - A11) (B11 + B12)
              M7 = (A12 - A22) (B21 + B22)
            **/
 
            double [][] M1 = multiply(add(A11, A22), add(B11, B22));
            double [][] M2 = multiply(add(A21, A22), B11);
            double [][] M3 = multiply(A11, sub(B12, B22));
            double [][] M4 = multiply(A22, sub(B21, B11));
            double [][] M5 = multiply(add(A11, A12), B22);
            double [][] M6 = multiply(sub(A21, A11), add(B11, B12));
            double [][] M7 = multiply(sub(A12, A22), add(B21, B22));
 
            /**
              C11 = M1 + M4 - M5 + M7
              C12 = M3 + M5
              C21 = M2 + M4
              C22 = M1 - M2 + M3 + M6
            **/
            double [][] C11 = add(sub(add(M1, M4), M5), M7);
            double [][] C12 = add(M3, M5);
            double [][] C21 = add(M2, M4);
            double [][] C22 = add(sub(add(M1, M3), M2), M6);
 
            //объединение 4 половинок в одну матрицу результата
            join(C11, R, 0 , 0);
            join(C12, R, 0 , n/2);
            join(C21, R, n/2, 0);
            join(C22, R, n/2, n/2);
        }  
        return R;
    }
    // Функция вычитания двух матриц
    public double[][] sub(double[][] A, double[][] B)
    {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }
    // Функция сложения двух матриц
    public double[][] add(double[][] A, double[][] B)
    {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }
    // Функция разделения родительской матрицы на дочерние матрицы 
    public void split(double[][] P, double[][] C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }
    // Функция для присоединения родительской матрицы дочерних матриц
    public void join(double[][] C, double[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }    
    
    
    // 
    //---------------
    //
    
    //Определитель гаусс
    public double GaussDet(double[][] mas, int razm) 
    {
    	int kz = 0; // количество замен строк
    	double opr = 1;
 	   	for (int ns = 0; ns < razm; ns++)
 	   	{
 	   		double max = 0;
 	   		int max_ind = 0;
 	   		//Нахождение макс элемента
 	   		for (int i = ns; i < razm; i++)
 	   			if (Math.abs(mas[i][ns]) > Math.abs(max))
 	   			{
 	   				max = mas[i][ns];
 	   				max_ind = i;
 	   			}
 	   		//Обмен переменными
 	   		double tmp = 0;
	 	  
	 	   if (max_ind != ns)
	 	   {
	 		  
		 	   double [] tmp1;
		 	   tmp1 = mas[ns];
		 	   mas[ns] = mas[max_ind]; // меняем местами
		 	   mas[max_ind] = tmp1;
		 	   kz++;
	 	   }
	 	   //Деление максимального элемента
	 	   double tmp2 = 0;
	 	   tmp2 = mas[ns][ns];
	 	   for (int j = ns; j < razm; j++)
	 		   mas[ns][j] = mas[ns][j] / tmp2;
	 	   opr = opr * tmp2;
	 	   //Вычитание оставшихся строк
	 	   double mnog = 0;
	 	   for (int i = ns + 1; i < razm; i++)
	 	   {
	 		   mnog = mas[i][ns];
	 		   for (int j = 0; j < razm; j++)
	 			   mas[i][j] = mas[i][j] - mnog*mas[ns][j];
	 	   }
 	   }
 	  if (kz % 2 == 1)
 		 opr *= -1;
 	  
 	  return opr;
    }
    
    
    // 
    //---------------
    //
    public double[][] Degree(double[][] A, int st)
    {
        int n = A.length;
        double[][] B = new double[n][n];
        double[][] tmp = new double[n][n];
        double[][] Res = new double[n][n];
        String str =  Integer.toBinaryString(st);
        
        for(int i=0;i<n;i++)
        	Res[i][i]=1;
        
        if(st != 0)
        {
        	for(int i=0;i<n;i++)
        	for(int j=0;j<n;j++)
        		B[i][j] = A [i][j];
        
	        for(int i=str.length(); i > 0; i--)
	        {
	        	if(str.charAt(i-1) == '1')
	        	{
	        		for(int k=0;k<n;k++)
	                	for(int j=0;j<n;j++)
	                		tmp[k][j] = Res[k][j];
	        		Zeros(Res, n);
	        		
	        		for (int row = 0; row < n; row++) 
	    		        for (int col = 0; col < n; col++)
	    		            for (int inner = 0; inner < n; inner++) 
	    		            	Res[row][col] += tmp[row][inner] * B[inner][col];  
	        	}
	        	for(int k=0;k<n;k++)
                	for(int j=0;j<n;j++)
                		tmp[k][j] = B[k][j];
        		Zeros(B, n);
        		
        		 for (int row = 0; row < n; row++) 
        		        for (int col = 0; col < n; col++)
        		            for (int inner = 0; inner < n; inner++) 
        		                B[row][col] += tmp[row][inner] * tmp[inner][col];  
	        }
        }
        return Res;
        
    }
    
    //----------------------------------------------------------------------------------
    //
    //Алгоритмы для проверки
    
    //Умножение
    public double[][] fun1(double[][] A, double[][] B)
    {
    	 int n = A.length;
         double[][] R = new double[n][n];
         for(int i=0;i<n;i++)
        	 for(int j=0;j<n;j++)
        		 for(int k=0;k<n;k++)
        			 R[i][j] += A[i][k] * B[k][j];
         
         return R;
    }
    
    //Определитель
    public double fun2(double[][] A, int size)
    {
    	double det = 0;
    	if(size==1)
        {
                det=A[0][0];
        }
        if(size==2)
        {
                det=A[0][0]*A[1][1]-A[0][1]*A[1][0];
        }
        if(size==3)
        {
        	det = A[0][0] * A[1][1] * A[2][2] + A[0][1] * A[1][2] * A[2][0] + A[0][2] * A[1][0] * A[2][1];
        	det = det - A[0][1] * A[1][0] * A[2][2] - A[0][0] * A[1][2] * A[2][1] - A[0][2] * A[1][1] * A[2][0];
        	 
        }
    	return det;
    }
    
    //Матрица в степень
    public double[][] fun3(double[][] A, int st)
    {
    	int n = A.length;
        double[][] R = new double[n][n];
        if(st >= 2)
        {
        	R = fun1(A,A); // степень 2
	        for(int i=2;i<st;i++) //степень 3
	        	R = fun1(R,A);
	        return R;
        }
        else
        	return A;
        
    }
    
}

