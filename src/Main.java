import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Main {

    static int turn = 0;
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
            {"-","-"},
            {"-","-","-"}
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
        while(turn <= 8) {
            System.out.println("-----------Bowling Score Calculator-----------");
            for(int i = 0; i < scoreboard.length; i++) {
                System.out.println("Frame " + (i+1) + ": [" + scoreboard[i][0] + "] [" + scoreboard[i][1] + "]");
            }
            System.out.println("current total score: " + score);
            System.out.println("please type your scores for each throw");
            System.out.println("type 10 for strikes and spares (even if less pins than 10 were hit for spare)");
            System.out.println("Score this throw: ");
            System.out.println("turn: " + turn + " || throwNum: " + throwNum);
            int currentThrow = scanner.nextInt();
            Main.scoreCalc(currentThrow);
        }
    }

    public static void scoreCalc(int currentThrow) {
        Scanner scanner = new Scanner(System.in);
        if (currentThrow == 10) {
            if (throwNum == 1) {
                scoreboard[turn][1] = "/";
                turn++;
                throwNum = 0;
            } else if (throwNum == 0) {
                scoreboard[turn][1] = "X";
                if (turn >= 2) {
                    if (scoreboard[turn - 1][1] == "X" && scoreboard[turn - 2][1] == "X") {
                        score += 30;
                    }
                }
                turn++;
            }
        } else if (currentThrow < 10 && currentThrow >= 0) {
            scoreboard[turn][throwNum] = String.valueOf(currentThrow);
            if (throwNum == 0) {
                if (turn > 0) {
                    if (scoreboard[turn - 1][1] == "/") {
                        score += 10 + currentThrow;
                    }
                }
                throwNum = 1;
            } else if (throwNum == 1) {
                int frameTotal = currentThrow + Integer.parseInt(scoreboard[turn][0]);
                score += frameTotal;
                if (turn > 0) {
                    if (scoreboard[turn - 1][1] == "X") {
                        score += 10 + frameTotal;
                        if (turn >= 2) {
                            if (scoreboard[turn - 2][1] == "X") {
                                score += 20 + frameTotal;
                            }
                        }
                    }
                }
                turn++;
                throwNum = 0;
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
