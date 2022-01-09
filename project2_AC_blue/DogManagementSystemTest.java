import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DogManagementSystemTest {

    @Test
    /**
     * Back End Developer Test1 for searchByName
     */ public void backEndDeveloper_searchByName() {
        DogDataBase DogDataBase = new DogDataBase();
        DogDataBase.insert(new DogNode("A", 10, "A"));
        DogDataBase.insert(new DogNode("B", 5, "A"));
        DogDataBase.insert(new DogNode("A", 15, "A"));
        boolean result = true;
        assertEquals(DogDataBase.searchByName("A").length, 2);
        assertEquals(DogDataBase.searchByName("A")[1].getType(), "A");
        assertEquals(DogDataBase.searchByName("A")[1].getType(), "A");
    }

    @Test
    /**
     * Back End Developer Test2 for searchByType
     */ public void backEndDeveloper_searchBytype() {
        DogDataBase DogDataBase = new DogDataBase();
        DogDataBase.insert(new DogNode("A", 10, "A"));
        DogDataBase.insert(new DogNode("B", 5, "B"));
        DogDataBase.insert(new DogNode("C", 15, "A"));
        boolean result = true;
        assertEquals(DogDataBase.searchByType("A").length, 2);
        assertEquals(DogDataBase.searchByType("A")[1].getType(), "A");
        assertEquals(DogDataBase.searchByType("A")[0].getType(), "A");
    }

    @Test
    /**
     * Back End Developer Test2 for changeVaccinationDate
     */ public void backEndDeveloper_changeVaccinationDate() {
        DogDataBase DogDataBase = new DogDataBase();
        DogDataBase.insert(new DogNode("A", 10, "A"));
        DogDataBase.insert(new DogNode("B", 5, "B"));
        DogNode cDog = new DogNode("C", 10, "A");
        cDog.setVaccinationDate("2021");
        DogDataBase.insert(cDog);
        boolean result = true;
        DogDataBase.changeVaccinationDate("1999", "C", 10, "A");
        assertEquals(DogDataBase.searchByName("C")[0].getVaccinationDate(), "1999");
    }
}

