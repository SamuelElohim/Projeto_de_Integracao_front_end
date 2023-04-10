package model;


import static service.HttpRestClient.getDatabaseJSON;

public class LineDto extends AbstractDtoObject {

    public LineDto() {}

    public LineDto(String name, int id) {
        super(name, id);
    }
}
