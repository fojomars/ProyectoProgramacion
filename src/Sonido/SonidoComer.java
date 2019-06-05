package Sonido;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author clamascabaleiro
 */
public class SonidoComer extends Thread {
   public void run() {
        try {
            Clip sonido = AudioSystem.getClip();
            File a = new File("/home/local/DANIELCASTELAO/clamascabaleiro/Descargas/comer.wav");
            sonido.open(AudioSystem.getAudioInputStream(a));
            sonido.start();
            Thread.sleep(10000); // 10000 ms (1s)
            sonido.close();
        } catch (Exception tipoError) {
            System.out.println("" + tipoError);
        }
    }
}

