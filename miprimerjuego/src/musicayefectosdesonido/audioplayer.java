package musicayefectosdesonido;

import java.util.Map;

import org.newdawn.slick.Sound;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

public class audioplayer {
	
	public static Map<String, Sound> soundmap = new HashMap<String, Sound>(); 
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load()  {
		try {
			
			musicMap.put("musica", new Music("resources/TheFatRat_Unity.ogg"));
			soundmap.put("danioPersonaje", new Sound("resources/choque_danio.ogg"));
			soundmap.put("danioJefe", new Sound("resources/Crash.ogg"));
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
	}

	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundmap.get(key);
	}
}
