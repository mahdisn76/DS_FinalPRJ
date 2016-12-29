import javax.swing.*;
import java.io.*;

public final class FileIO {

    public static File getFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
            return null;
        else
            return fileChooser.getSelectedFile();
    }

    public static String read(boolean newLine){
        return read(getFile(), newLine);
    }

    public static String read(File file, boolean newLine) {
        String text = "";
        BufferedReader reader = null;
        if (file != null) {
            try {
                reader = new BufferedReader(new FileReader(file.getPath()));
                StringBuilder sBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sBuilder.append(line);
                    if (newLine) sBuilder.append("\n");
                }
                text = sBuilder.toString();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error while opening file", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                if (reader != null)
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
        return text;
    }

    public static void write(String text) {
        BufferedWriter writer = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showSaveDialog(null) != JFileChooser.CANCEL_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                writer = new BufferedWriter(new FileWriter(file));
                String[] lines = text.split("\n");
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error while saving file", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                if (writer != null)
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

}
