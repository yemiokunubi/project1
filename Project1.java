import java.util.*;
public class Project1 {
	public static double[][] classic(double a[][],double b[][]){
    double[][] result = new double[a.length][a.length];

    for(int i = 0; i <a.length; i++){
      for(int j = 0; j <a.length; j++){
        double sum = 0;
        for(int k = 0; k <a.length; k++){
          double mult = a[i][k] * b[k][j];
          sum = sum + mult;

        }
        result[i][j] = sum;

      }
    }
    return result;
    // System.out.println("result: ");
    // System.out.println(Arrays.deepToString(result));
		
	}
  //code only works if the combining step is outside of the recurrsion, 
  // i do not know why
  //addition for divide and conquer
  public static double[][] addition(double a[][],double b[][]){

    double[][] combined = new double[a.length][a.length];
    for(int i=0; i < a.length; i++){
      for(int j=0; j < a.length; j++){
        combined[i][j] = a[i][j] + b[i][j];

      }
    }
    return combined;

  }

  public static double[][] divideCon(double a[][],double b[][]){
    
    int n = a.length;
    double[][] result = new double[n][n];
    if(n == 1){
      result[0][0] = a[0][0]*b[0][0];
    }
    else{
      //creating the 4 quadrants
      double[][] a11 = new double[n/2][n/2];
      double[][] a12 = new double[n/2][n/2];
      double[][] a21 = new double[n/2][n/2];
      double[][] a22 = new double[n/2][n/2];

      //a11
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          a11[i][j] = a[i][j];
        }

      }
      //a12
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          a12[i][j]= a[i][j+n/2];
        }
      }
      //a21
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          a21[i][j] = a[i+n/2][j];
        }

      }
      //a22
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          a22[i][j]= a[i+n/2][j+n/2];
        }
      }
 
      double[][] b11 = new double[n/2][n/2];
      double[][] b12 = new double[n/2][n/2];
      double[][] b21 = new double[n/2][n/2];
      double[][] b22 = new double[n/2][n/2];

      //b11
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          b11[i][j] = b[i][j];
        }

      }
      //b12
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          b12[i][j]= b[i][j+n/2];
        }
      }
      //b21
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          b21[i][j] = b[i+n/2][j];
        }

      }
      //b22
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          b22[i][j]= b[i+n/2][j+n/2];
        }
      }

      double[][] c11 = new double[a11.length][a11.length];
      double[][] c12 = new double[a11.length][a11.length];
      double[][] c21 = new double[a11.length][a11.length];
      double[][] c22 = new double[a11.length][a11.length];
      
      //adding the recursive calls as they are called
      c11 = addition(divideCon(a11,b11),divideCon(a12,b21));
      c12 = addition(divideCon(a11,b12),divideCon(a12,b22));
      c21 = addition(divideCon(a21,b11),divideCon(a22,b21));
      c22 = addition(divideCon(a21,b12),divideCon(a22,b22));


      //inputing sectional values into the result array
      //c11
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          result[i][j] = c11[i][j];
        }

      }
      //c12
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          result[i][j+n/2]= c12[i][j];
        }
      }
      //c21
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          result[i+n/2][j] = c21[i][j];
        }

      }
      //c22
      for(int i = 0; i < n/2; i++){
        for(int j = 0; j < n/2; j++){
          result[i+n/2][j+n/2]= c22[i][j];
        }
      }

    }
    return result;

  }
  public static void main(String[] args) {
		//matrix is fixed in here
    double[][] a = {{5,2,6,1},{0,6,2,0},{3,8,1,4},{1,8,5,6}};
		double[][] b = {{7,5,8,0},{1,8,2,6},{9,4,3,8},{5,3,7,9}};
    double startTime;
    double endTime;
    System.out.println("matrix a is\n" + Arrays.deepToString(a).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    System.out.println("matrix b is\n" + Arrays.deepToString(b).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    double[][] classicRes = new double[a.length][a.length];
    double[][] divRes = new double[a.length][a.length];

    System.out.println("Classic multiplicaton result ");
    startTime = System.nanoTime();
    classicRes = classic(a,b);
    endTime = System.nanoTime();
    System.out.println(Arrays.deepToString(classicRes).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    System.out.println("Classic multiplication took " + (endTime-startTime)/1000000000 + " seconds");

    System.out.println("Naive Divide and Conquer result ");
    startTime = System.nanoTime();
    divRes = divideCon(a, b);
    endTime = System.nanoTime();
    System.out.println(Arrays.deepToString(divRes).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    System.out.println("Naive Divide and Conquer took " + (endTime-startTime)/1000000000 + " seconds");
  }
}