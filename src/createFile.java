import java.io.File;
import java.io.FileWriter;

public class createFile {

    String fullText = "";

    public void toFile() {

    }

    protected void fileCreation(String name) {
        try {
            String fileName = name + ".txt";
            File file = new File(fileName);
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File is created");
                }
                else {
                    System.out.println("File Creation Error");
                }
            }

            if (file.canWrite()) {
                FileWriter writeInFile = new FileWriter(file);
                writeInFile.write(fullText);
                writeInFile.close();
            }
        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    protected void ToString(String text) {
        String text2 = text + "\n";
        fullText += text2;
        
    }
    
}
