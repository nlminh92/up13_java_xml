package com.thai.xml.xpath;

public class Marin {

    private long id;
    private String nom;
    private String prenom;
    private int age;

    public Marin() {
        this(0, null, null, 0);
    }

    public Marin(long id, String nom, String prenom , int age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Marin id = " + id + ": " + prenom + " " + nom + ", age: " + age;
    }

}