package self.ed.mapper;

import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.Date;

@Mapper
public interface CommonMapper {
    default Integer map(Int32Value value) {
        return value.getValue();
    }

    default Int32Value map(Integer value) {
        return Int32Value.of(value);
    }

    default Date map(Timestamp timestamp) {
        return Date.from(Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos()));
    }

    default Timestamp map(Date date) {
        Instant instant = date.toInstant();
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
