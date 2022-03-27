import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
public class Song {
	private String name;
	private Song nextSong;

	public Song(String name) {
		this.name = name;
	}

	public void setNextSong(Song nextSong) {
		this.nextSong = nextSong;
	}

	public boolean isRepeatingPlaylist() {
		HashSet<String> playedSongs = new HashSet<String>();
		Song currentSong = this;
		while(currentSong.nextSong != null){
			if (playedSongs.contains(currentSong.name)) {
				return true;
			}
			else{
				playedSongs.add(currentSong.name);
			}
			currentSong = currentSong.nextSong;
		}
		return false;
	}

	public static void main(String[] args) {
		Song first = new Song("Hello");
		Song second = new Song("Eye of the tiger");

		first.setNextSong(second);
		second.setNextSong(first);

		System.out.println(first.isRepeatingPlaylist());
	}
}