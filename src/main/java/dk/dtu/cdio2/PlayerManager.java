package dk.dtu.cdio2;

public class PlayerManager {
    public Player createPlayer(GUIManager gm) {
        return new Player();
    }

    class Player {
        Player() {

        }

        public int getPoints() {
            return 0;
        }

        public String getName() {
            return "";
        }

        public boolean withdraw(Integer amount) {
            return true;
        }
    }
}
