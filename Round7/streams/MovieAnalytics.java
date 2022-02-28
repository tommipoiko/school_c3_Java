import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.function.Consumer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class MovieAnalytics {

    // Private
    ArrayList<Movie> movieList;

    // Public
    public MovieAnalytics() {

        this.movieList = new ArrayList<>();
    }

    public static Consumer<Movie> showInfo() {

        Consumer<Movie> cm = new Consumer<Movie>() {
            @Override
            public void accept(Movie t) {
                System.out.format("%s (By %s, %d)\n", t.getTitle(), t.getDirector(), t.getReleaseYear());
            }
        };

        return cm;
    }

    public void populateWithData(String fileName) throws IOException {

        try(var file = new BufferedReader(new FileReader(fileName))) {
            String row = null;
            while ((row = file.readLine()) != null) {
                String[] file_row = row.split(";");
                String title = file_row[0];
                int releaseYear = Integer.parseInt(file_row[1]);
                int duration = Integer.parseInt(file_row[2]);
                String genre = file_row[3];
                double score = Double.parseDouble(file_row[4]);
                String director = file_row[5];

                movieList.add(new Movie(title, releaseYear, duration, genre, score, director));
            }
        }
    }

    public Stream<Movie> moviesAfter(int year) {

        ArrayList<Movie> tempList = new ArrayList<>();

        for (var i : movieList) {
            if (i.getReleaseYear() >= year) {
                tempList.add(i);
            }
        }

        return tempList.stream().sorted(Comparator.comparing(Movie::getReleaseYear).thenComparing(Movie::getTitle));
    }

    public Stream<Movie> moviesBefore(int year) {

        ArrayList<Movie> tempList = new ArrayList<>();

        for (var i : movieList) {
            if (i.getReleaseYear() <= year) {
                tempList.add(i);
            }
        }

        return tempList.stream().sorted(Comparator.comparing(Movie::getReleaseYear).thenComparing(Movie::getTitle));
    }

    public Stream<Movie> moviesBetween(int yearA, int yearB) {

        ArrayList<Movie> tempList = new ArrayList<>();

        for (var i : movieList) {
            if (i.getReleaseYear() >= yearA && i.getReleaseYear() <= yearB) {
                tempList.add(i);
            }
        }

        return tempList.stream().sorted(Comparator.comparing(Movie::getReleaseYear).thenComparing(Movie::getTitle));
    }

    public Stream<Movie> moviesByDirector(String director) {

        ArrayList<Movie> tempList = new ArrayList<>();

        for (var i : movieList) {
            if (i.getDirector().equals(director)) {
                tempList.add(i);
            }
        }

        return tempList.stream().sorted(Comparator.comparing(Movie::getReleaseYear).thenComparing(Movie::getTitle));
    }
}
