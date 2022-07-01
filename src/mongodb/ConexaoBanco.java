package mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ConexaoBanco {

    public MongoClient conectar() {

        ConnectionString connString = new ConnectionString(
                "mongodb://127.0.0.1:27017"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        System.out.println("Conexao aberta");
        System.out.println("--------------------------------------");

        return mongoClient;
    }

    public void fecharConecao(MongoClient con) {
        con.close();

        System.out.println("Conexao fechada");
        System.out.println("--------------------------------------");
    }

}
