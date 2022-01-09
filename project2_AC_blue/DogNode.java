interface DogNodeInterface {
    public String getName();

    public String getType();

    public int getBirthYear();

    public boolean vaccinationStatus();

    public boolean isRed();

    public String getVaccinationDate();

    public void setVaccinationStatus(boolean flag);

    public void setIsRed(boolean flag);

    public void setVaccinationDate(String date);

    public String toString();
}


public class DogNode implements DogNodeInterface, Comparable<DogNode> {
    private final String name;
    private final int birthYear;
    private final String type;
    private boolean isVaccinated;
    private String vaccinationDate;
    private boolean isRed;
    public String color;
    public DogNode leftChild;
    public DogNode rightChild;
    public DogNode parent;

    public DogNode(String name, int birthYear, String type) {
        this.name = name;
        this.birthYear = birthYear;
        this.type = type;
        this.isVaccinated = false;
        this.vaccinationDate = "";
        this.isRed = false;
    }

    @Override public String getName() {
        return this.name;
    }

    @Override public String getType() {
        return this.type;
    }

    @Override public int getBirthYear() {
        return this.birthYear;
    }

    @Override public boolean vaccinationStatus() {
        return this.isVaccinated;
    }

    @Override public boolean isRed() {
        return this.isRed;
    }

    @Override public String getVaccinationDate() {
        return this.vaccinationDate;
    }

    @Override public void setVaccinationStatus(boolean flag) {
        this.isVaccinated = flag;
    }

    @Override public void setIsRed(boolean flag) {
        this.isRed = flag;
    }

    @Override public void setVaccinationDate(String date) {
        this.vaccinationDate = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override public String toString() {
        return "The name of the dog is : " + this.name + ", the birth year of the dog is: "
            + this.birthYear + ", the dog type is: " + this.type + ". " + "The dog is vaccinated: "
            + this.isVaccinated + ", and its vaccination date is: " + this.vaccinationDate
            + ". The color of the dog is: " + this.color+"\n";
    }

    @Override public int compareTo(DogNode o) {
        int compareName = this.name.compareTo(o.getName());
        int compareYear = this.birthYear - o.getBirthYear();
        int compareType = this.type.compareTo(o.getType());
        return compareType + compareYear + compareName;
    }
}



class DogNodePlaceholderA implements DogNodeInterface, Comparable<DogNode> {
    DogNodeInterface dog1 = new DogNode("Name1", 2018, "Type1");

    @Override public String getName() {
        return dog1.getName();
    }

    @Override public String getType() {
        return dog1.getType();
    }

    @Override public int getBirthYear() {
        return dog1.getBirthYear();
    }

    @Override public boolean vaccinationStatus() {
        return dog1.vaccinationStatus();
    }

    @Override public boolean isRed() {
        return dog1.isRed();
    }

    @Override public String getVaccinationDate() {
        return dog1.getVaccinationDate();
    }

    @Override public void setVaccinationStatus(boolean flag) {
        dog1.setVaccinationStatus(flag);
    }

    @Override public void setIsRed(boolean flag) {
        dog1.setIsRed(flag);
    }

    @Override public void setVaccinationDate(String date) {
        dog1.setVaccinationDate(date);
    }

    @Override public int compareTo(DogNode o) {
        return 2;
    }
}


class DogNodePlaceholderB implements DogNodeInterface, Comparable<DogNode> {
    DogNodeInterface dog2 = new DogNode("Name2", 2020, "Type2");

    @Override public String getName() {
        return dog2.getName();
    }

    @Override public String getType() {
        return dog2.getType();
    }

    @Override public int getBirthYear() {
        return dog2.getBirthYear();
    }

    @Override public boolean vaccinationStatus() {
        return dog2.vaccinationStatus();
    }

    @Override public boolean isRed() {
        return dog2.isRed();
    }

    @Override public String getVaccinationDate() {
        return dog2.getVaccinationDate();
    }

    @Override public void setVaccinationStatus(boolean flag) {
        dog2.setVaccinationStatus(flag);
    }

    @Override public void setIsRed(boolean flag) {
        dog2.setIsRed(flag);
    }

    @Override public void setVaccinationDate(String date) {
        dog2.setVaccinationDate(date);
    }

    @Override public int compareTo(DogNode o) {
        return 3;
    }
}


class DogNodePlaceholderC implements DogNodeInterface, Comparable<DogNode> {
    DogNodeInterface dog3 = new DogNode("Name3", 2015, "Type3");

    @Override public String getName() {
        return dog3.getName();
    }

    @Override public String getType() {
        return dog3.getType();
    }

    @Override public int getBirthYear() {
        return dog3.getBirthYear();
    }

    @Override public boolean vaccinationStatus() {
        return dog3.vaccinationStatus();
    }

    @Override public boolean isRed() {
        return dog3.isRed();
    }

    @Override public String getVaccinationDate() {
        return dog3.getVaccinationDate();
    }

    @Override public void setVaccinationStatus(boolean flag) {
        dog3.setVaccinationStatus(flag);
    }

    @Override public void setIsRed(boolean flag) {
        dog3.setIsRed(flag);
    }

    @Override public void setVaccinationDate(String date) {
        dog3.setVaccinationDate(date);
    }

    @Override public int compareTo(DogNode o) {
        return -1;
    }
}
