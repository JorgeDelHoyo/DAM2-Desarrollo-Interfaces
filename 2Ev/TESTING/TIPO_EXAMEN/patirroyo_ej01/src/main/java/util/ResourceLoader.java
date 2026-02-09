package util;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class ResourceLoader {
    private ResourceLoader() {
    }

    public static Image loadImage(String path) {
        URL url = ResourceLoader.class.getClassLoader().getResource(path);
        if (url == null) {
            throw new IllegalArgumentException("Recurso no encontrado: " + path);
        }
        try {
            return ImageIO.read(url);
        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo cargar la imagen: " + path, ex);
        }
    }

    public static Clip loadClip(String path) {
        URL url = ResourceLoader.class.getClassLoader().getResource(path);
        if (url == null) {
            throw new IllegalArgumentException("Recurso no encontrado: " + path);
        }
        try (AudioInputStream stream = AudioSystem.getAudioInputStream(url)) {
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            return clip;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            throw new IllegalStateException("No se pudo cargar el audio: " + path, ex);
        }
    }

    public static void playClip(Clip clip) {
        if (clip == null) {
            return;
        }
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }
}
