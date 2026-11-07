import java.util.Scanner;

class Song{
    String title;
    Song next;
    Song prev;

    Song(String title){
        this.title = title;
        this.next = null;
        this.prev = null;
    }

}

public class MusicPlaylistManager {
    private Song  head;
    private Song tail;
    private Song current;

    public void addSong(String title){
        Song newSong = new Song(title);
       if(head == null){
           head = newSong;
           tail = newSong;
           current = newSong;
       }
       else{
           tail.next = newSong;
           newSong.prev = tail;
           tail = newSong;
       }
       System.out.println("Song added Successfully");
    }

    public void displaylaylist(){
        if(head == null){
            System.out.println("Playlist is empty..");
            return;
        }
        Song temp = head;
        while(temp!=null){
            if(temp == current){
                System.out.println(" //u25BA current song: " +temp.title);
            }
            else{
                System.out.println(temp.title);
            }

            temp = temp.next;
        }

    }

    public void playCurrentSong(){
        if(current == null){
            System.out.println("Playlist is empty");
            return;
        }
        System.out.println("Playing current song");
        System.out.println(current.title);

    }

    public void playNextSong(){
        if(current == null){
            System.out.println("playList is empty");
        }
        if(current.next == null){
            System.out.println("You are already at the last song");
            return;
        }
        current = current.next;
        System.out.println("Next song is "+current.title);

    }

    public void  playPrevSong(){
        if(current == null){
            System.out.println("playList is empty");
        }
        if(current.prev == null){
            System.out.println("You are already at the first song");
        }
        current = current.prev;
        System.out.println("Next song is "+current.title);
    }

    public void searchASong(String title){
        if(head == null){
            System.out.println("Playlist is empty ");
            return;
        }
        Song temp = head;
        while(temp != null){
            if(temp.title.equalsIgnoreCase(title)){
                System.out.println("Song found " +temp.title);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Song is not present in playlist");
    }

    public void deleteSong(String title){
        if(head == null){
            System.out.println("Playlist is empty ");
            return;
        }
        Song temp = head;
        while(temp != null){
            if(temp.title.equalsIgnoreCase(title)){
                //1. single song
                if(head == tail){
                    head = null;
                    tail = null;
                    current = null;
                }
               //1. song is preseent at head
                else if(temp == head){
                    head = head.next;
                    head.prev=null;
                    if(current == temp){
                        current = head;
                    }
                }
               //3. song is present at tail
                else if(temp == tail){
                    tail = tail.prev;
                    tail.next = null;
                    if(current == temp){
                        current = tail;
                    }
                }
               //4. other case
                else{
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;

                    if(current == temp){
                        current = temp.next;
                    }
                }

                System.out.println("song deleted successfully");
                return;
            }
            temp=temp.next;
        }

        System.out.println("song is not present in this playlist");

    }





    public static void main(String[] args) {
        MusicPlaylistManager musicPlaylistManager = new MusicPlaylistManager();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("--------------------");
            System.out.println("Music Playerlist Manager");
            System.out.println("--------------------");
            System.out.println("1. Add Song");
            System.out.println("2. Display Playlist");
            System.out.println("3. PlayCurrent Song");
            System.out.println("4. Next Song");
            System.out.println("5. Previous Song");
            System.out.println("6. Search Song");
            System.out.println("7. Delete Song");
            System.out.println("8. Exit");

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter the Song name: ");
                    String songName = scanner.nextLine();
                    musicPlaylistManager.addSong(songName);
                    break;
                case 2:
                    musicPlaylistManager.displaylaylist();
                    break;
                case 3:
                    musicPlaylistManager.playCurrentSong();
                    break;
                case 4:
                    musicPlaylistManager.playNextSong();
                    break;
                case 5:
                    musicPlaylistManager.playPrevSong();
                    break;
                case 6:
                    System.out.println("Enter Song to search: ");
                    String song = scanner.nextLine();
                    musicPlaylistManager.searchASong(song);
                    break;
                case 7:
                    System.out.println("enter Song to delete: ");
                    String songd = scanner.nextLine();
                    musicPlaylistManager.deleteSong(songd);
                    break;
                case 8:
                    System.out.println("Thank you for using Musoc PlaylistManager");
                    System.out.println("bye");
                    break;
                default:
                    System.out.println("Invalid choice. Pleasr enter a valid choice");
            }

        }   while(choice != 8);
        scanner.close();






    }


}
