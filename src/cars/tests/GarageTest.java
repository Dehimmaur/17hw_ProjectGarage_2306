package cars.tests;

import cars.dao.Garage;
import cars.dao.GarageImpl;
import cars.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GarageTest {
    Garage garage;
    Car[] myGarage;

    @BeforeEach
    void setUp() {
        garage = new GarageImpl(7);
        myGarage = new Car[5];
        myGarage[0] = new Car("10-345-28", "500 Cabrio","Fiat", 1.2, "Red");
        myGarage[1] = new Car("20-345-28", "Forester","Subaru", 2.5, "Black");
        myGarage[2] = new Car("30-345-28", "Octavia RS","Skoda", 2.0, "Green");
        myGarage[3] = new Car("40-345-28", "F 150 Raptor","Ford", 5.2, "Yellow");
        myGarage[4] = new Car("50-777-28", "Macan Turbo","Porsche", 3.0, "Red");
        for (Car car : myGarage) {
            garage.addCar(car);
        }
    }

    @Test
    void addCar() {
        Car futureCar1 = new Car("00-000-00","F40","Ferrari", 2.9, "White");
        Car futureCar2 = new Car("11-111-11","Defender", "Land Rover", 2.4, "Blue");
        Car futureCar3 = new Car("22-222-22","Cooper Cabrio", "Mini", 2.5, "Grey");
        assertFalse(garage.addCar(myGarage[4]));
        assertTrue(garage.addCar(futureCar1));
        assertTrue(garage.addCar(futureCar2));
        assertFalse(garage.addCar(futureCar3));
        assertFalse(garage.addCar(futureCar1));
    }

    @Test
    void removeCar() {
        Car futureCar1 = new Car("00-000-00","F40","Ferrari",2.9, "White");
        assertEquals(myGarage[0], garage.removeCar("10-345-28"));
        assertNull(garage.removeCar("10-345-28"));

        assertTrue(garage.addCar(futureCar1));
        assertEquals(futureCar1,garage.removeCar("00-000-00"));
    }

    @Test
    void findCarByNumber() {
        assertEquals(myGarage[2],garage.findCarByNumber("30-345-28"));
        assertNull(garage.findCarByNumber("30-555-28"));
    }

    @Test
    void findCarsByCompany() {
        Car newCar1 = new Car("50-888-28", "Cayene", "Porsche",2.9, "Black");
        Car newCar2 = new Car("50-999-28", "911","Porsche", 3.7, "Yellow");

        Car[] actual = garage.findCarsByCompany("Porsche");
        Car[] expected = {myGarage[4]};
        assertArrayEquals(expected, actual);

        garage.addCar(newCar1);
        garage.addCar(newCar2);
        Car[] actual2 = garage.findCarsByCompany("Porsche");
        Car[] expected2 = {myGarage[4], newCar1, newCar2};
        assertArrayEquals(expected2, actual2);
    }

    @Test
    void findCarsByEngine() {
        double min = 3.0;
        double max = 4.5;
        Car[] actual = garage.findCarsByEngine(min, max);
        Car[] expected = {myGarage[4]};
        assertArrayEquals(expected, actual);
    }

    @Test
    void findCarsByColor() {
        String color = "Red";
        Car[] actual = garage.findCarsByColor(color);
        Car[] expected = {myGarage[0], myGarage[4]};
        assertArrayEquals(expected, actual);
    }
}
