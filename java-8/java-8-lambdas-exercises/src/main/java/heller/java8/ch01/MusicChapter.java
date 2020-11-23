/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package heller.java8.ch01;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Richard Warburton
 */
public abstract class MusicChapter {
    
    protected final List<Artist> artists;
    protected final List<Album> albums;

    public MusicChapter() {
        artists = new ArrayList<>();
        albums = new ArrayList<>();
        loadData("");
    }

    private void loadData(String file) {
        
    }
    
}
