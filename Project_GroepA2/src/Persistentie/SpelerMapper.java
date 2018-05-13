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
        voegSpelersToe(dc);
        voegHuttenToe(dc);
    }
    
    public boolean voegSpelersToe(DomeinController dc)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement resetSpelers = conn.prepareStatement("DELETE FROM SpelerLijst");
            resetSpelers.executeUpdate();
            resetSpelers = conn.prepareStatement("ALTER TABLE SpelerLijst AUTO_INCREMENT = 0");
            resetSpelers.executeUpdate();
            
            for (int i = 0; i <  dc.getSpelerLijst().size();i++) {
            PreparedStatement queryNieuweGebruiker = conn.prepareStatement("INSERT INTO SpelerLijst (spelerNaam, rHout, pHout, rLeem, pLeem, rSteen, pSteen, rGoud, pGoud, rVoedsel, pVoedsel, rAkkerbouw, pAkkerbouw, rGereedschap, pSmith, rStamleden, pHut, gebruikteStamleden, rPunten, hut1, hut2, hut3, hut4, isAanBeurt) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            queryNieuweGebruiker.setString(1, dc.getSpelerLijst().get(i).getNaam());
            queryNieuweGebruiker.setInt(2, dc.getSpelerLijst().get(i).getResourceLijst().get(0).getAantal());
            queryNieuweGebruiker.setInt(3, dc.getSpelerLijst().get(i).getAantalBos());    
            //dc.getPlaatsenLijst().get(0).setAantalSpots(dc.getPlaatsenLijst().get(0).getAantalSpots() - dc.getSpelerLijst().get(i).getAantalBos());
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
            queryNieuweGebruiker.setInt(23, dc.getSpelerLijst().get(i).isPlaatsOpHutkaart4()?1:0);
            int spelerAanBeurt = dc.getHuidigeSpeler();
            if (i == spelerAanBeurt){
            queryNieuweGebruiker.setInt(24, 1);
            }
            else{queryNieuweGebruiker.setInt(24, 0);}
                
            queryNieuweGebruiker.executeUpdate();
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
            query = conn.prepareStatement("ALTER TABLE HuttenLijst AUTO_INCREMENT = 0");
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
            query.setString(2, dc.getHuttenLijst2().get(i).getResource1());
            query.setInt(3, dc.getHuttenLijst2().get(i).getAantalResource1());
            query.setString(4, dc.getHuttenLijst2().get(i).getResource2());
            query.setInt(5, dc.getHuttenLijst2().get(i).getAantalResource2());
            query.setString(6, dc.getHuttenLijst2().get(i).getResource3());
            query.setInt(7, dc.getHuttenLijst2().get(i).getAantalResource3());
            query.setInt(8, dc.getHuttenLijst2().get(i).getPunten());
            query.setInt(9, dc.getHuttenLijst2().get(i).getAantalSpots());
            query.executeUpdate();
            }
            
            for (int i = 0; i < dc.getHuttenLijst3().size(); i++) {
            query = conn.prepareStatement("INSERT INTO HuttenLijst (hutLijstNr,rNaam1, rAantal1, rNaam2, rAantal2, rNaam3, rAantal3, punten, aantalSpots) VALUES (?,?,?,?,?,?,?,?,?)");
            query.setInt(1, 2);
            query.setString(2, dc.getHuttenLijst3().get(i).getResource1());
            query.setInt(3, dc.getHuttenLijst3().get(i).getAantalResource1());
            query.setString(4, dc.getHuttenLijst3().get(i).getResource2());
            query.setInt(5, dc.getHuttenLijst3().get(i).getAantalResource2());
            query.setString(6, dc.getHuttenLijst3().get(i).getResource3());
            query.setInt(7, dc.getHuttenLijst3().get(i).getAantalResource3());
            query.setInt(8, dc.getHuttenLijst3().get(i).getPunten());
            query.setInt(9, dc.getHuttenLijst3().get(i).getAantalSpots());
            query.executeUpdate();
            }
            
            for (int i = 0; i < dc.getHuttenLijst4().size(); i++) {
            query = conn.prepareStatement("INSERT INTO HuttenLijst (hutLijstNr,rNaam1, rAantal1, rNaam2, rAantal2, rNaam3, rAantal3, punten, aantalSpots) VALUES (?,?,?,?,?,?,?,?,?)");
            query.setInt(1, 3);
            query.setString(2, dc.getHuttenLijst3().get(i).getResource1());
            query.setInt(3, dc.getHuttenLijst4().get(i).getAantalResource1());
            query.setString(4, dc.getHuttenLijst4().get(i).getResource2());
            query.setInt(5, dc.getHuttenLijst4().get(i).getAantalResource2());
            query.setString(6, dc.getHuttenLijst4().get(i).getResource3());
            query.setInt(7, dc.getHuttenLijst4().get(i).getAantalResource3());
            query.setInt(8, dc.getHuttenLijst4().get(i).getPunten());
            query.setInt(9, dc.getHuttenLijst4().get(i).getAantalSpots());
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
    
    public void setSpelFromDataBank(DomeinController dc)
    {
        //eerst lijsten maken voor nullpointers te voorkomen
        List<Speler> spelerLijst= new ArrayList<>();
        dc.setSpelerLijst(spelerLijst);
        
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            
            //hutlijst count uitvoeren
            PreparedStatement queryHutten = conn.prepareStatement("SELECT COUNT(*) AS count FROM HuttenLijst GROUP BY hutLijstNr;");
            int[] hutLijstSize = new int[4];
            hutLijstSize = countHutLijsten(queryHutten);
            
            //lijsten vullen
            dc.vuldbLijsten(hutLijstSize);
            
            
            //connectie om de hutten af te halen
            queryHutten = conn.prepareStatement("SELECT * FROM HuttenLijst");
            setHutten(dc, queryHutten, hutLijstSize);
            
            
            //eerste connectie is om te zien hoeveel spelers er zijn
            PreparedStatement queryAlleGebruikers = conn.prepareStatement("select count(*) as count from SpelerLijst;");
            int count = countSpelers(dc, queryAlleGebruikers);
            
            //dit moet voor nulpointer te voorkomen
            dc.vulSpelerLijst(count);
            dc.geefSpelersResources();
            
            
            //connectie om alle spelers van de databank te halen.
            queryAlleGebruikers = conn.prepareStatement("select * from SpelerLijst;");
            setSpelers(dc, queryAlleGebruikers);
            
            
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
    }
    
    public int countSpelers(DomeinController dc, PreparedStatement queryAlleGebruikers)
    {
        int count = 0;
        try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
            rs.next();
            count = rs.getInt("count");
            dc.setAantalSpelers(count);
            for (int i = 0; i < count; i++) {
                Speler speler = new Speler(i);
                dc.getSpelerLijst().add(speler);
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
        return count;
    }
    
    public int[] countHutLijsten(PreparedStatement queryHutten)
    {
        int[] hutLijstSize = new int[4];
        try (ResultSet rs = queryHutten.executeQuery()) {
            rs.next();
            hutLijstSize[0] = rs.getInt("count");
            rs.next();
            hutLijstSize[1] = rs.getInt("count");
            rs.next();
            hutLijstSize[2] = rs.getInt("count");
            rs.next();
            hutLijstSize[3] = rs.getInt("count");
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
        return hutLijstSize;
    }
    
    public void setHutten(DomeinController dc, PreparedStatement queryHutten, int[] hutLijstSize)
    {
        try (ResultSet rs = queryHutten.executeQuery()) {
                while(rs.next()){
                    int hutLijstNr = rs.getInt("hutLijstNr");
                    switch (hutLijstNr)
                    {
                        case 0:
                            for (int i = 0; i < hutLijstSize[0]; i++) {
                                String rNaam1 = rs.getString("rNaam1");
                                int rAantal1 = rs.getInt("rAantal1");
                                String rNaam2 = rs.getString("rNaam2");
                                int rAantal2 = rs.getInt("rAantal2");
                                String rNaam3 = rs.getString("rNaam3");
                                int rAantal3 = rs.getInt("rAantal3");
                                int punten = rs.getInt("punten");
                                int aantalSpots = rs.getInt("aantalSpots");
                                
                                dc.getHuttenLijst1().get(i).setResource1(rNaam1);
                                dc.getHuttenLijst1().get(i).setAantalResource1(rAantal1);
                                dc.getHuttenLijst1().get(i).setResource2(rNaam2);
                                dc.getHuttenLijst1().get(i).setAantalResource1(rAantal2);
                                dc.getHuttenLijst1().get(i).setResource3(rNaam3);
                                dc.getHuttenLijst1().get(i).setAantalResource1(rAantal3);
                                dc.getHuttenLijst1().get(i).setPunten(punten);
                                dc.getHuttenLijst1().get(i).setAantalSpots(aantalSpots);
                            }
                            break;
                        case 1:
                            for (int i = 0; i < hutLijstSize[1]; i++) {
                                String rNaam1 = rs.getString("rNaam1");
                                int rAantal1 = rs.getInt("rAantal1");
                                String rNaam2 = rs.getString("rNaam2");
                                int rAantal2 = rs.getInt("rAantal2");
                                String rNaam3 = rs.getString("rNaam3");
                                int rAantal3 = rs.getInt("rAantal3");
                                int punten = rs.getInt("punten");
                                int aantalSpots = rs.getInt("aantalSpots");
                                
                                dc.getHuttenLijst2().get(i).setResource1(rNaam1);
                                dc.getHuttenLijst2().get(i).setAantalResource1(rAantal1);
                                dc.getHuttenLijst2().get(i).setResource2(rNaam2);
                                dc.getHuttenLijst2().get(i).setAantalResource1(rAantal2);
                                dc.getHuttenLijst2().get(i).setResource3(rNaam3);
                                dc.getHuttenLijst2().get(i).setAantalResource1(rAantal3);
                                dc.getHuttenLijst2().get(i).setPunten(punten);
                                dc.getHuttenLijst2().get(i).setAantalSpots(aantalSpots);
                            }
                            break;
                        case 2:
                            for (int i = 0; i < hutLijstSize[2]; i++) {
                                String rNaam1 = rs.getString("rNaam1");
                                int rAantal1 = rs.getInt("rAantal1");
                                String rNaam2 = rs.getString("rNaam2");
                                int rAantal2 = rs.getInt("rAantal2");
                                String rNaam3 = rs.getString("rNaam3");
                                int rAantal3 = rs.getInt("rAantal3");
                                int punten = rs.getInt("punten");
                                int aantalSpots = rs.getInt("aantalSpots");
                                
                                dc.getHuttenLijst3().get(i).setResource1(rNaam1);
                                dc.getHuttenLijst3().get(i).setAantalResource1(rAantal1);
                                dc.getHuttenLijst3().get(i).setResource2(rNaam2);
                                dc.getHuttenLijst3().get(i).setAantalResource1(rAantal2);
                                dc.getHuttenLijst3().get(i).setResource3(rNaam3);
                                dc.getHuttenLijst3().get(i).setAantalResource1(rAantal3);
                                dc.getHuttenLijst3().get(i).setPunten(punten);
                                dc.getHuttenLijst3().get(i).setAantalSpots(aantalSpots);
                            }
                            break;
                        case 3: 
                            for (int i = 0; i < hutLijstSize[3]; i++) {
                                String rNaam1 = rs.getString("rNaam1");
                                int rAantal1 = rs.getInt("rAantal1");
                                String rNaam2 = rs.getString("rNaam2");
                                int rAantal2 = rs.getInt("rAantal2");
                                String rNaam3 = rs.getString("rNaam3");
                                int rAantal3 = rs.getInt("rAantal3");
                                int punten = rs.getInt("punten");
                                int aantalSpots = rs.getInt("aantalSpots");
                                
                                dc.getHuttenLijst4().get(i).setResource1(rNaam1);
                                dc.getHuttenLijst4().get(i).setAantalResource1(rAantal1);
                                dc.getHuttenLijst4().get(i).setResource2(rNaam2);
                                dc.getHuttenLijst4().get(i).setAantalResource1(rAantal2);
                                dc.getHuttenLijst4().get(i).setResource3(rNaam3);
                                dc.getHuttenLijst4().get(i).setAantalResource1(rAantal3);
                                dc.getHuttenLijst4().get(i).setPunten(punten);
                                dc.getHuttenLijst4().get(i).setAantalSpots(aantalSpots);
                            break;
                        }
                    }
                }
            } catch (SQLException ex) {
                for (Throwable t : ex) {
                    t.printStackTrace();
                }
            }
    }
    
    public void setSpelers(DomeinController dc, PreparedStatement queryAlleGebruikers)
    {
        try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                int teller = -1;
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
                    int hut4 = rs.getInt("hut4");
                    int isAanBeurt = rs.getInt("isAanBeurt");
                    
                    
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
                        dc.getHuttenLijst1().get(0).setAantalSpots(0);
                    }else {
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart1(false);}
                    if(hut2 == 1){
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart2(true);
                        dc.getHuttenLijst2().get(0).setAantalSpots(0);
                    }else {
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart2(false);}
                    if(hut3 == 1){
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart3(true);
                        dc.getHuttenLijst3().get(0).setAantalSpots(0);
                    }else {
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart3(false);} 
                    if(hut4 == 1){
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart3(true);
                        dc.getHuttenLijst4().get(0).setAantalSpots(0);
                    }else {
                        dc.getSpelerLijst().get(teller).setPlaatsOpHutkaart4(false);}
                    
                    
                    //de speler aan beurt toestellen
                    if (isAanBeurt == 1){
                    dc.setHuidigeSpeler(teller);
                    }
                    
                    //de plaatsen goed zetten
                    dc.getPlaatsenLijst().get(0).setAantalSpots(dc.getPlaatsenLijst().get(0).getAantalSpots() - dc.getSpelerLijst().get(teller).getAantalBos());
                    if (dc.getSpelerLijst().get(teller).getAantalBos() > 0) {
                        dc.getSpelerLijst().get(teller).setPlaatsOpBos(true);
                    }
                    dc.getPlaatsenLijst().get(1).setAantalSpots(dc.getPlaatsenLijst().get(1).getAantalSpots() - dc.getSpelerLijst().get(teller).getAantalLeemgroeve());
                    if (dc.getSpelerLijst().get(teller).getAantalLeemgroeve()> 0) {
                        dc.getSpelerLijst().get(teller).setPlaatsOpLeemgroeve(true);
                    }
                    dc.getPlaatsenLijst().get(2).setAantalSpots(dc.getPlaatsenLijst().get(2).getAantalSpots() - dc.getSpelerLijst().get(teller).getAantalSteengroeve());
                    if (dc.getSpelerLijst().get(teller).getAantalSteengroeve()> 0) {
                        dc.getSpelerLijst().get(teller).setPlaatsOpSteengroeve(true);
                    }
                    dc.getPlaatsenLijst().get(3).setAantalSpots(dc.getPlaatsenLijst().get(3).getAantalSpots() - dc.getSpelerLijst().get(teller).getAantalGoudmijn());
                    if (dc.getSpelerLijst().get(teller).getAantalGoudmijn() > 0) {
                        dc.getSpelerLijst().get(teller).setPlaatsOpGoudmijn(true);
                    }
                    dc.getPlaatsenLijst().get(4).setAantalSpots(dc.getPlaatsenLijst().get(4).getAantalSpots() - dc.getSpelerLijst().get(teller).getAantalJachtgebied());
                    if (dc.getSpelerLijst().get(teller).getAantalJachtgebied()> 0) {
                        dc.getSpelerLijst().get(teller).setPlaatsOpJachtgebied(true);
                    }
                    dc.getPlaatsenLijst().get(5).setAantalSpots(dc.getPlaatsenLijst().get(5).getAantalSpots() - dc.getSpelerLijst().get(teller).getAantalAkkerbouw());
                    if (dc.getSpelerLijst().get(teller).getAantalAkkerbouw() > 0) {
                        dc.getSpelerLijst().get(teller).setPlaatsOpAkkerbouw(true);
                    }
                    dc.getPlaatsenLijst().get(6).setAantalSpots(dc.getPlaatsenLijst().get(6).getAantalSpots() - dc.getSpelerLijst().get(teller).getAantalSmith());
                    if (dc.getSpelerLijst().get(teller).getAantalSmith()> 0) {
                        dc.getSpelerLijst().get(teller).setPlaatsOpSmith(true);
                    }
                    dc.getPlaatsenLijst().get(7).setAantalSpots(dc.getPlaatsenLijst().get(7).getAantalSpots() - dc.getSpelerLijst().get(teller).getAantalHut());
                    if (dc.getSpelerLijst().get(teller).getAantalHut()> 0) {
                        dc.getSpelerLijst().get(teller).setPlaatsOpHut(true);
                    }
                    
                }
            } catch (SQLException ex) {
        for (Throwable t : ex) {
            t.printStackTrace();
            }
        }
    }
}
