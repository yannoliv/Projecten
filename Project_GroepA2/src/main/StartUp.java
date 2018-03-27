package main;

import cui.SpelApplicatie;
import domein.DomeinController;

public class StartUp 
{
    public static void main (String[] args) throws InterruptedException
    {
        new SpelApplicatie(new DomeinController());   // maakt dc aan , dc maakt sr aan, sr maakt sm aan, sm maakt spelers aan
    }
}