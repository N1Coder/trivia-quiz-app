package com.main.triviaquizapp.utils.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {

    public void playMusic(String pathName, float volume) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(pathName);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue((float) (20f * Math.log10(volume)));
        clip.start();
    }

    public void setMusicVolume(Clip clip, float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue((float) (20f * Math.log10(volume)));
    }

}