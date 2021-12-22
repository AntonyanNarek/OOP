package oop;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SimpleTable<T> implements Table<T> {
    private Object[][] matrix;

    public SimpleTable(T[][] matrix) {
        this.matrix = matrix;
    }

    public SimpleTable() {
        this(null);
    }

    @Override
    public void setMatrix(Object[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public Object[][] getAll() {
        return this.matrix;
    }

    @Override
    public void addRow(Object[] row) {
        if(this.matrix != null) {
            Object[][] newMatrix = Arrays.copyOf(matrix, matrix.length + 1);
            newMatrix[newMatrix.length - 1] = row;
            this.matrix = newMatrix;
        } else {
            Object[][] newMatrix = new Object[1][];
            newMatrix[0] = row;
            this.matrix = newMatrix;
        }
    }

    @Override
    public void addCol(Object[] col) {
        if(this.matrix != null) {
            Object[][] newMatrix = Arrays.copyOf(matrix, matrix.length);
            for (int i = 0; i < col.length; i++) {
                newMatrix[i] = Arrays.copyOf(matrix[i], matrix[i].length + 1);
                newMatrix[i][newMatrix[i].length - 1] = col[i];
                this.matrix = newMatrix;
            }
        } else {
            Object[][] newMatrix = new Object[col.length][1];
            for (int i = 0; i < col.length; i++){
                newMatrix[i][0] = col[i];
            }
            this.matrix = newMatrix;
        }
    }

    @Override
    public Object[] getRow(int i) {
        return this.matrix[i];
    }

    @Override
    public Object[] getCol(int i) {
        List<Object> list = new LinkedList<>();
        for (Object[] objects : matrix) {
            for (int k = 0; k < matrix[i].length; k++) {
                if (i == k) list.add(objects[i]);
            }
        }
        return new Object[]{list};
    }

    @Override
    public void removeRow(int n){
        Table<Object> table = new SimpleTable<>();
        for (int i = 0; i < this.matrix.length; i++){
            if (i != n){
                table.addRow(matrix[i]);
            }
        }
        this.matrix = table.getAll();
    }

    @Override
    public void removeCol(int n){
        Table<Object> table = new SimpleTable<>();
        for (int i = 0; i < this.matrix[0].length; i++){
            if (i != n){
                Object[] arr = new Object[getAll().length];
                for (int j = 0; j < getAll().length; j++){
                    arr[j] = this.matrix[j][i];
                }
                table.addCol(arr);
            }
        }
        this.matrix = table.getAll();
    }

    @Override
    public void filterRow(int stPos, int lastPos, int colNum, Object obj){
        Table<Object> table = new SimpleTable<>();
        for (int i = stPos; i < lastPos; i++){
            if (matrix[i][colNum] == (Integer) Integer.parseInt((String) obj) || matrix[i][colNum] == obj){
                table.addRow(matrix[i]);
            }
        }
        this.matrix = table.getAll();
    }

    @Override
    public void filterCol (int stPos, int lastPos, int rowNum, Object obj){
        Table<Object> table = new SimpleTable<>();
        for (int i = stPos; i < lastPos; i++){
            if (matrix[rowNum][i] == (Integer) Integer.parseInt((String) obj) || matrix[rowNum][i] == obj){
                Object[] arr = new Object[getAll().length];
                for (int j = 0; j < getAll().length; j++){
                    arr[j] = this.matrix[j][i];
                }
                table.addCol(arr);
            }
        }
        this.matrix = table.getAll();
    }

    @Override
    public Table<Object> selectRows(int to, int in) {
        int n = Math.abs(to - in) + 1;
        int m = 0;
        Object[][] oMatrix = new Object[n][];
        for (int i = to; i <= in; i++) {
            oMatrix[m] = matrix[i];
            m++;
        }
        Table<Object> newTable = new SimpleTable<>();
        newTable.setMatrix(oMatrix);
        return newTable;
    }

    @Override
    public Table<Object> selectCol(int to, int in) {
        Table<Object> newTable = new SimpleTable<>();
        for (int i = to; i < in; i++) {
           newTable.addCol(getCol(i));
        }
        return newTable;
    }

    @Override
    public List<T> toList() {
        List<T> oList = new LinkedList<T>();
        for (Object[] objects : matrix) {
            for (Object object : objects) {
                oList.add((T) object);
            }
        }
        return oList;
    }


    @Override
    public String toString() {
        String[] arr = new String[matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            arr[i] = Arrays.toString(matrix[i]);
        }
        return Arrays.toString(arr);

    }

    @Override
    public void clean() {
        this.matrix = null;
    }
}
