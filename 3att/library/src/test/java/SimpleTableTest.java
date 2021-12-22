import oop.SimpleTable;
import oop.Table;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SimpleTableTest {

    @Test
    void addRow() {
        Table<Object> actual = new SimpleTable<>();
        Object[][] actualM = {
                {0, 1, 2, 7},
                {3, 4, 5, 7},
        };
        actual.setMatrix(actualM);
        actual.addRow(new Object[]{6, 7, 8, 7});
        Object[][] expectedM = {
                {0, 1, 2, 7},
                {3, 4, 5, 7},
                {6, 7, 8, 7}
        };
        for (int i = 0; i < expectedM.length; i++) {
            for (int j = 0;  j < expectedM[0].length; j++) {
                Assertions.assertEquals(actual.getAll()[i][j], expectedM[i][j]);
            }
        }
    }

    @Test
    void addCol() {
        Table<Object> actual = new SimpleTable<>();
        Object[][] actualM = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        };
        actual.setMatrix(actualM);
        actual.addCol(new Object[]{7, 7, 7});
        Object[][] expectedM = {
                {0, 1, 2, 7},
                {3, 4, 5, 7},
                {6, 7, 8, 7}
        };
        if (expectedM.length == actual.getAll().length) {
            for (int i = 0; i < expectedM.length; i++) {
                for (int j = 0; j < expectedM[0].length; j++) {
                    Assertions.assertEquals(actual.getAll()[i][j], expectedM[i][j]);
                }
            }
        }
    }

    @Test
    void filterRow() {
        Table<Object> actual = new SimpleTable<>();
        Object[][] actualM = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 2}
        };
        actual.setMatrix(actualM);
        actual.filterRow(0, 2, 2, "2");
        Object[][] expectedM = {
                {0, 1, 2},
                {6, 7, 2}
        };
        if (expectedM.length == actual.getAll().length) {
            for (int i = 0; i < expectedM.length; i++) {
                for (int j = 0; j < expectedM[0].length; j++) {
                    Assertions.assertEquals(actual.getAll()[i][j], expectedM[i][j]);
                }
            }
        }
    }

    @Test
    void filterCol() {
        Table<Object> actual = new SimpleTable<>();
        Object[][] actualM = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 6}
        };
        actual.setMatrix(actualM);
        actual.filterCol(0, 3, 2, "6");
        Object[][] expectedM = {
                {0, 2},
                {3, 5},
                {6, 6}
        };
        if (expectedM.length == actual.getAll().length) {
            for (int i = 0; i < expectedM.length; i++) {
                for (int j = 0; j < expectedM[0].length; j++) {
                    Assertions.assertEquals(actual.getAll()[i][j], expectedM[i][j]);
                }
            }
        }
    }

    @Test
    void getRow(){
        Table<Object> actual = new SimpleTable<>();
        Object[][] actualM = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 6}
        };
        actual.setMatrix(actualM);
        for (int i = 0; i < actualM.length; i++) {
            for (int j = 0; j < actualM[i].length; j++) {
                Assertions.assertEquals(actual.getRow(i)[j], actualM[i][j]);
            }
        }
    }


    @Test
    void removeRow(){
        Table<Object> actual = new SimpleTable<>();
        Object[][] actualM = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 6}
        };
        actual.setMatrix(actualM);
        actual.removeRow(1);
        Object[][] expectedM = {
                {0, 1, 2},
                {6, 7, 6}
        };
        for (int i = 0; i < expectedM.length; i++) {
            for (int j = 0; j < expectedM[i].length; j++) {
                Assertions.assertEquals(actual.getAll()[i][j], expectedM[i][j]);
            }
        }
    }

    @Test
    void removeCol(){
        Table<Object> actual = new SimpleTable<>();
        Object[][] actualM = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 6}
        };
        actual.setMatrix(actualM);
        actual.removeCol(1);
        Object[][] expectedM = {
                {0, 2},
                {3, 5},
                {6, 6}
        };
        for (int i = 0; i < expectedM.length; i++) {
            for (int j = 0; j < expectedM[i].length; j++) {
                Assertions.assertEquals(actual.getAll()[i][j], expectedM[i][j]);
            }
        }
    }

    @Test
    void clean(){
        Table<Object> actual = new SimpleTable<>();
        Object[][] actualM = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 6}
        };
        actual.setMatrix(actualM);
        actual.clean();
        Assertions.assertNull(actual.getAll());
    }


}