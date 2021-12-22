import oop.Table;

import java.util.Arrays;
import java.util.Scanner;

public class GUI {

    public void print(Table<Object> table){
        for (int i = 0; i < table.getAll().length; i++) {
            System.out.println(Arrays.toString(table.getRow(i)));
        }
    }

    public void addRow(Table<Object> table){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значения строки через пробел: ");
        String line = scanner.nextLine();
        Object[] objects = line.split(" ");
        table.addRow(objects);
        print(table);
    }

    public void addCol(Table<Object> table){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значения строки через пробел: ");
        String line = scanner.nextLine();
        Object[] objects = line.split(" ");
        table.addCol(objects);
        print(table);
    }


    public void removeRow(Table<Object> table){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер строки которую желаете удалить: ");
        int n = scanner.nextInt();
        table.removeRow(n);
        print(table);
    }

    public void removeCol(Table<Object> table){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер столбца который желаете удалить: ");
        int n = scanner.nextInt();
        table.removeCol(n);
        print(table);
    }

    public void filterRow(Table<Object> table){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение для выборки: ");
        Object obj = scanner.nextLine();
        System.out.println("Введите диапазон выборки (значение 'от' и значение 'до' через пробел): ");
        String line = scanner.nextLine();
        String[] zone = line.split(" ");
        System.out.println("Выберите номер столбца, по которому будет происходить выборка");
        int a = scanner.nextInt();
        table.filterRow(Integer.parseInt(zone[0]) - 1, Integer.parseInt(zone[1]), a - 1, obj);
        print(table);
    }

    public void filterCol(Table<Object> table){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение для выборки: ");
        Object obj = scanner.nextLine();
        System.out.println("Введите диапазон выборки (значение 'от' и значение 'до' через пробел): ");
        String line = scanner.nextLine();
        String[] zone = line.split(" ");
        System.out.println("Выберите номер строки, по которому будет происходить выборка");
        int a = scanner.nextInt();
        table.filterCol(Integer.parseInt(zone[0]) - 1, Integer.parseInt(zone[1]), a - 1, obj);
        print(table);
    }

    public void groupRow(Table<Object> table){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строки для группировки (значение 'от' и значение 'до' через пробел): ");
        String line = scanner.nextLine();
        String[] zone = line.split(" ");
        int a = Integer.parseInt(zone[0]);
        int b = Integer.parseInt(zone[1]);
        Table<Object> newTable = table.selectRows(a - 1, b - 1);
        for (int i = a - 1; i < b; i++){
            table.removeRow(a - 1);
        }
        for (int i = 0; i < table.getAll().length; i++) {
            System.out.println(Arrays.toString(table.getRow(i)));
            if (i == a - 2){
                System.out.println(newTable);
            }
        }
    }

    public void groupCol(Table<Object> table){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строки для группировки (значение 'от' и значение 'до' через пробел): ");
        String line = scanner.nextLine();
        String[] zone = line.split(" ");
        int a = Integer.parseInt(zone[0]);
        int b = Integer.parseInt(zone[1]);
        Table<Object> newTable = table.selectCol(a - 1, b - 1);
        for (int i = a - 1; i < b; i++){
            table.removeCol(a - 1);
        }
        for (int i = 0; i < table.getAll().length; i++) {
            System.out.print(Arrays.toString(table.getRow(i)) + " ");
            System.out.println(Arrays.toString(newTable.getRow(i)));
        }

    }
}
