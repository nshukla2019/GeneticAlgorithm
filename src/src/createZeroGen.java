package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class createZeroGen {

    /**
     * reading in file
     * @param fileName is the path of the file being used as a String
     * @param puzzleType is 1 indicating Puzzle 1, 2 indicating Puzzle 2
     */
    public Pool readFile(String fileName, int puzzleType, int POPULATION_SIZE) throws FileNotFoundException {
        List<Integer> integersProvided = new ArrayList<>();
        List<Piece> listOfPieces = new ArrayList<>();

        switch(puzzleType) {
            case 1:
                File intFile = new File(fileName);
                Scanner intScanner = new Scanner(intFile);

                while (intScanner.hasNextLine()) {
                    integersProvided.add(intScanner.nextInt());
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
                
                //comenting out to fix errors
                return null;// createZerothGeneration(2, integersProvided, listOfPieces, POPULATION_SIZE);
        }
        return null;
    }

    /**
     * given a list of integers, randomly assign 10 to each bin
     * @param listOfIntegers is the list of ints provided by the sample file
     * @return Bins which are populated
     */
    public Bins createRandomBins(List<Integer> listOfIntegers) {
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

        List<Integer> randomIndexes = getRandomIntegers(listOfPieces);

        Piece randomPieceForTop = listOfPieces.get(randomIndexes.get(0));
        Piece randomPieceForBottom = listOfPieces.get(randomIndexes.get(1));
        Piece randomPieceMiddle1 = listOfPieces.get(randomIndexes.get(2));
        Piece randomPieceMiddle2 = listOfPieces.get(randomIndexes.get(3));
        Piece randomPieceMiddle3 = listOfPieces.get(randomIndexes.get(4));

        middlePieces.add(randomPieceMiddle1);
        middlePieces.add(randomPieceMiddle2);
        middlePieces.add(randomPieceMiddle3);

        zeroGenerationTower.top = randomPieceForTop;
        zeroGenerationTower.bottom = randomPieceForBottom;
        zeroGenerationTower.middle_pieces = middlePieces;

        return zeroGenerationTower;
    }

    /**
     * generates a unique list of indexes given a list of Pieces
     * @param listOfPieces is a list of Pieces
     * @return a list of unique indexes
     */
    public List<Integer> getRandomIntegers(List<Piece> listOfPieces) {
        Random rand = new Random();

        int count = 0;
        List<Integer> randomIndexes = new ArrayList<>();

        // TODO: currently all random towers for 0th generation have a height of 5
        while (count!=5) {
            int randomIndex = -1;
            randomIndex = rand.nextInt(listOfPieces.size());
            if (!randomIndexes.contains(randomIndex)) {
                randomIndexes.add(randomIndex);
                count++;
            }
        }
        return randomIndexes;
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
    //commenting out to fix errors
/*
    public Pool createZerothGeneration(int puzzleType, List<Integer> listOfInteger, List<Piece> listOfPieces, int POPULATION_SIZE) {
        Pool organisms = new Pool(POPULATION_SIZE, null, false);
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
    }*/


}