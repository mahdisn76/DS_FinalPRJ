import java.io.IOException;

public class FileIOTester_read {

    public static void main(String[] args) {
        try {
            System.out.println(FileIO.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
