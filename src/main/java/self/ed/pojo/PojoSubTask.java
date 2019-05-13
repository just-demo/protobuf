package self.ed.pojo;

import java.util.Date;
import java.util.Objects;

public class PojoSubTask {
    private Long id;
    private Long createdBy;
    private Date createdDate;
    private Long updatedBy;
    private Date updatedDate;
    private long version;
    private String name;
    private String description;
    private Integer estimate;
    private PojoStatus status;
    private String result;

    public PojoSubTask() {
        // for deserialization
    }

    public PojoSubTask(
            Long id,
            Long createdBy,
            Date createdDate,
            Long updatedBy,
            Date updatedDate,
            long version,
            String name,
            String description,
            Integer estimate,
            PojoStatus status,
            String result
    ) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.version = version;
        this.name = name;
        this.description = description;
        this.estimate = estimate;
        this.status = status;
        this.result = result;
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

    public Integer getEstimate() {
        return estimate;
    }

    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }

    public PojoStatus getStatus() {
        return status;
    }

    public void setStatus(PojoStatus status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PojoSubTask that = (PojoSubTask) o;
        return version == that.version &&
                Objects.equals(id, that.id) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(updatedDate, that.updatedDate) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(estimate, that.estimate) &&
                status == that.status &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdBy, createdDate, updatedBy, updatedDate, version, name, description, estimate, status, result);
    }

    @Override
    public String toString() {
        return "PojoSubTask{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimate=" + estimate +
                ", status=" + status +
                ", result='" + result + '\'' +
                '}';
    }
}