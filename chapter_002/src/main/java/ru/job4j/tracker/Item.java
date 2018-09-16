package ru.job4j.tracker;

public class Item {
    private String id;
    private String name;
    private String desc;
    private long create;

    public Item() {

    }

    public Item(String name, String desc, long create) {
        this.name = name;
        this.desc = desc;
        this.create = create;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCreated() {
        return this.create;
    }

    public void setCreated(long created) {
        this.create = created;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
