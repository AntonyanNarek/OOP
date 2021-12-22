import oop.SimpleTable;
import oop.Table;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Object[][] mat = {{1, 2, "a", "s" }, { 0, "b", 5, 7}, {0, 3, 6, "z"}, {7, 7, 7, 7}, {8, 8, 8, 8}};
        Table<Object> table = new SimpleTable<>();
        table.setMatrix(mat);
        GUI gui = new GUI();
        gui.print(table);
        gui.groupCol(table);
        Scanner scanner = new Scanner(System.in);
        String line = "";
        while (!line.equals("/end")) {
            System.out.println("Выберите действие: (/addRow - добавить строку, /addCol - добавить столбец, /fRow - отфильтровать по строке, /fCol отфильтровать по столбцу,  /end - завершить программу)");
            line = scanner.nextLine();
            switch (line){
                case "/addRow" -> gui.addRow(table);
                case "/addCol" -> gui.addCol(table);
                case "/removeRow" -> gui.removeRow(table);
                case "/removeCol" -> gui.removeCol(table);
                case "/fRow" -> gui.filterRow(table);
                case "/fCol" -> gui.filterCol(table);
            }
        }
    }
}
