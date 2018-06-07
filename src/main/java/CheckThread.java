import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class CheckThread extends Thread {

    @Override
    public void run() {

        while (true) {
            try {
                // getting song tag from URL
                URL tag = new URL("http://www.guerrillaradio.ro/player/data/id3.txt");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(tag.openStream()));
                String songTag = in.readLine();
                // select songs already in playlist db
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement st = connection.prepareStatement("SELECT name FROM playlist WHERE name LIKE ?");
                st.setString(1, songTag);
                ResultSet rs = st.executeQuery();
                //verifying if current song is already in playlist
                boolean songExists = rs.next();
                rs.close();
                st.close();
                if (!songExists) {
                    st = connection.prepareStatement("INSERT INTO playlist (name) " +
                            "values (?)");
                    st.setString(1, songTag);
                    st.executeUpdate();
                    st.close();
                    System.out.println("\"" + songTag + "\"" + " was added in the playlist database");
                } else {
                    System.out.println(songTag + " already exists");
                }
                DatabaseManager.cleanup(st, connection);
                sleep(1 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {

            }
        }
    }
}