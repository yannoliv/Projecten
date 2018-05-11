package Persistentie;

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
 
    
    public void slaSpelOp(DomeinController dc)
    {
        for (int i = 0; i <  dc.getSpelerLijst().size();i++) 
        {
            voegSpelerToe(dc.getSpelerLijst().get(i), dc);
        }
        
        //voegHuttenToe(dc);
    }
    
    public boolean voegSpelerToe(Speler speler, DomeinController dc)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuweGebruiker = conn.prepareStatement("INSERT INTO Stenen_Tijdperk (spelerNaam, rHout, pHout, rLeem, pLeem, rSteen, pSteen, rGoud, pGoud, rVoedsel, pVoedsel, rAkkerbouw, pAkkerbouw, rGereedschap, pSmith, rStamleden, pHut, gebruikteStamleden, rPunten, hut1, hut2, hut3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            queryNieuweGebruiker.setString(1, speler.getNaam());
            queryNieuweGebruiker.setInt(2, speler.getResourceLijst().get(0).getAantal());
            queryNieuweGebruiker.setInt(3, speler.getAantalBos());
            queryNieuweGebruiker.setInt(4, speler.getResourceLijst().get(1).getAantal());
            queryNieuweGebruiker.setInt(5, speler.getAantalLeemgroeve());
            queryNieuweGebruiker.setInt(6, speler.getResourceLijst().get(2).getAantal());
            queryNieuweGebruiker.setInt(7, speler.getAantalSteengroeve());
            queryNieuweGebruiker.setInt(8, speler.getResourceLijst().get(3).getAantal());
            queryNieuweGebruiker.setInt(9, speler.getAantalGoudmijn());
            queryNieuweGebruiker.setInt(10, speler.getResourceLijst().get(6).getAantal());
            queryNieuweGebruiker.setInt(11, speler.getAantalJachtgebied());
            queryNieuweGebruiker.setInt(12, speler.getResourceLijst().get(5).getAantal());
            queryNieuweGebruiker.setInt(13, speler.getAantalSmith());
            queryNieuweGebruiker.setInt(14, speler.getResourceLijst().get(4).getAantal());
            queryNieuweGebruiker.setInt(15, speler.getAantalAkkerbouw());
            queryNieuweGebruiker.setInt(16, speler.getResourceLijst().get(7).getAantal());
            queryNieuweGebruiker.setInt(17, speler.getAantalHut());
            queryNieuweGebruiker.setInt(18, speler.getGebruikteStamleden());
            queryNieuweGebruiker.setInt(19, speler.getResourceLijst().get(8).getAantal());
            queryNieuweGebruiker.setInt(20, speler.isPlaatsOpHutkaart1()?1:0);
            queryNieuweGebruiker.setInt(21, speler.isPlaatsOpHutkaart2()?1:0);
            queryNieuweGebruiker.setInt(22, speler.isPlaatsOpHutkaart3()?1:0);
            queryNieuweGebruiker.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }
    
    public boolean voegHuttenToe(DomeinController dc)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuweGebruiker = conn.prepareStatement("INSERT INTO Stenen_Tijdperk (hut1, hut2, hut3) VALUES (?,?,?)");
        //3 hutten, namen en aantal
        for (int i = 0; i < 3; i++) {
        queryNieuweGebruiker.setString(0, dc.getHuttenLijst().get(i).getResource1());
        queryNieuweGebruiker.setInt(0, dc.getHuttenLijst().get(i).getAantalResource1());
        queryNieuweGebruiker.setString(0, dc.getHuttenLijst().get(i).getResource2());
        queryNieuweGebruiker.setInt(0, dc.getHuttenLijst().get(i).getAantalResource2());
        queryNieuweGebruiker.setString(0, dc.getHuttenLijst().get(i).getResource3());
        queryNieuweGebruiker.setInt(0, dc.getHuttenLijst().get(i).getAantalResource3());
        }
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
