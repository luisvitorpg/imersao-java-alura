package aluraStickers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {
		
		// Fazer uma conexão HTTP e buscar os top 250 filmes
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		var endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		//System.out.println(body);
		
		// Extrair só os dados que interessam (título, poster, classificação)
		var parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		//System.out.println(listaDeFilmes);
		
		// Exibir e manipular os dados
		for (Map<String, String> filme : listaDeFilmes) {
			System.out.println("Titulo: " + filme.get("title"));
			System.out.println("Poster: " + filme.get("image"));
			System.out.println("Classificacao: " + filme.get("imDbRating"));
			System.out.println();
		}
		
	}
}
