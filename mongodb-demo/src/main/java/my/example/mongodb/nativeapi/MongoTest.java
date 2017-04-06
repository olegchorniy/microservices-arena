package my.example.mongodb.nativeapi;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Date;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.*;

public class MongoTest {

    private static final Block<Document> PRINT = System.out::println;

    public static void main(String[] args) {
        withReplicaSetClient(client -> {

            while (true) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    System.out.println("Current server address list: " + new Date());
                    client.getServerAddressList().forEach(System.out::println);

                    System.out.println("Current collection state:");
                    readAll(client);

                    System.out.println();
                } catch (Exception e) {
                    System.out.println("Operation failed");
                    e.printStackTrace(System.out);
                    System.out.println();
                }
            }
        });
    }

    private static void withReplicaSetClient(Consumer<MongoClient> clientConsumer) {
        MongoClientURI clientURI = new MongoClientURI("mongodb://" +
                "127.0.0.1:27001," +
                "127.0.0.1:27002/?" +
                "replicaSet=rs0"
        );

        try (MongoClient client = new MongoClient(clientURI)) {
            clientConsumer.accept(client);
        }
    }

    private static void withStandaloneClient(Consumer<MongoClient> clientConsumer) {
        try (MongoClient client = new MongoClient("localhost", 27123)) {
            clientConsumer.accept(client);
        }
    }

    private static void readAll(MongoClient client) {
        MongoCollection<Document> test = testCollection(client)
                .withReadPreference(ReadPreference.secondaryPreferred());

        test.find().forEach(PRINT);
    }

    private static void readWithFilterAndProjection(MongoClient client) {
        MongoCollection<Document> test = testCollection(client);

        Bson filter = lt("age", 18);
        Bson projection = fields(include("name"), excludeId());

        Block<Document> documentConsumer = System.out::println;

        test.find(filter).projection(projection).forEach(documentConsumer);
    }

    private static MongoCollection<Document> testCollection(MongoClient client) {
        return client.getDatabase("test_db").getCollection("test_coll");
    }
}
