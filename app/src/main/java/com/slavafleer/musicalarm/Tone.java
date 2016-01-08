package com.slavafleer.musicalarm;

// Data Class
public class Tone {

    private int id;
    private String name;

    public Tone() {
    }

    public Tone(int id, String name) {

        setId(id);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id >= 0) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        return getName();
    }
}
