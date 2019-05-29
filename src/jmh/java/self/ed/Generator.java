package self.ed;

import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import self.ed.pojo.PojoStatus;
import self.ed.pojo.PojoSubItem;
import self.ed.pojo.PojoItem;
import self.ed.proto.ProtoStatus;
import self.ed.proto.ProtoSubItem;
import self.ed.proto.ProtoItem;

import java.time.Instant;
import java.util.Date;

import static java.util.Arrays.asList;

public class Generator {
    public static class Item {
        long id = 123L;
        long createdBy = 456L;
        long createdDate = 1526124042000L;
        long updatedBy = 789L;
        long updatedDate = 1528892503000L;
        long version = 135;
        String name = "item name";
        String description = "item description";
        boolean draft = true;

        long id1 = 234L;
        long createdBy1 = 567L;
        long createdDate1 = 1531574564000L;
        long updatedBy1 = 890L;
        long updatedDate1 = 1534343025000L;
        long version1 = 246;
        String name1 = "sub-item name 1";
        String description1 = "sub-item description 1";
        int estimate1 = 10;
        PojoStatus pojoStatus1 = PojoStatus.PENDING;
        ProtoStatus protoStatus1 = ProtoStatus.PENDING;
        String result1 = "sub-item result 1";

        long id2 = 345L;
        long createdBy2 = 678L;
        long createdDate2 = 1537111486000L;
        long updatedBy2 = 901L;
        long updatedDate2 = 1539793547000L;
        long version2 = 357;
        String name2 = "sub-item name 2";
        String description2 = "sub-item description 2";
        int estimate2 = 20;
        PojoStatus pojoStatus2 = PojoStatus.STARTED;
        ProtoStatus protoStatus2 = ProtoStatus.STARTED;
        String result2 = "sub-item result 2";
    }

    public static Item generateItem() {
//        return random(Item.class);
        return new Item();
    }

    public static PojoItem toPojoItem(Item item) {
        return new PojoItem(
                item.id,
                item.createdBy,
                new Date(item.createdDate),
                item.updatedBy,
                new Date(item.updatedDate),
                item.version,
                item.name,
                item.description,
                item.draft,
                asList(
                        new PojoSubItem(
                                item.id1,
                                item.createdBy1,
                                new Date(item.createdDate1),
                                item.updatedBy1,
                                new Date(item.updatedDate1),
                                item.version1,
                                item.name1,
                                item.description1,
                                item.estimate1,
                                item.pojoStatus1,
                                item.result1
                        ),
                        new PojoSubItem(
                                item.id2,
                                item.createdBy2,
                                new Date(item.createdDate2),
                                item.updatedBy2,
                                new Date(item.updatedDate2),
                                item.version2,
                                item.name2,
                                item.description2,
                                item.estimate2,
                                item.pojoStatus2,
                                item.result2
                        )
                )
        );
    }

    public static ProtoItem toProtoItem(Item item) {
        return ProtoItem.newBuilder()
                .setId(item.id)
                .setCreatedBy(item.createdBy)
                .setCreatedDate(toTimestamp(item.createdDate))
                .setUpdatedBy(item.updatedBy)
                .setUpdatedDate(toTimestamp(item.updatedDate))
                .setVersion(item.version)
                .setName(item.name)
                .setDescription(item.description)
                .setDraft(item.draft)
                .addSubItems(ProtoSubItem.newBuilder()
                        .setId(item.id1)
                        .setCreatedBy(item.createdBy1)
                        .setCreatedDate(toTimestamp(item.createdDate1))
                        .setUpdatedBy(item.updatedBy1)
                        .setUpdatedDate(toTimestamp(item.updatedDate1))
                        .setVersion(item.version1)
                        .setName(item.name1)
                        .setDescription(item.description1)
                        .setEstimate(Int32Value.of(item.estimate1))
                        .setStatus(item.protoStatus1)
                        .setResult(item.result1))
                .addSubItems(ProtoSubItem.newBuilder()
                        .setId(item.id2)
                        .setCreatedBy(item.createdBy2)
                        .setCreatedDate(toTimestamp(item.createdDate2))
                        .setUpdatedBy(item.updatedBy2)
                        .setUpdatedDate(toTimestamp(item.updatedDate2))
                        .setVersion(item.version2)
                        .setName(item.name2)
                        .setDescription(item.description2)
                        .setEstimate(Int32Value.of(item.estimate2))
                        .setStatus(item.protoStatus2)
                        .setResult(item.result2))
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
