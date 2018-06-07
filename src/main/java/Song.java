/**
 * Song class.
 */
public class Song {
    /**
     * holds song tag/name
     */
    private String name;

    /**
     * Song constructor
     */
    public Song(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Song " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}