package com.sirine.jeudetexte.Model;

public interface Attaquable {
    void attaquer(Personnage cible);

    void defance(Personnage cible);

    void utilisercompetence(Personnage cible);

    void soin();
}