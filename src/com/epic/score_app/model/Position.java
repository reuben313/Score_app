package com.epic.score_app.model;



import java.util.Collection;

public class Position {
    /**
     * @attribute
     */
    public String Description;

    /**
     * @attribute
     */
    public String abbrivation;

    /**
     * @attribute
     */
    public String name;

    /**
     * @associates <{umlmodel.Player}>
     */
    Collection position;
}
