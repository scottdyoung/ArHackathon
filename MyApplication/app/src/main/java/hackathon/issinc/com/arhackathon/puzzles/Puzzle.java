package hackathon.issinc.com.arhackathon.puzzles;

import java.util.Vector;

import hackathon.issinc.com.arhackathon.utils.Texture;

public final class Puzzle {
    private String datasetName;
    final private Vector<Texture> textures;

    public Puzzle(final String datasetName,
                  final Vector<Texture> textures) {
        this.datasetName = datasetName;
        this.textures = textures;
    }

    public String getDatasetName() {
        return this.datasetName;
    }

    public Vector<Texture> getTextures() {
        return this.textures;
    }
}
