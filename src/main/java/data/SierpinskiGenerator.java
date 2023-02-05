package data;

import java.util.ArrayList;
import java.util.Arrays;
import vector.Vector2;

public class SierpinskiGenerator {
  public static DataSet generate(int fieldSize) {
    var field = new int[fieldSize][fieldSize];
    for(int i = 0; i < fieldSize; i++) {
      Arrays.fill(field[i], 0);
      for(int j = 0; j < fieldSize; j++) {
        if(i + Math.abs(fieldSize / 2 - j) <= fieldSize / 2)
          field[i][j] = 1;
      }
    }
    for(int i = 0; i < fieldSize / 2; i++) {
      for(int j = 0; j < fieldSize; j++) {
        if((Math.abs(fieldSize / 4 - i) + Math.abs(fieldSize / 2 - j) <= fieldSize / 4) && (i < fieldSize / 4))
          field[i][j] = 0;
      }
    }

    var objects = new ArrayList<Vector2>();
    for(int i = 0; i < fieldSize; i++)
      for(int j = 0; j < fieldSize; j++)
        if(field[i][j] == 1)
          objects.add(new Vector2(j, i));
    return new DataSet(fieldSize, objects);
  }
}
