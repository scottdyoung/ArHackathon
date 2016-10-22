package hackathon.issinc.com.arhackathon;

import java.util.Map;
import java.util.Vector;

import hackathon.issinc.com.arhackathon.puzzles.Puzzle;
import hackathon.issinc.com.arhackathon.utils.Texture;

public final class GameState {

    final private Map<String, Puzzle> puzzles;
    private String activePuzzle;

    public GameState(final Map<String, Puzzle> puzzles) {
        this.puzzles = puzzles;
    }

    public Puzzle getPuzzle(final String name) {
        return this.puzzles.get(name);
    }

    public void setActivePuzzle(final String puzzleName) {
        this.activePuzzle = puzzleName;
    }

    public Puzzle getActivePuzzle() {
        return this.getPuzzle(this.activePuzzle);
    }

    public String getActiveDataSetName() {
        return this.getActivePuzzle().getDatasetName();
    }

    public Vector<Texture> getActiveTextures() {
        return this.getActivePuzzle().getTextures();
    }
}
