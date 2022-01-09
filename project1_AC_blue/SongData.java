// --== CS400 Project One File Header ==--
// Name: Yijie Shi
// Email: shi257@wisc.edu
// Team: blue
// Group: AN
// TA: April Roszkowski
// Lecturer: Gary Dahl-Noland
// Notes to Grader: SongData class from Data Wrangler

// public class (implemented primarily in final app week)

/**
 * This class has an implementation for SongData objects, which contain information about the name, 
 * performing artist, and publication year of a particular song.
 *
 *
 * @author Yijie Shi
 *
 */
public class SongData implements SongDataInterface {

    // private field that stores the title of this song
    private String title;

    // private field that stores the artist of this song
    private String artist;

    // private filed that stores the publication year of this song
    private int year;

    /**
     * Constructor of SongData class
     *
     * @param title : the title of this song
     * @param artist : the artist of this song
     * @param year: the publication year of this song
     */
    public SongData(String title, String artist, int year) {
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    /**
     * Default constructor of SongData class
     */
    public SongData() {
        this.title = null;
        this.artist = null;
        this.year = -1;
    }

    /**
     * Set the name for this SongData object
     *
     * @param name : the name of the song
     */
    public void setTitle(String name) {
        this.title = name;
    }

    /**
     * Set the name of the artist for this SongData object
     *
     * @param artist : the name of the performing artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Set the publication year for this SongData object
     *
     * @param year : the publication year of this song
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get the title of this song
     *
     * @return the title of this song
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Get the performing artist of this song
     *
     * @return the name of the artist
     */
    @Override
    public String getArtist() {
        return this.artist;
    }

    /**
     * Get the publication year of this song
     *
     * @return the year published
     */
    @Override
    public int getYearPublished() {
        return this.year;
    }
}

//placeholder(s) (implemented with proposal, and possibly added to later)
class SongDataPlaceholderA implements SongDataInterface {
    public String getTitle() { return "Song A Vowel"; }
    public String getArtist() { return "Artist X"; }
    public int getYearPublished() { return 1900; }
}
class SongDataPlaceholderB implements SongDataInterface {
    public String getTitle() { return "Song B Consonant"; }
    public String getArtist() { return "Artist Y"; }
    public int getYearPublished() { return 2000; }
}
class SongDataPlaceholderC implements SongDataInterface {
    public String getTitle() { return "Song C Consonant"; }
    public String getArtist() { return "Artist X"; }
    public int getYearPublished() { return 2021; }
}
