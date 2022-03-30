package ui;

import model.Event;
import model.EventLog;
import model.Symptom;
import model.Patient;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// a class that runs the gui
// code credit to AlarmControllerUI.java

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

    // MODIFIES: this
    // EFFECTS: initializes objects
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

    // EFFECTS: runs the GUI
    public static void main(String[] args) {
        new HealthGUI();
    }

    // MODIFIES: this
    // EFFECTS: runs the buttom menu
    private void addButtonMenu() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2));
        buttonPanel.add(new JButton(new AddSymptom()));
        buttonPanel.add(new JButton(new MedicalSearch()));
        buttonPanel.add(new JButton(new MedicalRecord()));
        buttonPanel.add(new JButton(new Save()));
        buttonPanel.add(new JButton(new VaccinationRecord()));
        buttonPanel.add(new JButton(new Load()));
        buttonPanel.add(new JButton(new QuitApplication()));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: centres the panel on the screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: adds symptoms to the patient
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

    // MODIFIES: this
    // EFFECTS:  option pane to enter a booking time
    private class MedicalSearch extends AbstractAction {

        // EFFECTS: constructor
        MedicalSearch() {
            super("Medical Search");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
           // patient.analyzeSymptoms(patient.getSymptoms());
            if (patient.analyzeSymptoms(patient.getSymptoms()) == true) {
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

    // MODIFIES: this
    // EFFECTS: loads the information from file
    public class Load extends AbstractAction {
        // EFFECTS: constructor
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

    // MODIFIES: this
    // EFFECTS: saves information from file
    public class Save extends AbstractAction {
        // EFFECTS: constructor
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

    // MODIFIES: this
    // EFFECTS: prints the information on the screen
    private class MedicalRecord extends AbstractAction {

        // EFFECTS: constructor
        MedicalRecord() {
            super("Medical Record");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String str = "Medical Record: \n";
            for (Symptom d : patient.getSymptoms()) {
                str += "Symptoms:" + d.getSymptomName() + " \n";
            }
            str += "Booking time: " + patient.getBookingTime() + "\n";
            str += "Vaccination record: " + patient.getVaccinationRecord() + " doses";
            ImageIcon icon = new ImageIcon("./data/healthrecord.png");
            JOptionPane.showMessageDialog(null, str, "Symptoms",
                    JOptionPane.PLAIN_MESSAGE, icon);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds vaccination record
    private class VaccinationRecord extends AbstractAction {
        // EFFECTS: constructor
        VaccinationRecord() {
            super("Vaccination Record");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String doses = JOptionPane.showInputDialog(null,
                    " How many doses of the Vaccine have you had?",
                    "Doses",
                    JOptionPane.QUESTION_MESSAGE);
            patient.updatedVaccinationRecord(Integer.parseInt(doses));
        }
    }

    // MODIFIES: this
    // EFFECTS: quits application and prints logged events
    private class QuitApplication extends AbstractAction {
        // EFFECTS: constructor
        QuitApplication() {
            super("Quit Application");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            for (Event events : EventLog.getInstance()) {
                System.out.println(events.getDescription());
            }
            System.exit(0);
        }
    }
}
