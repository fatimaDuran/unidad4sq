package mx.com.dloza.crudcar.model;

/**
 * Created by Jairo on 09/04/2018.
 */

public class Cop {

    private long id;
    private String name;
    private String mark;
    private String model;
    private String image;
    private String procesador;
    private String memoria;
    private String year;

    public Cop() {
    }

    public Cop(String name, String mark, String model, String image, String procesador, String memoria, String year) {
        this.name = name;
        this.mark = mark;
        this.model = model;
        this.image = image;
        this.procesador = procesador;
        this.memoria = memoria;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

