package data;

import java.util.ArrayList;
import java.util.Arrays;
import vector.Vector2;

public class TriangleGenerator {
  public static DataSet generate(int R) {
    int width = (int) Math.pow(2, R);
    int[][] pascalTriangle = new int[width][];
    for (int i = 0; i < pascalTriangle.length; i++)
    {
      pascalTriangle[i] = new int[width];
      Arrays.fill(pascalTriangle[i], 0);
      pascalTriangle[i][0] = 1;
      pascalTriangle[i][i] = 1;
    }

    for (int i = 1; i < pascalTriangle.length; i++)
      for (int j = 1; j < pascalTriangle[i].length; j++)
        pascalTriangle[i][j] = (pascalTriangle[i - 1][j - 1] + pascalTriangle[i - 1][j]) % 2;

    var objects = new ArrayList<Vector2>();
    for(int i = 0; i < width; i++)
      for(int j = 0; j < width; j++)
        if(pascalTriangle[i][j] == 1)
          objects.add(new Vector2(j, width - i - 1));
    return new DataSet(width, objects);
  }
}
