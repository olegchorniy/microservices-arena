package my.example.oauth2;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oauth2DemoApplication {

    public static void main(String[] args) throws ClientException, ApiException {
        //SpringApplication.run(Oauth2DemoApplication.class, args);

        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Integer asd = vk.messages()
                .send(new UserActor(1, "asd"))
                .execute();

    }
}
