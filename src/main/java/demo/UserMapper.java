package demo;

import com.google.protobuf.Int32Value;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;
import self.ed.pojo.PojoStatus;
import self.ed.proto.ProtoStatus;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(collectionMappingStrategy = ADDER_PREFERRED, unmappedTargetPolicy = IGNORE,
        uses = {AnotherMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "items", source = "itemsList")
    demo.pojo.User map(demo.proto.User user);

    @Mapping(target = "itemsList", source = "items")
    demo.proto.User map(demo.pojo.User user);

    default Integer map(Int32Value value) { return value.getValue(); }
    default Int32Value map(Integer value) { return Int32Value.of(value); }

    @ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.NULL)
    demo.pojo.SomeEnum map(demo.proto.SomeEnum someEnum);

    demo.proto.User.Builder pojoToBuilder(demo.pojo.User user);
    default demo.proto.User builderToProto(demo.proto.User.Builder builder) { return builder.build(); }
    default demo.proto.User.Builder builderFactory() { return demo.proto.User.newBuilder(); }
}
