package main;

import static java.lang.Math.log;

import algorithms.FasterFractalDimension;
import algorithms.FractalDimension;
import data.CarpetGenerator;
import data.DataSet;
import data.SierpinskiGenerator;
import data.TriangleGenerator;
import vector.Vector2;

public class MainClass {
  public static void main(String[] args) throws Exception {
//    var ds = new DataSet(16);
//    ds.addElement(new Vector2(1, 1));
//    ds.displayDataSet();
//    //System.out.println(FractalDimension.FDAlgorithm(ds, 4));
//    var newds = ds.getSubDataSet(new Vector2(2, 3), new Vector2(6, 7));
//    newds.displayDataSet();
//    //System.out.println(FractalDimension.FDAlgorithm(newds, 2));;
    int R = 5;
    var s = TriangleGenerator.generate(R);
    //s.displayDataSet();
    System.out.println("РЕЗУЛЬТАТЫ ДЛЯ ТРЕУГОЛЬНИКА");
    var time0 = System.nanoTime();
    double fd = FractalDimension.FDAlgorithm(s, (int) R);
    var time1 = System.nanoTime();
    double ffd = FasterFractalDimension.FFDAlgorithm(s, (int) R);
    var time2 = System.nanoTime();
    System.out.println(fd);
    System.out.println(fd - Math.log(3) / Math.log(2));
    System.out.println(time1-time0);
    System.out.println("________________________________________");
    System.out.println(ffd);
    System.out.println(ffd - Math.log(3) / Math.log(2));
    System.out.println(time2-time1);

    var c = CarpetGenerator.generate(R);
    c.displayDataSet();
  }
}
