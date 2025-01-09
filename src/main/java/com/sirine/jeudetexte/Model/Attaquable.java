package com.sirine.jeudetexte.Model;

import java.util.Scanner;

public interface Attaquable {
    void attaquer(Personnage cible,Scanner scanner);

    void defance(Personnage cible,Scanner scanner);

    void utilisercompetence(Personnage cible,Scanner scanner);

    void soin();
}