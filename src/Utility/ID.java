package Utility;

import java.io.Serializable;
import java.util.UUID;

//representing ID.ID of accounts
public class ID implements Serializable {

    private final UUID id;

    public ID(){
        this.id = generateNewID();
    }

    //generates a new ID.ID
    public UUID generateNewID(){
        return UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    //compare if two IDs are equal

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof ID){
            return getId().equals(((ID) obj).getId());
        }
        return false;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
