import javax.swing.*;
import java.io.*;

public final class FileIO {

    public static String read() throws IOException {
        String text = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(null) != JFileChooser.CANCEL_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file.getPath()));
            StringBuilder sBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                sBuilder.append(line);
            text = sBuilder.toString();
            reader.close();
        }
        return text;
    }

    public static void write(String text) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showSaveDialog(null) != JFileChooser.CANCEL_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String[] lines = text.split("\n");
            for(String line:lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        }
    }

}
