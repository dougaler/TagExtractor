import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class listmaster {
    static ArrayList<String> noiseList = new ArrayList<>();
    static ArrayList<String> file = new ArrayList<>();
    static ArrayList<String> noNoise = new ArrayList<>();
    static ArrayList<String> newList = new ArrayList<>();
    static ArrayList<Integer> wordCount = new ArrayList<>();
    static int counter = 0;
    static boolean isNoise = false;

    public static void main(String[] args) {
        getFile();
        System.out.println(removeNoise());
        System.out.println(wordCounter());
    }
    public static String getFile() {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String oldline;
        String line;
        Path Filename = null;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());
        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();

                inFile = new Scanner(target);
                int i = 5;
                Filename = target.getName(i);
                while (inFile.hasNextLine()) {
                    oldline = inFile.nextLine();
                    line = oldline.replaceAll("[^a-zA-Z ]", "");
                    line = line.trim();
                    List formater = Arrays.asList(line.split(" "));

                    for (int j = 0; j < formater.size(); j++) {
                        file.add(j, (String) formater.get(j));
                    }

                }

                inFile.close();
            } else {
                System.out.println("You did not choose a file. Quitting.");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
        return String.valueOf(Filename);
    }

    public static void stopWords() {

        Scanner inFile;
        String line;
        try
        {
            File newFile=new File("C:\\Users\\legol\\IdeaProjects\\TagExtractor\\src\\stopwords.txt");

            inFile = new Scanner(newFile);
                int i = 5;
                while(inFile.hasNextLine())
                {
                    line = inFile.nextLine();

                    List formater = Arrays.asList(line.split("\n"));

                    for(int j= 0; j < formater.size(); j++) {
                        noiseList.add(j, (String) formater.get(j));
                    }

                }
                inFile.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
    }
    public static ArrayList<String> removeNoise() {
        stopWords();
        for(int i= 0; i < file.size(); i++) {
            isNoise = false;
            for(int q = 0; q < noiseList.size();q++) {
                if (file.get(i).equalsIgnoreCase(noiseList.get(q))) {
                    isNoise = true;
                }
            }
            if(!isNoise){
                noNoise.add(file.get(i));
            }
        }
        for(int c = 0; c < noNoise.size(); c++){
            if(!noNoise.get(c).equals("")){
                newList.add(noNoise.get(c));
            }
        }
        return newList;
    }
    public static ArrayList<Integer> wordCounter(){
        for(int i = 0; i < newList.size(); i++){
            counter = 0;
            for(int j = 0; j < newList.size(); j++){
                if(newList.get(i).equalsIgnoreCase(newList.get(j))){
                    counter = counter + 1;
                }
            }
            wordCount.add(counter);
        }
        return wordCount;
    }

}
