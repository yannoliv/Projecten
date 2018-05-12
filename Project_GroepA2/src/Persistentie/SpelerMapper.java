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
                System.out.println("count1: " + dc.getSpelerLijst().size());
        voegSpelersToe(dc);
        voegHuttenToe(dc);
    }
    
    public boolean voegSpelersToe(DomeinController dc)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement resetSpelers = conn.prepareStatement("DELETE FROM SpelerLijst WHERE spelerID > -1;");
            resetSpelers.executeUpdate();
                            System.out.println("count2: " + dc.getSpelerLijst().size());
            for (int i = 0; i <  dc.getSpelerLijst().size();i++) {
            PreparedStatement queryNieuweGebruiker = conn.prepareStatement("INSERT INTO SpelerLijst (spelerNaam, rHout, pHout, rLeem, pLeem, rSteen, pSteen, rGoud, pGoud, rVoedsel, pVoedsel, rAkkerbouw, pAkkerbouw, rGereedschap, pSmith, rStamleden, pHut, gebruikteStamleden, rPunten, hut1, hut2, hut3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            queryNieuweGebruiker.setString(1, dc.getSpelerLijst().get(i).getNaam());
            queryNieuweGebruiker.setInt(2, dc.getSpelerLijst().get(i).getResourceLijst().get(0).getAantal());
            queryNieuweGebruiker.setInt(3, dc.getSpelerLijst().get(i).getAantalBos());
            queryNieuweGebruiker.setInt(4, dc.getSpelerLijst().get(i).getResourceLijst().get(1).getAantal());
            queryNieuweGebruiker.setInt(5, dc.getSpelerLijst().get(i).getAantalLeemgroeve());
            queryNieuweGebruiker.setInt(6, dc.getSpelerLijst().get(i).getResourceLijst().get(2).getAantal());
            queryNieuweGebruiker.setInt(7, dc.getSpelerLijst().get(i).getAantalSteengroeve());
            queryNieuweGebruiker.setInt(8, dc.getSpelerLijst().get(i).getResourceLijst().get(3).getAantal());
            queryNieuweGebruiker.setInt(9, dc.getSpelerLijst().get(i).getAantalGoudmijn());
            queryNieuweGebruiker.setInt(10, dc.getSpelerLijst().get(i).getResourceLijst().get(6).getAantal());
            queryNieuweGebruiker.setInt(11, dc.getSpelerLijst().get(i).getAantalJachtgebied());
            queryNieuweGebruiker.setInt(12, dc.getSpelerLijst().get(i).getResourceLijst().get(5).getAantal());
            queryNieuweGebruiker.setInt(13, dc.getSpelerLijst().get(i).getAantalSmith());
            queryNieuweGebruiker.setInt(14, dc.getSpelerLijst().get(i).getResourceLijst().get(4).getAantal());
            queryNieuweGebruiker.setInt(15, dc.getSpelerLijst().get(i).getAantalAkkerbouw());
            queryNieuweGebruiker.setInt(16, dc.getSpelerLijst().get(i).getResourceLijst().get(7).getAantal());
            queryNieuweGebruiker.setInt(17, dc.getSpelerLijst().get(i).getAantalHut());
            queryNieuweGebruiker.setInt(18, dc.getSpelerLijst().get(i).getGebruikteStamleden());
            queryNieuweGebruiker.setInt(19, dc.getSpelerLijst().get(i).getResourceLijst().get(8).getAantal());
            queryNieuweGebruiker.setInt(20, dc.getSpelerLijst().get(i).isPlaatsOpHutkaart1()?1:0);
            queryNieuweGebruiker.setInt(21, dc.getSpelerLijst().get(i).isPlaatsOpHutkaart2()?1:0);
            queryNieuweGebruiker.setInt(22, dc.getSpelerLijst().get(i).isPlaatsOpHutkaart3()?1:0);
                System.out.println("nieuwe gebruiker voegspelerstoe");
            queryNieuweGebruiker.executeUpdate();
                    System.out.println("count3: " + dc.getSpelerLijst().size());
            }
            
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
            PreparedStatement query = conn.prepareStatement("DELETE FROM HuttenLijst");
            query.executeUpdate();
            
            for (int i = 0; i < dc.getHuttenLijst1().size(); i++) {
            query = conn.prepareStatement("INSERT INTO HuttenLijst (hutLijstNr,rNaam1, rAantal1, rNaam2, rAantal2, rNaam3, rAantal3, punten, aantalSpots) VALUES (?,?,?,?,?,?,?,?,?)");
            query.setInt(1, 0);
            query.setString(2, dc.getHuttenLijst1().get(i).getResource1());
            query.setInt(3, dc.getHuttenLijst1().get(i).getAantalResource1());
            query.setString(4, dc.getHuttenLijst1().get(i).getResource2());
            query.setInt(5, dc.getHuttenLijst1().get(i).getAantalResource2());
            query.setString(6, dc.getHuttenLijst1().get(i).getResource3());
            query.setInt(7, dc.getHuttenLijst1().get(i).getAantalResource3());
            query.setInt(8, dc.getHuttenLijst1().get(i).getPunten());
            query.setInt(9, dc.getHuttenLijst1().get(i).getAantalSpots());
            query.executeUpdate();
            }
            
            for (int i = 0; i < dc.getHuttenLijst2().size(); i++) {
            query = conn.prepareStatement("INSERT INTO HuttenLijst (hutLijstNr,rNaam1, rAantal1, rNaam2, rAantal2, rNaam3, rAantal3, punten, aantalSpots) VALUES (?,?,?,?,?,?,?,?,?)");
            query.setInt(1, 1);
            query.setString(2, dc.getHuttenLijst1().get(i).getResource1());
            query.setInt(3, dc.getHuttenLijst1().get(i).getAantalResource1());
            query.setString(4, dc.getHuttenLijst1().get(i).getResource2());
            query.setInt(5, dc.getHuttenLijst1().get(i).getAantalResource2());
            query.setString(6, dc.getHuttenLijst1().get(i).getResource3());
            query.setInt(7, dc.getHuttenLijst1().get(i).getAantalResource3());
            query.setInt(8, dc.getHuttenLijst1().get(i).getPunten());
            query.setInt(9, dc.getHuttenLijst1().get(i).getAantalSpots());
            query.executeUpdate();
            }
            
            for (int i = 0; i < dc.getHuttenLijst3().size(); i++) {
            query = conn.prepareStatement("INSERT INTO HuttenLijst (hutLijstNr,rNaam1, rAantal1, rNaam2, rAantal2, rNaam3, rAantal3, punten, aantalSpots) VALUES (?,?,?,?,?,?,?,?,?)");
            query.setInt(1, 2);
            query.setString(2, dc.getHuttenLijst1().get(i).getResource1());
            query.setInt(3, dc.getHuttenLijst1().get(i).getAantalResource1());
            query.setString(4, dc.getHuttenLijst1().get(i).getResource2());
            query.setInt(5, dc.getHuttenLijst1().get(i).getAantalResource2());
            query.setString(6, dc.getHuttenLijst1().get(i).getResource3());
            query.setInt(7, dc.getHuttenLijst1().get(i).getAantalResource3());
            query.setInt(8, dc.getHuttenLijst1().get(i).getPunten());
            query.setInt(9, dc.getHuttenLijst1().get(i).getAantalSpots());
            query.executeUpdate();
            }
            
            for (int i = 0; i < dc.getHuttenLijst4().size(); i++) {
            query = conn.prepareStatement("INSERT INTO HuttenLijst (hutLijstNr,rNaam1, rAantal1, rNaam2, rAantal2, rNaam3, rAantal3, punten, aantalSpots) VALUES (?,?,?,?,?,?,?,?,?)");
            query.setInt(1, 3);
            query.setString(2, dc.getHuttenLijst1().get(i).getResource1());
            query.setInt(3, dc.getHuttenLijst1().get(i).getAantalResource1());
            query.setString(4, dc.getHuttenLijst1().get(i).getResource2());
            query.setInt(5, dc.getHuttenLijst1().get(i).getAantalResource2());
            query.setString(6, dc.getHuttenLijst1().get(i).getResource3());
            query.setInt(7, dc.getHuttenLijst1().get(i).getAantalResource3());
            query.setInt(8, dc.getHuttenLijst1().get(i).getPunten());
            query.setInt(9, dc.getHuttenLijst1().get(i).getAantalSpots());
            query.executeUpdate();
            }
        
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
            PreparedStatement queryAlleGebruikers = conn.prepareStatement("SELECT spelerNaam, MAX(spelerPunten) AS punten FROM HighScores GROUP BY spelerNaam ORDER BY MAX(spelerPunten) DESC LIMIT 10;");
            try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                int teller = -1;
                while (rs.next()) {
                    teller++;
                    String spelerNaam = rs.getString("spelerNaam");
                    int punten = rs.getInt("punten");
                    Speler speler = new Speler(teller);
                    speler.setNaam(spelerNaam);
                    speler.setTotaalScore(punten);
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
    
    public void setSpelersFromDataBank(DomeinController dc)
    {
        List<Speler> spelerLijst= new ArrayList<>();
        dc.setSpelerLijst(spelerLijst);
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            //eerste connectie is om te zien hoeveel spelers er zijn
            PreparedStatement queryAlleGebruikers = conn.prepareStatement("select count(*) as count from SpelerLijst;");
            try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                rs.next();
                int count = rs.getInt("count");
                dc.setAantalSpelers(count);
                for (int i = 0; i < count; i++) {
                    Speler speler = new Speler(i);
                    dc.getSpelerLijst().add(speler);
                    System.out.println("speler toevegen adhv count; " + count);
                }
            } catch (SQLException ex) {
                for (Throwable t : ex) {
                    t.printStackTrace();
                }
            }
            //tweede connectie is om ALLES van de databank te halen.
            queryAlleGebruikers = conn.prepareStatement("SELECT * FROM SpelerLijst");
            try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                int teller = -1;
                dc.vulLijsten();
                while (rs.next()) {
                    teller++;                    
                    String naam = rs.getString("spelerNaam");
                    int rHout = rs.getInt("rHout");
                    int pHout = rs.getInt("pHout");
                    int rLeem = rs.getInt("rLeem");
                    int pLeem = rs.getInt("pLeem");
                    int rSteen = rs.getInt("rSteen");
                    int pSteen = rs.getInt("pSteen");
                    int rGoud = rs.getInt("rGoud");
                    int pGoud = rs.getInt("pGoud");
                    int rVoedsel = rs.getInt("rVoedsel");
                    int pVoedsel = rs.getInt("pVoedsel");
                    int rAkkerbouw = rs.getInt("rAkkerbouw");
                    int pAkkerbouw = rs.getInt("pAkkerbouw");
                    int rGereedschap = rs.getInt("rGereedschap");
                    int pSmith = rs.getInt("pSmith");
                    int rStamleden = rs.getInt("rStamleden");
                    int pHut = rs.getInt("pHut");
                    int gebruikteStamleden = rs.getInt("gebruikteStamleden");
                    int rPunten = rs.getInt("rPunten");
                    int hut1 = rs.getInt("hut1");
                    int hut2 = rs.getInt("hut2");
                    int hut3 = rs.getInt("hut3");
                    
                    dc.getSpelerLijst().get(teller).setNaam(naam);
                    dc.getSpelerLijst().get(teller).getResourceLijst().get(0).setAantal(rHout);
                    dc.getSpelerLijst().get(teller).getResourceLijst().get(1).setAantal(rLeem);
                    dc.getSpelerLijst().get(teller).getResourceLijst().get(2).setAantal(rSteen);
                    dc.getSpelerLijst().get(teller).getResourceLijst().get(3).setAantal(rGoud);
                    dc.getSpelerLijst().get(teller).getResourceLijst().get(4).setAantal(rAkkerbouw);
                    dc.getSpelerLijst().get(teller).getResourceLijst().get(5).setAantal(rGereedschap);
                    dc.getSpelerLijst().get(teller).getResourceLijst().get(6).setAantal(rVoedsel);
                    dc.getSpelerLijst().get(teller).getResourceLijst().get(7).setAantal(rStamleden);
                    dc.getSpelerLijst().get(teller).getResourceLijst().get(8).setAantal(rPunten);
                    dc.getSpelerLijst().get(teller).setGebruikteStamleden(gebruikteStamleden);
                    dc.getSpelerLijst().get(teller).setAantalBos(pHout);
                    dc.getSpelerLijst().get(teller).setAantalLeemgroeve(pLeem);
                    dc.getSpelerLijst().get(teller).setAantalSteengroeve(pSteen);
                    dc.getSpelerLijst().get(teller).setAantalGoudmijn(pGoud);
                    dc.getSpelerLijst().get(teller).setAantalJachtgebied(pVoedsel);
                    dc.getSpelerLijst().get(teller).setAantalAkkerbouw(pAkkerbouw);
                    dc.getSpelerLijst().get(teller).setAantalSmith(pSmith);
                    dc.getSpelerLijst().get(teller).setAantalHut(pHut);
                    if(hut1 == 1){
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart1(true);
                    }else {
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart1(false);}
                    if(hut2 == 1){
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart2(true);
                    }else {
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart2(false);}
                    if(hut3 == 1){
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart3(true);
                    }else {
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart3(false);}      
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
    }
    
    public void reset(DomeinController dc)
    {
        for (int i = 0; i < dc.getSpelerLijst().size(); i++) {
           dc.getSpelerLijst().remove(i); 
        }
    }


}
