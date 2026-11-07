package AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AudioPlayer1 {
    private Clip clip;
    private ArrayList<String> playlist;
    private int currentSongIndex;

    AudioPlayer1(ArrayList<String> playlist){
        this.playlist=playlist;
        this.currentSongIndex=0;
    }

    public void loadSong(String filePath) throws Exception{
//        File testFile = new File(playlist.get(0));
//        System.out.println("Checking Path "+testFile.getAbsolutePath());
//        System.out.println("Does file exist "+testFile.exists());
//        AudioSystem.getAudioInputStream(song);
//        File diagnosticFile = new File(filePath);
//        System.out.println("--- DIAGNOSTIC CHECK ---");
//        System.out.println("Exists: " + diagnosticFile.exists());
//        System.out.println("Is an actual file: " + diagnosticFile.isFile());
//        System.out.println("Has read permission: " + diagnosticFile.canRead());
//        System.out.println("File size (bytes): " + diagnosticFile.length());
//        System.out.println("------------------------");
        if(clip!= null){
            clip.stop();
            clip.close();
        }
        File song = new File(filePath);
        //1. find the song file
        //2. opens it
        //3. reads the music data

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);

        clip = AudioSystem.getClip();//with the help of clip we can play sound
        clip.open(audioInputStream);
    }

    //1. Play song
    public void playSong(){
        clip.start();


    }

    //2. Pause song
    public void pauseSong(){
        clip.stop();

    }

    //3. Resume song
    public void resumeSong(){
        clip.start();

    }

    public void stopSong(){
        clip.stop();
        clip.setFramePosition(0);

    }

    public void playNextSong() throws Exception{
        currentSongIndex++;
        if(currentSongIndex>=playlist.size()){
            currentSongIndex=0;
        }
        loadSong(playlist.get(currentSongIndex));
        pauseSong();
        playSong();

    }

    public void playPrevSong() throws Exception{
        currentSongIndex--;
        if(currentSongIndex<0){
            currentSongIndex = playlist.size()-1;
        }
        loadSong(playlist.get(currentSongIndex));
        playSong();

    }

    public void showDuration(){
        double seconds = clip.getMicrosecondLength();
        System.out.println("Duration of song is: "+seconds);

    }

    public void setVolume(float volume){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        System.out.println("Min "+gainControl.getMinimum());
        System.out.println("Max "+gainControl.getMaximum());
        gainControl.setValue(volume);
    }



    public static void main(String[] args)  {

        try{
            Clip clip = null;

            ArrayList<String> playlist = new ArrayList<>();
            playlist.add("C:\\Users\\rahul\\Downloads\\Enna\\enna.wav");
            playlist.add("C:\\Users\\rahul\\Downloads\\Enna\\RanaJi.wav");
            playlist.add("C:\\Users\\rahul\\Downloads\\Enna\\TuBolNa.wav");

            AudioPlayer1 player = new AudioPlayer1(playlist);
            player.loadSong(playlist.get(0));
            Scanner sc = new Scanner(System.in);

            int choice;
            do {
                System.out.println("\n===== MUSIC PLAYER =====");
                System.out.println("1. Play ");
                System.out.println("2. Pause");
                System.out.println("3. Resume");
                System.out.println("4. Stop ");
                System.out.println("5. Next Song");
                System.out.println("6. Previous song");
                System.out.println("7. Show duration");
                System.out.println("8. Volume control");
                System.out.println("Enter choice");

                choice = sc.nextInt();

                switch(choice){
                    case 1:
                        player.playSong();
                        break;
                    case 2:
                        player.pauseSong();
                        break;
                    case 3:
                        player.resumeSong();
                        break;
                    case 4:
                        player.stopSong();
                        break;
                    case 5:
                        player.playNextSong();
                        break;
                    case 6:
                        player.playPrevSong();
                        break;
                    case 7:
                        player.showDuration();
                        break;
                    case 8:
                        System.out.println("Enter volume: (-80 to 6) ");
                        float volume = sc.nextFloat();
                        player.setVolume(volume);
                        break;
                    case 9:
                        System.out.println("Thank you for usig music player.. bye");
                        break;
                    default:
                        System.out.println("Invalid choice ");


                }
            } while(choice != 3);
            sc.close();

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
