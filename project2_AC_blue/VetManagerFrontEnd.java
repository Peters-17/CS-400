// --== CS400 Project Two File Header ==--
// Name: Yuan Haoyang
// Email: hyuan73@wisc.edu
// Team: Blue
// Group: AC
// TA: Ilay
// Lecturer: Gary
// Notes to Grader: Noun

import java.util.List;
import java.util.Scanner;

interface VetManagerFrontEndInterface{
	public void run(ExtendedSortedCollectionInterface searchEngine);

	public boolean insertNewDog(ExtendedSortedCollectionInterface engine, Scanner newScanner);

	public boolean searchDogName(ExtendedSortedCollectionInterface engine, Scanner newScanner);

	public boolean searchDogType(ExtendedSortedCollectionInterface engine, Scanner newScanner);
	
	public boolean displayTree(ExtendedSortedCollectionInterface engine, Scanner newScanner);

	public boolean changeVaccinateDate(ExtendedSortedCollectionInterface engine, Scanner newScanner);

	public boolean changeColor(ExtendedSortedCollectionInterface engine, Scanner newScanner);
}
class VetManagerFrontEnd implements VetManagerFrontEndInterface{
	public void run(ExtendedSortedCollectionInterface engine){
		Scanner sc = new Scanner(System.in);
		String order = null;
		boolean isQuit = false;
		boolean returnMenu = true;
		while (!isQuit) {
			if (returnMenu) {
				System.out.print("1. Insert New dog into Database\n"
						+ "2. Search For Dog names\n"
						+ "3. Search For Dog types\n"
						+ "4. Display all dogs in database\n"
						+ "6. change vaccinate date”
						+ "7. change dog color"
						+ "5. Quit\n");

				System.out.print("Please enter the order:");
				order = sc.nextLine();
			}
			if (order.equals("1")) {
				insertNewDog(engine, sc);
			} else if (order.equals("2")) {
				searchDogName(engine, sc);
			} else if (order.equals("3")) {
				searchDogType(engine, sc);
			} else if (order.equals("4")) {
				displayTree(engine, sc);
			} else if (order.equals("5")) {
				changeVaccinateDate(engine, sc);
			} else if (order.equals("6")) {
				changeColor(engine, sc);
			} else if (order.equals("7")) {
				displayTree(engine, sc);
			}

			else if (order.equals("5")) {
				System.out.println("You have quited the program.");
				sc.close();
				isQuit = true;
			} else {
				System.out.println("No such order.");
			}
		}
	}



	public boolean insertNewDog(ExtendedSortedCollectionInterface engine, Scanner newScanner){
		try {
			System.out.println("Input a dog name.");
			String name = newScanner.nextLine();
			System.out.println("Input a born year.");
			int year = Integer.parseInt(newScanner.nextLine());
			System.out.println("Input a dog type.");
			String type = newScanner.nextLine();
			return engine.insert(name, year, type);
		} catch (Exception e) {
			System.out.println("Something bad happened in insertion.");
		}
	}

	public DogNode[] searchDogName(ExtendedSortedCollectionInterface engine, Scanner newScanner){
		try {
			System.out.println("Input a dog name");
			String name = newScanner.nextLine();
			System.out.println("searching for the dog......");
			return engine.searchByName(name);
		} catch (Exception e) {
			System.out.println("Something bad happened in searching names.");
		}
	}

	public DogNode[] searchDogType(ExtendedSortedCollectionInterface engine, Scanner newScanner){
		try {
			System.out.println("Input a dog type");
			String type = newScanner.nextLine();
			System.out.println("searching for the dog......");
			return engine.searchByType(type);
		} catch (Exception e) {
			System.out.println("Something bad happened in searching types.");
		}
	}

	public void displayTree(ExtendedSortedCollectionInterface engine, Scanner newScanner){
		System.out.println(DogDataBase.redBlackTree);
	}

	public boolean changeVaccinateDate(ExtendedSortedCollectionInterface engine, Scanner newScanner){
		try {
			System.out.println("Input a vaccinate date.");
			String date = newScanner.nextLine();
			System.out.println("Input a dog name.");
			String name = newScanner.nextLine();
			System.out.println("Input a born year.");
			int year = Integer.parseInt(newScanner.nextLine());
			System.out.println("Input a dog type.");
			String type = newScanner.nextLine();
			return engine.insert(date, name, year, type);
		} catch (Exception e) {
			System.out.println("Something bad happened in changing date.");
		}
	}

	public boolean changeColor(ExtendedSortedCollectionInterface engine, Scanner newScanner){
		try {
			System.out.println("Input a dog color.");
			String color = newScanner.nextLine();
			System.out.println("Input a dog name.");
			String name = newScanner.nextLine();
			System.out.println("Input a born year.");
			int year = Integer.parseInt(newScanner.nextLine());
			System.out.println("Input a dog type.");
			String type = newScanner.nextLine();
			return engine.insert(color, name, year, type);
		} catch (Exception e) {
			System.out.println("Something bad happened in changing color.");
		}
	}
}
