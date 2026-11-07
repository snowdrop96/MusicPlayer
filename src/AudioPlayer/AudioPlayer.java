package AudioPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AudioPlayer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Clip clip = null;
        try{
            File song = new File( "C:\\Windows\\Enna\\Enna Sona - OK Jaanu (128 kbps).wav");
            //1. find the song file
            //2. opens it
            //3. reads the music data

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);

            clip = AudioSystem.getClip();//with the help of clip we can play sound
            clip.open(audioInputStream);

        } catch (IOException e) {
             System.out.println("Song is not present at specified position");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio file format is not supported");
        } catch(LineUnavailableException e){
            System.out.println("There is no music player to play song");
        }

        int choice;
        do {
            System.out.println("\n===== AUDIO PLAYER =====");
            System.out.println("1. Play Audio");
            System.out.println("2. Stop Audio");
            System.out.println("3. Exit");
            System.out.println("Enter choice: ");

            choice = sc.nextInt();

            switch(choice){
                case 1:
                    clip.setFramePosition(0);
                    clip.start();
                    System.out.println("Playing audio...");
                    break;
                case 2:
                    clip.stop();
                    System.out.println("audio stopped");
                    break;
                case 3:
                    clip.stop();
                    clip.close();
                    System.out.println("Exiting..");
                    break;
                default:
                    System.out.println("Invalid choice");

            }
        } while(choice != 3);
        sc.close();

    }
}
