package geekbrains.lesson4;

import java.util.Random;
import java.util.Scanner;

public class MainApp {
    static char[][] map;


    static Scanner scanner = new Scanner(System.in);
    static final char PLAYER_CELL = 'X';
    static final int CELLS_FOR_WIN = 3;
    static final char COMP_CELL = '0';
    static final char EMPTY_CELL = '*';
    static final int SIZE = 3;//5
    static Random random = new Random();

    public static void main(String[] args) {
        prepareMap();
        printMap();
        while (true){
            playerTurn();
            printMap();
            if (checkWin(PLAYER_CELL)){
                System.out.println("ПОБЕДА!");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья!");
                break;
            }
            compTurn();
            printMap();
            if (checkWin(COMP_CELL)){
                System.out.println("Вы проиграли!");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья!");
                break;
            }

        }
    }

    public static boolean checkWin(char dot){
        int count1 = 0, count2 = 0;

        for (int i = 0; i < SIZE; i++) {
            int col = 0, row = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == dot) row++;
                if (map[j][i] == dot) col++;
            }
            if (col == CELLS_FOR_WIN || row == CELLS_FOR_WIN) return true;
            if (map[i][i] == dot) count1++;
            if (map[i][map[i].length -i - 1] == dot) count2++;
            if (count1 == CELLS_FOR_WIN || count2 == CELLS_FOR_WIN) return true;
        }
 /*
        if((map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) ||
                (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot)) {
            return true;
        }
        for (int i = 0; i < map.length; i++) {
            if((map[i][0] == dot && map[i][1] == dot && map[i][2] == dot) ||
                    (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot)){
                return true;
            }
        }
*/
        return false;
    }

    public static void playerTurn(){
       int x, y;
       do{
           System.out.println("Сделайте Ваш ход в формате x, y");
           x = scanner.nextInt() - 1;
           y = scanner.nextInt() - 1;
       } while(!isCellEmpty(x,y));
       map[x][y] = PLAYER_CELL;
        System.out.printf("Вы походили %d, %d \n", x+1, y+1);
    }

    public static void compTurn(){
        int x, y;
        do{
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while(!isCellEmpty(x,y));
        map[x][y] = COMP_CELL;
        System.out.printf("Ход компьютера - %d %d \n", x+1, y+1);
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(isCellEmpty(i,j)) return false;
            }
        }
        return true;
    }

    public static boolean isCellEmpty(int x, int y) {
        if(x < 0 || y < 0 || x > SIZE || y > SIZE) {
            return false;
        }
        if(map[x][y] == EMPTY_CELL){
            return true;
        }
        return false;
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int y = 0; y < SIZE; y++) {
            System.out.print((y+1) + " ");
            for (int x= 0; x < SIZE; x++) {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static void prepareMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = EMPTY_CELL;
            }
        }
    }
}
