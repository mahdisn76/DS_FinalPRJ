import java.io.IOException;

public class FileIOTester_write {

    public static void main(String[] args) {
        String text = "Hello\nThis is some text\nTableFlipperZ\n";
        try {
            FileIO.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
