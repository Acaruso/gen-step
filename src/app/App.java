package app;

public class App {
    public static void main(String[] args) throws Exception {
        Song song = Song.read("src/app/song.json");
        System.out.println(song);
        Song.write(song, "my-midi.mid");
    }
}
