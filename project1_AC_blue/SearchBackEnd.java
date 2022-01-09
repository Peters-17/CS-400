// --== CS400 Project One File Header ==--
// Name: Zhaowei Yin
// Email: zyin57@wisc.edu
// Team: Blue
// Group: AC
// TA: Ilay
// Lecturer: Gary
// Notes to Grader: Noun

import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

// interface (implemented with proposal)

interface SearchBackEndInterface {
    public void addSong(SongDataInterface song);

    public boolean containsSong(SongDataInterface song);

    // returns list of the titles of all songs that contain the word titleWord in their song title
    public List<String> findTitles(String titleWord);

    // returns list of the artists of all songs that contain the word titleWord in their song title
    public List<String> findArtists(String titleWord);

    // returns the number of songs that contain the word titleWord in their song title, and were published in year
    public int findNumberOfSongsInYear(String titleWord, int year);
}

/**
 * This is the BackEnd Class that implements the hashtable data structure and provides
 * searching functionalities.
 */
public class SearchBackEnd implements SearchBackEndInterface {
    //These are hashtable to store the SongDataInterface obj in association with
    //title, artists, and published year.
    HashtableMap hashtableMapName = new HashtableMap();

    /**
     * This method adds a song to the hashtable
     *
     * @param song SongDataInterface obj to be added
     */
    @Override
    @SuppressWarnings("unchecked")
    public void addSong(SongDataInterface song) {
        Scanner scanner = new Scanner(song.getTitle());
        while (scanner.hasNext()) {
            String key = scanner.next();
            LinkedList<SongDataInterface> list = new LinkedList<>();
            list.add(song);
            boolean stat = hashtableMapName.put(key, list);
            if (!stat) {
                list = (LinkedList<SongDataInterface>) hashtableMapName.get(key);
                list.add(song);
                hashtableMapName.remove(key);
                hashtableMapName.put(key, list);
            }
        }
    }

    /**
     * This method checks if the hashtable contains the given song
     *
     * @param song SongDataInterface obj to be searched
     * @return true if is found false otherwise
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean containsSong(SongDataInterface song) {
        List<SongDataInterface> list = new LinkedList<>();
        Scanner scanner = new Scanner(song.getTitle());
        String key = scanner.next();
        list = (List<SongDataInterface>) hashtableMapName.get(key);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(song.getTitle())) {
                if (list.get(i).getArtist().equals(song.getArtist())) {
                    if (list.get(i).getYearPublished() == song.getYearPublished()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method gives a list of songs that contain the keyword in their title
     *
     * @param titleWord keyword to be searched
     * @return list of songs with the keyword in their title
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String> findTitles(String titleWord) {
        List<String> list = new LinkedList<>();
        try {
            List<SongDataInterface> songList = (List<SongDataInterface>) hashtableMapName.get(titleWord);
            if (songList != null) {
                for (int i = 0; i < songList.size(); i++) {
                    list.add(songList.get(i).getTitle());
                }
            }
            return list;
        }
        catch (NoSuchElementException e){
            return new LinkedList<>();
        }
    }

    /**
     * This gives the list of artists that published songs with the given keyword
     * in their song's title
     *
     * @param titleWord keyword to search
     * @return list of artists matching the condition
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String> findArtists(String titleWord) {
        List<String> list = new LinkedList<>();
        try {
            List<SongDataInterface> songList = (List<SongDataInterface>) hashtableMapName.get(titleWord);
            if (songList != null) {
                for (int i = 0; i < songList.size(); i++) {
                    if (!list.contains(songList.get(i).getArtist())) {
                        list.add(songList.get(i).getArtist());
                    }
                }
            }
            return list;
        }
        catch (NoSuchElementException e){
            return new LinkedList<>();
        }
    }

    /**
     * This method checks how many songs in a particular year contains the given keyword
     * in the title
     *
     * @param titleWord the keyword in the title to be searched
     * @param year      the year the song is published
     * @return number of songs identified
     */
    @Override
    @SuppressWarnings("unchecked")
    public int findNumberOfSongsInYear(String titleWord, int year) {
        int counter = 0;
        try {
            List<SongDataInterface> songList = (List<SongDataInterface>) hashtableMapName.get(titleWord);
            if (songList != null) {
                for (int i = 0; i < songList.size(); i++) {
                    if (songList.get(i).getYearPublished() == year) {
                        counter++;
                    }
                }
            }
            return counter;
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

}

// placeholder(s) (implemented with proposal, and possibly added to later)

class SearchBackEndPlaceholder implements SearchBackEndInterface {

    private SongDataInterface onlySong;

    public void addSong(SongDataInterface song) {
        this.onlySong = song;
    }

    public boolean containsSong(SongDataInterface song) {
        return onlySong.equals(song);
    }

    public List<String> findTitles(String titleWord) {
        List<String> titles = new LinkedList<>();
        if (onlySong.getTitle().contains(titleWord))
            titles.add(onlySong.getTitle());
        return titles;
    }

    public List<String> findArtists(String titleWord) {
        List<String> artists = new LinkedList<>();
        if (onlySong.getArtist().contains(titleWord))
            artists.add(onlySong.getArtist());
        return artists;
    }

    public int findNumberOfSongsInYear(String titleWord, int year) {
        if (onlySong.getYearPublished() == year) return 1;
        return 0;
    }
}
