import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        //
    }

    public void printAverageDurationByGenre() {

        //
    }

    public void printAverageScoreByGenre() {

        //
    }
}
