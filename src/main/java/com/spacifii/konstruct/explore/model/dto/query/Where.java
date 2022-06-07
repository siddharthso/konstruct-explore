package com.spacifii.konstruct.explore.model.dto.query;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Where implements Serializable

{
    private static final long serialVersionUID = 7050797027520355013L;

    private Clause clause;

    private List<String> value = new ArrayList<String>();

    public Clause getClause() {
        return clause;
    }

    public void setClause(Clause clause) {
        this.clause = clause;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

}
