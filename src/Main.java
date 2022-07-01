import java.util.Objects;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Main {

    static int turn = 0;
    static int throwNum = 0;
    static String[][] scoreboard = {
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-","-"}
    };

    public static void main(String[] args) {
        while (turn <= 9) {
            Main.bowl();
        }
        if(turn == 10) {
            int currentScore = scoreCalc(scoreboard);
            System.out.println("-*-*-*-*-*-*-*-*-end of game-*-*-*-*-*-*-*-*-");
            for(int i = 0; i < scoreboard.length-1; i++) {
                System.out.println("Frame " + (i+1) + ": [" + scoreboard[i][0] + "] [" + scoreboard[i][1] + "]");
            }
            System.out.println("Frame 10: [" + scoreboard[9][0] + "] [" + scoreboard[9][1] + "] [" + scoreboard[9][2] + "]");
            System.out.println("final score: " + currentScore);
        }
    }

    public static void bowl() {
        Scanner scanner = new Scanner(System.in);
        int currentScore = scoreCalc(scoreboard);
        System.out.println("-----------Bowling Score Calculator-----------");
        for(int i = 0; i < scoreboard.length-1; i++) {
            System.out.println("Frame " + (i+1) + ": [" + scoreboard[i][0] + "] [" + scoreboard[i][1] + "]");
        }
        System.out.println("Frame 10: [" + scoreboard[9][0] + "] [" + scoreboard[9][1] + "] [" + scoreboard[9][2] + "]");
        System.out.println("current total score: " + currentScore);
        System.out.println("please type your scores for each throw");
        System.out.println("type 10 for strikes and spares (even if less pins than 10 were hit for spare)");
        System.out.println("Score this throw: ");
        System.out.println("turn: " + turn + " || throwNum: " + throwNum);
        int currentThrow = scanner.nextInt();
        Main.scoreCalc(currentThrow);
    }

    public static void scoreCalc(int currentThrow) {
        Scanner scanner = new Scanner(System.in);
        if (throwNum == 0) {
            if (currentThrow == 10) {
                scoreboard[turn][1] = "X";
                turn++;
                throwNum++;
            } else if (currentThrow > 0 && currentThrow < 10){
                scoreboard[turn][0] = String.valueOf(currentThrow);
                throwNum++;
            } else {
                System.out.println("---------not a valid score---------");
                System.out.println("please type your scores for each throw");
                System.out.println("type 10 for strikes and spares");
                System.out.println("Score this throw: ");
                scoreCalc(scanner.nextInt());
            }
        } else {
            if (currentThrow == 10) {
                scoreboard[turn][1] = "/";
                throwNum--;
                turn++;
            }else if (currentThrow + Integer.parseInt(scoreboard[turn][0]) > 0 && currentThrow + Integer.parseInt(scoreboard[turn][0]) < 10) {
                scoreboard[turn][1] = String.valueOf(currentThrow);
                throwNum--;
                turn ++;
            } else {
                System.out.println("---------not a valid score---------");
                System.out.println("please type your scores for each throw");
                System.out.println("type 10 for strikes and spares");
                System.out.println("Score this throw: ");
                scoreCalc(scanner.nextInt());
            }
        }
    }

    public static int scoreCalc(String[][] scoreboard) {
        int score = 0;
            for(int i = 0; i < 9; i++){
                if(Objects.equals(scoreboard[i][1], "X")) {
                    score += 10;
                    if(Objects.equals(scoreboard[i-1][1], "X")) {
                        score += 10;
                        if(Objects.equals(scoreboard[i-1][1], "X")) {
                            score += 10;
                        }
                    }
                } else if(Objects.equals(scoreboard[i][1], "/")) {
                    score += 10;
                } else {
                    if (Objects.equals(scoreboard[i-1][1], "/")) {
                        score += Integer.parseInt(scoreboard[i][0]);
                    }
                    score += Integer.parseInt(scoreboard[i][0]) + Integer.parseInt(scoreboard[i][1]);
                }
            }

            for(int i = 0; i < 3; i++) {
               if(Objects.equals(scoreboard[9][i], "X")) {
                   score += 10;
               } else if(Objects.equals(scoreboard[9][i], "/")) {
                   score += 10 - Integer.parseInt(scoreboard[9][i-1]);
               } else {
                   score += Integer.parseInt(scoreboard[9][i]);
               }
            }
        return score;
    }
}
