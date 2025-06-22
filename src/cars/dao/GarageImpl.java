package cars.dao;

import cars.model.Car;

import java.util.function.Predicate;

public class GarageImpl implements Garage{
    private Car[] cars;
    private int size;

    public GarageImpl(int capacity) {cars = new Car[capacity];}

    @Override
    public boolean addCar(Car car) {
        if (size == cars.length) return false;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                cars[i] = car;
                size = i + 1;
                return true;
            }
            if (car.getRegNumber() == cars[i].getRegNumber()) return false;
        }
        return false;
    }

    @Override
    public Car removeCar(String regNumber) {
        Car removeCar;
        for (int i = 0; i < size; i++) {
            if (cars[i].getRegNumber().equals(regNumber)){
                removeCar = cars[i];
                cars[i] = cars[size - 1];
                size--;
                return removeCar;
            }
        }
        return null;
    }

    @Override
    public Car findCarByNumber(String regNumber) {
        for (Car car : cars) {
            if (car == null) return null;
            if (car.getRegNumber() == regNumber) return car;
        }
        return null;
    }

    @Override
    public Car[] findCarsByCompany(String company) {
        Predicate<Car> predicate = new Predicate<Car>() {
            @Override
            public boolean test(Car car) {
                return (car.getCompany() == company);
            }
        };
        return findCarsByPredicate(predicate);
    }

    @Override
    public Car[] findCarsByEngine(double min, double max) {
        Predicate<Car> predicate = new Predicate<Car>() {
            @Override
            public boolean test(Car car) {
                return car.getEngine() >= min && car.getEngine() <= max;
            }
        };
        return findCarsByPredicate(predicate);
    }

    @Override
    public Car[] findCarsByColor(String color) {
        Predicate<Car> predicate = new Predicate<Car>() {
            @Override
            public boolean test(Car car) {
                return (car.getColor() == color);
            }
        };
        return findCarsByPredicate(predicate);
    }

    private Car[] findCarsByPredicate (Predicate<Car> predicate) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(cars[i])) {
                count++;
            }
        }
        Car[] selectedCars  = new Car[count];
        for (int i = 0, j = 0; i < size; i++) {
            if (predicate.test(cars[i])) {
                selectedCars[j++] = cars[i];
            }
        }
        return selectedCars;
    }
}
