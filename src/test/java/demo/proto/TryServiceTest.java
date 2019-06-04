package demo.proto;

import com.google.protobuf.*;
import org.junit.Test;

public class TryServiceTest {
    @Test
    public void testCustom() {
        CustomService service = new CustomService() {
            @Override
            public void demo(RpcController controller, ServiceRequest request, RpcCallback<ServiceResponse> done) {
                // ...
            }
        };
    }

    @Test
    public void testWrapper() {
        WrapperService service = new WrapperService() {
            @Override
            public void demo(RpcController controller, StringValue request, RpcCallback<BoolValue> done) {
                // ...
            }
        };
    }

    @Test
    public void testEmpty() {
        EmptyService service = new EmptyService() {
            @Override
            public void demo(RpcController controller, Empty request, RpcCallback<Empty> done) {
                // ...
            }
        };
    }
}
