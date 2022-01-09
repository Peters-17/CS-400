import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
// --== CS400 Project One File Header ==--
// Name: Qi Dong
// Email: qdong36@wisc.edu
// Team: blue
// Group: AC
// TA: Ilay TA
// Lecturer: Gary
// Notes to Grader: from data Wrangler

interface SongLoaderInterface {
  public List<SongDataInterface> loadFile(String csvFilePath) throws IOException;

  public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath) throws IOException;
}


/**
 * This class read from given csv file and load the content into the Linkedlist using Songdata
 * object
 * 
 * @author Qi Dong
 */
public class SongLoader implements SongLoaderInterface {
  // create the linked list which stores the song data object
  LinkedList<SongDataInterface> list = new LinkedList<>();


  /**
   * count the number of quotes in a string
   *
   * @param input the string which need to be count
   * @return the number of quotes contained in the string
   */
  private int countQuotes(String input) {
    int num = 0;
    for (int j = 0; j < input.length(); j++) {
      if (input.charAt(j) == '"') {
        num++;
      }
    }
    return num;
  }

  /**
   * load file
   * 
   * @param csvFilePath the path of the file
   */
  @Override
  public List<SongDataInterface> loadFile(String csvFilePath) throws IOException {
    int title = -1;
    int artist = -1;
    int year = -1;
    String theTitle;
    String theArtist;
    int theYear;
    String line;
    File file = new File(csvFilePath);// create file instance
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

    String firstLine = bufferedReader.readLine();
    // get the first line
    while (firstLine.charAt(0) == '\uFEFF') {
      firstLine = firstLine.substring(1);
    }
    String[] lines = change(firstLine.split(","));// use split method to split the line with comma

    for (int i = 0; i < lines.length; i++) {
      if (lines[i].equals("title")) {
        title = i;
      }
      if (lines[i].equals("artist")) {
        artist = i;
      }
      if (lines[i].equals("year") || lines[i].equals("released")) {
        year = i;
      }
    }
    // scan the whole file
    int lineNo = 1;
    while ((line = bufferedReader.readLine()) != null) {
      System.out.println("Parse line " + (lineNo++) + ": " + line);
      lines = change(line.split(","));
      System.out.println("Parsed: " + Arrays.toString(lines));
      theTitle = lines[title];
      theArtist = lines[artist];
      theYear = Integer.parseInt((lines[year].trim().substring(lines[year].trim().length() - 4)));
      System.out.printf("[title=%s][artist=%s][year=%s]%n", theTitle, theArtist, theYear);
      System.out.println("---------------");
      list.add(new SongData(theTitle, theArtist, theYear));
    }

    bufferedReader.close();
    return list;
  }

  /**
   * create the new array and remove the double quotes on the outside
   * 
   * @param formerStr the original array of string
   * @return an array of string with meaningful texts at each index
   */
  private String[] change(String[] formerStr) {
    int num = 0;
    int numQ;
    String strAfter;
    int currentSize = 0;
    for (int i = 0; i < formerStr.length; i++) {
      numQ = countQuotes(formerStr[i]);
      if (numQ % 2 == 0) {
        num++;
      }
    }

    int newSize = num + (formerStr.length - num) / 2;
    String[] newStr = new String[newSize];

    for (int j = 0; j < formerStr.length; j++) {
      numQ = countQuotes(formerStr[j]);

      if (numQ % 2 == 0) {
        newStr[currentSize] = formerStr[j];
        currentSize++;
      } else {
        int numC = 1;
        boolean test = false;
        while (!test) {
          if (countQuotes(formerStr[j + numC]) % 2 == 0) {
            numC += 1;
          } else {
            test = true;
          }
        }

        for (int k = 1; k <= numC; k++) {
          formerStr[j] = formerStr[j] + "," + formerStr[j + k];
        }

        newStr[currentSize] = formerStr[j];
        currentSize++;
        j += numC;
      }
    }

    for (int i = 0; i < newStr.length; i++) {
      if (newStr[i] != null && newStr[i].charAt(0) == '"') {
        strAfter = newStr[i].substring(1, newStr[i].length() - 1);// remove ""
        newStr[i] = strAfter;
      }
    }

    return newStr;
  }

  /**
   * load file into one directory
   * 
   * @param directoryPath the path of the directory
   */
  @Override
  public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath) throws IOException {

    File directory = new File(directoryPath);
    String[] files = directory.list();
    for (String file : files) {
      loadFile(directoryPath + "/" + file);
    }

    return list;
  }
}


class SongLoaderPlaceholder implements SongLoaderInterface {
  public List<SongDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
    List<SongDataInterface> list = new LinkedList<>();
    list.add(new SongDataPlaceholderA());
    list.add(new SongDataPlaceholderB());
    return list;
  }

  public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath)
      throws FileNotFoundException {
    List<SongDataInterface> list = new LinkedList<>();
    list.add(new SongDataPlaceholderA());
    list.add(new SongDataPlaceholderB());
    list.add(new SongDataPlaceholderC());
    return list;
  }
}

