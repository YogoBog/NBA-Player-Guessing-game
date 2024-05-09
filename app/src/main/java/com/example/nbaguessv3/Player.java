package com.example.nbaguessv3;

public class Player {
    private String playerName;
    private String team;
    private String position;
    private String num;
    private int age;
    private String height;
    private String div;
    private String conf;

    public Player(String playerName, String team, String position, String num, int age, String height, String div, String conf) {
        this.playerName = playerName;
        this.team = team;
        this.position = position;
        this.num = num;
        this.age = age;
        this.height = height;
        this.div = div;
        this.conf = conf;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getTeam() {
        return team;
    }

    public String getPosition() {
        return position;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTeam(String team) {
        this.team = team;
    }

}

