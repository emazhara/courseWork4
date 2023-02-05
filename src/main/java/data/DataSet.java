package data;

import java.util.ArrayList;
import java.util.Arrays;
import vector.Vector2;

public class DataSet {
  private final int euclidDimension = 2;
  public ArrayList<Vector2> objects;
  public int fieldSize;
  private int[][] field;

  /**
   * конструктор пустого датасета на множестве заданного размера
   * @param fieldSize
   *  размер поля (двумерного множества, на котором расположены элементы датасета)
   */
  public DataSet(int fieldSize) {
    this.fieldSize = fieldSize;
    this.field = new int[fieldSize][fieldSize];
    for(int i = 0; i < fieldSize; i++) Arrays.fill(this.field[i], 0);
    this.objects = new ArrayList<>();
  }

  /**
   * конструктор датасета по заданному множеству объектов
   * @param fieldSize
   *  размер поля (двумерного множества, на котором расположены элементы датасета)
   * @param objects
   *  список координат элементов, по которым строится датасет
   */
  public DataSet(int fieldSize, ArrayList<Vector2> objects) {
    this.fieldSize = fieldSize;
    this.field = new int[fieldSize][fieldSize];
    for(int i = 0; i < fieldSize; i++) Arrays.fill(this.field[i], 0);
    this.objects = objects;
    for(var object : objects) this.field[fieldSize - object.y - 1][object.x] = 1;
  }

  /**
   * метод позволяет добавить элемент в датасет
   * @param position
   *  координаты добавляемого элемента
   */
  public void addElement(Vector2 position) {
    objects.add(position);
    field[fieldSize - position.y - 1][position.x] = 1;
  }

  /**
   *
   * @param pnt1
   *  верхний левый угол квадрата, ограничивающего новое подмножество
   * @param pnt2
   *  нижний правый угол квадрата, ограничивающего новое подмножество
   * @return
   *  возвращает датасет, ограниченный заданным квадратом
   */
  public DataSet getSubDataSet(Vector2 pnt1, Vector2 pnt2) throws Exception {
    if((pnt2.x - pnt1.x) != (pnt2.y - pnt1.y))
      throw new Exception("Попытка получить сабдатасет, ограниченный не квадратом\n");
    var newObjects = new ArrayList<Vector2>();
    for(var object : objects) {
      if((object.x >= pnt1.x)
          && (object.x <= pnt2.x)
          && (object.y <= pnt1.y)
          && (object.y >= pnt2.y))
        newObjects.add(object);
    }
    return new DataSet(pnt2.x - pnt1.x, newObjects);
  }

  /**
   * визуализация датасета в консоль
   */
  public void displayDataSet() {
    System.out.println("\n\nDataset:");

    for(int i = 0; i < 2 * fieldSize + 3; i++) System.out.print("_");
    System.out.println();
    for(int i = 0; i < fieldSize; i++) {
      System.out.print("| ");
      for(int j = 0; j < fieldSize; j++)
        System.out.print(field[i][j] == 1 ? "#" : " ");
      System.out.print("|\n");
    }
    for(int i = 0; i < 2 * fieldSize + 3; i++) System.out.print("_");
  }
}
