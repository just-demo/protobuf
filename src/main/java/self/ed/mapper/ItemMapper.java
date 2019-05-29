package self.ed.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;
import self.ed.pojo.PojoStatus;
import self.ed.pojo.PojoSubItem;
import self.ed.pojo.PojoItem;
import self.ed.proto.ProtoStatus;
import self.ed.proto.ProtoSubItem;
import self.ed.proto.ProtoItem;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(
        // to resolve "java.lang.UnsupportedOperationException at java.util.Collections$UnmodifiableCollection.addAll"
        collectionMappingStrategy = ADDER_PREFERRED,
        // to resolve "warning: Unmapped target properties:"
        unmappedTargetPolicy = IGNORE,
        uses = {
                ItemMapper.PojoToBuilderMapper.class,
                ItemMapper.BuilderToProtoMapper.class,
                ItemMapper.BuilderFactory.class,
                CommonMapper.class,
        })
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mapping(source = "subItems", target = "subItemsList")
    ProtoItem map(PojoItem item);

    @Mapping(source = "subItemsList", target = "subItems")
    PojoItem map(ProtoItem item);

    ProtoSubItem map(PojoSubItem subItem);

    PojoSubItem map(ProtoSubItem subItem);

    // to resolve "The following constants from the property "..." enum have no corresponding constant in the "..." enum and must be be mapped via adding additional mappings: UNRECOGNIZED."
    @ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.NULL)
    PojoStatus map(ProtoStatus status);

    interface PojoToBuilderMapper {
        ProtoItem.Builder map(PojoItem item);

        ProtoSubItem.Builder map(PojoSubItem item);
    }

    class BuilderToProtoMapper {
        ProtoItem map(ProtoItem.Builder builder) {
            return builder.build();
        }

        ProtoSubItem map(ProtoSubItem.Builder builder) {
            return builder.build();
        }
    }

    class BuilderFactory {
        ProtoItem.Builder item() {
            return ProtoItem.newBuilder();
        }

        ProtoSubItem.Builder subItem() {
            return ProtoSubItem.newBuilder();
        }
    }
}
