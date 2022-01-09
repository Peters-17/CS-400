// --== CS400 Project Three File Header ==--
// Name: Yang Qiu
// Email: qiu67@wisc.edu
// Team: Blue
// Group: AC
// TA: Ilay
// Lecturer: Gary
// Notes to Grader: Noun
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * contains necessary methods to load data
 */
public class CSVLoader implements CSVLoaderInterface {
    private ArrayList<WIStatePark> data;

    /**
     * read in a csv file that reads in lines from a csv file in format of
     * "[source state park name],[target state park name],[distance]"
     * and return a ArrayList;
     *
     * @param csvPath file to be readed
     * @return a ArrayList of WIStateParks
     * @throws IOException if the csv file is not found
     */
    @Override
    public ArrayList<WIStatePark> load(String csvPath) throws IOException {
        String source = "";
        String target = "";
        int weight = 0;
        String line;
        File file = new File(csvPath);// create file instance
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String firstLine = bufferedReader.readLine();
        // get the first line
        while (firstLine.charAt(0) == '\uFEFF') {
            firstLine = firstLine.substring(1);
        }
        String[] lines =
            change(firstLine.split(","));// use split method to split the line with comma
        ArrayList<WIStatePark> list = new ArrayList<WIStatePark>();
        // scan the whole file
        int lineNo = 1;
        while ((line = bufferedReader.readLine()) != null) {
            lines = change(line.split(","));
            source = lines[0];
            target = lines[1];
            weight = Integer.parseInt(lines[2]);
            WIStatePark park = new WIStatePark(source);
            Path path = new ParkPath(source, target, weight);
            boolean isInsert = true;
            for (WIStatePark wiStatePark : list) {
                if (wiStatePark.getName().equals(park.getName())) {
                    isInsert = false;
                    LinkedList paths = (LinkedList) wiStatePark.getPaths().clone();
                    ParkPath newPath = new ParkPath(source, target, weight);
                    paths.add(newPath);
                    wiStatePark.setPaths(paths);
                    break;
                }
            }
            if (isInsert == true) {
                LinkedList paths = new LinkedList();
                ParkPath newPath = new ParkPath(source, target, weight);
                paths.add(newPath);
                park.setPaths(paths);
                list.add(park);
            }
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
}






