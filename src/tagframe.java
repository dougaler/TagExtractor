import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class tagframe extends JFrame{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel displayPnl;
    JLabel titleLbl;
    JPanel inputPnl;

    JButton quitBtn;

    JButton getFileBtn;
    JButton saveTagBtn;

    static JTextArea displayTA;
    JScrollPane scroller;
    static ArrayList<String> tagInfo = new ArrayList<>();
    static ArrayList<String> tagsList = new ArrayList<>();
    static ArrayList<Integer> tagCount = new ArrayList<>();

    public tagframe()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTitlePanel();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createInputPanel();
        mainPnl.add(inputPnl, BorderLayout.CENTER);

        createDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void createTitlePanel()
    {
        titlePnl = new JPanel();
        titlePnl.setLayout(new GridLayout(2, 1));
        titleLbl = new JLabel("Tag Extractor", JLabel.CENTER);
        titleLbl.setFont(new Font("Courier", Font.BOLD,30));
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);


        titlePnl.add(titleLbl);
    }
    private void createInputPanel() {
        inputPnl = new JPanel();
        inputPnl.setLayout(new GridLayout(3, 1));

        getFileBtn = new JButton("Choose File");
        getFileBtn.addActionListener((ActionEvent ae) ->{
            displayTA.append(listmaster.getFile()+"\n");
            doListStuff();
        });

        saveTagBtn = new JButton("Save Tags");
        saveTagBtn.addActionListener((ActionEvent ae) -> {
            try {
                getTagsSaved();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {System.exit(0);});

        inputPnl.add(getFileBtn);
        inputPnl.add(saveTagBtn);
        inputPnl.add(quitBtn);
    }

    private void createDisplayPanel()
    {
        displayPnl = new JPanel();

        displayPnl.setLayout(new GridLayout(1, 1));

        displayTA = new JTextArea(15, 48);

        displayTA.setEditable(false);
        displayTA.setFont(new Font("Courier New", Font.PLAIN, 12));

        scroller = new JScrollPane(displayTA);
        displayPnl.add(scroller);
        quitBtn.setSize(600, 10);
    }
    public static void doListStuff(){
        tagsList = listmaster.removeNoise();
        tagCount = listmaster.wordCounter();
        for(int i = 0; i < tagsList.size(); i++){
            tagInfo.add(tagsList.get(i)+": "+tagCount.get(i));
            displayTA.append(tagInfo.get(i)+"\n");
        }

    }
    public static void getTagsSaved() throws IOException {
        Path path = Paths.get("C:\\Users\\legol\\IdeaProjects\\TagExtractor\\src\\tags.txt");
        //create file
        try {
            Path createdFilePath = Files.createFile(path);
            displayTA.append("Created a file at : "+createdFilePath+ "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Files.write(path, tagInfo);
    }



}
