package Opgave12_Sende_Et_Array;

public class Person {

    private int id;
    private String navn;
    private String by;

    public Person(int id, String navn, String by){
        this.id = id;
        this.navn = navn;
        this.by = by;
    }

    public String toString(){
        return this.id + " " + this.navn + " " + this.by;
    }

}
