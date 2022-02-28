import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.Map;

public class MovieAnalytics2 {

    // Private
    ArrayList<Movie> movieList;

    // Public
    public MovieAnalytics2() {

        this.movieList = new ArrayList<>();
    }

    public void populateWithData(String fileName) throws IOException {

        try (var file = new BufferedReader(new FileReader(fileName))) {
            Stream<String> r = file.lines();

            r.forEach(row -> {
                String[] file_row = row.split(";");
                String title = file_row[0];
                int releaseYear = Integer.parseInt(file_row[1]);
                int duration = Integer.parseInt(file_row[2]);
                String genre = file_row[3];
                double score = Double.parseDouble(file_row[4]);
                String director = file_row[5];

                movieList.add(new Movie(title, releaseYear, duration, genre, score, director));
            });
        }
    }

    public void printCountByDirector(int n) {

        Map<String, Long> map = this.movieList.stream()
                .collect(Collectors.groupingBy(Movie::getDirector,
                        Collectors.counting()));
        Stream<Map.Entry<String, Long>> stream = mapToStream(map);
        stream.sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                .thenComparing(Map.Entry.<String, Long>comparingByKey()))
                .limit((long)n).forEach(s -> System.out
                        .format("%s: %s movies%n", s.getKey(), s.getValue()));
    }

    public void printAverageDurationByGenre() {

        Map<String, Double> map = this.movieList.stream()
                .collect(Collectors.groupingBy(Movie::getGenre,
                        Collectors.averagingInt(Movie::getDuration)));
        Stream<Map.Entry<String, Double>> stream = mapToStream(map);
        stream.sorted(Map.Entry.<String, Double>comparingByValue()
                        .thenComparing(Map.Entry.<String, Double>comparingByKey()))
                .forEach(s -> System.out
                        .format("%s: %.2f%n", s.getKey(), s.getValue()));
    }

    public void printAverageScoreByGenre() {

        Map<String, Double> map = this.movieList.stream()
                .collect(Collectors.groupingBy(Movie::getGenre,
                        Collectors.averagingDouble(Movie::getScore)));
        Stream<Map.Entry<String, Double>> stream = mapToStream(map);
        stream.sorted(Map.Entry.<String, Double>comparingByValue().reversed()
                .thenComparing(Map.Entry.<String, Double>comparingByKey()))
                .forEach(s -> System.out
                        .format("%s: %.2f%n", s.getKey(), s.getValue()));
    }

    private static <K, V> Stream<Map.Entry<K, V>> mapToStream (Map<K, V> map) {
        return map.entrySet().stream();
    }
}
