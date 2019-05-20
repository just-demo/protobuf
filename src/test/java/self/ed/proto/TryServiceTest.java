package self.ed.proto;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import org.junit.Test;

public class TryServiceTest {
    @Test
    public void testInterface() {
        DemoService service = new DemoService() {
            @Override
            public void demo(RpcController controller, ServiceRequest request, RpcCallback<ServiceResponse> done) {
                // ...
            }
        };
    }
}
