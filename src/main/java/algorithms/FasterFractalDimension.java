package algorithms;

import data.DataSet;
import java.util.Arrays;

public class FasterFractalDimension {
  public static double FFDAlgorithm(DataSet data, int R) {
    double[][] plot = new double[R][2];

    int r = (int)(Math.pow(2, R));
    var occupancies = new int[r][r];
    var gridSize =  data.fieldSize / r;
    for(var point : data.objects)
      occupancies[point.y / gridSize][point.x / gridSize]++;
    double sum = 0;
    for(int i = 0; i < R; i++)
      for(int j = 0; j < R; j++)
        sum += Math.pow(occupancies[i][j], 2);
    plot[R - 1][1] = sum;
    plot[R - 1][0] = R;

    for(int j = R - 1; j > 0; j--) {
      r = (int) Math.pow(2, j);
      var newOccupancies = new int[r][r];
      sum = 0;
      for(int i = 0; i < r; i++) {
        for(int k = 0; k < r; k++) {
          newOccupancies[i][k]
              = occupancies[2 * i][2 * k]
              + occupancies[2 * i][2 * k + 1]
              + occupancies[2 * i + 1][2 * k]
              + occupancies[2 * i + 1][2 * k + 1];
          sum += Math.pow(newOccupancies[i][k], 2);
        }
      }
      plot[j - 1][1] = sum;
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

    return -D;
  }
}
