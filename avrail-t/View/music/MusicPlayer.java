package View.music;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

class MusicPlayer {
    private Clip clip;
    private boolean isPlaying;

    public MusicPlayer() {
        isPlaying = false;
    }

    public void play(File file) {
        stop();
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isPlaying = true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            isPlaying = false;
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}

