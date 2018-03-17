package main;

import domein.DomeinController;
import gui.SpelApplicatie;

public class StartUp 
{
    public static void main (String[] args) throws InterruptedException
    {
        DomeinController dc = new DomeinController();   // maakt dc aan , dc maakt sr aan, sr maakt sm aan, sm maakt spelers aan
        SpelApplicatie spel = new SpelApplicatie(dc);   //hier komt de methode da alles start
    }
}