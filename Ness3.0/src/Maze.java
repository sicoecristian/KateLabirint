import java.util.ArrayList;
import java.util.Arrays;

public class Maze {

    public static Character[][] addToAMatrix(String[] maze) throws KateAlreadyExistException, NoKateInTheLabirintException {
        int kateCounter = 0;
        String tempString = new String();
        Character[][] matrix = new Character[maze.length][maze[0].length()];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length(); j++) {
                matrix[i][j] = maze[i].charAt(j);
                if (matrix[i][j] == 'k') {
                    kateCounter++;
                    if (kateCounter > 1)
                        throw new KateAlreadyExistException();
                }
            }
        }
        if (kateCounter == 0)
            throw new NoKateInTheLabirintException();
        return matrix;
    }

    public static int calculateNumberOfWhiteSpaces(Character[][] mazeMatrix) {
        int noOfSpaces = 0;
        for (int i = 0; i < mazeMatrix.length; i++) {
            for (int j = 0; j < mazeMatrix[i].length; j++) {
                if (mazeMatrix[i][j] == ' ' || mazeMatrix[i][j] == 'k')
                    noOfSpaces++;
            }
        }
        return noOfSpaces;
    }
    public static boolean checkIfAnElementIsInMatrix(Character[][] matrixChar,int i,int j){
        if(i>=matrixChar.length || i<0)
            return false;
        if(j<0 || j>=matrixChar.length)
            return false;
        return true;
    }

    public static boolean checkIfIFoundTheExit(Character[][] mazeMatrix,MazeCoordinate curentKatePosition){
        if(!checkIfAnElementIsInMatrix(mazeMatrix,curentKatePosition.getX()-1,curentKatePosition.getY())){
            return true;
        }
        if(!checkIfAnElementIsInMatrix(mazeMatrix,curentKatePosition.getX()+1,curentKatePosition.getY())){
            return true;
        }
        if(!checkIfAnElementIsInMatrix(mazeMatrix,curentKatePosition.getX(),curentKatePosition.getY()-1)){
            return true;
        }
        if(!checkIfAnElementIsInMatrix(mazeMatrix,curentKatePosition.getX(),curentKatePosition.getY()+1)){
            return true;
        }
        return false;
    }

    public static MazeCoordinate findKatePosition(Character[][] mazeMatrix) throws CanNotFindKateException {
        for(int i=0;i<mazeMatrix.length;i++){
            for(int j=0;j<mazeMatrix[i].length;j++)
            {
                if(mazeMatrix[i][j]=='k')
                    return new MazeCoordinate(i,j);
            }
        }
        throw new CanNotFindKateException();
    }

    public static int calculateNumberOfEligibleNeighbours(Character[][]mazeMatrix,MazeCoordinate mazeCoordinate){

        int numberOfEligibleNeighbours=0;

        if(checkIfAnElementIsInMatrix(mazeMatrix,mazeCoordinate.getX()-1,mazeCoordinate.getY())  && (mazeMatrix[mazeCoordinate.getX()-1][mazeCoordinate.getY()]=='k'||mazeMatrix[mazeCoordinate.getX()-1][mazeCoordinate.getY()]==' ')){
            numberOfEligibleNeighbours++;
        }
        if(checkIfAnElementIsInMatrix(mazeMatrix,mazeCoordinate.getX()+1,mazeCoordinate.getY()) && (mazeMatrix[mazeCoordinate.getX()+1][mazeCoordinate.getY()]=='k'||mazeMatrix[mazeCoordinate.getX()+1][mazeCoordinate.getY()]==' ')){
            numberOfEligibleNeighbours++;
        }
        if(checkIfAnElementIsInMatrix(mazeMatrix,mazeCoordinate.getX(),mazeCoordinate.getY()+1) && (mazeMatrix[mazeCoordinate.getX()][mazeCoordinate.getY()+1]=='k'|| mazeMatrix[mazeCoordinate.getX()][mazeCoordinate.getY()+1]==' ')){
            numberOfEligibleNeighbours++;
        }
        if(checkIfAnElementIsInMatrix(mazeMatrix,mazeCoordinate.getX(),mazeCoordinate.getY()-1) && (mazeMatrix[mazeCoordinate.getX()][mazeCoordinate.getY()-1]=='k'||mazeMatrix[mazeCoordinate.getX()-1][mazeCoordinate.getY()-1]==' ')){
            numberOfEligibleNeighbours++;
        }
        return numberOfEligibleNeighbours;

    }

    public static boolean checkIfICanMoveUp(Character[][] mazeMatrix,MazeCoordinate mazeCoordinate){
        if(checkIfAnElementIsInMatrix(mazeMatrix,mazeCoordinate.getX()-1,mazeCoordinate.getY())  && (mazeMatrix[mazeCoordinate.getX()-1][mazeCoordinate.getY()]=='k'||mazeMatrix[mazeCoordinate.getX()-1][mazeCoordinate.getY()]==' ')){
           return true;
        }
        return false;
    }

    public static boolean checkIfICanMoveDown(Character[][] mazeMatrix,MazeCoordinate mazeCoordinate){
        if(checkIfAnElementIsInMatrix(mazeMatrix,mazeCoordinate.getX()+1,mazeCoordinate.getY()) && (mazeMatrix[mazeCoordinate.getX()+1][mazeCoordinate.getY()]=='k'||mazeMatrix[mazeCoordinate.getX()+1][mazeCoordinate.getY()]==' '))
            { return true;}

            return false;
    }

    public static boolean checkIfICanMoveRight(Character[][] mazeMatrix,MazeCoordinate mazeCoordinate){
        if(checkIfAnElementIsInMatrix(mazeMatrix,mazeCoordinate.getX(),mazeCoordinate.getY()+1) && (mazeMatrix[mazeCoordinate.getX()][mazeCoordinate.getY()+1]=='k'|| mazeMatrix[mazeCoordinate.getX()][mazeCoordinate.getY()+1]==' ')){
            return true;
        }
        return false;
    }

    public static boolean checkIfICanMoveLeft(Character[][] mazeMatrix,MazeCoordinate mazeCoordinate){
        if(checkIfAnElementIsInMatrix(mazeMatrix,mazeCoordinate.getX(),mazeCoordinate.getY()-1) && (mazeMatrix[mazeCoordinate.getX()][mazeCoordinate.getY()-1]=='k'||mazeMatrix[mazeCoordinate.getX()-1][mazeCoordinate.getY()-1]==' ')){
            return true;
        }
        return false;
    }

    public static boolean hasExit(String[] maze) throws NoKateInTheLabirintException, KateAlreadyExistException, CanNotFindKateException {
        Character[][] mazeMatrix=addToAMatrix(maze);
        MazeCoordinate katePosition=findKatePosition(mazeMatrix);

        ArrayList<MazeCoordinate> visited = new ArrayList<MazeCoordinate>();

        ComeBack[] comeBacks=new ComeBack[100];
        int comeBacksSize=0;
        ComeBack comeBack=new ComeBack(katePosition.getX(),katePosition.getY(),calculateNumberOfEligibleNeighbours(mazeMatrix,katePosition));
        comeBacks[comeBacksSize]=comeBack;
        comeBacksSize++;

       if( checkIfIFoundTheExit(mazeMatrix,katePosition))
           return true;

       while (comeBacksSize!=1g){
           if(checkIfIFoundTheExit(mazeMatrix,katePosition)){
               return true;
           }
           if(checkIfICanMoveUp(mazeMatrix,katePosition)){
               if(!visited.contains(new MazeCoordinate(katePosition.getX()-1, katePosition.getY() ))) {
                   katePosition = new MazeCoordinate(katePosition.getX() - 1, katePosition.getY());
                   visited.add(katePosition);

                   if(calculateNumberOfEligibleNeighbours(mazeMatrix,katePosition)>2){
                       if(!Arrays.asList(comeBacks).contains(new ComeBack(katePosition.getX(),katePosition.getY(),calculateNumberOfEligibleNeighbours(mazeMatrix,katePosition)))) {
                           comeBack = new ComeBack(katePosition.getX(), katePosition.getY(), calculateNumberOfEligibleNeighbours(mazeMatrix, katePosition));
                           comeBacks[comeBacksSize] = comeBack;
                           comeBacksSize++;
                           visited.remove(new MazeCoordinate(comeBacks[comeBacksSize].getX(), comeBacks[comeBacksSize].getY()));
                       }
                   }
               }
               else if(checkIfICanMoveDown(mazeMatrix,katePosition)){
                   if(!visited.contains(new MazeCoordinate(katePosition.getX()+1, katePosition.getY() ))) {
                       katePosition = new MazeCoordinate(katePosition.getX() +1, katePosition.getY());
                       visited.add(katePosition);

                       if(calculateNumberOfEligibleNeighbours(mazeMatrix,katePosition)>2){
                           if(!Arrays.asList(comeBacks).contains(new ComeBack(katePosition.getX(),katePosition.getY(),calculateNumberOfEligibleNeighbours(mazeMatrix,katePosition)))) {
                               comeBack = new ComeBack(katePosition.getX(), katePosition.getY(), calculateNumberOfEligibleNeighbours(mazeMatrix, katePosition));
                               comeBacks[comeBacksSize] = comeBack;
                               comeBacksSize++;
                               visited.remove(new MazeCoordinate(comeBacks[comeBacksSize].getX(), comeBacks[comeBacksSize].getY()));
                           }
                       }
                   }
               }
               else if(checkIfICanMoveLeft(mazeMatrix,katePosition)){
                   if(visited.contains(new MazeCoordinate(katePosition.getX(), katePosition.getY()-1 ))) {
                       katePosition = new MazeCoordinate(katePosition.getX() , katePosition.getY()-1);
                       visited.add(katePosition);

                       if(calculateNumberOfEligibleNeighbours(mazeMatrix,katePosition)>2){
                           if(!Arrays.asList(comeBacks).contains(new ComeBack(katePosition.getX(),katePosition.getY(),calculateNumberOfEligibleNeighbours(mazeMatrix,katePosition)))) {
                               comeBack = new ComeBack(katePosition.getX(), katePosition.getY(), calculateNumberOfEligibleNeighbours(mazeMatrix, katePosition));
                               comeBacks[comeBacksSize] = comeBack;
                               comeBacksSize++;
                               visited.remove(new MazeCoordinate(comeBacks[comeBacksSize].getX(), comeBacks[comeBacksSize].getY()));
                           }
                       }
                   }
               }
               else if(checkIfICanMoveRight(mazeMatrix,katePosition)){
                   if(visited.contains(new MazeCoordinate(katePosition.getX(), katePosition.getY()+1 ))) {
                       katePosition = new MazeCoordinate(katePosition.getX() , katePosition.getY()+1);
                       visited.add(katePosition);

                       if(calculateNumberOfEligibleNeighbours(mazeMatrix,katePosition)>2){
                           if(!Arrays.asList(comeBacks).contains(new ComeBack(katePosition.getX(),katePosition.getY(),calculateNumberOfEligibleNeighbours(mazeMatrix,katePosition)))) {
                               comeBack = new ComeBack(katePosition.getX(), katePosition.getY(), calculateNumberOfEligibleNeighbours(mazeMatrix, katePosition));
                               comeBacks[comeBacksSize] = comeBack;
                               comeBacksSize++;
                               visited.remove(new MazeCoordinate(comeBacks[comeBacksSize].getX(), comeBacks[comeBacksSize].getY()));
                           }
                       }
                   }
               }
               else {
                   katePosition=new MazeCoordinate(comeBacks[comeBacksSize].getX(),comeBacks[comeBacksSize].getY());
                   //comeBacks[comeBacksSize].setNumberOfNeighbours(comeBacks[comeBacksSize].getNumberOfNeighbours()-1);
                   comeBacksSize--;
               }

           }
       }

        return false;
    }
    public static void main(String[] args) throws KateAlreadyExistException, NoKateInTheLabirintException, CanNotFindKateException {
        String[] newString=new String[1000];
        String[] kates=new String[100];
        String[] oneKate=new String[100];
        String[] noKate=new String[100];
        kates=new String[]{"k ",
                "kk"};
        newString= new String[]{"########",
                "# # ####",
                "# #k#   ",
                "# # # ##",
                "# # # ##",
                "#      #",
                "########"};
        oneKate= new String[]{"k"};
        noKate=new String[]{"###",
                "###"};


        System.out.println(hasExit(newString));
    }
}

