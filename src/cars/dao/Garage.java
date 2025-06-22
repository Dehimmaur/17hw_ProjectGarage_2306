package cars.dao;

import cars.model.Car;

public interface Garage {

    boolean addCar(Car car);

    Car removeCar(String regNumber);

    Car findCarByNumber(String regNumber);

    Car[] findCarsByCompany(String company);

    Car[] findCarsByEngine(double min, double max);

    Car[]  findCarsByColor(String color);
}
