package oop;

import java.util.List;

public interface Table<T> {
    void setMatrix(T[][] matrix);

    Object[][] getAll();

    void addRow(Object[] row);

    void addCol(Object[] col);

    Object[] getRow(int i);

    Object[] getCol(int i);

    void removeRow(int n);

    void removeCol(int n);

    void filterRow(int stPos, int lastPos, int colNum, Object obj);

    void filterCol(int stPos, int lastPos, int colNum, Object obj);

    Table<Object> selectRows(int to, int in);

    Table<Object> selectCol(int to, int in);

    List<T> toList();

    void clean();

}
