package com.connectfirebase;

class Member {
    private String Name;
    private String Cedula;
    private String EncuentaUno;
    private String EncuentaDos;

    public Member() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getEncuentaUno() {
        return EncuentaUno;
    }

    public void setEncuentaUno(String encuentaUno) {
        EncuentaUno = encuentaUno;
    }

    public String getEncuentaDos() {
        return EncuentaDos;
    }

    public void setEncuentaDos(String encuentaDos) {
        EncuentaDos = encuentaDos;
    }
}
