package self.ed.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PojoTask {
    private Long id;
    private Long createdBy;
    private Date createdDate;
    private Long updatedBy;
    private Date updatedDate;
    private long version;
    private String name;
    private String description;
    private boolean draft;
    private List<PojoSubTask> subTasks = new ArrayList<>();

    public PojoTask() {
        // for deserialization
    }

    public PojoTask(
            Long id,
            Long createdBy,
            Date createdDate,
            Long updatedBy,
            Date updatedDate,
            long version,
            String name,
            String description,
            boolean draft,
            List<PojoSubTask> subTasks
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
        this.subTasks = subTasks;
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

    public List<PojoSubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<PojoSubTask> subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PojoTask pojoTask = (PojoTask) o;
        return version == pojoTask.version &&
                draft == pojoTask.draft &&
                Objects.equals(id, pojoTask.id) &&
                Objects.equals(createdBy, pojoTask.createdBy) &&
                Objects.equals(createdDate, pojoTask.createdDate) &&
                Objects.equals(updatedBy, pojoTask.updatedBy) &&
                Objects.equals(updatedDate, pojoTask.updatedDate) &&
                Objects.equals(name, pojoTask.name) &&
                Objects.equals(description, pojoTask.description) &&
                Objects.equals(subTasks, pojoTask.subTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdBy, createdDate, updatedBy, updatedDate, version, name, description, draft, subTasks);
    }

    @Override
    public String toString() {
        return "PojoTask{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", draft=" + draft +
                ", subTasks=" + subTasks +
                '}';
    }
}