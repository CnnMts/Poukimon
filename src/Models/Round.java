package Models;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<String> actions;

    public Round() {
        this.actions = new ArrayList<>();
    }

    public void addAction(String action) {
        actions.add(action);
    }

    public List<String> getActions() {
        return actions;
    }
}
