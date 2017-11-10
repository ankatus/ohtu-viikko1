package ohtuesimerkkitestit;

import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StatisticsTest {
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void testSearch() {
        Player p = stats.search("Semenko");
        assertEquals("Semenko", p.getName());
    }

    @Test
    public void testSearchNonexistentName() {
        Player p = stats.search("ana");
        assertNull(p);
    }

    @Test
    public void testTeamSearch() {
        List<Player> team = stats.team("EDM");
        ArrayList<String> names = new ArrayList();
        for (Player p : team) {
            names.add(p.getName());
        }
        assertTrue(names.contains("Semenko"));
        assertTrue(names.contains("Kurri"));
        assertTrue(names.contains("Gretzky"));
    }

    @Test
    public void testTeamSearchNonexistentTeam() {
        List team = stats.team("asdf");
        assertEquals(team.size(), 0);
    }

    @Test
    public void topScorersTest() {
        List<Player> scorers = stats.topScorers(2);
        ArrayList<String> names = new ArrayList<>();
        for (Player p : scorers) {
            names.add(p.getName());
        }
        assertEquals("Gretzky", names.get(0));
        assertEquals("Lemieux", names.get(1));
    }
}
