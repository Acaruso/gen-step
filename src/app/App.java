package app;

public class App {
    public static void main(String[] args) throws Exception {
        Song song = Song.read("src/app/song2.json");
        Song.write(song, "my-midi.mid");
    }
}
