public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Expected three arguments for the program: <audio file name>, " +
                    "<k - number of groups " + "and algorithm for visualisation (\"minmax\" or \"fft\"");
        }
        var musicVisualiser = new MusicVisualiser(args[0], Integer.parseInt(args[1]), Algorithm.valueOf(args[2].toUpperCase()));
        musicVisualiser.visualise();
    }
}
