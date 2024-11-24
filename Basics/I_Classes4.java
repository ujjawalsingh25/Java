import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Player {
    int playerId;
    String skill;
    String level;
    int points;

    public Player(int playerId, String skill, String level, int points) {
        this.playerId = playerId;
        this.skill = skill;
        this.level = level;
        this.points = points;

    }

    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
}

class I_Classes4
{
    public static int findPointsForGivenSkill(Player[] players, String skill) {
        int points  = 0;
        for(Player playerIter : players) {
            if(playerIter.getSkill().equalsIgnoreCase(skill)) 
                points += playerIter.getPoints();
        }
        return points;
    }

    // public static ArrayList<Player> getPlayerBasedOnLevel(Player[] players, String skill, String level) {
    public static Player getPlayerBasedOnLevel(Player[] players, String skill, String level) {
        // ArrayList<Player> ans = new ArrayList<>();
        Player ans = null;
        for(Player playerIter : players) {
            if(
                playerIter.getSkill().equalsIgnoreCase(skill) && 
                playerIter.getLevel().equalsIgnoreCase(level) &&
                playerIter.getPoints() >= 20
            // )   ans.add(playerIter);
            )   ans = playerIter;
        }

        return ans;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Player player[] = new Player[4];
        for(int i=0;i<4;i++) {
            int playerId = scanner.nextInt();
            scanner.nextLine();
            String skill = scanner.nextLine();
            String level = scanner.nextLine();
            int points = scanner.nextInt();
            scanner.nextLine();

            player[i] = new Player(playerId, skill, level, points);
        }

        String skill = scanner.nextLine();
        String level = scanner.nextLine();

        int points = findPointsForGivenSkill(player, skill);
        System.out.println(points);

        Player ans = getPlayerBasedOnLevel(player, skill, level);
        // for(Player playerIter : ans) {
        //     System.out.println(playerIter.getPlayerId());
        // }      
        System.out.println(ans.getPlayerId());  

        scanner.close();
    }
}