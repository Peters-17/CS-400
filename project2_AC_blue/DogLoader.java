import java.util.Scanner;

interface DogLoaderInterface {
    public DogNodeInterface getUserInput();
}

public class DogLoader implements DogLoaderInterface{
    @Override
    public DogNodeInterface getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please the name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter dog type: ");
        String type = scanner.nextLine();
        System.out.println("Please enter birth year: ");
        int birth = scanner.nextInt();
        DogNodeInterface dog = new DogNode(name, birth, type);
        return dog;
    }
}

class DogLoaderPlaceholderA implements DogLoaderInterface {
    @Override
    public DogNodeInterface getUserInput() {
        return new DogNodePlaceholderA();
    }
}

class DogLoaderPlaceholderB implements DogLoaderInterface {
    @Override
    public DogNodeInterface getUserInput() {
        return new DogNodePlaceholderB();
    }
}

class DogLoaderPlaceholderC implements DogLoaderInterface {
    @Override
    public DogNodeInterface getUserInput() {
        return new DogNodePlaceholderC();
    }
}



