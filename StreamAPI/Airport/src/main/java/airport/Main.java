package airport;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static long findCountAircraftWithModelAirbus(Airport airport, String model) {
        //TODO Метод должен вернуть количество самолетов указанной модели.
        // подходят те самолеты, у которых name начинается со строки model
        return airport.getTerminals().stream()
                .flatMap(t -> Stream.concat(t.getFlights().stream().map(Flight::getAircraft), t.getParkedAircrafts().stream()))
                .filter(a -> a.getModel().startsWith(model))
                .count();
    }

    public static Map<String, Integer> findMapCountParkedAircraftByTerminalName(Airport airport) {
        //TODO Метод должен вернуть словарь с количеством припаркованных самолетов в каждом терминале.
        return airport.getTerminals().stream()
                .collect(Collectors.toMap(
                        Terminal::getName,
                        t -> t.getParkedAircrafts().size()
                ));
    }

    public static List<Flight> findFlightsLeavingInTheNextHours(Airport airport, int hours) {
        //TODO Метод должен вернуть список отправляющихся рейсов в ближайшее количество часов.
        Instant now = Instant.now();
        Instant end = now.plusSeconds(hours * 36000L);

        return airport.getTerminals().stream()
                .flatMap(t -> t.getFlights().stream())
                .filter(f -> f.getType() == Flight.Type.DEPARTURE)
                .filter(f -> f.getDate().isAfter(now)).filter(f -> f.getDate().isBefore(end))
                .sorted(Comparator.comparing(Flight::getDate))
                .collect(Collectors.toList());
    }

    public static Optional<Flight> findFirstFlightArriveToTerminal(Airport airport, String terminalName) {
        //TODO Найти ближайший прилет в указанный терминал.
        Instant now = Instant.now();
        return airport.getTerminals().stream()
                .filter(t -> t.getName().equals(terminalName))
                .flatMap(t -> t.getFlights().stream())
                .filter(f -> f.getType() == Flight.Type.ARRIVAL)
                .filter(f -> f.getDate().isAfter(now))
                .min(Comparator.comparing(Flight::getDate));
    }
}
