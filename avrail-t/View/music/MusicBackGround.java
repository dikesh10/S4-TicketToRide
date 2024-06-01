package View.music;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MusicBackGround extends JFrame {
    JLabel lab;
    static MusicPlayer musicPlayer = null;
    Image newImg;
    Image img;
    ImageIcon bgButton;

    public MusicBackGround() {
        setSize(400, 400);
        setLayout(new GridLayout(4, 1));
        ImageIcon background = new ImageIcon("Resources/BackGroundSelectedMaps.jpeg");
        Image backgroundResized = background.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        background = new ImageIcon(backgroundResized);
        lab = new JLabel(background);

        if (musicPlayer == null)
            musicPlayer = new MusicPlayer();

        String[] music = {
                "Resources/music/music_ascenseur.wav",
                "Resources/music/music_jazz.wav",
                "Resources/music/music_arcade.wav",
                "Resources/music/stranger-things.wav",
                "Resources/music/music_In_Dreams.wav",
                "Resources/music/music_epic.wav"

        };
        JButton[] musicButtons = new JButton[music.length];

        for (int i = 0; i < musicButtons.length; i++) {
            final int index = i;
            if (i == 0) {
                bgButton = new ImageIcon("Resources/music/ascenseurPic.jpeg");
                img = bgButton.getImage();
                newImg = img.getScaledInstance(260, 110, Image.SCALE_SMOOTH);
            } else if (i == 1) {
                bgButton = new ImageIcon("Resources/music/JazzPic.jpeg");
                img = bgButton.getImage();
                newImg = img.getScaledInstance(260, 110, Image.SCALE_SMOOTH);
            } else if (i == 2) {
                bgButton = new ImageIcon("Resources/music/arcadePic.jpeg");
                img = bgButton.getImage();
                newImg = img.getScaledInstance(260, 110, Image.SCALE_SMOOTH);
            } else if (i == 3) {
                bgButton = new ImageIcon("Resources/music/StrangerThingsPic.jpeg");
                img = bgButton.getImage();
                newImg = img.getScaledInstance(260, 110, Image.SCALE_SMOOTH);
            } else if (i == 4) {
                bgButton = new ImageIcon("Resources/music/InDreamPic.jpeg");
                img = bgButton.getImage();
                newImg = img.getScaledInstance(260, 110, Image.SCALE_SMOOTH);
            } else {
                bgButton = new ImageIcon("Resources/music/EpicPic.jpeg");
                img = bgButton.getImage();
                newImg = img.getScaledInstance(260, 110, Image.SCALE_SMOOTH);
            }

            musicButtons[i] = new JButton("Musique " + (i + 1), bgButton);
            musicButtons[i].setIcon(new ImageIcon(newImg));

            musicButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (musicPlayer.isPlaying()) {
                        musicPlayer.stop();
                    }
                    musicPlayer.play(new File(music[index]));
                }
            });
            add(musicButtons[i]);
        }

        JButton stopButton = new JButton("Arrêter musique");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicPlayer.stop();
            }
        });

        JButton fermerButton = new JButton("Fermer");
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(stopButton);
        add(fermerButton);
        setLocation(830, 180);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setVisible(true);
    }
}
