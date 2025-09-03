package com.example;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recommender {

    public static void main(String[] args) throws Exception {
        // 1. Criar um modelo de dados a partir do arquivo ratings.csv
        DataModel model = new FileDataModel(new File("ratings.csv"));

        // 2. Definir a métrica de similaridade entre usuários
        // A correlação de Pearson varia de -1 a 1.
        // Quanto mais próximo de 1, mais similares são os usuários.
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        // 3. Definir a vizinhança de usuários (quem são os "vizinhos" de um usuário)
        // Threshold de 0.1 significa que consideramos usuários com similaridade acima de 10%.
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

        // 4. Criar o recomendador
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        // 5. Obter recomendações para um usuário específico
        long userId = 1;
        List<RecommendedItem> recommendations = recommender.recommend(userId, 3);

        // 6. Mapear os IDs dos itens para seus nomes a partir de item_names.csv
        Map<Long, String> itemNames = loadItemNames("item_names.csv");

        // 7. Imprimir as recomendações de forma mais clara
        System.out.println("Recomendações para o usuário " + userId + ":");
        for (RecommendedItem recommendation : recommendations) {
            String itemName = itemNames.getOrDefault(recommendation.getItemID(), "Nome do Item Desconhecido");
            System.out.println(
                    "  - Item: " + itemName +
                    " (ID: " + recommendation.getItemID() + "), " +
                    "Valor de preferência: " + String.format("%.2f", recommendation.getValue())
            );
        }
    }

    /**
     * Lê um arquivo CSV de mapeamento de ID do item para nome e o retorna como um Mapa.
     * O arquivo deve ter o formato: itemId,itemName
     */
    private static Map<Long, String> loadItemNames(String filePath) throws Exception {
        Map<Long, String> itemNames = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    long itemId = Long.parseLong(parts[0].trim());
                    String itemName = parts[1].trim();
                    itemNames.put(itemId, itemName);
                }
            }
        }
        return itemNames;
    }
}