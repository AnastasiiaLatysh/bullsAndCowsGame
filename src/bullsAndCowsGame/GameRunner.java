package bullsAndCowsGame;


public class GameRunner {

    public static void main(String[] args){
        GameModel model = new GameModel();
        GameViewer viewer = new GameViewer();
        viewer.setVisible(true);
        new GameController(viewer, model);
    }
}
