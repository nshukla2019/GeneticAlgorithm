package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {

    /**
     * reading in file
     * @param fileName is the path of the file being used as a String
     * @param puzzleType is 1 indicating Puzzle 1, 2 indicating Puzzle 2
     */
    public Subpart readFile(String fileName, int puzzleType) throws FileNotFoundException {
        List<Integer> integersProvided = new ArrayList<>();

        switch(puzzleType) {
            case 1:
                File intFile = new File(fileName);
                Scanner intScanner = new Scanner(intFile);

                while (intScanner.hasNextLine()) {
                    integersProvided.add(intScanner.nextInt());
                }
                return assignNumbersToBins(integersProvided);

            case 2:
                File pieceFile = new File(fileName);
                Scanner pieceScanner = new Scanner(pieceFile);
                List<String> pieceAttributes = new ArrayList<>();

                while (pieceScanner.hasNextLine()) {
                    String curLine = pieceScanner.nextLine();
                    pieceAttributes.add(curLine);
                }

                List<Piece> listOfPieces = createPieces(pieceAttributes);
                return createRandomTower(listOfPieces);
        }
        return null;
    }

    /**
     * given a list of integers, randomly assign 10 to each bin
     * @param listOfIntegers is the list of ints provided by the sample file
     * @return Bins which are populated
     */
    public Bins assignNumbersToBins(List<Integer> listOfIntegers) {

        Collections.shuffle(listOfIntegers);

        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        List<Integer> three = new ArrayList<>();
        List<Integer> four = new ArrayList<>();

        Bins zeroGenerationBin = new Bins(one, two, three, four, -1);
        for (Integer i : listOfIntegers) {
            if (listOfIntegers.indexOf(i) == 0 || listOfIntegers.indexOf(i) <= 9) {
                zeroGenerationBin.binOne.add(i);
            }
            else if (listOfIntegers.indexOf(i) == 10 || listOfIntegers.indexOf(i) <= 19) {
                zeroGenerationBin.binTwo.add(i);
            }
            else if (listOfIntegers.indexOf(i) == 20 || listOfIntegers.indexOf(i) <= 29) {
                zeroGenerationBin.binThree.add(i);
            }
            else if (listOfIntegers.indexOf(i) == 30 || listOfIntegers.indexOf(i) <= 39) {
                zeroGenerationBin.binFour.add(i);
            }
        }
        return zeroGenerationBin;
    }

    /**
     * given a list of pieces create a random tower
     * @param listOfPieces is the list of pieces provided by the sample file
     * @return Bins which are populated
     */
    public Tower createRandomTower(List<Piece> listOfPieces) {
        List<Piece> middlePieces = new ArrayList<>();
        Tower zeroGenerationTower = new Tower(null, middlePieces, null, -1);

        Random rand = new Random();
        Piece randomPieceForTop = listOfPieces.get(rand.nextInt(listOfPieces.size()));
        Piece randomPieceForBottom = listOfPieces.get(rand.nextInt(listOfPieces.size()));
        Piece randomPieceMiddle1 = listOfPieces.get(rand.nextInt(listOfPieces.size()));
        Piece randomPieceMiddle2 = listOfPieces.get(rand.nextInt(listOfPieces.size()));
        Piece randomPieceMiddle3 = listOfPieces.get(rand.nextInt(listOfPieces.size()));

        middlePieces.add(randomPieceMiddle1);
        middlePieces.add(randomPieceMiddle2);
        middlePieces.add(randomPieceMiddle3);

        zeroGenerationTower.top = randomPieceForTop;
        zeroGenerationTower.bottom = randomPieceForBottom;
        zeroGenerationTower.middle_pieces = middlePieces;

        return zeroGenerationTower;
    }


    /**
     * given a list of strings that contain piece attributes, create pieces with those attributes
     * @param listOfStringPieces is a list of pieces in a String format
     * @return a list of Pieces
     */
    public List<Piece> createPieces(List<String> listOfStringPieces) {
        List<Piece> listOfPieces = new ArrayList<>();

        for (String s : listOfStringPieces) {
            String[] attribute = new String[3];
            attribute = s.split("\\t");

            TYPE typeOfPiece = determineType(attribute[0]);
            int width = Integer.parseInt(attribute[1]);
            int strength = Integer.parseInt(attribute[2]);
            int cost = Integer.parseInt(attribute[3]);

            Piece curPiece = new Piece(typeOfPiece, width, strength, cost);

            listOfPieces.add(curPiece);
        }

        return listOfPieces;
    }

    /**
     * given a String of "Door", "Wall", or "Lookout", return the type of piece
     * @param type is either "Door", "Wall", or "Lookout"
     * @return type of piece
     */
    public TYPE determineType(String type) {
        TYPE typeOfPiece = null;
        switch(type) {
            case "Door":
                typeOfPiece = TYPE.BOTTOM;
                break;
            case "Wall":
                typeOfPiece = TYPE.MIDDLE;
                break;
            case "Lookout":
                typeOfPiece = TYPE.TOP;
                break;
        }
        return typeOfPiece;
    }


}