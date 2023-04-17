package model;

import java.util.Objects;

public abstract class AbstractDtoObject {
    protected int id;
    protected String name;
    public AbstractDtoObject() {}

    public AbstractDtoObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AbstractDtoObject)) {
            return false;
        }

        AbstractDtoObject otherObject = (AbstractDtoObject) obj;

        return Objects.equals(name, otherObject.name)
                && Objects.equals(id, otherObject.id);
    }

    @Override
    public String toString() {
        return name;
    }

}
