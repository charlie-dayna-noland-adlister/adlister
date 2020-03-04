import java.sql.*;
import com.mysql.cj.jdbc.Driver;
public class MySQLActions {

    public static void main(String[] args) {
        Config config = new Config();
        try {
            DriverManager.registerDriver(new Driver());
            Connection initConn = DriverManager.getConnection(config.getCreateDBUrl(), config.getMyUsername(), config.getMyPassword());
            Statement initStmt = initConn.createStatement();
            initStmt.execute(config.getCreateDBStatement());
            initStmt.execute(config.getCreateDBUSerStatement());
            initStmt.execute(config.getCreateDBUSerPrivileges());
            initConn.close();
            Connection conn = DriverManager.getConnection(config.getUrl(), config.getDBUsername(), config.getDBPassword());
            Statement stmt = conn.createStatement();
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users(\n" +
                    "    id INT NOT NULL AUTO_INCREMENT,\n" +
                    "    username VARCHAR(20) NOT NULL,\n" +
                    "    email  VARCHAR(50) NOT NULL,\n" +
                    "    password VARCHAR(255) NOT NULL,\n" +
                    "    PRIMARY KEY (id),\n" +
                    "    CONSTRAINT uc_id_username_email UNIQUE (id, username, email)\n" +
                    ");";
            System.out.println("First Table Exists");
            stmt.execute(createUsersTable);
            String createAdsTable = "CREATE TABLE IF NOT EXISTS ads(\n" +
                    "    id INT NOT NULL AUTO_INCREMENT,\n" +
                    "    user_id INT NOT NULL,\n" +
                    "    title  VARCHAR(50) NOT NULL,\n" +
                    "    description VARCHAR(200) NOT NULL,\n" +
                    "    FOREIGN KEY (user_id) REFERENCES users (id),\n" +
                    "    PRIMARY KEY (id),\n" +
                    "    CONSTRAINT uc_id_title UNIQUE (id, title)\n" +
                    ");";
            stmt.execute(createAdsTable);
            String insertUser1 = String.format("INSERT IGNORE INTO users (id, username, email, password) VALUES (%d, '%s', '%s', '%s');",
                    1,
                    "bobbyShmurda",
                    "bshmurda@gmail.com",
                    "password");
            stmt.execute(insertUser1);
            System.out.println("Yippie!");
        } catch (SQLException sqle){
            sqle.printStackTrace();
            System.out.println("A MySQL error has occurred");
        }
    }
}
