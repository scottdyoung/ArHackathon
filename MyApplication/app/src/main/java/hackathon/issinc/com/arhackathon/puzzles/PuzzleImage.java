package hackathon.issinc.com.arhackathon.puzzles;

import java.nio.Buffer;

public interface PuzzleImage {
    public int getNumVertices();
    public int getNumObjectIndex();
    public Buffer getVertices();
    public Buffer getTexCoords();
    public int getNumObjectVertex();
    public Buffer getIndices();
}
