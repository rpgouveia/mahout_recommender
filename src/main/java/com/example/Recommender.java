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

import java.io.File;
import java.util.List;

public class Recommender {

    public static void main(String[] args) throws Exception {
        // 1. Criar um modelo de dados a partir de um arquivo
        // O arquivo deve ter o formato: userId,itemId,rating
        // Ex: 101,201,5.0
        DataModel model = new FileDataModel(new File("ratings.csv"));

        // 2. Definir a métrica de similaridade entre usuários
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        // 3. Definir a vizinhança de usuários (quem são os "vizinhos" de um usuário)
        // Aqui, usamos um limite de similaridade de 0.1
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

        // 4. Criar o recomendador
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        // 5. Obter recomendações para um usuário específico
        // Vamos recomendar 3 itens para o usuário com ID 1
        long userId = 1;
        List<RecommendedItem> recommendations = recommender.recommend(userId, 3);

        // 6. Imprimir as recomendações
        System.out.println("Recomendações para o usuário " + userId + ":");
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("  - Item ID: " + recommendation.getItemID() + ", Valor de preferência: " + recommendation.getValue());
        }
    }
}