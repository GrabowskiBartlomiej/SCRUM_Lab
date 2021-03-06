package pl.coderslab.model;

public class Plan implements Comparable<Plan> {
    private int id;
    private String name;
    private String description;
    private String created;
    private int adminId;

    public String toString() {
        return "Plan [id=" + id + ", name=" + name + ", description=" + description + ", created=" + created + ", adminId=" + adminId + "]";
    }

    public Plan() {

    }

    public Plan(int id) {
        this.id = id;
    }

    public Plan(String name, String description, String created, int adminId) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.adminId = adminId;
    }

    public Integer getId() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public int compareTo(Plan p) {
        return this.getId().compareTo(p.getId());
    }
}
