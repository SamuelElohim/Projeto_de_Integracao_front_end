package model;


import static service.HttpRestClient.getDatabaseJSON;

public class LineDto extends AbstractDtoObject {

    public LineDto() {}

    public LineDto(int id, String name) {
        super(id, name);
    }
}
