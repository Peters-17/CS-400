public class DogManagementApp{

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Dog management system!");
	DogDataBase list = new DogDataBase();
	list.insert();
	list.empty();
	list.search();
    }

}
