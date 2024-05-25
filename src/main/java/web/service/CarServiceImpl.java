package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarServiceImpl implements CarService {

    private List<Car> cars;

    {
        cars = new ArrayList<>();

        cars.add(new Car(1, "Muscovite", 23));
        cars.add(new Car(2, "Lada", 11));
        cars.add(new Car(3, "Volga", 14));
        cars.add(new Car(4, "Kopeck", 8));
        cars.add(new Car(5, "Niva", 10));
    }

    public List<Car> getCar(int id) {
        return cars.stream().limit(id).collect(Collectors.toList());
    }
}
