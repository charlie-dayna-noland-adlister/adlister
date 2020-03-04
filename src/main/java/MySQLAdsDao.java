import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;
public class MySQLAdsDao implements Ads {
    private Connection connection;
    MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            this.connection = DriverManager.getConnection(config.getUrl(), config.getDBUsername(), config.getDBPassword());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("Error in constructor");
        }
    }
    @Override
    public List<Ad> all() {
        List<Ad> output = new ArrayList<Ad>();
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ads");
            while (rs.next()) {
                Ad newAd = new Ad(rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description")
                );
                output.add(newAd);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("Error in all.");
        }
        return output;
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = String.format("INSERT INTO ads (id, user_id, title, description) VALUES (%d, %d, '%s', '%s')",
                    ad.getId(),
                    ad.getUserId(),
                    ad.getTitle(),
                    ad.getDescription()
            );
            Statement stmt = this.connection.createStatement();
            stmt.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
//            ResultSet rs = stmt.getGeneratedKeys();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("Error in insert.");
        }
        return null;
    }

    private List<Ad> generateAds() {
        List<Ad> ads = new ArrayList<>();
        ads.add(new Ad(
                1,
                1,
                "playstation for sale",
                "This is a slightly used playstation"
        ));
        ads.add(new Ad(
                2,
                1,
                "Super Nintendo",
                "Get your game on with this old-school classic!"
        ));
        ads.add(new Ad(
                3,
                1,
                "Junior Java Developer Position",
                "Minimum 7 years of experience required. You will be working in the scripting language for Java, JavaScript"
        ));
        ads.add(new Ad(
                4,
                1,
                "JavaScript Developer needed",
                "Must have strong Java skills"
        ));
        return ads;
    }
    @Override
    public void seedDB() {
        List <Ad> adList = generateAds();
        for(Ad ad : adList) {
           insert(ad);
        }
    }
}
