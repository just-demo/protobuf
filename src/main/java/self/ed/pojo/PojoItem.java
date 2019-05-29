package self.ed.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PojoItem {
    private Long id;
    private Long createdBy;
    private Date createdDate;
    private Long updatedBy;
    private Date updatedDate;
    private long version;
    private String name;
    private String description;
    private boolean draft;
    private List<PojoSubItem> subItems = new ArrayList<>();

    public PojoItem() {
        // for deserialization
    }

    public PojoItem(
            Long id,
            Long createdBy,
            Date createdDate,
            Long updatedBy,
            Date updatedDate,
            long version,
            String name,
            String description,
            boolean draft,
            List<PojoSubItem> subItems
    ) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.version = version;
        this.name = name;
        this.description = description;
        this.draft = draft;
        this.subItems = subItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
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

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public List<PojoSubItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<PojoSubItem> subItems) {
        this.subItems = subItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PojoItem pojoItem = (PojoItem) o;
        return version == pojoItem.version &&
                draft == pojoItem.draft &&
                Objects.equals(id, pojoItem.id) &&
                Objects.equals(createdBy, pojoItem.createdBy) &&
                Objects.equals(createdDate, pojoItem.createdDate) &&
                Objects.equals(updatedBy, pojoItem.updatedBy) &&
                Objects.equals(updatedDate, pojoItem.updatedDate) &&
                Objects.equals(name, pojoItem.name) &&
                Objects.equals(description, pojoItem.description) &&
                Objects.equals(subItems, pojoItem.subItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdBy, createdDate, updatedBy, updatedDate, version, name, description, draft, subItems);
    }

    @Override
    public String toString() {
        return "PojoItem{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", draft=" + draft +
                ", subItems=" + subItems +
                '}';
    }
}