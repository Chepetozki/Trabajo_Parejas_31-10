public class Mascota {

    private int idMascota; //id de la base de datos
    private String nombreMascota;
    private String dueñoMascota;
    private String domicilioMascota;
    private String especiesMascota;

    public Mascota(int idMascota, String nombreMascota, String dueñoMascota, String domicilioMascota, String especiesMascota) {
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.dueñoMascota = dueñoMascota;
        this.domicilioMascota = domicilioMascota;
        this.especiesMascota = especiesMascota;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getDueñoMascota() {
        return dueñoMascota;
    }

    public void setDueñoMascota(String dueñoMascota) {
        this.dueñoMascota = dueñoMascota;
    }

    public String getDomicilioMascota() {
        return domicilioMascota;
    }

    public void setDomicilioMascota(String domicilioMascota) {
        this.domicilioMascota = domicilioMascota;
    }

    public String getEspeciesMascota() {
        return especiesMascota;
    }

    public void setEspeciesMascota(String especiesMascota) {
        this.especiesMascota = especiesMascota;
    }

    
}
