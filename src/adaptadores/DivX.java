package adaptadores;

/**
 *
 * @author clamascabaleiro
 */
public class DivX implements MediaDivX {

    @Override
    public void playFilm(String filename) {
        System.out.println("Playing Divx File " + filename);
    }

}
