package self.ed.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;
import self.ed.pojo.PojoStatus;
import self.ed.pojo.PojoSubTask;
import self.ed.pojo.PojoTask;
import self.ed.proto.ProtoStatus;
import self.ed.proto.ProtoSubTask;
import self.ed.proto.ProtoTask;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(
        // to resolve "java.lang.UnsupportedOperationException at java.util.Collections$UnmodifiableCollection.addAll"
        collectionMappingStrategy = ADDER_PREFERRED,
        // to resolve "warning: Unmapped target properties:"
        unmappedTargetPolicy = IGNORE,
        uses = {
                TaskMapper.PojoToBuilderMapper.class,
                TaskMapper.BuilderToProtoMapper.class,
                TaskMapper.BuilderFactory.class,
                CommonMapper.class,
        })
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "subTasks", target = "subTasksList")
    ProtoTask map(PojoTask task);

    @Mapping(source = "subTasksList", target = "subTasks")
    PojoTask map(ProtoTask task);

    ProtoSubTask map(PojoSubTask subTask);

    PojoSubTask map(ProtoSubTask subTask);

    // to resolve "The following constants from the property "..." enum have no corresponding constant in the "..." enum and must be be mapped via adding additional mappings: UNRECOGNIZED."
    @ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.NULL)
    PojoStatus map(ProtoStatus status);

    interface PojoToBuilderMapper {
        ProtoTask.Builder map(PojoTask task);

        ProtoSubTask.Builder map(PojoSubTask task);
    }

    class BuilderToProtoMapper {
        ProtoTask map(ProtoTask.Builder builder) {
            return builder.build();
        }

        ProtoSubTask map(ProtoSubTask.Builder builder) {
            return builder.build();
        }
    }

    class BuilderFactory {
        ProtoTask.Builder task() {
            return ProtoTask.newBuilder();
        }

        ProtoSubTask.Builder subTask() {
            return ProtoSubTask.newBuilder();
        }
    }
}
