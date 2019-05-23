package self.ed;

import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import self.ed.pojo.PojoStatus;
import self.ed.pojo.PojoSubTask;
import self.ed.pojo.PojoTask;
import self.ed.proto.ProtoStatus;
import self.ed.proto.ProtoSubTask;
import self.ed.proto.ProtoTask;

import java.time.Instant;
import java.util.Date;

import static java.util.Arrays.asList;
import static self.ed.util.RandomUtils.random;

public class Generator {
    public static class Task {
        long id = 123L;
        long createdBy = 456L;
        long createdDate = 1526124042000L;
        long updatedBy = 789L;
        long updatedDate = 1528892503000L;
        long version = 135;
        String name = "task name";
        String description = "task description";
        boolean draft = true;

        long id1 = 234L;
        long createdBy1 = 567L;
        long createdDate1 = 1531574564000L;
        long updatedBy1 = 890L;
        long updatedDate1 = 1534343025000L;
        long version1 = 246;
        String name1 = "sub-task name 1";
        String description1 = "sub-task description 1";
        int estimate1 = 10;
        PojoStatus pojoStatus1 = PojoStatus.PENDING;
        ProtoStatus protoStatus1 = ProtoStatus.PENDING;
        String result1 = "sub-task result 1";

        long id2 = 345L;
        long createdBy2 = 678L;
        long createdDate2 = 1537111486000L;
        long updatedBy2 = 901L;
        long updatedDate2 = 1539793547000L;
        long version2 = 357;
        String name2 = "sub-task name 2";
        String description2 = "sub-task description 2";
        int estimate2 = 20;
        PojoStatus pojoStatus2 = PojoStatus.STARTED;
        ProtoStatus protoStatus2 = ProtoStatus.STARTED;
        String result2 = "sub-task result 2";
    }

    public static Task generateTask() {
//        return new Task();
        return random(Task.class);
    }

    public static PojoTask toPojoTask(Task task) {
        return new PojoTask(
                task.id,
                task.createdBy,
                new Date(task.createdDate),
                task.updatedBy,
                new Date(task.updatedDate),
                task.version,
                task.name,
                task.description,
                task.draft,
                asList(
                        new PojoSubTask(
                                task.id1,
                                task.createdBy1,
                                new Date(task.createdDate1),
                                task.updatedBy1,
                                new Date(task.updatedDate1),
                                task.version1,
                                task.name1,
                                task.description1,
                                task.estimate1,
                                task.pojoStatus1,
                                task.result1
                        ),
                        new PojoSubTask(
                                task.id2,
                                task.createdBy2,
                                new Date(task.createdDate2),
                                task.updatedBy2,
                                new Date(task.updatedDate2),
                                task.version2,
                                task.name2,
                                task.description2,
                                task.estimate2,
                                task.pojoStatus2,
                                task.result2
                        )
                )
        );
    }

    public static ProtoTask toProtoTask(Task task) {
        return ProtoTask.newBuilder()
                .setId(task.id)
                .setCreatedBy(task.createdBy)
                .setCreatedDate(toTimestamp(task.createdDate))
                .setUpdatedBy(task.updatedBy)
                .setUpdatedDate(toTimestamp(task.updatedDate))
                .setVersion(task.version)
                .setName(task.name)
                .setDescription(task.description)
                .setDraft(task.draft)
                .addSubTasks(ProtoSubTask.newBuilder()
                        .setId(task.id1)
                        .setCreatedBy(task.createdBy1)
                        .setCreatedDate(toTimestamp(task.createdDate1))
                        .setUpdatedBy(task.updatedBy1)
                        .setUpdatedDate(toTimestamp(task.updatedDate1))
                        .setVersion(task.version1)
                        .setName(task.name1)
                        .setDescription(task.description1)
                        .setEstimate(Int32Value.of(task.estimate1))
                        .setStatus(task.protoStatus1)
                        .setResult(task.result1))
                .addSubTasks(ProtoSubTask.newBuilder()
                        .setId(task.id2)
                        .setCreatedBy(task.createdBy2)
                        .setCreatedDate(toTimestamp(task.createdDate2))
                        .setUpdatedBy(task.updatedBy2)
                        .setUpdatedDate(toTimestamp(task.updatedDate2))
                        .setVersion(task.version2)
                        .setName(task.name2)
                        .setDescription(task.description2)
                        .setEstimate(Int32Value.of(task.estimate2))
                        .setStatus(task.protoStatus2)
                        .setResult(task.result2))
                .build();
    }

    private static Timestamp toTimestamp(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
