package adaptadores;

public class Adaptadores {

    public static void main(String[] args) {
        MediaPlayer player = new MP3();
        player.play("file.mp3");
        player = new FormatAdapter(new MP4());
        player.play("file.mp4");
        player = new FormatAdapter(new VLC());
        player.play("file.avi");
        player = new FormatAdapter (new DivX());
        player.play("file.divx");
        
    }

    private static class FormatAdapter implements MediaPlayer {

        private MediaPackage media;
        private MediaDivX media2;
        public FormatAdapter(MediaPackage m) {
            media = m;
        }
         public FormatAdapter(MediaDivX m2) {
            media2 = m2;
        }

        @Override
       public void play(String filename) {
            System.out.print("Using Adapter --> ");
            try {
                media.playFile(filename);
            } catch (Exception ex) {

            }
            try {
                media2.playFilm(filename);
            } catch (Exception ex) {

            }
        }
    }
 }

