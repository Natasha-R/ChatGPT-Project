package thkoeln.st.st2praktikum.exercise;

import java.util.ArrayList;
import java.util.List;

public class MiningMachinelist implements AddobjectInterface {
    private List<MiningMachine> miningmachinelist = new ArrayList<MiningMachine>();

    public List<MiningMachine> getMiningmachinelist() {
        return miningmachinelist;
    }

    @Override
    public void add(Object miningmachine) {
        miningmachinelist.add((MiningMachine) miningmachine);

    }
}
