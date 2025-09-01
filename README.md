# Sistema de Recomendação com Apache Mahout

Um sistema de recomendação colaborativo implementado em Java, que sugere itens para usuários baseado em avaliações e similaridades entre usuários.

## 📋 Descrição

Este projeto implementa um sistema de recomendação baseado em usuários (**User-Based Collaborative Filtering**) utilizando o **Apache Mahout 0.9**. O sistema analisa as avaliações de diferentes usuários para encontrar padrões de similaridade e fazer recomendações personalizadas.

## 🚀 Funcionalidades

- **Filtragem Colaborativa**: Utiliza avaliações de usuários similares para gerar recomendações.
- **Similaridade de Pearson**: Calcula correlação entre usuários para encontrar vizinhanças.
- **Vizinhança por Threshold**: Define usuários similares com base em um limite de similaridade (neste caso, 0.1).
- **Recomendações Personalizadas**: Gera sugestões específicas para cada usuário com um valor de preferência previsto.

## 🛠️ Tecnologias Utilizadas

- **Java**: Linguagem principal de desenvolvimento.
- **Apache Mahout 0.9**: Framework de machine learning para sistemas de recomendação.
- **Maven**: Gerenciamento de dependências e build.

## 📦 Pré-requisitos

- **Java Development Kit (JDK)** 8 ou superior.
- **Apache Maven** 3.6 ou superior.

## 🔧 Instalação e Execução

1.  **Clone** o repositório (se estiver em um sistema de controle de versão) e navegue até a pasta do projeto.
    ```bash
    git clone <url-do-repositorio>
    cd mahout_recommender
    ```

2.  **Crie a estrutura de diretórios** do projeto.
    ```bash
    mkdir -p src/main/java/com/example
    ```

3.  **Adicione as dependências do Mahout** no arquivo `pom.xml`.
    *O arquivo `mahout-core-0.9.jar` contém as implementações de MapReduce e os algoritmos necessários para a filtragem colaborativa.*
    ```xml
    <project>
        <dependencies>
            <dependency>
                <groupId>org.apache.mahout</groupId>
                <artifactId>mahout-core</artifactId>
                <version>0.9</version>
            </dependency>
            <dependency>
                <groupId>org.apache.mahout</groupId>
                <artifactId>mahout-math</artifactId>
                <version>0.9</version>
            </dependency>
        </dependencies>
    </project>
    ```

4.  **Coloque o código-fonte** da classe `Recommender.java` dentro da pasta `src/main/java/com/example`.

5.  **Crie o arquivo de dados** `ratings.csv` no diretório raiz do projeto com o formato `userId,itemId,rating`.

6.  **Compile e execute** o projeto usando o Maven.
    ```bash
    # Limpa e instala as dependências do projeto
    mvn clean install
    
    # Executa a classe principal do projeto
    mvn exec:java -Dexec.mainClass="com.example.Recommender"
    ```
    A saída irá mostrar as recomendações geradas.