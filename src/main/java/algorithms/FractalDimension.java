package algorithms;

import data.DataSet;
import java.util.Arrays;

public class FractalDimension {

  /**
   *
   * @param data
   *  набор данных, по которому вычисляется фрактальная размерность
   * @param R
   *  число узлов, по которым будет вычислена фрактальная размерность
   * @return
   *  значение фрактальной размерности
   */
  public static double FDAlgorithm (DataSet data, int R) {
    double[][] plot = new double[R - 1][2]; // S(R)
    for(int i = 0; i < R - 1; i++)
      plot[i][1] = 0;

    for(int j = 1; j <= R - 1; j++) {
      int r = (int)(Math.pow(2, j));
      var occupancies = new int[r][r];
      for(int i = 0; i < r; i++)
        Arrays.fill(occupancies[i], 0);
      int gridSize = data.fieldSize / r;

      for(var point : data.objects)
        occupancies[point.y / gridSize][point.x / gridSize]++;

      for(int i = 0; i < r; i++) {
        for(int k = 0; k < r; k++) {
          plot[j - 1][1] += Math.pow(occupancies[i][k], 2);
        }
      }
      plot[j - 1][0] = (double)r / data.fieldSize;
    }


    double D = 0;
    for(int i = 1; i < R - 1; i++) {
      System.out.println("\n" + Arrays.toString(plot[i]));
      System.out.println(Math.log(plot[i][1]));
      System.out.println(Math.log(1 / plot[i][0]));
      System.out.println();
      D += (Math.log(plot[i][1]) - Math.log(plot[i - 1][1])) / (Math.log(1 / plot[i][0]) - Math.log(1 / plot[i - 1][0]));
    }
    D /= (R - 2);

    return D;
  }
}