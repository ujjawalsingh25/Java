import java.util.ArrayList;
import java.util.Scanner;

class Cinema{
    private int movieId;
    private String director;
    private int rating;
    private int budget;

    public Cinema(int movieId, String director, int rating, int budget) {
        this.movieId = movieId;
        this.director = director;
        this.rating = rating;
        this.budget = budget;
    }

    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public int getBudget() {
        return budget;
    }
    public void setBudget(int budget) {
        this.budget = budget;
    }
} 

class I_Classes6 
{
    public static int findAvgBudgetByDirector(Cinema[] movie, String director){
        int sum=0, count=0;
        for(Cinema movieIter : movie) {
            if(movieIter.getDirector().equalsIgnoreCase(director)) {
                sum += movieIter.getBudget();
                count++;
            }
        }
        int avg = sum/count;
        return avg;
    }

    // public static Cinema getMovieByRatingBudget (Cinema[] movie, int budget, int rating){
    //     Cinema ans = null;
    //     for(Cinema movieIter : movie) {
    //         if(movieIter.getRating() == rating && movieIter.getBudget() == budget) {
    //             ans = movieIter;
    //         }
    //     }
    //     return ans;
    // }
    public static Cinema[] getMovieByRatingBudget (Cinema[] movie, int budget, int rating){
        // Cinema[] ans = null;
        ArrayList<Cinema> ans = new ArrayList<>();
        for(Cinema movieIter : movie) {
            if(movieIter.getRating() == rating && movieIter.getBudget() == budget) {
                ans.add(movieIter);
            }
        }
        return ans.toArray(new Cinema[0]);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Cinema[] movie = new Cinema[4];
        for(int i=0;i<4;i++) {
            int id = scanner.nextInt();
            scanner.nextLine();
            String director = scanner.nextLine();
            int rating = scanner.nextInt();
            scanner.nextLine();
            int budget = scanner.nextInt();
            scanner.nextLine();
            
            movie[i] = new Cinema(id, director, rating, budget);
        }

        String director = scanner.nextLine();
        int rating = scanner.nextInt();scanner.nextLine();
        int budget = scanner.nextInt();scanner.nextLine();

        int avg = findAvgBudgetByDirector(movie, director);
        System.out.println(avg);

        // Cinema ans = getMovieByRatingBudget(movie, budget, rating);
        // System.out.println(ans.getMovieId());
        Cinema[] ans = getMovieByRatingBudget(movie, budget, rating);
        for(Cinema movieIter : ans) {
            System.out.println(movieIter.getMovieId());
        }

        scanner.close();
    }
}