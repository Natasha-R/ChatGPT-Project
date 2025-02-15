package thkoeln.st.st2praktikum.exercise;

import org.springframework.stereotype.Component;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Connection;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Coordinate;
import thkoeln.st.st2praktikum.exercise.domainprimitives.Task;
import thkoeln.st.st2praktikum.exercise.field.application.FieldService;
import thkoeln.st.st2praktikum.exercise.field.domain.Barrier;
import thkoeln.st.st2praktikum.exercise.field.domain.Field;
import thkoeln.st.st2praktikum.exercise.miningmachine.application.MiningMachineService;
import thkoeln.st.st2praktikum.exercise.miningmachine.domain.MiningMachine;
import thkoeln.st.st2praktikum.exercise.transporttechnology.application.TransportTechnologyService;
import thkoeln.st.st2praktikum.exercise.transporttechnology.domain.TransportTechnology;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MachineDirections {

    private MiningMachineService miningMachineService;
    private FieldService fieldService;
    private TransportTechnologyService transportTechnologyService;

    public MachineDirections(MiningMachineService miningMachineService, FieldService fieldService, TransportTechnologyService transportTechnologyService) {
        this.miningMachineService = miningMachineService;
        this.fieldService = fieldService;
        this.transportTechnologyService = transportTechnologyService;
    }

    public void west(int anzahl, MiningMachine machine) {
            /*
            Potentielle Wände raussuchen
             */
        Field field = MainComponent.machineDirections.fieldService.fieldRepo.findById(machine.getFieldId()).get();

        List<String> tmp = new ArrayList<>();
        for (Barrier barrier : field.getHORIZONTAL()) {
            for (int i = barrier.getStart().getY(); i < barrier.getEnd().getY(); i++) {
                tmp.add("(" + barrier.getStart().getX() + "," + i + ")");
            }
        }
        List<String> tmp2 = new ArrayList<>();
        for (String s : tmp) {
            String[] abc = s.split(","); // -> String bei ',' aufteilen -> ["(0", "3)"]
            String def = abc[1].replace(")", ""); // -> Index 1 aus Array und Klammer entfernen -> "3"

            if (Integer.parseInt(def) == machine.getCoordinate().getY() && // schauen ob potentielle wand selbe Höhe ist
                    Integer.parseInt(abc[0].replace("(", "")) > machine.getCoordinate().getX() && // schauen ob x von potentieller Wand kleiner gleich als aktuelles x ist
                    (machine.getCoordinate().getX() - anzahl) < Integer.parseInt(abc[0].replace("(", ""))) {  // schauen ob neue position größer als x von potentieller Wand ist
                tmp2.add(s);
            }
        }
        if (tmp2.size() == 0) { // falls keine Wände gefunden wurden -> Neue position kann ohne Probleme gesetzt werden
            if (machine.getCoordinate().getX() - anzahl >= 0)
                machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(machine.getCoordinate().getX() - anzahl, machine.getCoordinate().getY()), machine.getFieldId(), machine.getTasks());
            else
                machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(0, machine.getCoordinate().getY()), machine.getFieldId(), machine.getTasks());
        } else {
            // Liste mit den Potentiellen Wänden durchgehen und die größte X Koordinate davon finden
            int max = Integer.parseInt(tmp2.get(0).split(",")[0].replace("(", "")); // Beispiel -> ("0,3") -> 3
            for (int i = 0; i < tmp2.size(); i++) {
                if (max < Integer.parseInt(tmp2.get(i).split(",")[0].replace("(", ""))) {
                    max = Integer.parseInt(tmp2.get(i).split(",")[0].replace("(", ""));
                }
            }
            machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(max, machine.getCoordinate().getY()), machine.getFieldId(), machine.getTasks());

        }
        if (machine.getCoordinate().getX() < 0) {
            machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(0, machine.getCoordinate().getY()), machine.getFieldId(), machine.getTasks());
        }
        miningMachineService.miningMachineRepo.save(machine);
    }

    public void east(int anzahl, MiningMachine machine) {
        /*
        Potentielle Wände raussuchen
         */
        Field field = MainComponent.machineDirections.fieldService.fieldRepo.findById(machine.getFieldId()).get();

        List<String> tmp = new ArrayList<>();
        for (Barrier barrier : field.getHORIZONTAL()) {
            for (int i = barrier.getStart().getY(); i < barrier.getEnd().getY(); i++) {
                tmp.add("(" + barrier.getStart().getX() + "," + i + ")");
            }
        }

        List<String> tmp2 = new ArrayList<>();

        for (String s : tmp) {
            String[] abc = s.split(","); // -> String bei ',' aufteilen -> ["(0", "3)"]
            String def = abc[1].replace(")", ""); // -> Index 1 aus Array und Klammer entfernen -> "3"

            if (Integer.parseInt(def) == machine.getCoordinate().getY() && // schauen ob potentielle wand selbe Höhe ist
                    Integer.parseInt(abc[0].replace("(", "")) > machine.getCoordinate().getX() && // schauen ob x von potentieller Wand kleiner gleich als aktuelles x ist
                    (machine.getCoordinate().getX() + anzahl) >= Integer.parseInt(abc[0].replace("(", ""))) {  // schauen ob neue position größer als x von potentieller Wand ist
                tmp2.add(s); //Falls Bedingungen zutreffen, füge die Wand der Temporären Liste hinzu
            }
        }
        if (tmp2.size() == 0) { // falls keine Wände gefunden wurden -> Neue position kann ohne Probleme gesetzt werden
            machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(machine.getCoordinate().getX() + anzahl, machine.getCoordinate().getY()), machine.getFieldId(), machine.getTasks());
        } else {
            // Liste mit den Potentiellen Wänden durchgehen und die größte X Koordinate davon finden
            int max = Integer.parseInt(tmp2.get(0).split(",")[0].replace("(", "")); // Beispiel -> ("0,3") -> 3
            for (int i = 0; i < tmp2.size(); i++) {
                if (max > Integer.parseInt(tmp2.get(i).split(",")[0].replace("(", ""))) {
                    max = Integer.parseInt(tmp2.get(i).split(",")[0].replace("(", ""));
                }
            }
            machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(max - 1, machine.getCoordinate().getY()), machine.getFieldId(), machine.getTasks());
        }
        if (machine.getCoordinate().getX() > field.getWidth() - 1) {
            machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(field.getWidth() - 1, machine.getCoordinate().getY()), machine.getFieldId(), machine.getTasks());
        }
        miningMachineService.miningMachineRepo.save(machine);
    }


    public void south(int anzahl, MiningMachine machine) {
        Field field = MainComponent.machineDirections.fieldService.fieldRepo.findById(machine.getFieldId()).get();

        List<String> tmp = new ArrayList<>();
        for (Barrier barrier : field.getVERTICAL()) {
            for (int i = barrier.getStart().getX(); i < barrier.getEnd().getX(); i++) {
                tmp.add("(" + i + "," + barrier.getStart().getY() + ")");
            }
        }

        List<String> tmp2 = new ArrayList<>();

        for (String s : tmp) {
            String[] abc = s.split(","); // -> String bei ',' aufteilen -> ["(0", "3)"]
            String def = abc[0].replace("(", ""); // -> Index 1 aus Array und Klammer entfernen -> "3"

            if (Integer.parseInt(def) == machine.getCoordinate().getX() && // schauen ob potentielle wand selbe Höhe ist
                    Integer.parseInt(abc[1].replace(")", "")) < machine.getCoordinate().getY() && // schauen ob x von potentieller Wand kleiner gleich als aktuelles x ist
                    (machine.getCoordinate().getY() - anzahl) < Integer.parseInt(abc[1].replace(")", ""))) {  // schauen ob neue position größer als x von potentieller Wand ist
                tmp2.add(s); //Falls Bedingungen zutreffen, füge die Wand der Temporären Liste hinzu
            }
        }
        if (tmp2.size() == 0) { // falls keine Wände gefunden wurden -> Neue position kann ohne Probleme gesetzt werden
            if (machine.getCoordinate().getY() - anzahl >= 0)
                machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(machine.getCoordinate().getX(), machine.getCoordinate().getY() - anzahl), machine.getFieldId(), machine.getTasks());
            else
                machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(machine.getCoordinate().getX(), 0), machine.getFieldId(), machine.getTasks());
        } else {
            // Liste mit den Potentiellen Wänden durchgehen und die größte X Koordinate davon finden
            int max = Integer.parseInt(tmp2.get(0).split(",")[1].replace(")", "")); // Beispiel -> ("0,3") -> 3
            for (int i = 0; i < tmp2.size(); i++) {
                if (max > Integer.parseInt(tmp2.get(i).split(",")[1].replace(")", ""))) {
                    max = Integer.parseInt(tmp2.get(i).split(",")[1].replace(")", ""));
                }
            }
            machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(machine.getCoordinate().getX(), max), machine.getFieldId(), machine.getTasks());

        }
        if (machine.getCoordinate().getY() < 0) {
            machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(machine.getCoordinate().getX(), 0), machine.getFieldId(), machine.getTasks());
        }

        miningMachineService.miningMachineRepo.save(machine);
    }

    public void north(int anzahl, MiningMachine machine) {
        Field field = MainComponent.machineDirections.fieldService.fieldRepo.findById(machine.getFieldId()).get();

        List<String> tmp = new ArrayList<>();
        for (Barrier barrier : field.getVERTICAL()) {
            for (int i = barrier.getStart().getX(); i < barrier.getEnd().getX(); i++) {
                tmp.add("(" + i + "," + barrier.getStart().getY() + ")");
            }
        }

        List<String> tmp2 = new ArrayList<>();

        for (String s : tmp) {
            String[] abc = s.split(","); // -> String bei ',' aufteilen -> ["(0", "3)"]
            String def = abc[0].replace("(", ""); // -> Index 1 aus Array und Klammer entfernen -> "3"
            if (Integer.parseInt(def) == machine.getCoordinate().getX() && // schauen ob potentielle wand selbe Höhe ist
                    Integer.parseInt(abc[1].replace(")", "")) > machine.getCoordinate().getY() && // schauen ob x von potentieller Wand kleiner gleich als aktuelles x ist
                    (machine.getCoordinate().getY() + anzahl) >= Integer.parseInt(abc[1].replace(")", ""))) {  // schauen ob neue position größer als x von potentieller Wand ist
                tmp2.add(s); //Falls Bedingungen zutreffen, füge die Wand der Temporären Liste hinzu
            }
        }
        if (tmp2.size() == 0) { // falls keine Wände gefunden wurden -> Neue position kann ohne Probleme gesetzt werden
            machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(machine.getCoordinate().getX(), machine.getCoordinate().getY() + anzahl), machine.getFieldId(), machine.getTasks());
        } else {
            // Liste mit den Potentiellen Wänden durchgehen und die größte X Koordinate davon finden
            int max = Integer.parseInt(tmp2.get(0).split(",")[1].replace(")", "")); // Beispiel -> ("0,3") -> 3
            for (int i = 0; i < tmp2.size(); i++) {
                if (max > Integer.parseInt(tmp2.get(i).split(",")[1].replace(")", ""))) {
                    max = Integer.parseInt(tmp2.get(i).split(",")[1].replace(")", ""));
                }
            }
            machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(machine.getCoordinate().getX(), max - 1), machine.getFieldId(), machine.getTasks());
        }
        if (machine.getCoordinate().getY() > field.getHeight() - 1) {
            machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(machine.getCoordinate().getX(), field.getHeight() - 1), machine.getFieldId(), machine.getTasks());
        }
        miningMachineService.miningMachineRepo.save(machine);
    }

    public Boolean transfer(MiningMachine machine, Task task) {
        Field field;
        if (machine.getFieldId() != null)
            field = MainComponent.machineDirections.fieldService.fieldRepo.findById(machine.getFieldId()).get();
        else
            field = MainComponent.machineDirections.fieldService.fieldRepo.findById(task.getGridId()).get();

        Connection connection = null;
        for (TransportTechnology transportTechnology : transportTechnologyService.transportTechnologyRepo.findAll()) {
            if (connection == null) {
                for (Connection connection1 : transportTechnology.getConnection()) {
                    if (connection1.getSourceFieldID().equals(machine.getFieldId()) && connection1.getSourceCoordinate().equals(machine.getCoordinate()))
                        connection = connection1;
                }
            }
        }
        if(connection == null)
            return false;
        machine.setFieldId(connection.getDestinationFieldId());
        machine.setCoordinate(connection.getDestCoordinate());
        miningMachineService.miningMachineRepo.save(machine);
        return false;
    }

    public Boolean entrance(MiningMachine machine, UUID destinationFieldId) {
        for (MiningMachine entry : miningMachineService.miningMachineRepo.findAll()) {
            if (entry.getFieldId() != null) {
                Field field = fieldService.fieldRepo.findById(entry.getFieldId()).get();
                if (field.getId().equals(destinationFieldId)) {
                    if (entry.getCoordinate().getX() == 0 && entry.getCoordinate().getY() == 0) {
                        return false;
                    }
                }
            }
        }
        machine = new MiningMachine(machine.getMiningMachine_ID(), machine.getName(), new Coordinate(0, 0), destinationFieldId, machine.getTasks());

        miningMachineService.miningMachineRepo.save(machine);
        return true;
    }
}
