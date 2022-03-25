package ui;

import model.Symptom;
import model.Patient;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HealthGUI extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private static final String JSON_STORE = "./data/medicalsearch.json";
    private Patient patient = new Patient("Leah", 0, 0);
    private Symptom first = new Symptom();
    private Symptom second = new Symptom();
    private Symptom third = new Symptom();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel bottomPanel;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;


    public HealthGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        desktop = new JDesktopPane();

        controlPanel = new JInternalFrame("Health Application", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        controlPanel.setLocation((width - getWidth()) / 10, (height - getHeight()) / 5);

        setContentPane(desktop);
        setTitle("Medical Search");
        setSize(WIDTH, HEIGHT);

        addButtonMenu();

        bottomPanel = new JPanel();
        controlPanel.add(bottomPanel, BorderLayout.PAGE_END);

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    public static void main(String[] args) {
        new HealthGUI();
    }

    private void addButtonMenu() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2));
        buttonPanel.add(new JButton(new AddSymptom()));
        buttonPanel.add(new JButton(new MedicalSearch()));
        buttonPanel.add(new JButton(new MedicalRecord()));
        buttonPanel.add(new JButton(new Save()));
        buttonPanel.add(new JButton(new VaccinationRecord()));
        buttonPanel.add(new JButton(new Load()));


        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    private class AddSymptom extends AbstractAction {
        AddSymptom() {
            super("Add Symptoms");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {


            String symptom = JOptionPane.showInputDialog(null,
                    " Enter first symptom",
                    "Enter Symptom",
                    JOptionPane.QUESTION_MESSAGE);

            String symptomTwo = JOptionPane.showInputDialog(null,
                    " Enter second symptom",
                    "Enter Symptom",
                    JOptionPane.QUESTION_MESSAGE);

            String symptomThree = JOptionPane.showInputDialog(null,
                    " Enter third symptom",
                    "Enter Symptom",
                    JOptionPane.QUESTION_MESSAGE);

            Symptom one = first;
            one.convertSymptomName(symptom);
            patient.addToSearch(one);

            Symptom two = second;
            two.convertSymptomName(symptomTwo);
            patient.addToSearch(two);

            Symptom three = third;
            three.convertSymptomName(symptomThree);
            patient.addToSearch(three);


        }
    }

    private class MedicalSearch extends AbstractAction {

        MedicalSearch() {
            super("Medical Search");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            patient.analyzeSymptoms(patient.getSymptoms());
            if (true) {
                String covidBooking = JOptionPane.showInputDialog(null,
                        " You are eligible for a Covid test, Please enter a booking time",
                        "Hour",
                        JOptionPane.QUESTION_MESSAGE);
                patient.setBookedTime(Integer.parseInt(covidBooking));
            } else {
                String time = JOptionPane.showInputDialog(null,
                        " You are eligible for a Doctor's test, Please enter booking time",
                        "Hour",
                        JOptionPane.QUESTION_MESSAGE);
                patient.setBookedTime(Integer.parseInt(time));
            }
        }
    }

    public class Load extends AbstractAction {

        Load() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                patient = jsonReader.read();
                String message = "Loaded" + JSON_STORE;
                JOptionPane.showMessageDialog(null, message, " Loaded",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                String message = "Unable to read " + JSON_STORE;
                JOptionPane.showMessageDialog(null, message, "Not Loaded",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class Save extends AbstractAction {

        Save() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(patient);
                jsonWriter.close();
                String message = "Saved" + JSON_STORE;
                JOptionPane.showMessageDialog(null, message, "Saved",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (FileNotFoundException e) {
                String message = "Unable to save " + JSON_STORE;
                JOptionPane.showMessageDialog(null, message, "Not Saved",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class MedicalRecord extends AbstractAction {

        MedicalRecord() {
            super("Medical Record");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //String str;

            String str = "Medical Record: \n";
            for (Symptom d : patient.getSymptoms()) {
                str += "Symptoms:" + d.getSymptomName() + " \n";
            }

            str += "Booking time: " + patient.getBookingTime() + "\n";
            str += "Vaccination record: " + patient.getVaccinationRecord() + " doses";
            ImageIcon icon = new ImageIcon("./data/healthrecord.png");
            JOptionPane.showMessageDialog(null, str, "Symptoms",
                    JOptionPane.PLAIN_MESSAGE,icon);

        }
    }

    private class VaccinationRecord extends AbstractAction {

        VaccinationRecord() {
            super("Vaccination Record");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String doses = JOptionPane.showInputDialog(null,
                    " How many doses of the Vaccine have you had?",
                    "Doses",
                    JOptionPane.QUESTION_MESSAGE);
            patient.setBookedTime(Integer.parseInt(doses));
        }
    }


}
