package src;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        FileReader reader = new FileReader();
        Subpart part = new Subpart(-1);
        part = reader.readFile("sampleFiles/puzzle2", 2);
        System.out.println("hi");
    }

}

