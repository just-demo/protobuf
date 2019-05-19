package self.ed.proto.service;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import org.junit.Test;

public class SearchServiceTest {
    @Test
    public void testSearch() {
        SearchService service = new SearchService() {
            @Override
            public void search(RpcController controller, SearchRequest request, RpcCallback<SearchResponse> done) {
                // TODO: try to implement
            }
        };
    }
}
