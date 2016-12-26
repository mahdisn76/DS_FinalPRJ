import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;

public final class FileReader {

    private static File getFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
            return null;
        else
            return fileChooser.getSelectedFile();
    }

    public static String readHTML() {
        String text = "";
        try {
            File file = getFile();
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file.getPath()));
            StringBuilder sBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                sBuilder.append(line);
            text = sBuilder.toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while reading file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return text;
    }

}