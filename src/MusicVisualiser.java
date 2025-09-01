import java.util.Arrays;

public class MusicVisualiser {
    private final String audioFile;
    private final int k;

    public MusicVisualiser(String audioFile, int k) {
        this.audioFile = audioFile;
        this.k = k;
    }

    public void minMax() {
        StdDraw.setCanvasSize(1000, 100);
        StdDraw.setPenColor(StdDraw.BLUE);
        var signal = StdAudio.read(audioFile);
        var sampleGroups = new double[signal.length/k][k];
        StdDraw.setXscale(0, sampleGroups.length);
        StdDraw.setYscale(-1, 1);

        for (int i = 0; i < signal.length/k; i++)
        {
            sampleGroups[i] = Arrays.copyOfRange(signal, i * k, (i + 1) * k);
        }

        for (var i = 0; i < sampleGroups.length; i++) {
            var sampleGroup = sampleGroups[i];
            var maxAbsValue = Arrays.stream(sampleGroup).map(Math::abs).max().getAsDouble();
            StdAudio.play(sampleGroup);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(i, -maxAbsValue, i, maxAbsValue);
        }
        StdDraw.show();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected two arguments for the program: <audio file name>, " +
                    "<k - number of groups");
        }
        var musicVisualiser = new MusicVisualiser(args[0], Integer.parseInt(args[1]));
        musicVisualiser.minMax();
    }
}
