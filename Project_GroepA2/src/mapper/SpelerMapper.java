package mapper;

import domein.DomeinController;
import domein.Speler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class SpelerMapper 
{
    
    //werkt nog niet
    public boolean voegSpelerToe(Speler speler)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuweGebruiker = conn.prepareStatement("INSERT INTO Stenen_Tijdperk (spelerNaam, rPunten) VALUES (?,?)");
            queryNieuweGebruiker.setString(1, speler.getNaam());
            queryNieuweGebruiker.setInt(2, speler.getResourceLijst().get(8).getAantal());
            queryNieuweGebruiker.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }


    //methode geefHighscores
    //toont de 5 beste spelers met de meeste punten
    public List<Speler> geefHighScores(DomeinController dc)
    {
        List<Speler> spelerLijst = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryAlleGebruikers = conn.prepareStatement("SELECT * FROM Stenen_Tijdperk ORDER BY rPunten DESC LIMIT 10;");
            try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                //teller voor elke speler
                int teller = -1;
                while (rs.next()) {
                    teller++;
                    String spelerNaam = rs.getString("spelerNaam");
                    int rPunten = rs.getInt("rPunten");
                    Speler speler = new Speler(teller);
                    speler.setNaam(spelerNaam);
                    speler.setTotaalScore(rPunten);
                    spelerLijst.add(speler);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return spelerLijst;
    }


}
