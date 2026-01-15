package com.groep3.model;

public class Fruit {

    private String name;
    private String description;
    private String imagePath;
    protected double price;

    private String producer;

    // ✅ NIEUWE VELDEN (uit Vrucht / Producent)
    private String categorie;
    private String herkomst;
    private String soort;
    private long beschikbaarheid;
    private String boerderij;
    private String streek;
    private String boerBericht;

    public Fruit() {
    }

    // Constructor met afbeelding
    public Fruit(String name, String description, String imagePath, double price) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }

    // Constructor zonder afbeelding
    public Fruit(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.imagePath = null;
        this.price = price;
    }

    // ===== BESTAANDE GETTERS / SETTERS =====

    public String getProducer() { return producer; }
    public void setProducer(String producer) { this.producer = producer; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // ===== NIEUWE GETTERS / SETTERS =====

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public String getHerkomst() { return herkomst; }
    public void setHerkomst(String herkomst) { this.herkomst = herkomst; }

    public String getSoort() { return soort; }
    public void setSoort(String soort) { this.soort = soort; }

    public long getBeschikbaarheid() { return beschikbaarheid; }
    public void setBeschikbaarheid(long beschikbaarheid) { this.beschikbaarheid = beschikbaarheid; }

    public String getBoerderij() { return boerderij; }
    public void setBoerderij(String boerderij) { this.boerderij = boerderij; }

    public String getStreek() { return streek; }
    public void setStreek(String streek) { this.streek = streek; }

    public String getBoerBericht() { return boerBericht; }
    public void setBoerBericht(String boerBericht) { this.boerBericht = boerBericht; }

    // ===== BELANGRIJK VOOR HASHMAP IN SHOPPINGCART =====

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fruit)) return false;
        Fruit f = (Fruit) o;
        return name != null && name.equalsIgnoreCase(f.name);
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.toLowerCase().hashCode();
    }
}
