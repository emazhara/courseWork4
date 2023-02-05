package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import vector.Vector2;

public class CarpetGenerator {
  public static DataSet generate(int R) {
    int size = (int) Math.pow(2, R);
    var field = new int[size][size];
    for(int i = 0; i < size; i++) {
      Arrays.fill(field[i], 1);
    }
    int sqSize = size;
    sqSize += R % 2 == 0? -1 : -2;

    getCarpet(sqSize, field, new Vector2(0,0), new Vector2(size, size));

    var objects = new ArrayList<Vector2>();
    for(int i = 0; i < size; i++)
      for(int j = 0; j < size; j++)
        if(field[i][j] == 1)
          objects.add(new Vector2(j, size - i - 1));
    return new DataSet(size, objects);
  }
  private static void getCarpet(int sqSize, int[][] array, Vector2 pnt1, Vector2 pnt2) {
    if (sqSize <= 1)
      return;
    sqSize /= 3;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        var newpnt1 = new Vector2(pnt1.x + j * sqSize, pnt1.y + i * sqSize);
        var newpnt2 = new Vector2(pnt2.x + (j - 2) * sqSize, pnt2.y + (i - 2) * sqSize);
        if (i == 1 && j == 1)
          eraseArea(newpnt1, newpnt2, array);
        else
          getCarpet(sqSize, array, newpnt1, newpnt2);
      }
    }
  }
  private static void eraseArea(Vector2 pnt1, Vector2 pnt2, int[][] array) {
    for(int i = pnt1.y; i < pnt2.y; i++) {
      for(int j = pnt1.x; j < pnt2.x; j++) {
        array[i][j] = 0;
      }
    }
  }
}
