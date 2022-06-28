import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Main {

    static int turn = 1;
    static int throwNum = 0;
    static int score = 0;
    static String[][] scoreboard = {
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"},
            {"-","-"}
    };

    public static void main(String[] args) {
        while (turn < 10) {
            Main.bowl();
        }
        if(turn == 10) {
            System.out.println("-----------end of game-----------");
            System.out.println("final score: " + score);
        }
    }

    public static void bowl() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------Bowling Score Calculator-----------");
        for(int i = 0; i < scoreboard.length; i++) {
            System.out.println("Frame " + (i+1) + ": [" + scoreboard[i][0] + "] [" + scoreboard[i][1] + "]");
        }
        System.out.println("current total score: " + score);
        System.out.println("please type your scores for each throw");
        System.out.println("type 10 for strikes and spares (even if less pins than 10 were hit for spare)");
        System.out.println("Score this throw: ");
        int currentThrow = scanner.nextInt();
        Main.scoreCalc(currentThrow);
    }

    public static void scoreCalc(int currentThrow) {
        Scanner scanner = new Scanner(System.in);
        if (currentThrow == 10 && throwNum == 0) {
            scoreboard[turn][0] = "-";
            scoreboard[turn][1] = "X";
            if(scoreboard[turn - 1][1] == "X" && scoreboard[turn - 2][1] == "X") {
                score += 30;
            }
            throwNum ++;
            turn++;
        } else if (currentThrow == 10 && throwNum == 1) {
            scoreboard[turn][1] = "/";
            if(scoreboard[turn - 1][1] == "X" && scoreboard[turn - 2][1] == "X") {
                score += 30;
            }
            throwNum --;
        } else if (currentThrow < 10 && currentThrow >= 0) {
            if(throwNum == 0) {
                if (scoreboard[turn - 1][1] == "/") {
                    score += 10 + currentThrow;
                }
                scoreboard[turn][0] = String.valueOf(currentThrow);
                throwNum ++;
            } else {
                int frameScore = currentThrow + parseInt(scoreboard[turn][0]);
                if(scoreboard[turn - 1][1] == "X") {
                    if(scoreboard[turn - 2][1] == "X") {
                        score += 30 + (frameScore * 2);
                    } else {
                        score += 10 + (frameScore * 2);
                    }
                } else {
                    score += frameScore;
                }
                scoreboard[turn][1] = String.valueOf(currentThrow);
                turn++;
            }
        } else {
            System.out.println("---------not a valid score---------");
            System.out.println("please type your scores for each throw");
            System.out.println("type 10 for strikes and spares");
            System.out.println("Score this throw: ");
            scoreCalc(scanner.nextInt());
        }
    }
}
