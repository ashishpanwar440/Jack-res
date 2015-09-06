package minesweeper;
public class MineSweeper {
    public static void main(String[] args) {
        Sweep sweep = new Sweep();
        sweep.setLocationRelativeTo(null);
        sweep.setExtendedState(6);
        sweep.show();
    }
}
