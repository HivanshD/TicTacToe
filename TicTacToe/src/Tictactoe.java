import java.util.Scanner;
public class Tictactoe {
    public static void main(String[] args) {
        int n=0;
        int turn=1;
        int x=0,y=0;
        Scanner input = new Scanner(System.in);
        String move=" ";
        boolean validMove= false, gameOver=false;
        while(n<3 || (n%2!=1)){
            System.out.println("Enter the dimension N of the tic tac toe board that is odd and >= 3 ");
            n= input.nextInt();
            input.nextLine();
        }
        char[][] grid= new char[n][n];
        printBoard(grid);

        System.out.println("Player 1 is X, and Player 2 is O.");
        while(isFull(grid)!=true && (gameOver!=true)){
            while(validMove!= true){
                System.out.println("Player"+whoseTurn(turn)+"'s turn. Enter the coordinates for your move: ");
                move= input.nextLine();
                int[] coordinates= moveRegister(move, x, y);
                x=coordinates[0];
                y=coordinates[1];
                validMove=isValid(grid,x,y);
                if(validMove){
                    if(whoseTurn(turn)==1){
                        grid[x][y]= 'X';
                    }
                    else{
                        grid[x][y]= 'O';
                    }
                }
            }
            printBoard(grid);
            gameOver= isGameOver(grid, turn);
            turn++;
            validMove=false;
        }
        if(gameOver!=true){
            System.out.println("Game ended in a draw.");
        }

    }

    public static void printBoard(char[][] a){
        for(int i=0; i< a.length; i++){
            for(int j=0; j< a[i].length; j++){
                System.out.print("|");
                if(a[i][j]!= '\0'){
                    System.out.print(a[i][j]);
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }

    }

    public static boolean isFull(char[][] a){
        boolean x=true;
        for(int i=0; i< a.length; i++){
            for(int j=0; j< a[i].length; j++){
                if(a[i][j]== '\0'){
                    x=false;
                }
            }
        }
        return x;
    }

    public static int whoseTurn(int a){
        if(a%2 ==0){
            return 2;
        }
        else
            return 1;
    }

    public static int[] moveRegister(String a, int xPos, int yPos){
        int i=0;
        char x= '\0';
        if(i<a.length()){
            x= a.charAt(i);
        }
        while(x != '('){
            i++;
            if(i<a.length()){
                x= a.charAt(i);
            }
        }
        while(x < '0' || x > '9'){
            i++;
            if(i<a.length()){
                x= a.charAt(i);
            }
        }
        xPos= ((char)x- '0');
        i++;
        if(i<a.length()){
            x= a.charAt(i);
        }
        while(x != ','){
            i++;
            if(i<a.length()){
                x= a.charAt(i);
            }
        }
        while(x < '0' || x > '9'){
            i++;
            if(i<a.length()){
                x= a.charAt(i);
            }
        }
        yPos= x- '0';
        int[] out= {xPos,yPos};

        return out;
    }

    public static boolean isValid(char[][] a, int x, int y){
        boolean runningoutofnames= true;
        if(x>= a.length || y>= a[0].length){
            runningoutofnames=false;
            System.out.println("Invalid coordinate.");
        }
        else{
            if(a[x][y] != '\0'){
                runningoutofnames=false;
                System.out.println("Invalid coordinate.");
            }
        }
        return runningoutofnames;
    }

    public static boolean isGameOver(char[][] a, int b){
        int match=0;
        boolean gameOver=false;
        // check horizontal
        for(int i=0; (i<a.length && gameOver!= true);i++){
            match=0;
            for(int j=0; j<a[i].length-1;j++){
                if(a[i][j]==a[i][j+1] && a[i][j]!= '\0'){
                    match++;
                }
                if(match== (a.length-1)){
                    gameOver=true;
                    System.out.println("Game over. Player "+whoseTurn(b) +" wins!.");
                }
            }
        }

        // check vertical

        for(int i=0; (i<a[0].length && gameOver!= true);i++){
            match=0;
            for(int j=0; j<a.length-1;j++){
                if(a[j][i]==a[j+1][i] && a[j][i] != '\0'){
                    match++;
                }
            }
            if(match== (a.length-1)){
                gameOver=true;
                System.out.println("Game over. Player "+whoseTurn(b) +" wins!.");
            }
        }

        //check diagonal
        match=0;
        for(int i=0; (i<a.length-1 && gameOver!= true);i++){
            if(a[i][i]==a[i+1][i+1] && a[i][i] != '\0'){
                match++;
            }
        }

        if(match== (a.length-1)){
            gameOver=true;
            System.out.println("Game over. Player "+whoseTurn(b) +" wins!.");
        }

        return gameOver;
    }

}
}
