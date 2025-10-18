package com.example.projectilm;

public class Book {
    int id;
    String title, author, imageName;
    int cost;

    public Book(int id, String title, String author, int cost, String imageName) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.cost = cost;
        this.imageName = imageName;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getCost() { return cost; }
    public String getImageName() { return imageName; }
}
