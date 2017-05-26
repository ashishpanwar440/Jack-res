package cursv;

import java.io.Serializable;
import java.util.ArrayList;

public class CursvCharacter implements Serializable {
    final static int TEXT_UNIT_PIXEL = 256;
    /*
    Begin padding positive: number of vertices only shown when there are no
    characters connecting to it.
    Begin padding negative: number of vertices only shown to connect another
    character to. Does not show when there's no character connecting to.
    */
    int beginPadding = 0;
    /*
    End padding positive: number of vertices only shown when there are no
    characters for it to connect to.
    End padding negative: number of vertices only shown to connect another
    character to. Does not show when there's no character for it to connect to.
    */
    int endPadding = 0;
    ArrayList<Vertex> vertexList = new ArrayList<>();
    double characterWidth = 1; // 1 = 1 text unit.
    char characterName;
    
    public CursvCharacter(char character) {
        this.characterName = character;
    }
}
