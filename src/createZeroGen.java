package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class createZeroGen {

    public static List<Float> integersProvided = new ArrayList<>();
    public static List<Piece> listOfPieces = new ArrayList<>();

    /**
     * reading in file
     * @param fileName is the path of the file being used as a String
     * @param puzzleType is 1 indicating Puzzle 1, 2 indicating Puzzle 2
     */
    public Pool readFile(String fileName, int puzzleType, int POPULATION_SIZE) throws FileNotFoundException {

        switch(puzzleType) {
            case 1:
                File intFile = new File(fileName);
                Scanner floatScanner = new Scanner(intFile);

                while (floatScanner.hasNextLine()) {
                    integersProvided.add(floatScanner.nextFloat());
                }
                return createZerothGeneration(1, integersProvided, listOfPieces, POPULATION_SIZE);

            case 2:
                File pieceFile = new File(fileName);
                Scanner pieceScanner = new Scanner(pieceFile);
                List<String> pieceAttributes = new ArrayList<>();

                while (pieceScanner.hasNextLine()) {
                    String curLine = pieceScanner.nextLine();
                    pieceAttributes.add(curLine);
                }

                listOfPieces = createPieces(pieceAttributes);
                return  createZerothGeneration(2, integersProvided, listOfPieces, POPULATION_SIZE);
        }
        return null;
    }

    /**
     * given a list of integers, randomly assign 10 to each bin
     * @param listOfIntegers is the list of ints provided by the sample file
     * @return Bins which are populated
     */
    public Bins createRandomBins(List<Float> listOfIntegers) {
        Collections.shuffle(listOfIntegers);
        List<ArrayList<Float>> bins = new ArrayList<>();

        ArrayList<Float> one = new ArrayList<>();
        ArrayList<Float> two = new ArrayList<>();
        ArrayList<Float> three = new ArrayList<>();
        ArrayList<Float> four = new ArrayList<>();

        bins.add(one);
        bins.add(two);
        bins.add(three);
        bins.add(four);

        Bins zeroGenerationBin = new Bins(bins, -1);

        for (int i =0; i <= listOfIntegers.size(); i++) {
            if (i <= 9) {
                one.add(listOfIntegers.get(i));
            }
            else if (i <= 19) {
                two.add(listOfIntegers.get(i));
            }
            else if (i <= 29) {
                three.add(listOfIntegers.get(i));
            }
            else if (i <= 39) {
                four.add(listOfIntegers.get(i));
            }
        }
        return zeroGenerationBin;
    }

    /**
     * given a list of pieces create a random tower with a random height
     * @param listOfPieces is the list of pieces provided by the sample file
     * @return Bins which are populated
     */
    public Tower createRandomTower(List<Piece> listOfPieces) {
        int numOfPieces = listOfPieces.size();
        int randomNumOfPieces = (int) ((Math.random() * ((numOfPieces) - 3)) + 3);

        List<Piece> middlePieces = new ArrayList<>();
        Tower zeroGenerationTower = new Tower(null, middlePieces, null, -1);

        Collections.shuffle(listOfPieces);


        Piece randomPieceForTop = listOfPieces.get(0);
        Piece randomPieceForBottom = listOfPieces.get(1);

        for (int i = 2; i < randomNumOfPieces; i++) {
            Piece randomMiddlePiece = listOfPieces.get(i);
            middlePieces.add(randomMiddlePiece);
        }


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
     * takes all lowercase or all uppercase string
     */
    public TYPE determineType(String type) {
        TYPE typeOfPiece = null;
        switch(type) {
            case "Door":
            case "DOOR":
                typeOfPiece = TYPE.DOOR;
                break;
            case "Wall":
            case "WALL":
                typeOfPiece = TYPE.WALL;
                break;
            case "Lookout":
            case "LOOKOUT":
                typeOfPiece = TYPE.LOOKOUT;
                break;
        }
        return typeOfPiece;
    }

    public Pool createZerothGeneration(int puzzleType, List<Float> listOfInteger, List<Piece> listOfPieces, int POPULATION_SIZE) {
        Pool organisms = new Pool(POPULATION_SIZE, null,0);
        List<Organism> organismList = new ArrayList<>();

        switch (puzzleType) {
            case 1:
                for (int i=0; i < organisms.POPULATION_SIZE; i++) {
                    organismList.add(createRandomBins(listOfInteger));
                }
                break;
            case 2:
                for (int i=0; i < organisms.POPULATION_SIZE; i++) {
                    organismList.add(createRandomTower(listOfPieces));
                }
                break;
        }

        organisms.generation = organismList;

        return organisms;
    }


}