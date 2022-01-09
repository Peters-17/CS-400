// --== CS400 Project One File Header ==--
// Name: Yang Qiu
// Email: qiu67@wisc.edu
// Team: Blue
// Group: AC
// TA: Ilay
// Lecturer: Gary
// Notes to Grader: Noun

import java.util.List;
import java.util.Scanner;

interface SearchFrontEndInterface {
    public void run(SearchBackEndInterface searchEngine);

    // Here is a sample of the minimal set of options that 
    // this front end will support:

    // SongSearch Command Menu:
    // 1. Insert New Song into Database
    // 2. Search For Song Titles by Words in those Title
    // 3. Search For Artists by Words in their Song Titles
    // 4. Display Years of Songs with Word in Title as Histogram
    // 5. Quit
}

// public class (implemented primarilly in final app week)


public class SearchFrontEnd implements SearchFrontEndInterface {

    @Override
    public void run(SearchBackEndInterface searchEngine) {
        Scanner sc = new Scanner(System.in);
        String order = null;
        boolean isQuit = false;
        boolean returnMenu = true;
        while (!isQuit) {
            if (returnMenu) {
                System.out.print("1. Insert New Song into Database\n"
                    + "2. Search For Song Titles by Words in those Title\n"
                    + "3. Search For Artists by Words in their Song Titles\n"
                    + "4. Display Years of Songs with Word in Title as Histogram\n" + "5. Quit\n");
                System.out.print("Please enter the order:");
                order = sc.nextLine();
            }
            if (order.equals("1")) {
                System.out.println("Please enter the title of the song.");
                String title = sc.nextLine();
                System.out.println("Please enter the artist of the song.");
                String artist = sc.nextLine();
                System.out.println("Please enter the pubilshed year of the song.");
                int year = sc.nextInt();
                sc.nextLine(); // read newline after double
                try {
                    SongData song = new SongData();
                    song.setTitle(title);
                    song.setArtist(artist);
                    song.setYear(year);
                    searchEngine.addSong(song);
                } catch (Exception e) {
                    System.out.println("Something bad happened in insertion.");
                }
                System.out.println(
                    "You have completed song insertion, press y to keep adding song, "
                        + "n to go back to menu.");
                String keepAdd = sc.nextLine();
                if (keepAdd.equals("n")) {
                    returnMenu = true;
                } else {
                    returnMenu = false;
                }
            } else if (order.equals("2")) {
                System.out.println("What title you want to search?");
                String title = sc.nextLine();
                try {
                    List<String> songList = searchEngine.findTitles(title);
                    System.out.println("This is the list of songs that contain you title: ");
                    System.out.println(songList.toString());
                } catch (Exception e) {
                    System.out.println("Something bad happened in searching for title.");
                }
                System.out.println(
                    "You have completed searching for title, press y to keep adding searching,"
                        + " n to go back to menu.");
                String keepSearchTitle = sc.nextLine();
                if (keepSearchTitle.equals("n")) {
                    returnMenu = true;
                } else {
                    returnMenu = false;
                }

            } else if (order.equals("3")) {
                System.out.println("What titleWord you want to search?");
                String titleWord = sc.nextLine();
                try {
                    List<String> songList = searchEngine.findArtists(titleWord);
                    System.out.println("This is the list of artists that contain you title word: ");
                    System.out.println(songList.toString());
                } catch (Exception e) {
                    System.out.println("Something bad happened in searching for artist.");
                }
                System.out.println("You have completed searching for artists, "
                    + "press y to keep adding searching, n to go back to menu.");
                String keepSearchArtists = sc.nextLine();
                if (keepSearchArtists.equals("n")) {
                    returnMenu = true;
                } else {
                    returnMenu = false;
                }

            } else if (order.equals("4")) {
                System.out.println("Type the title of the song.");
                String title = sc.nextLine();
                int yearMin = 1900;
                int yearMax = 2021;
                for (int i = yearMin; i <= yearMax; i++) {
                    int number = searchEngine.findNumberOfSongsInYear(title, i);
                    if (number != 0) {
                        System.out.print(i + " :");
                        for (int j = 0; j < number; j++) {
                            System.out.print("#");
                        }
                        System.out.print(" " + number);
                        System.out.println();
                    }
                }
                System.out.println("Press y to keep drawing histogram, n to go back to menu.");
                String keepDrawing = sc.nextLine();
                if (keepDrawing.equals("n")) {
                    returnMenu = true;
                } else {
                    returnMenu = false;
                }
            } else if (order.equals("5")) {
                System.out.println("You have quited the program.");
                sc.close();
                isQuit = true;
            } else {
                System.out.println("No such order.");
            }
        }
    }

}


// placeholder(s) (implemented with proposal, and possibly added to later)
class SearchFrontEndPlaceholder implements SearchFrontEndInterface {
    public void run(SearchBackEndInterface searchEngine) {
        System.out.println("This front end has not been implemented yet.");
    }
}
