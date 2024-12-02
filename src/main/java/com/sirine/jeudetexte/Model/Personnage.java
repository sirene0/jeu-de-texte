package com.sirine.jeudetexte.Model;

import java.io.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public abstract class Personnage implements Attaquable {
    protected String nom;
    protected int pointdevie;
    protected int pointsDeVieMax;
    protected int coeur;
    protected int niveau;
    protected int degat;
    protected int experience;
    private final String saveFilePath = "savefile.txt";
    private Timer timer;

    public Personnage(String nom, int pointsDeVieMax,int pointdevie,int degat) {
        this.nom = nom;
        this.pointsDeVieMax = pointsDeVieMax;
        this.pointdevie = pointsDeVieMax;
        this.coeur = 5;
        this.degat = degat;
        this.niveau = 1;
        this.experience = 0;
        this.timer = new Timer();
        restaurerCoeurs();
        lancerMinuteur();
    }

    public String getnom() {
        return nom;
    }

    public void recevoirdegat(int degat) {
        pointdevie -= degat;
        System.out.println(nom + " a reçu " + degat + " points de dégâts. Points de vie restants : " + pointdevie);
        if (pointdevie <= 0) {
            perdreCoeur();
        }
    }

    public boolean estvivant() {
        return pointdevie > 0 || coeur > 0;
    }

    private void perdreCoeur() {
        coeur--;
        if (coeur > 0) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println(nom + " a perdu un cœur. Cœurs restants : " + coeur);
                System.out.println("Voulez-vous continuer à jouer ? (1 : Oui, 2 : Non)");

                int choix = scanner.nextInt();
                if (choix == 1) {
                    pointdevie = 50; // Réinitialise les points de vie
                    System.out.println(nom + " continue avec " + pointdevie + " points de vie.");
                } else {
                    sauvegarderEtat();
                    System.out.println(nom + " a choisi d'arrêter. Partie terminée !");
                    System.exit(0);
                }
            }
        
        } else {
            sauvegarderEtat();
            System.out.println(nom + " a perdu tous ses cœurs et est définitivement éliminé !");
            System.exit(0); // Termine la partie
        }
    }

    public void gagnerExperience(int xp) {
        experience += xp;
        if (experience >= 100) {
            experience = 0;
            niveau++;
            degat += degat * 0.5;
            System.out.println(nom + " a atteint le niveau " + niveau);
        }
    }

    private void lancerMinuteur() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (coeur < 5) {
                    coeur++;
                    System.out.println(nom + " a récupéré un cœur grâce à la régénération. Cœurs actuels : " + coeur);
                }
            }
        }, 15 * 60 * 1000, 15 * 60 * 1000); // Régénère un cœur toutes les 15 minutes
    }

    private void sauvegarderEtat() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(saveFilePath))) {
            writer.println(LocalDateTime.now()); // Enregistre l'heure de sortie
            writer.println(coeur); // Sauvegarde le nombre de cœurs
            System.out.println("État sauvegardé.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde de l'état : " + e.getMessage());
        }
    }

    private void restaurerCoeurs() {
        File saveFile = new File(saveFilePath);
        if (saveFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
                String lastLogoutTimeStr = reader.readLine();
                int savedCoeurs = Integer.parseInt(reader.readLine());
                LocalDateTime lastLogoutTime = LocalDateTime.parse(lastLogoutTimeStr);
                LocalDateTime now = LocalDateTime.now();

                long minutesElapsed = ChronoUnit.MINUTES.between(lastLogoutTime, now);
                int coeurToRegenerate = (int) (minutesElapsed / 15); // Un cœur toutes les 15 minutes

                coeur = Math.min(5, savedCoeurs + coeurToRegenerate); // Ne dépasse pas 5 cœurs
                System.out.println(nom + " a restauré les cœurs. Cœurs actuels : " + coeur);
            } catch (IOException | NumberFormatException e) {
                System.out.println("Erreur lors de la restauration des cœurs : " + e.getMessage());
            }
        }
    }


    public int getPointdevie() {
        return pointdevie;
    }
    
    public int getCoeur() {
        return coeur;
    }
}