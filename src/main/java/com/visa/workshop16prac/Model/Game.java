package com.visa.workshop16prac.Model;

import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable{
    
    private String name;
    private Pieces pieces;
    private int updateCount;
    private int insertCount;
    private boolean upsert;
    private String id;


    public Game(){  generateId();   }   // constructor to provide id

    public String getId() { return id;  }
    public void setId(String id) {  this.id = id;   }

    public synchronized void generateId(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < 6){
            sb.append( Integer.toHexString(random.nextInt()) );
        }
        id = sb.substring(0,6) + "game";
    }

    public int getUpdateCount() {
        return updateCount;
    }
    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }
    public int getInsertCount() {
        return insertCount;
    }
    public void setInsertCount(int insertCount) {
        this.insertCount = insertCount;
    }
    public boolean isUpsert() {
        return upsert;
    }
    public void setUpsert(boolean upsert) {
        this.upsert = upsert;
    }
    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public Pieces getPieces() { return pieces; }
    public void setPieces(Pieces value) { this.pieces = value; }
}
