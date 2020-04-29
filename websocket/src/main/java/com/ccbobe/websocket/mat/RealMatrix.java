package com.ccbobe.websocket.mat;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;
import org.ujmp.core.calculation.Calculation;
import org.ujmp.core.util.matrices.MatrixLibraries;

/**
 * @author cc
 */
public class RealMatrix {

    public static void main(String[] args) {

        //createMatrix();
        createMat();
    }

    public  static void createMatrix(){
        //创建矩阵
        Matrix matrix = DenseMatrix.Factory.zeros(4,4);
        //   System.out.println(matrix);
        matrix.setAsDouble(1,0,0);
        matrix.setAsDouble(1,1,1);
        matrix.setAsDouble(3,3,3);
        // System.out.println(matrix.toString());
        Matrix clone = matrix.clone();
        System.out.println(clone);
    }

    public  static void createMat(){
        double[][] array  ={{2,2},{2,0}};
        Matrix matrix = DenseMatrix.Factory.importFromArray(array);
        System.out.println(matrix);
        System.out.println(matrix.inv());
        //矩阵转置
        Matrix transpose = matrix.transpose();
        Calculation.Ret ret =Calculation.LINK;
        Matrix exp = matrix.exp(ret);

        double det = matrix.det();
        System.out.println(det);

        System.out.println(exp);

    }

}
