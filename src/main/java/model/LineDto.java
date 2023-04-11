package model;


import java.util.Objects;

import static service.HttpRestClient.getDatabaseJSON;

public class LineDto extends AbstractDtoObject {

    public LineDto() {}

    public LineDto(int id, String name) {
        super(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LineDto)) {
            return false;
        }

        LineDto otherLine = (LineDto) obj;
        return super.equals(obj);
    }
}

