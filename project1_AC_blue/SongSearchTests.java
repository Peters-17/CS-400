import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SongSearchTests {

    public static void main(String[] args) throws Exception {
        // Back End Developer Tests
        System.out
            .println("backEndDeveloper_testFindArtists(): " + backEndDeveloper_testFindArtists());
        System.out
            .println("backEndDeveloper_testContainsSong(): " + backEndDeveloper_testContainsSong());
        System.out
            .println("backEndDeveloper_testFindTitles(): " + backEndDeveloper_testFindTitles());
        System.out.println("backEndDeveloper_testFindNumberOfSongsInYear(): "
            + backEndDeveloper_testFindNumberOfSongsInYear());

        // Front End Developer Tests
        System.out
            .println("frontEndDeveloper_TestInsertSong(): " + frontEndDeveloper_TestInsertSong());
        System.out.println("frontEndDeveloper_TestSearchSongByTitle(): "
            + frontEndDeveloper_TestSearchSongByTitle());
        System.out.println("frontEndDeveloper_TestSearchArtistByTitle(): "
            + frontEndDeveloper_TestSearchArtistByTitle());

	// Data Wrangler Test
	System.out
	    .println("DataWranglers_testLoadFile(): " + DataWranglers_testLoadFile());
	System.out
	    .println("DataWranglers_testSongDataConstructor(): " + DataWranglers_testSongDataConstructor());
	System.out.
            println("DataWranglers_testSongDataGetters(): " + DataWranglers_testSongDataGetters());
    }

    // Data Wrangler Code Tests
/**
 * test the load file function in the songLoader class
 * @return true if no exception catch otherwise false
 */
private static boolean DataWranglers_testLoadFile() {
  SongLoader loader = new SongLoader();
  boolean testLoadFile = true;
  try {
    List<SongDataInterface> list1 = loader.loadFile("");
  }catch(Exception e) {
    testLoadFile = false;
  }
  return testLoadFile;
}
/**
 * test the constructor of the songData class
 * @return true if constructor works correctly
 */
private static boolean DataWranglers_testSongDataConstructor() {
  // test the default constructor
  SongData songA = new SongData();
  boolean test1 = false;
  if(songA.getTitle() == null && songA.getArtist() == null && songA.getYearPublished() == -1) {
    test1 = true;
  }
  // test constructor with specific parameter
  SongData songB = new SongData("ABC","DEF",2000);
  boolean test2 = false;
  if(songA.getTitle() == "ABC" && songA.getArtist() == "DEF" && songA.getYearPublished() == 2000) {
    test2 = true;
  }
  return test1 && test2;
}
/**
 * test the getTitle, getArtist and getYearPublished method of the songData class
 * @return true if getter methods works correctly
 */
private static boolean DataWranglers_testSongDataGetters() {
  boolean testA = false;
  boolean testB = false;
  SongData songA = new SongData("DEF","ABC",1990);
  SongData songB = new SongData("CDF","QEC",1880);
  if(songA.getTitle() == "DEF" && songA.getArtist() == "ABC" && songA.getYearPublished() == 1990) {
    testA = true;
  }
  if(songA.getTitle() == "CDE" && songA.getArtist() == "QEC" && songA.getYearPublished() == 1880) {
    testB = true;
  }
  return testA && testB;
}
    // Back End Developer Tests

    /**
     * This tests containsSong() method
     *
     * @return true if tests passed false otherwise
     */
    private static boolean backEndDeveloper_testContainsSong() {
        SearchBackEndInterface backend = new SearchBackEnd();
        SongData song1 = new SongData();
        song1.setArtist("DopeDrop");
        song1.setTitle("Cyberpunk");
        song1.setYear(2015);

        SongData song2 = new SongData();
        song2.setArtist("Locke");
        song2.setTitle("Pylot");
        song2.setYear(2012);

        SongData song3 = new SongData();
        song3.setArtist("Out of Luck");
        song3.setTitle("Tokyo Rose");
        song3.setYear(2012);

        SongData song4 = new SongData();
        song4.setArtist("Luck Locke");
        song4.setTitle("Pylot");
        song4.setYear(2011);

        backend.addSong(song1);
        backend.addSong(song2);
        backend.addSong(song3);
        backend.addSong(song4);

        boolean stat = (backend.containsSong(song1) && backend.containsSong(song2) && backend
            .containsSong(song3) && backend.containsSong(song4));

        return stat;


    }

    /**
     * This tests findTitles method
     *
     * @return true if tests passed false otherwise
     */
    private static boolean backEndDeveloper_testFindTitles() {
        SearchBackEndInterface backend = new SearchBackEnd();
        SongData song1 = new SongData();
        song1.setArtist("DopeDrop");
        song1.setTitle("Cyberpunk");
        song1.setYear(2015);

        SongData song2 = new SongData();
        song2.setArtist("Pylot");
        song2.setTitle("Lucky this Song");
        song2.setYear(2012);

        SongData song3 = new SongData();
        song3.setArtist("Pylot");
        song3.setTitle("Tokyo Rose Lucky");
        song3.setYear(2012);

        SongData song4 = new SongData();
        song4.setArtist("Ssay");
        song4.setTitle("Lucky Diamond");
        song4.setYear(2012);

        backend.addSong(song1);
        backend.addSong(song2);
        backend.addSong(song3);
        backend.addSong(song4);

        List<String> list1 = backend.findTitles("Lucky");
        if (list1.size() != 3) {
            return false;
        }
        if (!list1.get(0).equals("Lucky this Song")) {
            return false;
        }
        if (!list1.get(1).equals("Tokyo Rose Lucky")) {
            return false;
        }
        if (!list1.get(2).equals("Lucky Diamond")) {
            return false;
        }
        if (!backend.findTitles("no").isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * This tests findArtists() method
     *
     * @return true if tests passed false otherwise
     */
    private static boolean backEndDeveloper_testFindArtists() {
        SearchBackEndInterface backend = new SearchBackEnd();
        SongData song1 = new SongData();
        song1.setArtist("DopeDrop");
        song1.setTitle("Cyberpunk");
        song1.setYear(2012);

        SongData song2 = new SongData();
        song2.setArtist("Pylot");
        song2.setTitle("Lucky this Song");
        song2.setYear(2011);

        SongData song3 = new SongData();
        song3.setArtist("Pylot");
        song3.setTitle("Tokyo Rose Lucky");
        song3.setYear(2012);

        SongData song4 = new SongData();
        song4.setArtist("Ssay");
        song4.setTitle("Lucky Diamond");
        song4.setYear(2012);

        backend.addSong(song1);
        backend.addSong(song2);
        backend.addSong(song3);
        backend.addSong(song4);

        if (!backend.findArtists("noartists").isEmpty()) {
            return false;
        }

        List<String> list = new LinkedList<>();
        list.add("Pylot");
        list.add("Ssay");
        if (!backend.findArtists("Lucky").equals(list)) {
            return false;
        }

        list = new LinkedList<>();
        list.add("DopeDrop");
        if (!backend.findArtists("Cyberpunk").equals(list)) {
            return false;
        }
        return true;
    }

    /**
     * This tests FindNumberOfSongsInYear() method
     *
     * @return true if tests passed false otherwise
     */
    private static boolean backEndDeveloper_testFindNumberOfSongsInYear() {
        SearchBackEndInterface backend = new SearchBackEnd();
        SongData song1 = new SongData();
        song1.setArtist("DopeDrop");
        song1.setTitle("Cyberpunk");
        song1.setYear(2012);

        SongData song2 = new SongData();
        song2.setArtist("Pylot");
        song2.setTitle("Lucky this Song");
        song2.setYear(2011);

        SongData song3 = new SongData();
        song3.setArtist("Pylot");
        song3.setTitle("Tokyo Rose Lucky");
        song3.setYear(2012);

        SongData song4 = new SongData();
        song4.setArtist("Ssay");
        song4.setTitle("Lucky Diamond");
        song4.setYear(2012);

        backend.addSong(song1);
        backend.addSong(song2);
        backend.addSong(song3);
        backend.addSong(song4);

        if (backend.findNumberOfSongsInYear("Lucky", 2012) != 2) {
            return false;
        }
        if (backend.findNumberOfSongsInYear("nosongs", 2000) != 0) {
            return false;
        }
        if (backend.findNumberOfSongsInYear("Lucky", 2010) != 0) {
            return false;
        }

        return true;
    }
    // Front End Developer Tests

    /**
     * This tests function 1 of the menu.
     *
     * @return true if tests passed false otherwise
     */
    private static boolean frontEndDeveloper_TestInsertSong() throws IOException {
        boolean test = true;
        TextUITester tester = new TextUITester("1\nQIUYANG\nQIUYANG\n2021\nn\n5\n");
        //1. Create a new TextUITester
        List<SongDataInterface> songs = new SongLoader().loadAllFilesInDirectory("./data/");
        //2. Run the code that you want to test here:
        SearchBackEndInterface engine = new SearchBackEnd();
        for (SongDataInterface song : songs)
            engine.addSong(song);
        SearchFrontEndInterface ui = new SearchFrontEnd();
        ui.run(engine);
        // 3. Check whether the output printed to System.out matches your expectations.
        String output = tester.checkOutput();
        if (output.contains("You have completed song insertion"))
            test = true;
        else
            test = false;
        return test;
    }

    /**
     * This tests SearchSong function from menu.
     *
     * @return true if tests passed false otherwise
     */
    private static boolean frontEndDeveloper_TestSearchSongByTitle() throws IOException {
        boolean test = true;
        TextUITester tester = new TextUITester("2\nLove\nn\n5\n");
        //1. Create a new TextUITester
        List<SongDataInterface> songs = new SongLoader().loadAllFilesInDirectory("./data/");
        //2. Run the code that you want to test here:
        SearchBackEndInterface engine = new SearchBackEnd();
        for (SongDataInterface song : songs)
            engine.addSong(song);
        SearchFrontEndInterface ui = new SearchFrontEnd();
        ui.run(engine);
        // 3. Check whether the output printed to System.out matches your expectations.
        String output = tester.checkOutput();
        if (output.contains("Baby Love") && output.contains("I Cant Make You Love Me"))
            test = true;
        else
            test = false;
        return test;
    }

    /**
     * This tests SearchSong function from menu.
     *
     * @return true if tests passed false otherwise
     */
    private static boolean frontEndDeveloper_TestSearchArtistByTitle() throws IOException {
        boolean test = true;
        TextUITester tester = new TextUITester("3\nLove\nn\n5\n");
        //1. Create a new TextUITester
        List<SongDataInterface> songs = new SongLoader().loadAllFilesInDirectory("./data/");
        //2. Run the code that you want to test here:
        SearchBackEndInterface engine = new SearchBackEnd();
        for (SongDataInterface song : songs)
            engine.addSong(song);
        SearchFrontEndInterface ui = new SearchFrontEnd();
        ui.run(engine);
        // 3. Check whether the output printed to System.out matches your expectations.
        String output = tester.checkOutput();
        if (output.contains("Bonnie Raitt") && output.contains("The Supremes"))
            test = true;
        else
            test = false;
        return test;
    }


    // Integration Manager Tests


}
